package org.checkerframework.checker.nullness;

import org.checkerframework.dataflow.analysis.FlowExpressions;
import org.checkerframework.framework.flow.CFAbstractAnalysis;
import org.checkerframework.framework.flow.CFAbstractStore;

public class KeyForStore extends CFAbstractStore<KeyForValue, KeyForStore> {
    public KeyForStore(
            CFAbstractAnalysis<KeyForValue, KeyForStore, ?> analysis, boolean sequentialSemantics) {
        super(analysis, sequentialSemantics);
    }

    protected KeyForStore(CFAbstractStore<KeyForValue, KeyForStore> other) {
        super(other);
    }

    /**
     * [Invalidation of Dataflow] When NullnessLite is enabled, canAlias returns false to disallow
     * aliasing; Note: Since KeyForSubChecker is not inherit from NullnessChecker, modify
     * KeyForStore is the only way to change the control flow.
     */
    @Override
    public boolean canAlias(FlowExpressions.Receiver a, FlowExpressions.Receiver b) {
        if (((KeyForAnalysis) analysis).NULLNESS_LITE_OPTION) {
            return false;
        }
        return super.canAlias(a, b);
    }
}
