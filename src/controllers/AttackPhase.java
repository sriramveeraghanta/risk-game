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

public class AttackPhase {

	private PlayerModel attackPlayer;
	private PlayerModel defendPlayer;
	private PlayerController startUp;
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

	public AttackPhase(PlayerModel attackPlayer, PlayerController startUp, String attackerCountryName,
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

	public int getNumberAttackerDice() {
		return numberAttackerDice;
	}

	public void setNumberAttackerDice(int numberAttackerDice) {
		this.numberAttackerDice = numberAttackerDice;
	}

	public CountryModel getCountryAttacker() {
		return countryAttacker;
	}

	public void setCountryAttacker(CountryModel countryAttacker) {
		this.countryAttacker = countryAttacker;
	}

	public CountryModel getCountryDefender() {
		return countryDefender;

	}

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

	private void assignRemainingCardsToWinnerPlayer(PlayerModel winnerPlayer, PlayerModel loserPlayer) {
		if (loserPlayer.getCountries() != null || loserPlayer.getCountries().size() == 0) {
			if (loserPlayer.getDeck() != null || loserPlayer.getDeck().size() > 0) {
				ArrayList<CardModel> winnerDeck = winnerPlayer.getDeck();
				winnerDeck.addAll(loserPlayer.getDeck());
				loserPlayer.setActive(false);
			}
		}
	}

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

	public void assignCardToPlayer(PlayerModel player) {

		int index = new Random().nextInt(startUp.getCards().size());
		ArrayList<CardModel> deck = player.getDeck();
		deck.add(startUp.getCards().get(index));
		player.setDeck(deck);
	}
}