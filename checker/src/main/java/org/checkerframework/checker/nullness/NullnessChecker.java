package org.checkerframework.checker.nullness;

import java.io.File;
import java.util.*;
import javax.annotation.processing.SupportedOptions;

/** A concrete instantiation of {@link AbstractNullnessChecker} using freedom-before-commitment. */
@SupportedOptions({"NullnessLite"})
public class NullnessChecker extends AbstractNullnessChecker {

    private static final String NULLNESS_LITE_STUB = "nullness_lite.astub";
    private static final String[] NULLNESS_LITE_OPTS = {"init", "mapk", "inva", "boxp"};

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

            String oldStubs = this.getOption("stubs");
            String newStubs = NULLNESS_LITE_STUB;
            newStubs = (oldStubs == null) ? newStubs : oldStubs + File.pathSeparator + newStubs;

            if (opts == null) {
                // 1: assume all values initialized
                nullness_lite.put("suppressWarnings", newSWVal);
                // 2: assume all methods side-effect-free
                nullness_lite.put("assumeSideEffectFree", null);
                // 5: assume all BoxedClass.valueOf(primitiveType) are pure
                nullness_lite.put("stubs", newStubs);
            } else {

                List<String> optsList = new ArrayList<String>();
                Collections.addAll(optsList, opts.split(File.pathSeparator));

                if (optsList.contains(NULLNESS_LITE_OPTS[0])) {
                    // 1: assume all values initialized
                    nullness_lite.put("suppressWarnings", newSWVal);
                }
                if (optsList.contains(NULLNESS_LITE_OPTS[2])) {
                    // 2: assume all methods side-effect-free
                    nullness_lite.put("assumeSideEffectFree", null);
                }
                if (optsList.contains(NULLNESS_LITE_OPTS[3])) {
                    // 5: assume all BoxedClass.valueOf(primitiveType) are pure
                    nullness_lite.put("stubs", newStubs);
                }
            }
        }

        this.addOptions(nullness_lite);
        super.initChecker();
    }
}
