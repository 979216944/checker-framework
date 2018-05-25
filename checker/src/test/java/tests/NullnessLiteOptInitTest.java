package tests;

import java.io.File;
import java.util.List;
import org.checkerframework.framework.test.CheckerFrameworkPerDirectoryTest;
import org.junit.runners.Parameterized.Parameters;

/**
 * JUnit tests for the Nullness Checker -- testing {@code -ANullnessLite=init} command-line
 * argument.
 */
public class NullnessLiteOptInitTest extends CheckerFrameworkPerDirectoryTest {

    public NullnessLiteOptInitTest(List<File> testFiles) {

        super(
                testFiles,
                org.checkerframework.checker.nullness.NullnessChecker.class,
                "nullness",
                "-Anomsgtext",
                "-AstubWarnIfNotFound",
                "-ANullnessLite=init");
    }

    @Parameters
    public static String[] getTestDirs() {

        return new String[] {"nullness-liteoption-init"};
    }
}
