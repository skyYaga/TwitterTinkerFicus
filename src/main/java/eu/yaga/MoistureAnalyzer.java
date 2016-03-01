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
    private static int UPPER_BORDER;
    private static String OWNER;
    private StateEnum state = null;

    public MoistureAnalyzer(int border, String owner) {
        LOWER_BORDER = border - 25;
        UPPER_BORDER = border + 25;
        OWNER = owner;
    }

    /**
     * Analyze the current moisture value and return Tweet
     *
     * @param moisture current moisture value
     * @return message or null, if nothing changed
     */
    public String analyze(int moisture) {
        if (state == null) {
            if (moisture <= LOWER_BORDER) {
                state = StateEnum.BELOW_BORDER;
            } else if (moisture >= UPPER_BORDER) {
                state = StateEnum.ABOVE_BORDER;
            }
            LOGGER.info("Setting state to " + state);
        }

        LOGGER.info("Analyzing moisture (" + moisture + ") / last moisture: " + lastMoisture);

        int lastMoistureTmp = lastMoisture;
        lastMoisture = moisture;

        if (lastMoistureTmp != 0) {
            if (moisture <= LOWER_BORDER && lastMoistureTmp > LOWER_BORDER && state.equals(StateEnum.ABOVE_BORDER)) {
                LOGGER.info("Moisture recovery: below border (" + LOWER_BORDER + ").");
                state = StateEnum.BELOW_BORDER;
                LOGGER.info("Setting state to " + state);
                return TweetMessageGenerator.createRecoveryTweet(OWNER, moisture);
            }

            if (moisture > UPPER_BORDER && lastMoistureTmp <= UPPER_BORDER && state.equals(StateEnum.BELOW_BORDER)) {
                LOGGER.info("Moisture alert: (>" + UPPER_BORDER + ")");
                state = StateEnum.ABOVE_BORDER;
                LOGGER.info("Setting state to " + state);
                return TweetMessageGenerator.createPourTweet(OWNER, moisture);
            }
        }

        return null;
    }
}
