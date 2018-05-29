import java.lang.String;
import java.lang.reflect.Array;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;

/**
 * This test case illustrates "-ANullnessLite=boxp" with user passed in a command "-Astub=<some stub
 * files>"
 *
 * <p>Possible errors: If NullnessLite issues errors at f, meaning NullnessLite has defects, then
 * this test is meaningless before defects being fixed.
 *
 * <p>If NullnessLite issues errors in the constructor, then -Astubs is shadowed by NullnessLite option.
 *
 * <p>If NullnessLite issues errors in m2(), then -Astubs is shadowed by NullnessLite option.
 *
 * <p>If NullnessLite issues errors in m3(), then there a defect in Nullness Checker defining
 * nullness_lite.astub
 */
public class CommandLineShadowRegBoxpTest {
    public static @Nullable Object f;

    public CommandLineShadowRegBoxpTest() {
        m4(12);
    }

    public void m1() {
        @Nullable String[] a = null;
        Array.getLength(a);
    }

    public void m2() {
        @Nullable String foo = "foo";
        @Nullable String nullString = null;
        foo.replaceAll("foo", nullString);
    }

    public void m3() {
        if (m4(Integer.valueOf(10000)) != null) {
            m4(Integer.valueOf(10000)).toString();
        }
    }

    @Pure
    private @Nullable Object m4(int a) {
        return a;
    }
}
