package tests;

import java.io.File;
import java.util.List;
import org.checkerframework.framework.test.CheckerFrameworkPerDirectoryTest;
import org.junit.runners.Parameterized.Parameters;

/**
 * JUnit tests for the Nullness Checker -- testing {@code -ANullnessLite=inva} command-line
 * argument.
 */
public class NullnessLiteOptInvaTest extends CheckerFrameworkPerDirectoryTest {

    public NullnessLiteOptInvaTest(List<File> testFiles) {

        super(
                testFiles,
                org.checkerframework.checker.nullness.NullnessChecker.class,
                "nullness",
                "-Anomsgtext",
                "-AstubWarnIfNotFound",
                "-ANullnessLite=inva");
    }

    @Parameters
    public static String[] getTestDirs() {

        return new String[] {"nullness-liteoption-inva"};
    }
}
