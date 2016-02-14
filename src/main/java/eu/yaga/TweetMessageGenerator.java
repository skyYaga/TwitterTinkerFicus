package eu.yaga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

/**
 * This Class generates Random Tweets to make the Application more diverse
 */
public class TweetMessageGenerator {

    private static final Logger LOGGER = Logger.getLogger(TweetMessageGenerator.class.getName());

    public static final String POUR_1 = "Hey @%s, I need a shower (moisture: %d) #FicusLife";
    public static final String POUR_2 = "I'm so thirsty! @%s, gimme a drink! (moisture: %d) #FicusLife";
    public static final String POUR_3 = "There's more water in the desert than in my soil. @%s, pour me! (moisture: %d) #FicusLife";

    public static final String RECOVERY_1 = "I'm singing in the rain... @%s (moisture: %d) #FicusLife";
    public static final String RECOVERY_2 = "Thanks for the drink @%s! Tasted like pure awesomeness (moisture: %d) #FicusLife";
    public static final String RECOVERY_3 = "Damn, that shower was f*ckin' nice @%s (moisture: %d) #FicusLife";

    public static final String INFO_1 = "Yo people, what's going on? I'm feeling awesome, enjoying #FicusLife (moisture: %d)";
    public static final String INFO_2 = "It's time for a service tweet. My current moisture value is %d #FicusLife";
    public static final String INFO_3 = "OMG!!! What was that? Ah, nothing... (moisture: %d) #FicusLife";

    /**
     * Create a random tweet message asking the owner to pour
     *
     * @param owner Twitter username of the plant's owner
     * @param moisture current moisture value
     * @return random pour tweet
     */
    public static String createPourTweet(String owner, int moisture) {
        ArrayList<String> pourTweets = new ArrayList<String>(Arrays.asList(POUR_1, POUR_2, POUR_3));
        int tweetNr = ThreadLocalRandom.current().nextInt(0, pourTweets.size() - 1);
        String message = String.format(pourTweets.get(tweetNr), owner, moisture);

        LOGGER.info("Generated tweet message: " + message);

        return message;
    }

    /**
     * Create a random tweet message thanking the owner for pouring
     *
     * @param owner Twitter username of the plant's owner
     * @param moisture current moisture value
     * @return random thank you tweet
     */
    public static String createRecoveryTweet(String owner, int moisture) {
        ArrayList<String> pourTweets = new ArrayList<String>(Arrays.asList(RECOVERY_1, RECOVERY_2, RECOVERY_3));
        int tweetNr = ThreadLocalRandom.current().nextInt(0, pourTweets.size() - 1);
        String message = String.format(pourTweets.get(tweetNr), owner, moisture);

        LOGGER.info("Generated tweet message: " + message);

        return message;
    }

    /**
     * Create a random info message publishing the current moisture value
     * @param moisture current moisture value
     * @return random info tweet
     */
    public static String createStatusTweet(int moisture) {
        ArrayList<String> pourTweets = new ArrayList<String>(Arrays.asList(INFO_1, INFO_2, INFO_3));
        int tweetNr = ThreadLocalRandom.current().nextInt(0, pourTweets.size() - 1);
        String message = String.format(pourTweets.get(tweetNr), moisture);

        LOGGER.info("Generated tweet message: " + message);

        return message;
    }
}
