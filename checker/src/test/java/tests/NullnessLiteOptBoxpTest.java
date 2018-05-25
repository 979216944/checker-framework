package tests;

import java.io.File;
import java.util.List;
import org.checkerframework.framework.test.CheckerFrameworkPerDirectoryTest;
import org.junit.runners.Parameterized.Parameters;

/**
 * JUnit tests for the Nullness Checker -- testing {@code -ANullnessLite=mapk} command-line
 * argument.
 */
public class NullnessLiteOptBoxpTest extends CheckerFrameworkPerDirectoryTest {

    public NullnessLiteOptBoxpTest(List<File> testFiles) {

        super(
                testFiles,
                org.checkerframework.checker.nullness.NullnessChecker.class,
                "nullness",
                "-Anomsgtext",
                "-AstubWarnIfNotFound",
                "-ANullnessLite=boxp");
    }

    @Parameters
    public static String[] getTestDirs() {

        return new String[] {"nullness-liteoption-boxp"};
    }
}
