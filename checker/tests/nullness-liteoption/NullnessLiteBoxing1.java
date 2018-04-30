import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;

/**
 * This class illustrates a correct implementation of the following assumption: Assume that boxing
 * of primitives is @Pure: it returns the same value on every call.
 *
 * <p>8 Boxed classes: Integer, Float, Long, Short, Character, Double, Boolean, Byte (Boolean and
 * Byte has valueOf as @Pure by default)
 */
public class NullnessLiteBoxing1 {

    public static void main(String[] args) {}

    void testIntegerWithPure(int x) {
        if (obj(Integer.valueOf(x)) != null) {
            obj(Integer.valueOf(x)).toString(); // warning not @Pure
        }
    }

    void testFloatWithPure(float f) {
        if (obj(Float.valueOf(f)) != null) {
            obj(Float.valueOf(f)).toString(); // warning not @Pure
        }
    }

    void testLongWithPure(long l) {
        if (obj(Long.valueOf(l)) != null) {
            obj(Long.valueOf(l)).toString(); // warning not @Pure
        }
    }

    void testShortWithPure(short s) {
        if (obj(Short.valueOf(s)) != null) {
            obj(Short.valueOf(s)).toString(); // warning not @Pure
        }
    }

    void testByteWithPure(byte b) {
        if (obj(Byte.valueOf(b)) != null) {
            obj(Byte.valueOf(b)).toString(); // no warning
        }
    }

    void testCharacterWithPure(char c) {
        if (obj(Character.valueOf(c)) != null) {
            obj(Character.valueOf(c)).toString(); // warning not @Pure
        }
    }

    void testDoubleWithPure(double d) {
        if (obj(Double.valueOf(d)) != null) {
            obj(Double.valueOf(d)).toString(); // warning not @Pure
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
