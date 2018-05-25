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
     * NullnessLite option for Nullness Checker: (1) assume all values initialized; (2) assume all
     * methods side-effect-free, not invalidating dataflow; (3) assume no aliasing, see method
     * canAlias under NullnessStore.java and KeyForStore.java; (4) assume all keys exist in the map,
     * all Map.get(key) returns nonnull; (5) assume all BoxedClass.valueOf(primitiveType) are pure,
     * returned Object are equal by ==.
     */
    @Override
    public void initChecker() {
        Map<String, String> nullness_lite = new HashMap<String, String>();

        if (this.hasOption("NullnessLite")) {
            String opts = this.getOption("NullnessLite");

            String oldSWVal = this.getOption("suppressWarnings");
            String newSWVal = "uninitialized";
            newSWVal = (oldSWVal == null) ? newSWVal : newSWVal + "," + oldSWVal;
            if (opts == null) {
                // 1: assume all values initialized
                nullness_lite.put("suppressWarnings", newSWVal);
                // 2: assume all methods side-effect-free
                nullness_lite.put("assumeSideEffectFree", null);
            } else {
                opts = opts.toLowerCase();

                if (opts.contains("init")) {
                    // 1: assume all values initialized
                    nullness_lite.put("suppressWarnings", newSWVal);
                }

                if (opts.contains("inva")) {
                    // 2: assume all methods side-effect-free
                    nullness_lite.put("assumeSideEffectFree", null);
                }

                if (!opts.contains("boxp")) {
                    // disable 5: boxing of primitives
                    nullness_lite.put("ignorejdkastub", null);
                }
            }
        } else {
            // ignore jdk stub if NullnessLite = OFF
            nullness_lite.put("ignorejdkastub", null);
        }

        this.addOptions(nullness_lite);
        super.initChecker();
    }
}
