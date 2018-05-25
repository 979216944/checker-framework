package tests;

import java.io.File;
import java.util.List;
import org.checkerframework.framework.test.CheckerFrameworkPerDirectoryTest;
import org.junit.runners.Parameterized.Parameters;

/**
 * JUnit tests for the Nullness Checker -- testing {@code -ANullnessLite=boxp} command-line argument
 * that will work with "Astubs=<some stub files></>".
 */
public class NullnessLiteComRegBoxpTest extends CheckerFrameworkPerDirectoryTest {

    public NullnessLiteComRegBoxpTest(List<File> testFiles) {

        super(
                testFiles,
                org.checkerframework.checker.nullness.NullnessChecker.class,
                "nullness",
                "-Anomsgtext",
                "-AstubWarnIfNotFound",
                "-ANullnessLite=boxp",
                "-AsuppressWarnings=method.invocation.invalid",
                "-Astubs="
                        + "tests/nullness-liteoption-comreg-boxp/stub1.astub:"
                        + "tests/nullness-liteoption-comreg-boxp/stub2.astub");
    }

    @Parameters
    public static String[] getTestDirs() {
        return new String[] {"nullness-liteoption-comreg-boxp"};
    }
}
