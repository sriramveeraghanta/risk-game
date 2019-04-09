package main.utills;

/**
 * this class contains the constatnt names and numbers in game
 */
public class GameConstants {
    // Constant Variables across the project
    public static final int MAXIMUM_NUMBER_OF_PLAYERS = 6;
    public static final int MINIMUM_NUMBER_OF_PLAYERS = 2;
    public static final int INITIAL_NUMBER_OF_UNITS = 0;

    // Constant Strings
    public static final String GAME_TITLE = "Risk Game";

    // some other string
    public static final String SELECT_PLAYERS = "Select the number of players";

    public static final String SAVE_GAME_CONFIRMATION = "Do you wish to save this game?";
    public static final String SAVE_GAME_CONFIRMATION_DESCRIPTION = "You can save the game and continue it later.";
    public static final String SAVE_GAME_ERROR = "Something Went Wrong, Your game is not saved.";
    public static final String LOAD_GAME_ERROR = "Something Went Wrong, This Game cant be loaded.";
    public static final String LOAD_GAME_SELECT_WARNING = "Please Select one file to load the game";

    // error Messages
    public static final String PLAYER_COUNT_ERROR = "Enter a valid Number-Players count should be between 2 and 6";
    public static final String INVALID_PLAYER_COUNT_ERROR = "Please Enter a valid player count between 2 to 6";
    public static final String INVALID_MAP_ERROR = "Enter a valid Map Data";
    public static final String INVALID_MAP_PLAYER_COUNT_ERROR = "Please select map file and enter player count to start new Game";
    public static final String VALID_MAP_FILE_SAVE = "Your map is saved start your new Game from home page";
    public static final String INVALID_TOURNAMENT_DATA = "Please select a map file, Number of turns count, number of games and select the players.";
    public static final String INVALID_NUMBER_TOURNAMENT = "Please enter a valid Integers for game Count and tun count";

    public static final String MAP_MSG="Do you want to Start a Game with new map ?";
    public static final String FORTIFY_INVALID_MSG = "Invalid Operation Data";
    public static final String FORTIFY_VALID_MSG = "Fortification Completed";
    public static final String ATTACK_ARMY_SWAP_INVALID_MSG = "You don't have enough army";
    public static final String ATTACK_ARMY_SWAP_VALID_MSG = "Army units swap completed";

    public static final String MAPS_DIR_PATH = System.getProperty("user.dir") + "/resources/maps/";
    public static final String SAVED_GAMES_PATH = System.getProperty("user.dir") + "/resources/saved_games/";
    public static final String USER_NO_INPUT = "Please enter all the fields to continue";
    // Splitters
    public static final String CONTINENT_DATA_SPLITTER = "=";
    public static final String COUNTRY_DATA_SPLITTER = ",";

    /**
     * constructor for game constants
     */
    public GameConstants() {

    }
}
