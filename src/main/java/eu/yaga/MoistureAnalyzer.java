package eu.yaga;

import java.util.logging.Logger;

/**
 * Analyzes if the moisture has fallen below the border
 * or recovered after being fallen below that border
 */
public class MoistureAnalyzer {
    private static final Logger LOGGER = Logger.getLogger(MoistureAnalyzer.class.getName());

    private int lastMoisture = 0;
    private static int LOWER_BORDER;
    private static String OWNER;

    public MoistureAnalyzer(int border, String owner) {
        LOWER_BORDER = border;
        OWNER = owner;
    }

    /**
     * Analyze the current moisture value and return Tweet
     *
     * @param moisture current moisture value
     * @return message or null, if nothing changed
     */
    public String analyze(int moisture) {
        LOGGER.info("Analyzing moisture (" + moisture + ") / last moisture: " + lastMoisture);

        int lastMoistureTmp = lastMoisture;
        lastMoisture = moisture;

        if (lastMoistureTmp != 0) {
            if (moisture <= LOWER_BORDER && lastMoistureTmp > LOWER_BORDER) {
                LOGGER.info("Moisture has fallen below border (" + LOWER_BORDER + ").");
                return TweetMessageGenerator.createPourTweet(OWNER, moisture);
            }

            if (moisture > LOWER_BORDER && lastMoistureTmp <= LOWER_BORDER) {
                LOGGER.info("Moisture recovered (>" + LOWER_BORDER + ")");
                return TweetMessageGenerator.createRecoveryTweet(OWNER, moisture);
            }
        }

        return null;
    }
}