package eu.yaga;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the MoistureAnalyzer
 */
public class MoistureAnalyzerTest {

    private MoistureAnalyzer analyzer;

    @Before
    public void setUp() {
        analyzer = new MoistureAnalyzer(1000, "nobody");
    }

    @Test
    public void testNothingChanged() {
        String message = analyzer.analyze(0);
        Assert.assertEquals(null, message);
    }

    @Test
    public void testBelowBorder() {
        analyzer.analyze(2000);
        String message = analyzer.analyze(500);
        Assert.assertEquals(String.class, message.getClass());
    }

    @Test
    public void testRecovery() {
        String message = analyzer.analyze(2000);
        Assert.assertEquals(String.class, message.getClass());
    }
}
