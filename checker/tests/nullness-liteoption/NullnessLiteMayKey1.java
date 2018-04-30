import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.KeyFor;
import org.checkerframework.checker.nullness.qual.NonNull;
import java.util.Map;
import java.util.HashMap;

/**
 * This class illustrates use of keyfor type annotations. Since the Nullness Checker has no dataflow
 * analysis for Map.put() method else where, 1) it does not warn for all Map.get(key) method if keys
 * are labeled with @KeyFor(); 2) it warns for Map.get(key) where elements are put in the map
 * elsewhere, say not within the same function.
 *
 * <p>NullnessLite: No map key analysis; assume that, at every call to Map.get, the given key
 * appears in the map. tests: 1) keyfor error still exists 2) the result of map.get() is always
 * non-null.
 */
public class NullnessLiteMapKey1 {

    // 1) keyfor error still exists
    void test1() {
        Map<@Nullable String, @Nullable String> m =
                new HashMap<@Nullable String, @Nullable String>();
        Map<String, @Nullable String> n = new HashMap<String, @Nullable String>();

        @KeyFor("m") String km = new @KeyFor("m") String("km");
        @KeyFor("n") String kn = new @KeyFor("n") String("kn");
        @KeyFor({"m", "n"}) String kmn = new @KeyFor({"m", "n"}) String("kmn");

        km = kmn;
        // :: error: (assignment.type.incompatible)
        km = kn;
    }

    // 2) the result of map.get() is always non-null.
    void test2() {
        Map<@Nullable String, @Nullable String> m =
                new HashMap<@Nullable String, @Nullable String>();
        Map<String, @Nullable String> n = new HashMap<String, @Nullable String>();

        @KeyFor("m") String in = new @KeyFor("m") String("in");
        String notin = "notin";
        //        m.put(in, in);
        //        n.put(in, in);

        m.get(in).toString(); // OK - a key for map m
        m.get(notin).toString(); // NullnessLite ON = no error
    }

    void foo(Map<String, String> m, String in) {
        m.put(in, in);
        return;
    }

    void showFalseWarnings() {
        Map<String, String> m = new HashMap<String, String>();

        @KeyFor("m") String notin = new @KeyFor("m") String("in");
        String in = "in";

        foo(m, in);

        m.get(notin).toString(); // error but no warning
        m.get(in).toString(); // Ok but a warning
    }

    void wierdKeyFor() {
        Map<String, String> m = new HashMap<String, String>();

        String in = "in";
        foo(m, in);

        m.get(in).toString(); // Ok but a warning
    }
}
