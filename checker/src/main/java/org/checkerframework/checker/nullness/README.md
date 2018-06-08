# The Developer Manual for Nullness_Lite option
## Five features in the Nullness_Lite option different from the Nullness Checker
1. Assume all values (fields & class variables) initialized
2. Assume all methods are `@SideEffectFree`
3. Assume no aliasing
4. Assume all keys are in the map and `Map.get(key)` returns `@NonNull`
5. Assume the boxing of primitives return the same object and `BoxedClass.valueOf()` are `@Pure`

Features 2 & 3 are both from the [invalidation of the dataflow](https://github.com/weifanjiang/Nullness_Lite/blob/master/README.md) feature in the Nullness_Lite. We split it here because they need to be implemented in different places in the Nullness_Lite. In this way, we can refer them easily.

## Files changed in the Nullness Checker
|File Name|Changes Description|
|--|--|
|NullnessChecker.java|ANullnessLite option added; initChecker() overrided for features 1, 2 & 5|
|KeyForSubchecker.java|ANullnessLite option added|
|NullnessAnalysis.java|Instance variables NO_ALIASING and ALL_KEY_EXIST added as flags for feature 3 & 4|
|KeyForAnalysis.java|Instance variable NO_ALIASING added as a flag for feature 3|
|NullnessStore.java|Method canAlias overrided for feature 3|
|KeyForStore.java|Method canAlias overrided for feature 3|
|NullnessTransfer.java|Instance variable ALL_KEY_EXIST added as a flag for feature 4; Method visitMethodInvocation revised for feature 4|
See documentation in source files for more details.

## Files added for testing
|File/folder Path|Description|
|--|--|
|checker-framework/checker/tests/nullness-liteoption/|spec. tests for the Nullness_Lite|
|checker-framework/checker/tests/nullness-liteoption-init/|spec. tests for feature 1|
|checker-framework/checker/tests/nullness-liteoption-mapk/|spec. tests for feature 4|
|checker-framework/checker/tests/nullness-liteoption-inva/|spec. tests for feature 2 & 3|
|checker-framework/checker/tests/nullness-liteoption-boxp/|spec. tests for feature 5|
|checker-framework/checker/tests/nullness-liteoption-comreg/|regression tests of [issue 5](https://github.com/979216944/checker-framework/issues/5)|
|checker-framework/checker/tests/nullness-liteoption-comreg-boxp/|regression tests of [issue 5](https://github.com/979216944/checker-framework/issues/5) for feature 5|
|checker-framework/checker/src/test/java/tests/NullnessLiteOptTest.java|Initialize the spec. test for the Nullness_Lite|
|checker-framework/checker/src/test/java/tests/NullnessLiteOptInitTest.java|Initialize the spec. test for feature 1|
|checker-framework/checker/src/test/java/tests/NullnessLiteOptInvaTest.java|Initialize the spec. test for feature 2 & 3|
|checker-framework/checker/src/test/java/tests/NullnessLiteOptMapkTest.java|Initialize the spec. test for feature 4|
|checker-framework/checker/src/test/java/tests/NullnessLiteOptBoxpTest.java|Initialize the spec. test for feature 5|
|checker-framework/checker/src/test/java/tests/NullnessLiteComRegTest.java|Initialize the regression test|
|checker-framework/checker/src/test/java/tests/NullnessLiteComRegBoxpTest.java|Initialize the regression test for feature 5|
