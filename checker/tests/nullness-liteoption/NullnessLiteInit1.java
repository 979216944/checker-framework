// This test aims to check whether the Nullness_Lite disables
// the uninitialized errors from the Initialization Checker;
//
// Uninitialized errors are from static fields uninitialized
// at declaration and non-static fields uninitialized in the
// constructor. The Nullness_Lite disallows these errors.
import org.checkerframework.checker.nullness.qual.Nullable;

public class NullnessLiteInit1 {
    static Object o1; // uninit error issued by the Nullness Checker
    static @Nullable Object o2;

    Object o3;
    @Nullable Object o4;

    public NullnessLiteInit1(int x, int y) {
        // uninit error issued by the Nullness Checker
        // :: error: (method.invocation.invalid)
        m();
    }

    public void m() {
        // although o3 is initialized here,
        // the Nullness Checker assumes the constructor
        // should initilaized all fields. What's more,
        // the Nullness Checker issue a unrelated error
        // to warn users for the side effects of calling
        // a helper method in the constructor.
        // The Nullness_Lite can do nothing about it.
        o3 = new Object();
    }
}
