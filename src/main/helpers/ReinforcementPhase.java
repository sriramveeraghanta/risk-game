package main.helpers;

import main.models.*;
import main.utills.EnumHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReinforcementPhase {

    private PlayerModel playerModel;
    private GameModel gameModel;

    /**
     * Constructor Reinforcement Phase
     *
     * @param playerModel the player
     * @param gameModel   the object of gameModel
     */
    public ReinforcementPhase(PlayerModel playerModel, GameModel gameModel) {
        this.playerModel = playerModel;
        this.gameModel = gameModel;
    }

    /**
     * this method calculates the number of army units player get according to the cards if they are similar of they are different
     * and the number will increase 5 times more each time he swap the cards with armies
     *
     * @return the total number of army units he can get for cards
     */
    public int swapCardsForArmyUnits() {
        int totalNumberOfArmyUnits = getNumberOfSimilarCards() + getNumberOfDifferentCards();
        if (totalNumberOfArmyUnits > 0) {
            this.playerModel.setSuccessfullCardSwapCounter(this.playerModel.getSuccessfullCardSwapCounter() + 1);

            return (totalNumberOfArmyUnits * 5) * this.playerModel.getSuccessfullCardSwapCounter();
        }

        return 0;
    }

    /**
     * getting the continent control value which shows the number of armies player can get after owning all the countries in a continent
     *
     * @return the number of control value of specific continent
     */
    public int getArmyUnitsForConqueredContinent() {

        return this.validateNewContinentOccupation();
    }

    /**
     * calculating the number of armies players can get for each round by default which should be the number of countries divided by 3
     * rounded down
     *
     * @return the number of armies each player can by default
     */
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

    /**
     * assigning the number of armies which calculated to the country of the player
     *
     * @param countryName   which country we want to place the armies
     * @param numberOfUnits
     * @return boolean if it goes in catch it return false
     */
    public boolean assignArmyUnitToCountry(String countryName, int numberOfUnits) {
        List<CountryModel> countries = this.playerModel.getCountries();

        CountryModel country = countries.stream().filter(c -> c.getCountryName().contentEquals(countryName))
                .findFirst().get();
        try {
            if (this.playerModel.getNumberOfArmyUnitOnHand() > numberOfUnits) {
                List<UnitModel> units = country.getArmyInCountry();
                UnitModel soldier = units.stream().filter(a -> a.getType().equals(EnumHandler.UnitType.INFANTRY)).findFirst().get();
                soldier.setUnitNumber((country.getNumberOfUnits() + numberOfUnits));

                this.playerModel.setNumberOfArmyUnitOnHand(this.playerModel.getNumberOfArmyUnitOnHand() - numberOfUnits);
            }

        } catch (Exception ex) {
            return false;
        }

        return true;
    }

    /**
     * checking the cards of the player if they are similar types and return the number of units he can get
     *
     * @return the number of units he can get for similar cards
     */
    public int getNumberOfSimilarCards() {
        int numberOfUnits = 0;
        int infantryCardNumber = 0;
        int artilaryCardNumber = 0;
        int cavalaryCardNumber = 0;
        List<CardModel> cards = this.playerModel.getDeck();

        infantryCardNumber = getNumberCardTypeByCardType(cards, EnumHandler.UnitType.INFANTRY);
        artilaryCardNumber = getNumberCardTypeByCardType(cards, EnumHandler.UnitType.ARTILLERY);
        cavalaryCardNumber = getNumberCardTypeByCardType(cards, EnumHandler.UnitType.CAVALRY);

        numberOfUnits += infantryCardNumber / 3;
        numberOfUnits += artilaryCardNumber / 3;
        numberOfUnits += cavalaryCardNumber / 3;

        if (infantryCardNumber / 3 > 0) {
            setPlayerDeckByCardType(cards, EnumHandler.UnitType.INFANTRY);
        }
        if (artilaryCardNumber / 3 > 0) {
            setPlayerDeckByCardType(cards, EnumHandler.UnitType.ARTILLERY);
        }
        if (cavalaryCardNumber / 3 > 0) {
            setPlayerDeckByCardType(cards, EnumHandler.UnitType.CAVALRY);
        }
        return numberOfUnits;
    }

    /**
     * checking the cards of the player if they are different types and return the number of units he can get
     *
     * @return the number of units he can get for different cards
     */
    public int getNumberOfDifferentCards() {
        int numberOfUnits = 0;
        int infantryCardNumber = 0;
        int artilaryCardNumber = 0;
        int cavalaryCardNumber = 0;
        List<CardModel> cards = this.playerModel.getDeck();

        infantryCardNumber = getNumberCardTypeByCardType(cards, EnumHandler.UnitType.INFANTRY);
        artilaryCardNumber = getNumberCardTypeByCardType(cards, EnumHandler.UnitType.ARTILLERY);
        cavalaryCardNumber = getNumberCardTypeByCardType(cards, EnumHandler.UnitType.CAVALRY);

        numberOfUnits = Math.min(infantryCardNumber, Math.min(artilaryCardNumber, cavalaryCardNumber));

        if (numberOfUnits > 0) {
            setPlayerDeckByCardType(cards, EnumHandler.UnitType.INFANTRY, numberOfUnits);
            setPlayerDeckByCardType(cards, EnumHandler.UnitType.ARTILLERY, numberOfUnits);
            setPlayerDeckByCardType(cards, EnumHandler.UnitType.CAVALRY, numberOfUnits);
        }

        return numberOfUnits;

    }

    /**
     * checking the cards type and return the number of each card type
     *
     * @param cards    list of all cards player own
     * @param unitType getting the unit type and return the number of it
     * @return the number of specific card type
     */
    public int getNumberCardTypeByCardType(List<CardModel> cards, EnumHandler.UnitType unitType) {
        try {
            CardModel card = cards.stream().filter(x -> x.getCardType().equals(unitType)).findFirst()
                    .get();
            if (card != null) {
                return card.getNumberOfCards();
            } else {
                return 0;
            }
        } catch (Exception ex) {
            return 0;
        }
    }

    /**
     * setting the player deck by getting the list of cards and unit type of it
     *
     * @param cards    a list of cards
     * @param unitType which is the type of cards
     */
    public void setPlayerDeckByCardType(List<CardModel> cards, EnumHandler.UnitType unitType) {
        CardModel card = cards.stream().filter(x -> x.getCardType().equals(unitType)).findFirst()
                .get();
        if (card != null) {
            card.setNumberOfCards(card.getNumberOfCards() - (3 * (card.getNumberOfCards() / 3)));
        }
    }

    /**
     * setting the player deck by getting the list of cards and unit type of it
     *
     * @param cards        a list of cards
     * @param unitType     which is the type of cards
     * @param numbeOfUnits the number of each card type player has
     */
    public void setPlayerDeckByCardType(List<CardModel> cards, EnumHandler.UnitType unitType, int numbeOfUnits) {
        CardModel card = cards.stream().filter(x -> x.getCardType().equals(unitType)).findFirst()
                .get();
        if (card != null) {
            card.setNumberOfCards(card.getNumberOfCards() - numbeOfUnits);
        }

    }

    /**
     * checking if the continent player occupy is in his continent list
     *
     * @return number of the control value of the continent player can occupies all the countries
     */
    public int validateNewContinentOccupation() {
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

    /**
     * checking if all the countries in a continent occupied by the player and return the control value of that continent
     *
     * @param continent the object of continent
     * @param countries a list of countries player own
     * @return if player can get whole continent return true
     */
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