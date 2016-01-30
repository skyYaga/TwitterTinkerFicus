package eu.yaga;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for the TweetMessageGenerator
 */
public class TweetMessageGeneratorTest {

    @Test
    public void testRandomPourTweet() {
        String message = TweetMessageGenerator.createPourTweet("nobody", 1000);
        Assert.assertTrue(message.contains("@nobody"));
        Assert.assertTrue(message.contains("1000"));
    }

    @Test
    public void testRandomRecoveryTweet() {
        String message = TweetMessageGenerator.createRecoveryTweet("nobody", 1000);
        Assert.assertTrue(message.contains("@nobody"));
        Assert.assertTrue(message.contains("1000"));
    }
}
