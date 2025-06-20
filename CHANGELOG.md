This is a history of changes to k13labs/clara-rules.

# 1.5.4
* add support for namespace props, so that salience can be set on rules in a namespace.

# 1.5.3
* save sorted-grouping-by accumulator result into array map

# 1.5.2
* implement sorting-by and sorted-grouping-by accumulators

# 1.5.1
* do not break inspection if no accumulated facts are found

# 1.5.0
* revert thread local use and replace with dynamic vars for safely using virtual threads.
* upgrade deps, update infinite loop tests with latest futurama library.

# 1.4.5
* upgrade to latest futurama, replace uses of instance-satisfies? with satisfies?

# 1.4.4
* ensure read-only sessions do not contain empty beta memory for nodes without any bindings results (empty queries).

# 1.4.3
* add support for read only rules sessions, ReadOnlyLocalSession, which can only be queried.
* make clara.rules.compiler/create-get-alphas-fn public, simplify usage.
* add output schema to clara.rules.compiled/build-network, validation.
* add functions rulebase->query-only-rulebase, as-read-only, and assemble-read-only in the rules-engine ns.
* add get-tokens-map to the IMemoryReader protocol to aid in fetching the memory state of entire nodes without bindings.
* rename the rules.platform macro `thread-local-binding` to `with-thread-local-binding`, consider replace with binding.
* add djblue/portal to dev profile.

# 1.4.2
* update k13labs/futurama to latest version

# 1.4.1
* ensure parse-rule, parse-query, defrule, defquery macros capture local env
* add env support to expression/accumulator join nodes
* add tests to ensure we can build and compile clara rules using graalvm

# 1.4.0
* (breaking change) renamed the protocol `IRuleSource/load-rules` to `IClaraSource/load-source`, which can load rules and hierarchies.
* (breaking change) rename `clear-ns-productions!` to `clear-ns-vars!` since now there are ns-installed vars that are not productions.
* update linter config for new macros.
* `defrule` now defines rules as functions with two arities, no args returns the rule map, and 2 args is the compiled RHS.
* `defrule` separator `=>` symbol is now optional when there is no LHS expressions
* `clojure.lang.Fn` now implements `clara.rules.compiler/IClaraSource`, and returns a single rule by invoking the like `(a-rule)`.
* add built-in support to serialize `clojure.lang.Var` so that a rule handler var can be serialized correctly.
* add function `clara.rules.compiler/load-source*` to simplify recursively loading using `IClaraSource/load-source`.
* add `defhierarchy` macro to define hierarchies of facts allowing to easily establish parent/child relationships.

# 1.3.5
* Update sum accumulator to support default-value, set to 0 if not specified, to avoid NPE during sum.

# 1.3.4
* Update CyclicalRulesListener to return the cycles-count value when converted to persistent.

# 1.3.3
* Upgrade to clojure 1.11.2 to fix `CVE-2024-22871`, despite not really affecting clara-rules.
* Add clj-kondo linter updates to fix bad docstring expression.

# 1.3.2
* Enhance memory add-activations implementation by replacing get/set with compute!

# 1.3.1
* Enhance caching performance by more predictable md5 caching and sorting productions

# 1.3.0
* Enhance compilation performance by using mutable maps from ham-fisted lib

# 1.2.0
* Enhance caching support by adding compile caching using core CacheProtocol

# 1.1.0
* Enhance caching support by adding session caching using core CacheProtocol

# 1.0.2
* Bump futurama version to 1.0.2 to get enhancements

# 1.0.1
* Bump futurama version to 1.0.1 to get enhancements

# 1.0.0
* Bump futurama version to first major version
* Initial major release of k13labs/clara-rules

# 0.9.9
* Bump futurama version to latest

# 0.9.8
* Update to latest async library (futurama) to get fixes for async-reduce

# 0.9.7
* Update to latest async library

# 0.9.6
* Async enhancements and add engine test with thousands of async rules fired for stress test

# 0.9.5
* Update docs and explicit async-future in fire-rules-async

# 0.9.4
* Update `futurama` to latest version

# 0.9.3
* Implement interruptible sessions using futurama's async-cancel capabilities.
* Add tests for infinite loop runaway sessions which can be interrupted.

# 0.9.2
* Update docs and futurama version bump again

# 0.9.1
* Update docs and futurama version bump

# 0.9.0
* Add parallel support to Node and RHS activation.
* Remove ClojureScript support, general cleanup.
* Refactor engine to support both fire-rules and fire-rules-async
* Replace mutable/transient collections with [ham-fisted](https://github.com/cnuernber/ham-fisted) data structures.

This is a history of changes to clara-rules prior to forking to k13labs/clara-rules.

### 0.24.0
* uplift to cljs 1.11.132
* uplift to clj 1.11.2
* remove atom usage in LHS functions
* remove redundant TestNode evaluations

### 0.23.0
* extract clara.rules.compiler/compile-test-handler from clara.rules.compiler/compile-test
* add support for `env` inside of test expressions
* use `.clj_kondo` extension for clj-kondo hook code for better tool compatibility (clj-kondo support now requires clj-kondo 2022.04.25 or higher)
* Include the invalid constraint in the exception thrown at session compilation time when negations have multiple children.  See [Issue 284](https://github.com/cerner/clara-rules/issues/284).

### 0.22.1
* fix incorrent lint warning triggered when this binding is not used in clj-kondo hooks

### 0.22.0
* add built-in clj-kondo support for clara-rules as hooks. Importing should be automatic if using clojure-lsp; for detailed instructions see clj-kondo's documentation on [how to import clj-kondo configuration](https://github.com/clj-kondo/clj-kondo/blob/master/doc/config.md#importing)
* use correct arity calling `->RuleOrderedActivation` constructor during serialization if clara session; this change should have the same effective behavior as before.

### 0.21.2
* Try and catch TestNode expression evaluation so that exceptions thrown are re-thrown wrapped in a condition exception which includes production name and bindings information. See [PR 471](https://github.com/cerner/clara-rules/pull/471).

### 0.21.1
* Add support to specify query binding arguments as symbols instead of only keywords so that defquery syntax looks closer to function definition syntax. See [PR 463](https://github.com/cerner/clara-rules/pull/463).

### 0.21.0
* Add names to anonymous functions generated by rule compilation; these names will be in the class names of the generated objects. [Issue 261](https://github.com/cerner/clara-rules/issues/261) and [issue 291](https://github.com/cerner/clara-rules/issues/291)
* Add types information to alpha nodes. [Issue 237](https://github.com/cerner/clara-rules/issues/237)
* Fix a bug related to Java object facts with IndexedPropertyDescriptor fields. [Issue 446](https://github.com/cerner/clara-rules/issues/446)
* Validate that parameters provided to queries exist on the query at compilation and throw an exception if queries on a session don't specify the required parameters. [Issue 454](https://github.com/cerner/clara-rules/issues/454)
* Add an optional listener that reports suspected infinite loops of rules.  [Issue 275](https://github.com/cerner/clara-rules/issues/275)

### 0.20.0
* Add a flag to omit compilation context (used by the durability layer) after Session compilation to save space when not needed. Defaults to true. [issue 422](https://github.com/cerner/clara-rules/issues/422)
* Correct duplicate bindings within the same condition. See [issue 417](https://github.com/cerner/clara-rules/issues/417)
* Correct sharing of nodes with different parents. See [issue 433](https://github.com/cerner/clara-rules/issues/433)

### 0.19.1
* Added a new field to the clara.rules.engine/Accumulator record.  This could be a breaking change for any user durability implementations with low-level performance optimizations.  See [PR 410](https://github.com/cerner/clara-rules/pull/410) for details.
* Performance improvements for :exists conditions.  See [issue 298](https://github.com/cerner/clara-rules/issues/298).
* Decrease memory usage post deserialization (Durability). See [Issue 419](https://github.com/cerner/clara-rules/issues/419)
* Added a new function that returns the number of times a rule was interacted with as a proxy for rules that may be the cause of performance problems.  This function requires information from the [tracing listener](http://www.clara-rules.org/docs/listeners/) to work.  See [issue 344](https://github.com/cerner/clara-rules/issues/344) for details.

### 0.19.0
* Remove a warning about `qualified-keyword?` being replaced when using Clojure 1.9.
* Batch evaluation of node expressions for better compilation performance. See [issue 381](https://github.com/cerner/clara-rules/issues/381).
* Remove unneeded use of get-in to improve performance. See [issue 402](https://github.com/cerner/clara-rules/issues/402).
* Fix issue in test conditions where there is a previous binding. See [issue 357](https://github.com/cerner/clara-rules/issues/357).
* Fix incorrect unification logic caused by differing equality semantics between Java and Clojure. See [issue 393](https://github.com/cerner/clara-rules/issues/393).

### 0.18.0
* Remove unnecessary memory operations from ProductionNode to optimize performance.  Remove :rule-matches in session inspection that did not cause logical insertions and add a new optional feature to return all rule matches, regardless of what their RHS did or whether they were retracted. Add a new listener and tracing method fire-activation!. These changes are moderately non-passive with respect to listening, tracing, and session inspection but are otherwise passive.  See [issue 386](https://github.com/cerner/clara-rules/issues/386) for details.
* Support keyword names for use in custom DSLs. See [issue 371](https://github.com/cerner/clara-rules/issues/371) for details.
* Remove unused let-bindings from generated RHS functions.  See [issue 383 ](https://github.com/cerner/clara-rules/issues/383).
* Improve performance of building rule sessions where conditions have many descendants.  See [issue 377](https://github.com/cerner/clara-rules/issues/377).
* Refactoring to better support custom user-designed DSLs in ClojureScript. See [issue 362](https://github.com/cerner/clara-rules/issues/362).
* Fix a ClassCastException in error handling code for shared LHS conditions.  See [issue 379](https://github.com/cerner/clara-rules/issues/379).

### 0.17.0
* Breaking change affecting clara.rules.listener and clara.tools.tracing namespaces. `insert-facts!` and `retract-facts!` listener methods are now called with `node` and `token` arguments. See [PR 366](https://github.com/cerner/clara-rules/pull/366).
* Fix issue with incorrect namespace qualification of rule and query code in ClojureScript. See [issue 359](https://github.com/cerner/clara-rules/issues/359).
* Add clear-ns-productions! functionality to support clean reloading of rule and query definitions. See [issue 316](https://github.com/cerner/clara-rules/issues/316) for details.
* Support session inspection and fact-graph in ClojureScript. See [issue 307](https://github.com/cerner/clara-rules/issues/307) for details.
* Refactor defrule and defquery to better support customization. See [issue 362](https://github.com/cerner/clara-rules/issues/362) for details.

### 0.16.1
* Fix deserialization failure when the rulebase contains a clojure.lang.PersistentList$EmptyList. See [issue 352](https://github.com/cerner/clara-rules/issues/352) for details.
* Fix bug in which bindings from previous conditions could be ignored in negations of compound boolean expressions.  See [issue 304](https://github.com/cerner/clara-rules/issues/304) for details.
* Fail at compile time when :test conditions are empty.  See [PR 349](https://github.com/cerner/clara-rules/pull/349) for details.
* Fix exception in clara.tools.inspect/explain-activations.  See [PR 346](https://github.com/cerner/clara-rules/pull/346) for details.
* Always return a vector from the all accumulator. See [issue 338](https://github.com/cerner/clara-rules/issues/338) for details.
* Refactored session inspection in [PR 339](https://github.com/cerner/clara-rules/pull/339), which is also expected to improve the performance of session inspection.

### 0.16.0
* Eliminate laziness that broke internal contracts around order of execution, causing an exception to be thrown when executing queries with negation conditions in some edge cases.  See [issue 303](https://github.com/cerner/clara-rules/issues/303) for details.

### 0.15.2
* Fix a bug in the distinct accumulator.  See [issue 325](https://github.com/cerner/clara-rules/issues/325).

### 0.15.1
* Do not resolve condition type symbols in the Clojure environment. See [issue 300](https://github.com/cerner/clara-rules/issues/300).
* Add def-rules-test macro. See [issue 296](https://github.com/cerner/clara-rules/issues/296).
* Support tracing in ClojureScript. See [issue 308](https://github.com/cerner/clara-rules/issues/308).
* Upgrade Schema version.

### 0.15.0
* Provide information on facts accumulated over, not just the result of the accumulation, in session inspection.  This is a breaking change to the structure of the clara.tools.inspect.Explanation record.  The information available in the Explanation record now is a superset of that available in 0.14.0.  See [issue 276](https://github.com/cerner/clara-rules/issues/276) for further details.
* Fix a memory leak in which the memory held references to bindings from retracted facts.  Fixing this leak also fixed some incorrect return data in session inspection. See [issue 280](https://github.com/cerner/clara-rules/issues/280) for details.
* Added a feature that uses data from session inspection to create a directed
graph of facts to logical insertions as a result of those facts. See [issue 277](https://github.com/cerner/clara-rules/issues/277) for details.
* Remove the unused input-condition field from the clara.rules.engine.Accumulator record. See [issue 287](https://github.com/cerner/clara-rules/issues/287) for details.
* Return information on matches for negation conditions in session inspection. See [issue 289](https://github.com/cerner/clara-rules/issues/289) for details.
* Extend the fix for incorrect handling of nested complex negation conditions [issue 149](https://github.com/cerner/clara-rules/issues/149) to ClojureScript; see [issue 241](https://github.com/cerner/clara-rules/issues/241) for details.

### 0.14.0
* Fixed a bug where variables bindings created in constraints could be missed by subsequent constraints in the same condition.  See [issue 267](https://github.com/cerner/clara-rules/issues/267) for details.
* Delayed inserting and retracting facts until fire-rules is called.  Some queries that would reflect changes to the rules network immediately after insertions and retractions now will not reflect these changes until after fire-rules is called.  See [issue 268](https://github.com/cerner/clara-rules/issues/268) for further details and discussion of the reasons for this change.
* Added an experimental performance optimization option to allow insertions and retractions of equal facts to cancel each other out during rules firing. See [issue 249](https://github.com/cerner/clara-rules/issues/249) for details.

### 0.13.0
* This release includes all changes in the previous 0.13.0-RC releases as well as improvements to error handling in the LHS discussed at [issue 255](https://github.com/cerner/clara-rules/issues/255).  Most notably relative to the 0.12.0 release, this replaces the previous durability implementation with a much more robust and performant one as discussed at [issue 198](https://github.com/cerner/clara-rules/issues/198).

### 0.13.0-RC7
* The work to improve batching in order to improve performance at [issue 236](https://github.com/cerner/clara-rules/issues/236) didn't actually improve performance for reasons discussed and fixed at [issue 257](https://github.com/cerner/clara-rules/issues/257).

### 0.13.0-RC6
This release is mostly for performance improvements to durability over 0.13.0-RC5.

* Lookup of the record factory functions during serialization is now cached. See [issue 245](https://github.com/cerner/clara-rules/issues/245) and [issue 253](https://github.com/cerner/clara-rules/issues/253).
* Removes undesired interaction of metadata on rules and queries that are built outside defrule and defquery with Clara's compiler that caused session compilation to fail. No sessions that previously compiled should be impacted by this change. See [pull request 243](https://github.com/cerner/clara-rules/pull/243).
* Elements and tokens that are identical by reference before serialization are now identical by reference after deserialization.  See [issue 247](https://github.com/cerner/clara-rules/issues/247).
* Replaces dynamic vars with JVM ThreadLocals in durability to improve performance.  See [pull request 251](https://github.com/cerner/clara-rules/pull/251).
* Fixes an edge case where a retraction could be duplicated. See [issue 250](https://github.com/cerner/clara-rules/issues/250).

### 0.13.0-RC5
*  The get-alphas-fn is now shared between deserialized sessions with the same rulebase to increase the performance benefit from caching.   Note that this a non-passive change to the experimental durability API. See [issue 234](https://github.com/cerner/clara-rules/issues/234).
* Improve performance on the JVM when productions have a type that has multiple descendant types that are found in the session. See [issue 236](https://github.com/cerner/clara-rules/issues/236).
* Improve performance on the JVM by replacing internal use of Clojure's hierarchies with class-based dispatch. See [issue 239](https://github.com/cerner/clara-rules/issues/239).

### 0.13.0-RC4

* The project is now moved to the com.cerner group and [Cerner's GitHub organization](https://github.com/cerner/).
* Avoid unneeded retractions through negation nodes. See [issue 231](https://github.com/cerner/clara-rules/issues/231).

### 0.13.0-RC3
Bug fixes over 0.13.0-RC2

* Improve performance by processing external retractions as a batch. See [issue 225](https://github.com/cerner/clara-rules/issues/225).
* Handle sorted collections in durability logic. See [PR 228](https://github.com/cerner/clara-rules/pull/228).
* Remove redundant retract calls to the change listener. See [PR 227](https://github.com/cerner/clara-rules/pull/227/files).


### 0.13.0-RC2
Bug fixes and additional tracing over 0.13.0-RC2

* Additional calls to the listener for better traceability of sessions. See [PR 222](https://github.com/cerner/clara-rules/pull/222).
* Fix invalid state when retracting certain accumulator flows. See [PR 223](https://github.com/cerner/clara-rules/pull/223).

### 0.13.0-RC1
This is an initial release to validate revamped durability logic. Details and a path forward will be in the full 0.13.0 release.

* Add a listener event for Alpha node activations. See (https://github.com/cerner/clara-rules/pull/220).
* Revamped approach to durable, serializable sessions. See (https://github.com/cerner/clara-rules/pull/219).

### 0.12.0

* Eliminate unnecessary retractions in accumulators. See [issue 182](https://github.com/cerner/clara-rules/issues/182).
* Rule activations fire in the order they are given to the compiler. See [issue 192](https://github.com/cerner/clara-rules/issues/192).
* Fix bug where rule constrained may be ignored. See [issue 194](https://github.com/cerner/clara-rules/issues/194).
* Make rule compilation deterministic by eliminating internal iteration over unordered data structures. See [issue 199](https://github.com/cerner/clara-rules/issues/199).
* Improve testing of rule firing permutations. See [issue 205](https://github.com/cerner/clara-rules/issues/205).
* Optimize common retraction pattern by checking fact identity first. See [issue 213](https://github.com/cerner/clara-rules/issues/213).
* Correct several accumulator edge cases. See issues [189](https://github.com/cerner/clara-rules/issues/189), [190](https://github.com/cerner/clara-rules/issues/190), and [102](https://github.com/cerner/clara-rules/issues/102).
* Working memory optimizations. See [issue 184](https://github.com/cerner/clara-rules/issues/184).
* Clojure doc clarifications.

### 0.11.1

* Generated code for the left-hand side should only access fields that are used. See [issue 180](https://github.com/cerner/clara-rules/pull/180).
* Fix incorrect qualification of let variables on right-hand side. See [issue 178](https://github.com/cerner/clara-rules/issues/178).
* Optimize fact retraction. See [issue 183](https://github.com/cerner/clara-rules/pull/183).

### 0.11.0

* Add a "grouping-by" accumulator. See [issue 164](https://github.com/cerner/clara-rules/pull/164).
* Fix truth maintenance when working with equal inserted facts. See [issue 171](https://github.com/cerner/clara-rules/issues/171).
* Fix incorrect rule activation edge case when dealing with complex nested negations and unconditional inserts. See [issue 174](https://github.com/cerner/clara-rules/issues/174).
* clara.rules/mk-session now loads rules stored in a var if given a qualified symbol for that var. See [issue 177](https://github.com/cerner/clara-rules/issues/177).

### 0.10.0
Clara 0.10 is compatible with previous versions, with a couple caveats. These are:

* The intermediate representation of the Rete network changed as reflected in schema updates in clara.rules.schema. This only affects users that build tooling that inspect the network structure.
* The order of rule ordering and the state of queries prior to (fire-rules) being called may have changed. This ordering was not guaranteed previously, but users may have depended on it accidentally.
* ClojureScript users will need to use ClojureScript 1.7.170 or newer.

Here are the specifics on what changed since 0.9.2:

* Fix unification bugs when dealing with nested negations. See [issue 166](https://github.com/cerner/clara-rules/issues/166).
* Properly handle tests nested in negation nodes. See [issue 165](https://github.com/cerner/clara-rules/issues/165).
* Improve inspect function to explain the insertion of a given fact. See [issue 161](https://github.com/cerner/clara-rules/issues/161).
* Remove duplicate rules and dependency on order of rules when creating sessions. See [issue 157](https://github.com/cerner/clara-rules/issues/157).
* Significantly improve performance of building the Rete network when dealing with large disjunctions. See [issue 153](https://github.com/cerner/clara-rules/issues/153).
* Allow multiple binding and equality checks in a single expression. See [issue 151](https://github.com/cerner/clara-rules/issues/151).
* Ensure variables bounded in a nested, negated conjunction are visible elsewhere in that conjunction. See [149](https://github.com/cerner/clara-rules/issues/149).

### 0.9.2
* Report better error and line number when parsing malformed productions. See issue [144](https://github.com/cerner/clara-rules/issues/144).
* Fix truth maintenance bug when using disjunctions. See issue [145](https://github.com/cerner/clara-rules/pull/145).
* Target Java API compilation to Java 1.6. See issue [146](https://github.com/cerner/clara-rules/issues/146).
* Catch exceptions thrown in rule actions and add context for debugging. See issue [147](https://github.com/cerner/clara-rules/issues/147).

### 0.9.1
* Allow binding of arbitrary expressions that use previous variables. See [issue 142](https://github.com/cerner/clara-rules/issues/142).
* Simplify variable dependencies with a topological sort of rule conditions. See [issue 133](https://github.com/cerner/clara-rules/issues/133).

### 0.9.0
* Move to Clojure 1.7 and adopt modern ClojureScript best practices, such as reader conditionals and cljs.test.
* ClojureScript users may now use macros from clara.rules; clara.rules.macros should be considered deprecated. See [issue 128](https://github.com/cerner/clara-rules/issues/128).
* Add an :exists operator. See [issue 130](https://github.com/cerner/clara-rules/issues/130).
* Pre-defined accumulators now handle fact retraction. See [issue 127](https://github.com/cerner/clara-rules/issues/127).
* Allow use of accumulator results in other rule conditions. See [issue 132](https://github.com/cerner/clara-rules/issues/132).
* Support arbitrary comparisons in accumulators in ClojureScript, bringing it inline with the Clojure support. See [issue 131](https://github.com/cerner/clara-rules/issues/131).
* Support multiple productions defined in a single var, useful for third-party macros. See [issue 134](https://github.com/cerner/clara-rules/issues/134).
* Update several dependencies.
* Mark internal namespaces as internal, as they may be moved in a future release.

### 0.8.9
* Properly handle deeply nested conjunctions. See [issue 126](https://github.com/cerner/clara-rules/pull/126).
* Report error for unbound condition variables across all condition types. See [issue 124](https://github.com/cerner/clara-rules/pull/124).
* Support munged record field names. See [issue 121](https://github.com/cerner/clara-rules/pull/121).
* Generalize schema used for s-expressions in rules. See [issue 120](https://github.com/cerner/clara-rules/pull/120).
* Support multiple expressions on right-hand side of defrule. See [issue 118](https://github.com/cerner/clara-rules/issues/118).
* Properly call retract-facts-logical! listener. See [issue 117](https://github.com/cerner/clara-rules/issues/117).
* Fix retraction when using custom fact type. See [issue 116](https://github.com/cerner/clara-rules/issues/116).
* Support type ancestors in ClojureScript. See [issue 115](https://github.com/cerner/clara-rules/pull/115).
* Handle aliased symbols in ClojureScript. See [issue 113](https://github.com/cerner/clara-rules/issues/113).

### 0.8.8
* Upgrade to Prismatic Schema 0.4.3
* Handle use of Clojure .. macro in rule expressions. See [issue 108](https://github.com/cerner/clara-rules/pull/108).
* Fix edge case yielding an NPE when analysis some expressions. See [issue 109](https://github.com/cerner/clara-rules/pull/109).

### 0.8.7
* Properly qualify references to Java classes on the RHS of rules, supporting try/catch and static method calls. See [issue 104](https://github.com/cerner/clara-rules/issues/104).
* Fix bug when retracting a subset of facts blocked by a negation rule. See [issue 105](https://github.com/cerner/clara-rules/issues/105).

### 0.8.6
* Fix a collection of issues surrounding referencing bound variables in nested functions. See [issue 90](https://github.com/cerner/clara-rules/issues/90) and items referenced from there.
* Fix a truth maintenance issue for accumulators that offer an initial value when there is nothing to accumulate over. See [issue 91](https://github.com/cerner/clara-rules/issues/91).
* Fix bug that caused options to be dropped in cljs. See [issue 92](https://github.com/cerner/clara-rules/pull/92).
* Allow explicitly specifying productions in CLJS. See [issue 94](https://github.com/cerner/clara-rules/pull/94).
* Better handle macro-generated rules. See [issue 100](https://github.com/cerner/clara-rules/pull/100).
* The :no-loop property now applies to facts retracted due to truth maintenance. See [issue 99](https://github.com/cerner/clara-rules/issues/99).

### 0.8.5
* Fix specific filtered accumulator bug. See [issue 89](https://github.com/cerner/clara-rules/pull/89).
* Allow binding variables in set and map literals. See [issue 88](https://github.com/cerner/clara-rules/pull/88).
* Fix truth maintenance consistency when working with equal facts. See [issue 84](https://github.com/cerner/clara-rules/issues/84).

### 0.8.4
* Ensure all truth maintenance updates are flushed. See [issue 83](https://github.com/cerner/clara-rules/issues/83).

### 0.8.3
* Fix for truth maintenance when an accumulator produces a nil value. See [issue 79](https://github.com/cerner/clara-rules/pull/79).
* Use bound facts in unification. See [issue 80](https://github.com/cerner/clara-rules/issues/80).
* Improve inspection and explainability support in the clara.tools.inspect namespace.

### 0.8.2
* Batch up inserts done by rules and apply them as a group. See [issue 58](https://github.com/cerner/clara-rules/issues/58).
* Optimize some internal functions based on real-world profiling.

### 0.8.1
* Fix stack overflow under workloads with many individually inserted facts. See [issue 76](https://github.com/cerner/clara-rules/pull/76).

### 0.8.0
* Support for salience. See [issue 25](https://github.com/cerner/clara-rules/issues/25).
* Rule compilation is significantly faster. See [issue 71](https://github.com/cerner/clara-rules/issues/71).
* Handle use cases where there are a large number of retracted facts. See [issue 74](https://github.com/cerner/clara-rules/pull/74).
* Add insert-all! and insert-all-unconditional!. See [issue 75](https://github.com/cerner/clara-rules/issues/75).

### 0.7.0
* Allow bound variables to be used by arbitrary functions in subsequent conditions. See [issue 66](https://github.com/cerner/clara-rules/issues/66)
* Add metadata to rule's right-hand side so we see line numbers in compilation errors and call stacks. See [issue 69](https://github.com/cerner/clara-rules/issues/69)
* Improved memory consumption in cases where rules may be retracted and re-added frequently.

### 0.6.2
* Properly handle retractions in the presence of negation nodes; see [issue 67](https://github.com/cerner/clara-rules/issues/67).
* Report error if the fact type in a rule appears to be malformed; see [issue 65](https://github.com/cerner/clara-rules/issues/65).

### 0.6.1
* Reduce depth of nested function for [issue 64](https://github.com/cerner/clara-rules/issues/64).
* Clean up reflection warnings.

### 0.6.0
* Several performance optimizations described in [issue 56](https://github.com/cerner/clara-rules/issues/56) and [this blog post](http://www.toomuchcode.org/blog/2014/06/16/micro-bench-macro-optimize/).
* Session durability as an experimental feature. See [issue 16](https://github.com/cerner/clara-rules/issues/16) and [the wiki](https://github.com/cerner/clara-rules/wiki/Durability).
* Improved ability to inspect session state and explain why rules and queries were activated. See the [inspection page on the wiki](https://github.com/cerner/clara-rules/wiki/Inspection) for details.
* A list of smaller changes can be seen via the [milestone summary](https://github.com/cerner/clara-rules/issues?milestone=8&page=1&state=closed)

### 0.5.0
Contains several bug fixes and some usage enhancements, including:

* The clara.tools.inspect package allows for inspection and explanation of why rules fired. Also see [issue 48](https://github.com/cerner/clara-rules/issues/48).
* [File and line information is preserved when compiling.](https://github.com/cerner/clara-rules/pull/51)
* A list of several other fixes can be seen via the [milestone summary](https://github.com/cerner/clara-rules/issues?milestone=7&page=1&state=closed)

### 0.4.0
This is a major refactoring of the Clara engine, turning all rule and Rete network representations into well-defined data structures. Details are at these links:

* [Rules as Data Support](http://www.toomuchcode.org/blog/2014/01/19/rules-as-data/)
* [ClojureScript Rete network on the server side](https://github.com/cerner/clara-rules/issues/34)

### 0.3.0
* [ClojureScript support](https://github.com/cerner/clara-rules/issues/4)
* [No-loop option for rules](https://github.com/cerner/clara-rules/issues/23)
* [Improved variable binding](https://github.com/cerner/clara-rules/pull/26)

### 0.2.2
* [Accumulators should always fire if all variables can be bound](https://github.com/cerner/clara-rules/issues/22)

### 0.2.1
A fix release with some internal optimizations, including the following:

* [Use activation list for rules](https://github.com/cerner/clara-rules/issues/19)
* [Support symbols with "-" in them](https://github.com/cerner/clara-rules/issues/20)

### 0.2.0
* [Remove need for == macro](https://github.com/cerner/clara-rules/pull/18)
* [Support for arbitrary Clojure maps](https://github.com/cerner/clara-rules/issues/6)

### 0.1.1
A number of bug fixes, see the [milestone summary](https://github.com/cerner/clara-rules/issues?milestone=1&page=1&state=closed)

### 0.1.0
The initial release.
