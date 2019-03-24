package main.models;

import main.utills.EnumHandler;

import java.util.ArrayList;

public class PlayerModel {

    private EnumHandler.Color color;
    private ArrayList<CountryModel> countriesList = new ArrayList<>();
    private ArrayList<ContinentModel> continentsList= new ArrayList<>();
    private ArrayList<UnitModel> army= new ArrayList<>();
    private ArrayList<CardModel> deck= new ArrayList<>();
    private boolean isActive;
    private int successfullCardSwapCounter;
    private int numberOfArmyUnitOnHand;

    /**
     *
     */
    public PlayerModel(EnumHandler.Color color) {
        this.setColor(color);
        this.setActive(true);
    }

    /**
     * @return the numberOfArmyUnitOnHand
     */
    public int getNumberOfArmyUnitOnHand() {
        return numberOfArmyUnitOnHand;
    }

    /**
     * @param numberOfArmyUnitOnHand the numberOfArmyUnitOnHand to set
     */
    public void setNumberOfArmyUnitOnHand(int numberOfArmyUnitOnHand) {
        this.numberOfArmyUnitOnHand = numberOfArmyUnitOnHand;
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

    /**
     * @return the army
     */
    public ArrayList<UnitModel> getArmy() {
        return army;
    }

    /**
     * @param army the army to set
     */
    public void setArmy(ArrayList<UnitModel> army) {
        this.army = army;
    }

    public void addCountryToPlayer(CountryModel country) {
        if (this.getCountries() == null) {
            this.setCountries(new ArrayList<CountryModel>());
        }
        this.countriesList = this.getCountries();
        this.countriesList.add(country);
        this.setCountries(countriesList);
    }

    public void removeCountryFromPlayer(CountryModel country) {
        if (this.getCountries() != null) {
            this.countriesList = this.getCountries();
            this.countriesList.remove(country);
            this.setCountries(countriesList);
        }
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
     * @return the successfullCardSwapCounter
     */
    public int getSuccessfullCardSwapCounter() {
        return successfullCardSwapCounter;
    }

    /**
     * @param successfullCardSwapCounter the successfullCardSwapCounter to set
     */
    public void setSuccessfullCardSwapCounter(int successfullCardSwapCounter) {
        this.successfullCardSwapCounter = successfullCardSwapCounter;
    }

}
