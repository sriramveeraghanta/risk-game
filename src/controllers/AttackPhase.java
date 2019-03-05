package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import models.CardModel;
import models.ContinentModel;
import models.CountryModel;
import models.GameModel;
import models.PlayerModel;
import models.UnitModel;
/**
 * 
 * This class include all the methods that the game needs for attacking like rolling dice and occupy countries 
 *
 */
public class AttackPhase {

	private PlayerModel attackPlayer;
	private PlayerModel defendPlayer;
	private StartUp startUp;
	public Integer[] diceArrayAttacker = new Integer[getNumberAttackerDice()];
	public Integer[] diceArrayDefender = new Integer[getNumberAttackerDice() - 1];
	int maxNumberDiceAttacker;
	int maxNumberDiceDefender;
	private int numberAttackerDice;
	private CountryModel countryAttacker;
	private CountryModel countryDefender;
	int numberArmiesAttackerCountry = countryAttacker.getNumberOfUnits();
	int numberArmiesDefenderCountry = countryDefender.getNumberOfUnits();
	private int roll;
	private Integer[] diceCount; /// It will have number of DiceModel
	private Random die;
	private String attackerCountryName;
	private String defenderCountryName;

	/**
	 * Constructor
	 * @param attackPlayer is the player whose turn is
	 * @param startUp we need to get player and cards from startup phase 
	 * @param attackerCountryName is needed to access to armies in the attacker country
	 * @param defenderCountryName is needed to access to armies in the defender country
	 */
	public AttackPhase(PlayerModel attackPlayer, StartUp startUp, String attackerCountryName,
			String defenderCountryName) {
		
		this.attackPlayer = attackPlayer;
		this.startUp = startUp;
		this.attackerCountryName = attackerCountryName;
		this.defenderCountryName = defenderCountryName;
		
		List<CountryModel> countries = this.attackPlayer.getCountries();

		CountryModel countryAttacker = countries.stream()
				.filter(c -> c.getCountryName().contentEquals(attackerCountryName)).findFirst().get();

		// defender

		this.defendPlayer = this.getDefender(defenderCountryName);

		List<CountryModel> defenderCountries = this.defendPlayer.getCountries();

		this.countryDefender = defenderCountries.stream()
				.filter(c -> c.getCountryName().contentEquals(defenderCountryName)).findFirst().get();
		this.countryAttacker = countryAttacker;

	}
	/**
	 * getting the number of attacker dice
	 * @return numberAttackerDice 
	 */
	public int getNumberAttackerDice() {
		return numberAttackerDice;
	}
	/**
	 * setting number of attacker dice 
	 * @param numberAttackerDice
	 */
	public void setNumberAttackerDice(int numberAttackerDice) {
		this.numberAttackerDice = numberAttackerDice;
	}
	/**
	 * getting the country attacker 
	 * @return countryAttacker
	 */
	public CountryModel getCountryAttacker() {
		return countryAttacker;
	}
	/**
	 * setting the country attacker
	 * @param countryAttacker
	 */
	public void setCountryAttacker(CountryModel countryAttacker) {
		this.countryAttacker = countryAttacker;
	}
	/**
	 * getting the country defender
	 * @return countryDefender
	 */
	public CountryModel getCountryDefender() {
		return countryDefender;

	}
	/**
	 * setting the country defender
	 * @param countryDefender
	 */
	public void setcountryDefender(CountryModel countryDefender) {
		this.countryDefender = countryDefender;
	}

	/**
	 * checking if the number of armies for that country (the selected attacker
	 * country ) is more than 1
	 */
	public boolean checkIfPlayerCanAttackCountry() {
		if (countryAttacker.getNumberOfUnits() < 2) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * rolling the dices for both attacker and defender
	 */
	public void rollDice() {
		diceArrayAttacker = roll(getNumberAttackerDice());
		diceArrayDefender = roll(getNumberAttackerDice() - 1);
	}

	/**
	 * check the dices and compare the dices number and find out the loser and the
	 * winner in attacking
	 */
	public void attackingResult() {
		for (int i = 0; i < diceArrayDefender.length; i++) {
			if (diceArrayAttacker[i] < diceArrayDefender[i]) {
				numberArmiesAttackerCountry--;
				
			} else {
				numberArmiesDefenderCountry--;
			}
		}
		//if attacker looses
		if(numberArmiesAttackerCountry==0) {
			this.assignCardToPlayer(this.defendPlayer);
			this.assignCountryToWinnerPlayer(this.defendPlayer, this.attackPlayer, this.attackerCountryName);
			this.assignRemainingCardsToWinnerPlayer(this.defendPlayer, this.attackPlayer);
			
		}
		//if defender looses
		if(numberArmiesDefenderCountry==0) {
			this.assignCardToPlayer(this.attackPlayer);
			this.assignCountryToWinnerPlayer(this.attackPlayer, this.defendPlayer, this.defenderCountryName);
			this.assignRemainingCardsToWinnerPlayer(this.attackPlayer, this.defendPlayer);
			
		}
	}

	/**
	 * checking the number of dices which attacker can roll based on the number of
	 * armies for that country
	 */
	public void checkMaxNumberOfDices() {
		if (numberArmiesAttackerCountry > 3) {
			maxNumberDiceAttacker = 3;
		} else {
			maxNumberDiceAttacker = numberArmiesAttackerCountry - 1;
		}
		if (numberArmiesDefenderCountry >= 2) {
			maxNumberDiceDefender = 2;
		} else {
			maxNumberDiceDefender = numberArmiesDefenderCountry;
		}
	}
	/**
	 * rolling the dice by getting the number of dices 
	 * @param numberOfDice
	 * @return an array of dice face numbers which is sorted 
	 */
	public Integer[] roll(int numberOfDice) {
		diceCount = new Integer[numberOfDice];
		for (int i = 0; i < diceCount.length; i++) {
			die = new Random();
			roll = die.nextInt(5) + 1;
			diceCount[i] = roll;
		}
		// Sorts in descending order
		Arrays.sort(diceCount);
		Arrays.sort(diceCount, Collections.reverseOrder());

		return diceCount;
	}
	/**
	 * adding or removing the country to or from the countrylist after battle 
	 * @param winnerPlayer 
	 * @param loserPlayer
	 * @param countryName
	 */
	private void assignCountryToWinnerPlayer(PlayerModel winnerPlayer, PlayerModel loserPlayer, String countryName) {
		// loser
		List<CountryModel> loserCountries = loserPlayer.getCountries();
		CountryModel countryWon = loserCountries.stream().filter(c -> c.getCountryName().contentEquals(countryName))
				.findFirst().get();
		loserCountries.remove(countryWon);
		// Winner
		List<CountryModel> winnerCountries = winnerPlayer.getCountries();
		winnerCountries.add(countryWon);

	}
	/**
	 * checking if the loser do not have any other countries and own any card then it should be given to the winner player 
	 * @param winnerPlayer
	 * @param loserPlayer
	 */
	private void assignRemainingCardsToWinnerPlayer(PlayerModel winnerPlayer, PlayerModel loserPlayer) {
		if (loserPlayer.getCountries() != null || loserPlayer.getCountries().size() == 0) {
			if (loserPlayer.getDeck() != null || loserPlayer.getDeck().size() > 0) {
				ArrayList<CardModel> winnerDeck = winnerPlayer.getDeck();
				winnerDeck.addAll(loserPlayer.getDeck());
				loserPlayer.setActive(false);
			}
		}
	}
	/**
	 * finding the defender player by getting the country name 
	 * @param defenderCountryName
	 * @return the player who is defending
	 */
	private PlayerModel getDefender(String defenderCountryName) {
		for (PlayerModel defender : this.startUp.getPlayers()) {
			if (defender.getColor() != this.attackPlayer.getColor()) {
				List<CountryModel> countries = defender.getCountries();
				List<String> countryNames = countries.stream().map(c -> c.getCountryName())
						.collect(Collectors.toList());
				if (countryNames.contains(defenderCountryName)) {
					return defender;
				}
			}
		}
		return null;
	}
	/**
	 * assign the cards to the player (deck) by getting the player as the parameter
	 * @param player
	 */
	public void assignCardToPlayer(PlayerModel player) {

		int index = new Random().nextInt(startUp.getCards().size());
		ArrayList<CardModel> deck = player.getDeck();
		deck.add(startUp.getCards().get(index));
		player.setDeck(deck);
	}
}