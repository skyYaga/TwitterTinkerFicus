package eu.yaga;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Tests for the MoistureAnalyzer
 */
public class MoistureAnalyzerTest {

    private MoistureAnalyzer analyzer;
    private static final String OWNER = "nobody";

    @Before
    public void setUp() {
        analyzer = new MoistureAnalyzer(1000, OWNER);
    }

    @Test
    public void testNothingChanged() {
        String message = analyzer.analyze(0);
        Assert.assertEquals(null, message);
    }


    @Test
    public void testRecovery() {
        // Just got poured
        analyzer.analyze(2000);
        String message = analyzer.analyze(500);
        Assert.assertEquals(String.class, message.getClass());

        String check1 = String.format(TweetMessageGenerator.RECOVERY_1, OWNER, 500);
        String check2 = String.format(TweetMessageGenerator.RECOVERY_2, OWNER, 500);
        String check3 = String.format(TweetMessageGenerator.RECOVERY_3, OWNER, 500);

        Assert.assertTrue(Arrays.asList(check1, check2, check3).contains(message));
    }

    @Test
    public void testPouringNeeded() {
        // pouring needed
        analyzer.analyze(500);
        String message = analyzer.analyze(2000);
        Assert.assertEquals(String.class, message.getClass());

        String check1 = String.format(TweetMessageGenerator.POUR_1, OWNER, 2000);
        String check2 = String.format(TweetMessageGenerator.POUR_2, OWNER, 2000);
        String check3 = String.format(TweetMessageGenerator.POUR_3, OWNER, 2000);

        Assert.assertTrue(Arrays.asList(check1, check2, check3).contains(message));
    }
}
