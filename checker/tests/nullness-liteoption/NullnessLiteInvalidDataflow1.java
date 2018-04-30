import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;

public class NullnessLiteInvalidDataflow1 {

    public static void main(String[] args) {}

    public @Nullable Node myField;

    public void m1() {
        myField = new Node("123");

        if (myField != null) {
            int result = computeValue();
            myField.toString(); // Nullness Checker Err here
        }
    }

    public int computeValue() {
        myField = null;
        return 0;
    }

    public void m2() {
        Node a = new Node("123");
        Node b = new Node("234");
        a.next = b;

        if (b.str != null) {
            a.next.str = null;
            b.str.toString(); // Nullness Checker Err here
        }

        if (a.next.str != null) {
            b.str = null;
            a.next.str.toString(); // Nullness Checker Err here
        }
    }

    public void arrayAccess() {
        Node a = new Node("12345");
        Node b = new Node("54321");
        Node[] c = new Node[2];
        a.next = b;
        c[0] = a;
        c[1] = a.next;

        if (b.str != null) {
            c[1].str = null;
            b.str.toString(); // Nullness Checker Err here
        }

        if (c[1].str != null) {
            b.str = null;
            c[1].str.toString(); // Nullness Checker Err here
        }
    }

    public void falsePositive() {
        foo(new Node("a"));
    }

    public void foo(Node a) {
        Node b = new Node("a");
        a.next = new Node("b");
        if (a.next != null) {
            b.next = null;
            a.next.toString(); // Nullness Checker False Positive
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
        @Pure
        public String toString() {
            if (str != null) {
                return str;
            } else {
                return "null";
            }
        }
    }
}
