(ns clara.rules.update-cache.cancelling
  (:require [clara.rules.update-cache.core :as uc]
            [clara.rules.platform :refer [jeq-wrap]]
            [ham-fisted.api :as hf]
            [ham-fisted.mut-map :as hm])
  (:import [java.util
            List
            Map
            LinkedList
            LinkedHashMap]))

;;; These functions essentially allow us to use a Java map to create a set that stores
;;; the frequency of its items.  Note that when multiple instances of a fact are added
;;; we keep both instances as distinct objects.  We don't strictly speaking need to do this
;;; but we expect it to perform better.  The memory will retain both distinct references
;;; and future updates are expected to be faster if these references are maintained since
;;; memory operations look for matches on identity first in tokens before falling back to matching
;;; on equality.
(defn inc-fact-count! [^Map m fact]
  (let [wrapper (jeq-wrap fact)
        ^List result (or (.get m wrapper) (LinkedList.))]
    (hm/compute-if-absent! m wrapper (constantly result))
    (.add result fact)))

(defn dec-fact-count! [^Map m fact]
  (let [wrapper (jeq-wrap fact)
        ;; Note that we specifically use a LinkedList type hint here since we
        ;; use methods from multiple interfaces here, namely List and Deque.
        ^LinkedList current-val (.get m wrapper)]
    (if current-val
      (do
        (if (= (.size current-val) 1)
          (.remove m wrapper)
          ;;; Since as noted above, the facts are equal, we don't actually care which one we remove.
          ;;; We remove the first here to avoid any work checking equality and since this is a constant-time
          ;;; operation on LinkedList.  Since the insertions will be newly inserted facts we probably won't
          ;;; have many identical retractions, so doing a sweep for identical facts first probably wouldn't
          ;;; have enough hits to be worth the cost.
          (.removeFirst current-val))
        true)
      false)))

(defn map->vals-concated
  [^Map m]
  (hf/apply-concat (hm/values m)))

;;; This is a pending updates cache that allows
;;  retractions and insertions of equal facts
;;; to cancel each other out.
;;; More formally, for i insertions and r retractions
;;; of a fact f, it will:
;;; - If i = r, no operations will be performed.
;;; - If i > r, f will be returned for insertion i - r times.
;;; - If r > i, f will be returned for retraction r - i times.
(deftype CancellingUpdateCache [^Map ^:unsynchronized-mutable insertions
                                ^Map ^:unsynchronized-mutable retractions]

  uc/UpdateCache

  (add-insertions! [this facts]
    (let [fact-iter (.iterator ^Iterable facts)]
      (loop []
        (when (.hasNext fact-iter)
          (let [fact (.next fact-iter)]
            (when-not (dec-fact-count! retractions fact)
              (inc-fact-count! insertions fact))
            (recur))))))

  (add-retractions! [this facts]
    (let [fact-iter (.iterator ^Iterable facts)]
      (loop []
        (when (.hasNext fact-iter)
          (let [fact (.next fact-iter)]
            (when-not (dec-fact-count! insertions fact)
              (inc-fact-count! retractions fact))
            (recur))))))

  (get-updates-and-reset! [this]
    (let [retractions-update (when (-> retractions .size pos?)
                               (uc/->PendingUpdate :retract (map->vals-concated retractions)))
          insertions-update (when (-> insertions .size pos?)
                              (uc/->PendingUpdate :insert (map->vals-concated insertions)))]
      (set! insertions (LinkedHashMap.))
      (set! retractions (LinkedHashMap.))

      (cond

        (and insertions-update retractions-update)
        ;; This could be ordered to have insertions before retractions if we ever
        ;; found that that performs better on average.  Either ordering should
        ;; be functionally correct.
        [[retractions-update] [insertions-update]]

        insertions-update
        [[insertions-update]]

        retractions-update
        [[retractions-update]]))))

;; We use LinkedHashMap so that the ordering of the pending updates will be deterministic.
(defn get-cancelling-update-cache
  []
  (CancellingUpdateCache. (LinkedHashMap.) (LinkedHashMap.)))
