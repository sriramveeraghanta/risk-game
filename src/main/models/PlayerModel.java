package main.models;

import main.utills.EnumHandler;

import java.util.ArrayList;

public class PlayerModel {

    private EnumHandler.Color color;
    private boolean isActive;
    private int successfulCardSwapCounter;
    private int armyInHand;
    private ArrayList<CardModel> deck = new ArrayList<>();
    private ArrayList<CountryModel> countriesList = new ArrayList<>();
    private ArrayList<ContinentModel> continentsList=new ArrayList<>();

    /**
     *
     */
    public PlayerModel() {

    }

    /**
     * @return the armyInHand
     */
    public int getArmyInHand() {
        return armyInHand;
    }

    /**
     * @param armyInHand the armyInHand to set
     */
    public void setArmyInHand(int armyInHand) {
        this.armyInHand = armyInHand;
    }

    /**
     * @return the isActive
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * @return the colour
     */
    public EnumHandler.Color getColor() {
        return color;
    }

    /**
     * @param color the colour to set
     */
    public void setColor(EnumHandler.Color color) {
        this.color = color;
    }

    /**
     * @return the deck
     */
    public ArrayList<CardModel> getDeck() {
        return deck;
    }

    /**
     * @param deck the deck to set
     */
    public void setDeck(ArrayList<CardModel> deck) {
        this.deck = deck;
    }

    /**
     * @return the successfulCardSwapCounter
     */
    public int getSuccessfulCardSwapCounter() {
        return successfulCardSwapCounter;
    }

    /**
     * @param successfulCardSwapCounter the successfulCardSwapCounter to set
     */
    public void setSuccessfulCardSwapCounter(int successfulCardSwapCounter) {
        this.successfulCardSwapCounter = successfulCardSwapCounter;
    }

    /**
     * @return the countryModels
     */
    public ArrayList<CountryModel> getCountries() {
        return countriesList;
    }

    /**
     * @param countiresList the countryModels to set
     */
    public void setCountries(ArrayList<CountryModel> countiresList) {
        this.countriesList = countiresList;
    }

    /**
     * @return the continentModels
     */
    public ArrayList<ContinentModel> getContinents() {
        return continentsList;
    }

    /**
     * @param continentsList the continentModels to set
     */
    public void setContinents(ArrayList<ContinentModel> continentsList) {
        this.continentsList = continentsList;
    }

    public void addCountry(CountryModel country) {
        if (this.getCountries() == null) {
            this.setCountries(new ArrayList<>());
        }
        this.countriesList = this.getCountries();
        this.countriesList.add(country);
        this.setCountries(countriesList);
    }
}
