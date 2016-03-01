package eu.yaga;

/**
 * Represents the last state (change) of the plant
 * BELOW_BORDER means that the last tweet was "recovery"
 * ABOVE_BORDER means that the last tweet was "pouring needed"
 */
public enum StateEnum {
    BELOW_BORDER, ABOVE_BORDER
}
