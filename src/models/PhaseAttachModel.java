package models;

/**
 * 
 * @author Sharareh_Keshavarzi
 *
 */
public class PhaseAttachModel {
	
	Integer[] diceArrayAttacker = new Integer[get_num_attacker_dice()];
	Integer[] diceArrayDefender = new Integer[get_num_defender_dice()];
	int maxNumberDiceAttacker;
	int maxNumberDiceDefender;
	private CountryModel country_Attacker;
	private CountryModel country_Defender;
	private int num_attacker_dice;
	private int num_defender_dice;
//	int NumArmiesAttackerCountry=country_Attacker.getNumberOfInfantry();
//	int NumArmiesDefenderCountry=country_Defender.getNumberOfInfantry();
	
	
	public int get_num_attacker_dice() {
		return num_attacker_dice;
	}
	
	public void set_num_attacker_dice(int num_attacker_dice) {
		this.num_attacker_dice=num_attacker_dice;
	}
	
	public int get_num_defender_dice() {
		return num_defender_dice;
	}
	public void set_num_defender_dice(int num_defender_dice) {
		this.num_defender_dice=num_defender_dice;
	}
	
	public CountryModel getCountry_Attacker() {
		return country_Attacker;
	}
	
	public void setCountry_Attacker(CountryModel country_Attacker) {
		this.country_Attacker = country_Attacker;
	}
	
	public CountryModel getCountry_Defender() {
		return country_Defender;

	}
	
	public void setCountry_Defender(CountryModel country_Defender) {
		this.country_Defender = country_Defender;
	}
	
	/**
	 * checking if the number of armies for that country (the selected attacker country ) is more than 1  
	 */
	public void checkEligibility() {
//		if(country_Attacker.getNumberOfInfantry()<2) {
//			//ERROR "You do not have enough army for attacking, Select another country that has more that 1 army"
//		}
	}
	
	/**
	 * rolling the dices for both attacker and defender
	 */
	public void rollDice() {
		
		DiceModel dice=new DiceModel();
		diceArrayAttacker=dice.roll(get_num_attacker_dice());
		diceArrayDefender=dice.roll(get_num_defender_dice());
	}
	/**
	 * check the dices and compare the dices number and find out the loser and the winner in attacking
	 */
	public void attackingResult() {
		for(int i=0; i<diceArrayDefender.length;i++) {
//			if (diceArrayAttacker[i]<diceArrayDefender[i]) {
//				NumArmiesAttackerCountry--;
//			}
//			else {
//				NumArmiesDefenderCountry--;
//			}
		}
	}
	/**
	 * checking the number of dices which attacker can roll based on the number of armies for that country
	 */
	public void checkMaxNumberOfDices() {
//		if(NumArmiesAttackerCountry>3) {
//			maxNumberDiceAttacker=3;
//		}
//		else {
//			maxNumberDiceAttacker=NumArmiesAttackerCountry-1;
//		}
//		if(NumArmiesDefenderCountry>=2) {
//			maxNumberDiceDefender=2;
//		}
//		else {
//			maxNumberDiceDefender=NumArmiesDefenderCountry;
//		}
		
	}
	
}
