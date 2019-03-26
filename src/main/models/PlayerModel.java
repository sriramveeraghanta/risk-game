package main.models;

import main.utills.EnumHandler;

import java.util.ArrayList;

/**
 * this class contains the information about the plsyer sech as color , army in hand the list of the cards he own
 * which called deck , the list of countries he owned .
 */
public class PlayerModel {

    private EnumHandler.Color color;
    private boolean isActive;
    private int successfulCardSwapCounter;
    private UnitModel armyInHand;
    private ArrayList<CardModel> deck = new ArrayList<>();
    private ArrayList<CountryModel> countriesList = new ArrayList<>();
    private ArrayList<ContinentModel> continentsList=new ArrayList<>();

    /**
     *constructor for player model class
     */
    public PlayerModel() {

    }

    /**
     * Getter method for getting the army in hand
     * @return the armyInHand
     */
    public UnitModel getArmyInHand() {
        return armyInHand;
    }

    /**
     * Setter method for setting the army in hand for player
     * @param armyInHand the armyInHand to set
     */
    public void setArmyInHand(UnitModel armyInHand) {
        this.armyInHand = armyInHand;
    }

    /**
     * if player is active and player plays it return true and just one player could be active
     * @return the isActive true/false
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * setting the is active whether it is true or false
     * @param isActive the isActive to set
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Gettre method for getting the color of player
     * @return the colour
     */
    public EnumHandler.Color getColor() {
        return color;
    }

    /**
     * Setter method for setting the color for player
     * @param color the colour to set
     */
    public void setColor(EnumHandler.Color color) {
        this.color = color;
    }

    /**
     * Getter method for getting the number of cards each player own (deck)
     * @return the deck
     */
    public ArrayList<CardModel> getDeck() {
        return deck;
    }

    /**
     * Setter method for setting the number of cards each player own (deck)
     * @param deck the deck to set
     */
    public void setDeck(ArrayList<CardModel> deck) {
        this.deck = deck;
    }

    /**
     * Getter methos for getting the  successful Card Swap Counter
     * @return the successfulCardSwapCounter
     */
    public int getSuccessfulCardSwapCounter() {
        return successfulCardSwapCounter;
    }

    /**
     * Setter methos for setting the successful Card Swap Counter
     * @param successfulCardSwapCounter the successfulCardSwapCounter to set
     */
    public void setSuccessfulCardSwapCounter(int successfulCardSwapCounter) {
        this.successfulCardSwapCounter = successfulCardSwapCounter;
    }

    /**
     * Getter method for getting the array list of countries
     * @return the countryModels
     */
    public ArrayList<CountryModel> getCountries() {
        return countriesList;
    }

    /**
     * Setter method for setting the array list of the countries
     * @param countiresList the countryModels to set
     */
    public void setCountries(ArrayList<CountryModel> countiresList) {
        this.countriesList = countiresList;
    }

    /**
     * Getter method for getting the array list of continents
     * @return the continentModels
     */
    public ArrayList<ContinentModel> getContinents() {
        return continentsList;
    }

    /**
     * Setter method for setting the array list of continents
     * @param continentsList the continentModels to set
     */
    public void setContinents(ArrayList<ContinentModel> continentsList) {
        this.continentsList = continentsList;
    }

    /**
     * Adding the country to the array list of country list
     * @param country we want to add to the list of countries
     */
    public void addCountry(CountryModel country) {
        if (this.getCountries() == null) {
            this.setCountries(new ArrayList<>());
        }
        this.countriesList = this.getCountries();
        this.countriesList.add(country);
        this.setCountries(countriesList);
    }
}
