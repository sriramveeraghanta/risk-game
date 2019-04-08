package main.utills;

/**
 * Enum class for declaring different types
 *  of armies and player respectively
 *
 */
public class EnumHandler {
     /**
     * UnitType is used in UnitModel class to store army data
     */
    public enum CardType {
        INFANTRY, CAVALRY, ARTILLERY
    }
    /**
     * color is the color which is assigned to the players
     */
    public enum Color {
        BLACK, BLUE, GREEN, PINK, RED, PURPLE
    }
}
