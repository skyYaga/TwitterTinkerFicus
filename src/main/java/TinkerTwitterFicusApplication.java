import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.util.logging.Logger;

public class TinkerTwitterFicusApplication {

    private Logger logger = Logger.getLogger(TinkerTwitterFicusApplication.class.getName());

    public static void main(String[] args) {
        new TinkerTwitterFicusApplication().start();
    }

    private void start() {
        logger.info("Application started");

        Twitter twitter = TwitterFactory.getSingleton();

        try {
            twitter.updateStatus("");
        } catch (TwitterException e) {
            e.printStackTrace();
        }

    }
}
