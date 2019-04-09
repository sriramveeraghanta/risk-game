package main.models;

import main.utills.GameCommon;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Country model have the data about a country like its name ,xAxis ,yAxis ,
 * in which continent it is belong to ,army country and array list of its
 * adjacent to that specific country
 */
public class CountryModel implements Serializable {

    private String countryName;
    private int xAxis;
    private int yAxis;
    private ContinentModel assignedContinent;
    private int armyInCountry;
    private ArrayList<CountryModel> adjacentCountries;
    private String adjacentCountriesDisplay;

    /**
     * CountryModel constructor to create countryModel object for each initialisation
     * @param countryName        string name of country
     * @param xAxis             integer xAxis for that country
     * @param yAxis             integer yAxis for that country
     * @param assignedContinent object of the continent that this country belongs to it
     */
    public CountryModel(String countryName, int xAxis, int yAxis, ContinentModel assignedContinent) {
        this.setCountryName(countryName);
        this.setxAxis(xAxis);
        this.setyAxis(yAxis);
        this.setAssignedContinent(assignedContinent);
    }

    /**
     * Getter and setter methods for Country Name
     * @return the name of country
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Setter method for setting the name of country
     * @param countryName the name of country
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Getter method for x axis
     * @return interger  x axis
     */
    public int getxAxis() {
        return xAxis;
    }

    /**
     * Setter methods for x axis
     * @param xAxis integer x axis for that country
     */
    public void setxAxis(int xAxis) {
        this.xAxis = xAxis;
    }

    /**
     * Getter methods for y Axis
     * @return the integer y axis
     */
    public int getyAxis() {
        return yAxis;
    }

    /**
     * Setter methods for y axis
     * @param yAxis the int y axis
     */
    public void setyAxis(int yAxis) {
        this.yAxis = yAxis;
    }

    /**
     * Getter methods for Continent object which country belong to
     * @return the continent object which country belong to
     */
    public ContinentModel getAssignedContinent() {
        return assignedContinent;
    }

    /**
     * Setter method for assigning the continent it is belong to according to the parameter
     * @param assignedContinent the continent that specific country belong to
     */
    public void setAssignedContinent(ContinentModel assignedContinent) {
        this.assignedContinent = assignedContinent;
    }

    /**
     * Getter and setter methods for
     * @return the number of armies in that country
     */
    public int getArmyInCountry() {
        return armyInCountry;
    }

    /**
     * Setter method to set the number of armies in that specific country
     * @param armyInCountry the number of armies in that country
     */
    public void setArmyInCountry(int armyInCountry) {
        this.armyInCountry = armyInCountry;
    }

    /**
     * Getter and setter methods for Adjacent Countries
     * @return the array list of the adjacent countries
     */
    public ArrayList<CountryModel> getAdjacentCountries() {
        return adjacentCountries;
    }

    /**
     * Setter method for setting the array list of adjacent countries
     * @param adjacentCountries the array list of the adjacent countries
     */
    public void setAdjacentCountries(ArrayList<CountryModel> adjacentCountries) {
        this.adjacentCountries = adjacentCountries;
    }

    /**
     * Adding the army unit to that specific country
     * @param unitsCount the number of armies we want to add to that specific country
     */
    public void addUnitsToCountry(int unitsCount) {
        this.setArmyInCountry(this.getArmyInCountry() + unitsCount);
    }

    /**
     * Getting all the adjacent Coutries as string, we are using this for only display purpose.
     * @return this returns a string value for list of adjacent Countries
     */
    public String getAdjacentCountriesDisplay() {
        GameCommon common = new GameCommon();
        ArrayList<String> adjCountries = common.getCountriesList(getAdjacentCountries());
        return String.join("\n", adjCountries);
    }

    /**
     * Setting all the adjacent Coutries as string, we are using this for only display purpose.
     * @param adjacentCountriesDisplay this takes a string value as parameter and display it.
     */
    public void setAdjacentCountriesDisplay(String adjacentCountriesDisplay) {
        this.adjacentCountriesDisplay = adjacentCountriesDisplay;
    }
}
