import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.common.value.qual.MinLen;
import org.checkerframework.framework.qual.ConditionalPostconditionAnnotation;
import org.checkerframework.framework.qual.PostconditionAnnotation;
import org.checkerframework.framework.qual.PreconditionAnnotation;

public class CustomContractWithArgs {
    // Postcondition for MinLen
    @PostconditionAnnotation(
        qualifier = MinLen.class,
        sourceArguments = "targetValue",
        targetArguments = "value"
    )
    @interface EnsuresMinLen {
        public String[] value();

        public int targetValue();
    }

    // Conditional postcondition for LTLengthOf
    @ConditionalPostconditionAnnotation(
        qualifier = LTLengthOf.class,
        sourceArguments = {"targetValue", "targetOffset"},
        targetArguments = {"value", "offset"}
    )
    @interface EnsuresLTLIf {
        public boolean result();

        public String[] expression();

        public String[] targetValue();

        public String[] targetOffset();
    }

    //Precondition for LTLengthOf
    @PreconditionAnnotation(
        qualifier = LTLengthOf.class,
        sourceArguments = {"targetValue", "targetOffset"},
        targetArguments = {"value", "offset"}
    )
    @interface RequiresLTL {
        public String[] value();

        public String[] targetValue();

        public String[] targetOffset();
    }

    class Base {
        @EnsuresMinLen(value = "#1", targetValue = 10)
        void minLenContract(int[] a) {
            if (a.length < 10) throw new RuntimeException();
        }

        @EnsuresMinLen(value = "#1", targetValue = 10)
        // :: error: (contracts.postcondition.not.satisfied)
        void minLenWrong(int[] a) {
            if (a.length < 9) throw new RuntimeException();
        }

        void minLenUse(int[] b) {
            minLenContract(b);
            int @MinLen(10) [] c = b;
        }

        public int b, y;

        @EnsuresLTLIf(
            expression = "b",
            targetValue = {"#1", "#1"},
            targetOffset = {"#2 + 1", "10"},
            result = true
        )
        boolean ltlPost(int[] a, int c) {
            if (b < a.length - c - 1 && b < a.length - 10) {
                return true;
            } else {
                return false;
            }
        }

        // TODO: this should be an error, because targetOffset refers to non-existent parameter
        @EnsuresLTLIf(expression = "b", targetValue = "#1", targetOffset = "#3", result = true)
        boolean ltlPostInvalid(int[] a, int c) {
            return false;
        }

        @RequiresLTL(
            value = "b",
            targetValue = {"#1", "#1"},
            targetOffset = {"#2 + 1", "-10"}
        )
        void ltlPre(int[] a, int c) {
            @LTLengthOf(value = "a", offset = "c+1") int i = b;
        }

        void ltlUse(int[] a, int c) {
            if (ltlPost(a, c)) {
                @LTLengthOf(value = "a", offset = "c+1") int i = b;

                ltlPre(a, c);
            }
            // :: error: (assignment.type.incompatible)
            @LTLengthOf(value = "a", offset = "c+1") int j = b;
        }
    }
    /* TODO: enable test
    class Derived extends Base {
        public int x;

        @Override
        @EnsuresLTLIf(
            expression = "b ",
            targetValue = {"#1", "#1"},
            targetOffset = {"#2 + 1", "11"},
            result = true
        )
        boolean ltlPost(int[] a, int d) {
            return false;
        }

        @Override
        @RequiresLTL(
            value = "b ",
            targetValue = {"#1", "#1"},
            targetOffset = {"#2 + 1", "-11"}
        )
        void ltlPre(int[] a, int d) {
            @LTLengthOf(
                value = {"a", "a"},
                offset = {"d+1", "-10"}
            )
            // :: error: (assignment.type.incompatible)
            int i = b;
        }
    }

    class DerivedInvalid extends Base {
        public int x;

        @Override
        @EnsuresLTLIf(
            expression = "b ",
            targetValue = {"#1", "#1"},
            targetOffset = {"#2 + 1", "9"},
            result = true
        )
        // :: error: (contracts.conditional.postcondition.true.override.invalid)
        boolean ltlPost(int[] a, int c) {
            // :: error: (contracts.conditional.postcondition.not.satisfied)
            return true;
        }

        @Override
        @RequiresLTL(
            value = "b ",
            targetValue = {"#1", "#1"},
            targetOffset = {"#2 + 1", "-9"}
        )
        // :: error: (contracts.precondition.override.invalid)
        void ltlPre(int[] a, int d) {
            @LTLengthOf(
                value = {"a", "a"},
                offset = {"d+1", "-10"}
            )
            int i = b;
        }
    }
    */
}
