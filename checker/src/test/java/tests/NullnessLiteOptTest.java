package tests;

import java.io.File;
import java.util.List;
import org.checkerframework.framework.test.CheckerFrameworkPerDirectoryTest;
import org.junit.runners.Parameterized.Parameters;

/** JUnit tests for the Nullness Checker -- testing {@code -ANullnessLite} command-line argument. */
public class NullnessLiteOptTest extends CheckerFrameworkPerDirectoryTest {

    public NullnessLiteOptTest(List<File> testFiles) {

        super(
                testFiles,
                org.checkerframework.checker.nullness.NullnessChecker.class,
                "nullness",
                "-Anomsgtext",
                "-ANullnessLite");
    }

    @Parameters
    public static String[] getTestDirs() {

        return new String[] {"nullness-liteoption"};
    }
}
