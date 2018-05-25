package org.checkerframework.checker.nullness;

import java.util.List;
import java.util.Set;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import org.checkerframework.common.basetype.BaseTypeChecker;
import org.checkerframework.framework.flow.CFAbstractAnalysis;
import org.checkerframework.framework.flow.CFAbstractValue;
import org.checkerframework.javacutil.Pair;

/**
 * The analysis class for the non-null type system (serves as factory for the transfer function,
 * stores and abstract values.
 */
public class NullnessAnalysis
        extends CFAbstractAnalysis<NullnessValue, NullnessStore, NullnessTransfer> {

    // fields inidicates whether nullness_lite is enabled with the following features
    protected boolean NO_ALIASING;
    protected boolean ALL_KEY_EXIST;

    public NullnessAnalysis(
            BaseTypeChecker checker,
            NullnessAnnotatedTypeFactory factory,
            List<Pair<VariableElement, NullnessValue>> fieldValues) {
        super(checker, factory, fieldValues);
        NO_ALIASING =
                checker.hasOption("NullnessLite")
                        && (checker.getOption("NullnessLite") == null
                                || checker.getOption("NullnessLite")
                                        .toLowerCase()
                                        .contains("inva"));
        ALL_KEY_EXIST =
                checker.hasOption("NullnessLite")
                        && (checker.getOption("NullnessLite") == null
                                || checker.getOption("NullnessLite")
                                        .toLowerCase()
                                        .contains("mapk"));
        this.transferFunction = createTransferFunction();
    }

    @Override
    public NullnessStore createEmptyStore(boolean sequentialSemantics) {
        return new NullnessStore(this, sequentialSemantics);
    }

    @Override
    public NullnessStore createCopiedStore(NullnessStore s) {
        return new NullnessStore(s);
    }

    @Override
    public NullnessValue createAbstractValue(
            Set<AnnotationMirror> annotations, TypeMirror underlyingType) {
        if (!CFAbstractValue.validateSet(annotations, underlyingType, qualifierHierarchy)) {
            return null;
        }
        return new NullnessValue(this, annotations, underlyingType);
    }
}
