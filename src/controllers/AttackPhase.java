package controllers;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import models.CountryModel;

public class AttackPhase {

	Integer[] diceArrayAttacker = new Integer[getNumberAttackerDice()];
	Integer[] diceArrayDefender = new Integer[getNumberAttackerDice() - 1];
	int maxNumberDiceAttacker;
	int maxNumberDiceDefender;
	private CountryModel countryAttacker;
	private CountryModel countryDefender;
	private int numberAttackerDice;
	int numberArmiesAttackerCountry = countryAttacker.getNumberOfUnits();
	int numberArmiesDefenderCountry = countryDefender.getNumberOfUnits();
	private int roll;
	private Integer[] diceCount; /// It will have number of DiceModel
	private Random die;

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
	public void checkEligibility() {
		if (countryAttacker.getNumberOfUnits() < 2) {
			// ERROR "You do not have enough army for attacking, Select another country that
			// has more that 1 army"
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
}