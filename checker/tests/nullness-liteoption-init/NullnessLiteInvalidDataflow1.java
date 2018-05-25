// This class illustrates a correct implementation of "-ANullnessLite=init":
// check when "-ANullnessLite=init", the Nullness_Lite does not disable
// the invalidation of dataflow;
//
// The Invalidation of dataflow include two aspects:
// 1). Methods call cannot invalidate the dataflow facts.
//     In other words, assume all methods call are SideEffectFree.
// 2). Variables assignement cannot invalidate the dataflow facts
//     of other variables.
import org.checkerframework.checker.nullness.qual.Nullable;

public class NullnessLiteInvalidDataflow1 {
    public static @Nullable Node myField;

    // test methods call
    public static void m1() {
        myField = new Node("123");

        if (myField != null) {
            dummyMethod();
            // error issued by the Nullness Checker
            // :: error: (dereference.of.nullable)
            myField.toString(); // "-ANullnessLite=init" = error
        }
    }

    public static void dummyMethod() {}

    // test aliasing in field
    public static void m2() {
        myField = new Node("");
        Node a = new Node("");
        a.next = new Node("");

        if (a.next != null) {
            myField.next = null;
            // error issued by the Nullness Checker
            // :: error: (dereference.of.nullable)
            a.next.toString(); // "-ANullnessLite=init" = error
        }
    }

    // test aliasing in array
    public void arrayAccess() {
        Node a = new Node("");
        Node b = new Node("");
        Node[] c = new Node[2];
        a.next = b;
        c[0] = a;
        c[1] = a.next;

        if (b.str != null) {
            c[1].str = null;
            // error issued by the Nullness Checker
            // :: error: (dereference.of.nullable)
            b.str.toString(); //  "-ANullnessLite=init" = error
        }
    }

    public static class Node {
        public @Nullable String str;
        public @Nullable Node next;

        public Node(String str) {
            this.str = str;
            this.next = null;
        }

        @Override
        public String toString() {
            if (str != null) {
                return str;
            } else {
                return "null";
            }
        }
    }
}
