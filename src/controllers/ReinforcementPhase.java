package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import models.CardModel;
import models.ContinentModel;
import models.CountryModel;
import models.GameModel;
import models.PlayerModel;
import models.UnitModel;
import utils.EnumClass;

public class ReinforcementPhase {

	private PlayerModel playerModel;
	private GameModel gameModel;

	public ReinforcementPhase(PlayerModel playerModel, GameModel gameModel) {
		this.playerModel = playerModel;
		this.gameModel = gameModel;
	}

	public int swapCardsForArmyUnits() {
		int totalNumberOfArmyUnits = getNumberOfSimilarCards() + getNumberOfDifferentCards();
		if (totalNumberOfArmyUnits > 0) {
			this.playerModel.setSuccessfullCardSwapCounter(this.playerModel.getSuccessfullCardSwapCounter() + 1);

			return (totalNumberOfArmyUnits * 5) * this.playerModel.getSuccessfullCardSwapCounter();
		}
		
		return 0;
	}

	public int getArmyUnitsForConqueredContinent() {

		return this.validateNewContinentOccupation();
	}

	public int getArmyUnitsFromCountries() {
		int numberOfUnits = 0;

		int numberOfCountries = this.playerModel.getCountries().size();

		if (numberOfCountries < 11) {
			numberOfUnits = 3;
		} else {
			numberOfUnits = Math.floorDiv(numberOfCountries, 3);
		}

		return numberOfUnits;
	}

	public boolean assignArmyUnitToCountry(String countryName, int numberOfUnits) {
		List<CountryModel> countries = this.playerModel.getCountries();

		CountryModel country = countries.stream().filter(c -> c.getCountryName().contentEquals(countryName))
				.findFirst().get();
		try {
			if(this.playerModel.getNumberOfArmyUnitOnHand()>numberOfUnits)
			{
				List<UnitModel>  units = country.getArmyInCountry();
				UnitModel soldier = units.stream().filter(a -> a.getType().equals(EnumClass.UnitType.INFANTRY)).findFirst().get();
				soldier.setUnitNumber((country.getNumberOfUnits() + numberOfUnits));
				
				this.playerModel.setNumberOfArmyUnitOnHand(this.playerModel.getNumberOfArmyUnitOnHand() -numberOfUnits);
			}

		} catch (Exception ex) {
			return false;
		}

		return true;
	}

	private int getNumberOfSimilarCards() {
		int numberOfUnits = 0;
		int infantryCardNumber = 0;
		int artilaryCardNumber = 0;
		int cavalaryCardNumber = 0;
		List<CardModel> cards = this.playerModel.getDeck();

		infantryCardNumber = getNumberCardTypeByCardType(cards, EnumClass.UnitType.INFANTRY);
		artilaryCardNumber = getNumberCardTypeByCardType(cards, EnumClass.UnitType.ARTILLERY);
		cavalaryCardNumber = getNumberCardTypeByCardType(cards, EnumClass.UnitType.CAVALRY);

		numberOfUnits += infantryCardNumber / 3;
		numberOfUnits += artilaryCardNumber / 3;
		numberOfUnits += cavalaryCardNumber / 3;

		if (infantryCardNumber / 3 > 0) {
			setPlayerDeckByCardType(cards, EnumClass.UnitType.INFANTRY);
		}
		if (artilaryCardNumber / 3 > 0) {
			setPlayerDeckByCardType(cards, EnumClass.UnitType.ARTILLERY);
		}
		if (cavalaryCardNumber / 3 > 0) {
			setPlayerDeckByCardType(cards, EnumClass.UnitType.CAVALRY);
		}

		return numberOfUnits;
	}

	private int getNumberOfDifferentCards() {
		int numberOfUnits = 0;
		int infantryCardNumber = 0;
		int artilaryCardNumber = 0;
		int cavalaryCardNumber = 0;
		List<CardModel> cards = this.playerModel.getDeck();

		infantryCardNumber = getNumberCardTypeByCardType(cards, EnumClass.UnitType.INFANTRY);
		artilaryCardNumber = getNumberCardTypeByCardType(cards, EnumClass.UnitType.ARTILLERY);
		cavalaryCardNumber = getNumberCardTypeByCardType(cards, EnumClass.UnitType.CAVALRY);

		numberOfUnits = Math.min(infantryCardNumber, Math.min(artilaryCardNumber, cavalaryCardNumber));

		if (numberOfUnits > 0) {
			setPlayerDeckByCardType(cards, EnumClass.UnitType.INFANTRY, numberOfUnits);
			setPlayerDeckByCardType(cards, EnumClass.UnitType.ARTILLERY, numberOfUnits);
			setPlayerDeckByCardType(cards, EnumClass.UnitType.CAVALRY, numberOfUnits);
		}

		return numberOfUnits;

	}

	private int getNumberCardTypeByCardType(List<CardModel> cards, EnumClass.UnitType unitType) {

		CardModel card = cards.stream().filter(x -> x.getCardType().equals(EnumClass.UnitType.INFANTRY)).findFirst()
				.get();
		if (card != null) {
			return card.getNumberOfCards();
		} else {
			return 0;
		}
	}

	private void setPlayerDeckByCardType(List<CardModel> cards, EnumClass.UnitType unitType) {
		CardModel card = cards.stream().filter(x -> x.getCardType().equals(EnumClass.UnitType.INFANTRY)).findFirst()
				.get();
		if (card != null) {
			card.setNumberOfCards(card.getNumberOfCards() - (3 * (card.getNumberOfCards() / 3)));
		}

	}

	private void setPlayerDeckByCardType(List<CardModel> cards, EnumClass.UnitType unitType, int numbeOfUnits) {
		CardModel card = cards.stream().filter(x -> x.getCardType().equals(EnumClass.UnitType.INFANTRY)).findFirst()
				.get();
		if (card != null) {
			card.setNumberOfCards(card.getNumberOfCards() - numbeOfUnits);
		}

	}

	private int validateNewContinentOccupation() {
		int totalContinetValues = 0;
		for (ContinentModel continent : gameModel.getContinents()) {

			if (checkIfPlayerCountriesHaveAllContinentCountries(continent, this.playerModel.getCountries())) {
				List<String> continentList = this.playerModel.getContinents().stream().map(c -> c.getContinentName())
						.collect(Collectors.toList());
				if (!continentList.contains(continent.getContinentName())) {
					ArrayList<ContinentModel> playerContinents = this.playerModel.getContinents();
					if (playerContinents == null) {
						playerContinents = new ArrayList<ContinentModel>();
					}
					playerContinents.add(continent);
					this.playerModel.setContinents(playerContinents);

					totalContinetValues += continent.getControlValue();
				}
			}
		}

		return totalContinetValues;
	}

	public boolean checkIfPlayerCountriesHaveAllContinentCountries(ContinentModel continent,
			List<CountryModel> countries) {
		List<String> continentCountryNames = continent.getCountries().stream().map(c -> c.getCountryName())
				.collect(Collectors.toList());
		List<String> countryNames = countries.stream().map(c -> c.getCountryName()).collect(Collectors.toList());
		if (countryNames.containsAll(continentCountryNames)) {
			return true;
		}

		return false;
	}

}
