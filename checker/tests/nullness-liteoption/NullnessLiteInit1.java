import org.checkerframework.checker.nullness.qual.NonNull;

public class NullnessLiteInit1 {
    private @NonNull Object f;

    public NullnessLiteInit1(int x, int y) {}

    public NullnessLiteInit1(int x) {
        // :: error: (dereference.of.nullable)
        this.f.toString();
    }

    public NullnessLiteInit1(int x, int y, int z) {
        // :: error: (method.invocation.invalid)
        m();
    }

    public void m() {
        this.f.toString();
    }
}
