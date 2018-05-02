package org.checkerframework.checker.nullness;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.SupportedOptions;

/** A concrete instantiation of {@link AbstractNullnessChecker} using freedom-before-commitment. */
@SupportedOptions({"NullnessLite"})
public class NullnessChecker extends AbstractNullnessChecker {

    public NullnessChecker() {
        super(true);
    }

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
