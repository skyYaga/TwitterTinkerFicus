package eu.yaga;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TwitterClient {

    private static final Logger LOGGER = Logger.getLogger(TwitterClient.class.getName());
    private Twitter twitter = TwitterFactory.getSingleton();

    public void tweet(String message) {
        try {
            LOGGER.info("updating status...");
            twitter.updateStatus(message);
            LOGGER.info("status updated");
        } catch (
                TwitterException e) {
            LOGGER.log(Level.SEVERE, "Exception occurred while updating status", e);
        }
    }
}
