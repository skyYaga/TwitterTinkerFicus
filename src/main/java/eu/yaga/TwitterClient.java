package eu.yaga;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TwitterClient {

    static Logger logger = Logger.getLogger(TwitterClient.class.getName());
    static Twitter twitter = TwitterFactory.getSingleton();

    public static void tweet(String message) {
        try {
            logger.info("updating status...");
            twitter.updateStatus("This is an awesome test tweet!");
            logger.info("status updated");
        } catch (
                TwitterException e) {
            logger.log(Level.SEVERE, "Exception occurred while updating status", e);
        }
    }
}
