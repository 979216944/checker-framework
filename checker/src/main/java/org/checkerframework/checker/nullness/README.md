# The Developer Manual for Nullness_Lite option
## 4 features in the Nullness_Lite option different from the Nullness Checker
1. Assume all values (fields & class variables) initialized
2. Assume all keys are in the map and `Map.get(key)` returns `@NonNull`
3. Assume no invalidation of dataflow
   - 3.1. Assume all methods are `@SideEffectFree` 
   - 3.2. Assume no aliasing
4. Assume the boxing of primitives return the same object and `BoxedClass.valueOf()` are `@Pure`

The implementation includes an additional command line argument `ANullnessLite` for the Nullness Checker and 4 values `ANullnessLite` option can accept for testing individual feature.

|Command line argument added|description|
|--|--|
|`-ANullnessLite`| all features in the Nullness_Lite is enabled |
|`-ANullnessLite=init`| feature 1 is enabled |
|`-ANullnessLite=mapk`|feature 2 is enabled|
|`-ANullnessLite=inva`|feature 3 is enabled|
|`-ANullnessLite=boxp`|feature 4 is enabled|

## Files changed in the Nullness Checker
|File Name|Changes Description|
|--|--|
|NullnessChecker.java|`ANullnessLite` option added; `initChecker()` overrided for features 1, 3.1 & 4|
|KeyForSubchecker.java|`ANullnessLite` option added|
|NullnessAnalysis.java|Instance variables `NO_ALIASING` and `ALL_KEY_EXIST` added as flags for feature 3.2 & 2|
|KeyForAnalysis.java|Instance variable `NO_ALIASING` added as a flag for feature 3.2|
|NullnessStore.java|Method `canAlias` overrided for feature 3.2|
|KeyForStore.java|Method `canAlias` overrided for feature 3.2|
|NullnessTransfer.java|Instance variable `ALL_KEY_EXIST` added as a flag and method `visitMethodInvocation` revised for feature 2|
See documentation in source files for more details.

## Files added for testing
[issue 5](https://github.com/979216944/checker-framework/issues/5)|

|Folder Path: checker-framework/checker/tests/| Description |
|-|-|
|nullness-liteoption/|spec. tests for the Nullness_Lite|
|nullness-liteoption-init/|spec. tests for feature 1|
|nullness-liteoption-mapk/|spec. tests for feature 2|
|nullness-liteoption-inva/|spec. tests for feature 3|
|nullness-liteoption-boxp/|spec. tests for feature 4|
|nullness-liteoption-comreg/|regression tests of issue 5|
|nullness-liteoption-comreg-boxp/|regression tests of issue 5 for feature 4|

|File Path: checker-framework/checker/src/test/java/tests/| Description |
|-|-|
|NullnessLiteOptTest.java|Initialize the spec. test for the Nullness_Lite|
|NullnessLiteOptInitTest.java|Initialize the spec. test for feature 1|
|NullnessLiteOptMapkTest.java|Initialize the spec. test for feature 2|
|NullnessLiteOptInvaTest.java|Initialize the spec. test for feature 3|
|NullnessLiteOptBoxpTest.java|Initialize the spec. test for feature 4|
|NullnessLiteComRegTest.java|Initialize the regression test of issue 5|
|NullnessLiteComRegBoxpTest.java|Initialize the regression test pf issue 5 for feature 4|
