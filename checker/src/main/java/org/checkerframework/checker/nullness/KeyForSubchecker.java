package org.checkerframework.checker.nullness;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.SupportedOptions;
import org.checkerframework.common.basetype.BaseTypeChecker;

/**
 * A type-checker for determining which values are keys for which maps. Typically used as part of
 * the compound checker for the nullness type system.
 *
 * @checker_framework.manual #map-key-checker Map Key Checker
 * @checker_framework.manual #nullness-checker Nullness Checker
 */
@SupportedOptions({"NullnessLite"})
public class KeyForSubchecker extends BaseTypeChecker {
    /**
     * NullnessLite option for Nullness Checker 1. [Initialization Checker] disabled as command-line
     * option. 2. [Invalidation of Dataflow] impure methods disallowed as command-line option. 3.
     * [Invalidation of Dataflow] aliasing disallowed, see method canAlias in NullnessStore.java
     */
    @Override
    public void initChecker() {
        super.initChecker();

        if (this.hasOption("NullnessLite")) {
            Map<String, String> nullness_lite = new HashMap<String, String>();
            nullness_lite.put("suppressWarnings", "uninitialized");
            nullness_lite.put("assumeSideEffectFree", null);
            this.addOptions(nullness_lite);
        }
    }
}
