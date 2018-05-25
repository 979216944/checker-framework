// If NullnessLite issues errors at f, meaning NullnessLite has defects,
// then this test is meaningless before defects being fixed.
//
// If NullnessLite issues errors in m1(),
// then -AsuppressWarnings is shadowed by NullnessLite option.
//
// If NullnessLite issues errors in m2(),
// then -Astubs is shadowed by NullnessLite option.
//
// If NullnessLite issues errors in m3(),
// then there a defect in Nullness Checker defining nullness_lite.astub

import java.lang.reflect.Array;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;

public class CommandLineShadowRegTest {
    public static @Nullable Object f;

    public CommandLineShadowRegTest() {
        m4(12);
    }

    public void m2() {
        @Nullable String[] a = null;
        Array.getLength(a);
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
