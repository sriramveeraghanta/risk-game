package utils;

/*
 * This is a simple Constants file,
 * where all the constants in the project are specified in this class.
 * 
 * */
public class GameConstant {
	// Constant Variables across the project
	public static final int MAXIMUM_NUMBER_OF_PLAYERS = 6;
	public static final int MINIMUM_NUMBER_OF_PLAYERS = 2;
	// Constant Strings
	public static final String PROJECT_TITLE = new String("Risk - SOEN 6441 Porject - Group # 40");
	public static final String GAME_TITLE = new String("Risk Game");
	// Button Title Strings
	public static final String NEW_GAME_BUTTON_TITLE = new String("New Game");
	public static final String EXIT_GAME_BUTTON_TITLE = new String("Exit Game");
	// some other string
	public static final String SELECT_PLAYERS = new String("Select the number of players");
	public static final String PLACE_ARMY = new String("Place your armies in any of your countries");
	// TODO: Please make changes in the below statements, just like the above modifications
	public String select_attacker_country="Select one of your countries for attacking";
	public String select_defender_country="Select one of your adjacent countries for attacking";
	public String number_of_dices="Select the number of dices";
	public String roll_dice="Rolling dices";
	public String attacker_win="Attacker wins";
	public String defender_win="Defender wins";

	public GameConstant() {
			
	}

}
