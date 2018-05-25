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
     * [Invalidation of Dataflow] When see -ANullnessLite or -ANullnessLite=inva, canAlias returns
     * false to disallow aliasing; Note KeyForSubChecker doesn't inherit from NullnessChecke; Thus,
     * changing KeyForStore is the only way to change the control flow.
     */
    @Override
    public boolean canAlias(FlowExpressions.Receiver a, FlowExpressions.Receiver b) {
        if (((KeyForAnalysis) analysis).NO_ALIASING) {
            return false;
        }
        return super.canAlias(a, b);
    }
}
