# The Developer Manual for Nullness_Lite option
## Five features in the Nullness_Lite option different from the Nullness Checker
1. Assume all values (fields & class variables) initialized
2. Assume all methods are `@SideEffectFree`
3. Assume no aliasing
4. Assume all keys are in the map and `Map.get(key)` returns `@NonNull`
5. Assume the boxing of primitives return the same object and `BoxedClass.valueOf()` are `@Pure`

## Files we changed in the Nullness Checker
|File Name|Changes Description|
|--|--|
|NullnessChecker.java|ANullnessLite option added; initChecker() overrided|
|KeyForSubchecker.java|ANullnessLite option added|
|NullnessAnalysis.java|Instance variables NO_ALIASING and ALL_KEY_EXIST added as flags for feature 3 & 4|
|KeyForAnalysis.java|Instance variable NO_ALIASING added as a flag for feature 3|
|NullnessStore.java|Method canAlias overrided for feature 3|
|KeyForStore.java|Method canAlias overrided for feature 3|
|NullnessTransfer.java|Instance variable ALL_KEY_EXIST added as a flag for feature 4; Method visitMethodInvocation revised for feature 4|
