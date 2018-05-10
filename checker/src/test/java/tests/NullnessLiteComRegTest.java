package tests;

import java.io.File;
import java.util.List;
import org.checkerframework.framework.test.CheckerFrameworkPerDirectoryTest;
import org.junit.runners.Parameterized.Parameters;

/**
 * JUnit tests for the Nullness Checker -- testing {@code -ANullnessLite} command-line argument that
 * will not cover other user commands.
 */
public class NullnessLiteComRegTest extends CheckerFrameworkPerDirectoryTest {

    public NullnessLiteComRegTest(List<File> testFiles) {

        super(
                testFiles,
                org.checkerframework.checker.nullness.NullnessChecker.class,
                "nullness",
                "-Anomsgtext",
                "-AstubWarnIfNotFound",
                "-ANullnessLite",
                "-AsuppressWarnings=method.invocation.invalid",
                "-Astubs=" + "tests/nullness-liteoption-comreg/issue5.astub");
    }

    @Parameters
    public static String[] getTestDirs() {
        return new String[] {"nullness-liteoption-comreg"};
    }
}
