package eu.yaga;

/**
 * Represents the last state (change) of the plant
 * LAST_BELOW_BORDER means that the last major state was below the border
 * LAST_ABOVE_BORDER means that the last tweet state was above the border
 */
public enum StateEnum {
    LAST_BELOW_BORDER, LAST_ABOVE_BORDER, UNKNOWN
}
