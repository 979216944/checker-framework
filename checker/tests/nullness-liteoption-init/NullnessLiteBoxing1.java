import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;

/**
 * This class illustrates a correct implementation of "-ANullnessLite=inva": the following
 * assumption is not enabled: Assume that boxing of primitives is @Pure: it returns the same value
 * on every call.
 *
 * <p>8 Boxed classes: Integer, Float, Long, Short, Character, Double, Boolean, Byte (Boolean and
 * Byte has valueOf as @Pure by default)
 */
public class NullnessLiteBoxing1 {

    public static void main(String[] args) {}

    // Map key analysis could work with boxing assumptions
    public void testinvaeyChecker(double key, Map<Double, Object> m) {
        if (m.get(Double.valueOf(key)) != null) {
            // :: error: (dereference.of.nullable)
            m.get(Double.valueOf(key)).toString(); // "ANullnessLite=init" ON = error
        }
    }

    void testIntegerWithPure(int x) {
        if (obj(Integer.valueOf(x)) != null) {
            // NullnessLite ON = no error
            // :: error: (dereference.of.nullable)
            obj(Integer.valueOf(x)).toString(); // "ANullnessLite=init" = error not @Pure
        }
    }

    void testFloatWithPure(float f) {
        if (obj(Float.valueOf(f)) != null) {
            // NullnessLite ON = no error
            // :: error: (dereference.of.nullable)
            obj(Float.valueOf(f)).toString(); // "ANullnessLite=init" = error not @Pure
        }
    }

    void testLongWithPure(long l) {
        if (obj(Long.valueOf(l)) != null) {
            // NullnessLite ON = no error
            // :: error: (dereference.of.nullable)
            obj(Long.valueOf(l)).toString(); // "ANullnessLite=init" = error not @Pure
        }
    }

    void testShortWithPure(short s) {
        if (obj(Short.valueOf(s)) != null) {
            // NullnessLite ON = no error
            // :: error: (dereference.of.nullable)
            obj(Short.valueOf(s)).toString(); // "ANullnessLite=init" = error not @Pure
        }
    }

    void testByteWithPure(byte b) {
        if (obj(Byte.valueOf(b)) != null) {
            obj(Byte.valueOf(b)).toString(); // no warning
        }
    }

    void testCharacterWithPure(char c) {
        if (obj(Character.valueOf(c)) != null) {
            // NullnessLite ON = no error
            // :: error: (dereference.of.nullable)
            obj(Character.valueOf(c)).toString(); // "ANullnessLite=init" = error not @Pure
        }
    }

    void testDoubleWithPure(double d) {
        if (obj(Double.valueOf(d)) != null) {
            // NullnessLite ON = no error
            // :: error: (dereference.of.nullable)
            obj(Double.valueOf(d)).toString(); // "ANullnessLite=init" = error not @Pure
        }
    }

    void testBooleanWithPure(boolean b) {
        if (obj(Boolean.valueOf(b)) != null) {
            obj(Boolean.valueOf(b)).toString(); // no warning
        }
    }

    @Pure
    @Nullable Object obj(int i) {
        return null;
    }

    @Pure
    @Nullable Object obj(float f) {
        return null;
    }

    @Pure
    @Nullable Object obj(long l) {
        return null;
    }

    @Pure
    @Nullable Object obj(short s) {
        return null;
    }

    @Pure
    @Nullable Object obj(byte b) {
        return null;
    }

    @Pure
    @Nullable Object obj(char c) {
        return null;
    }

    @Pure
    @Nullable Object obj(double d) {
        return null;
    }

    @Pure
    @Nullable Object obj(boolean b) {
        return null;
    }
}
