package main.models;

import java.util.ArrayList;

/**
 * Continent Model stores the data specific to continent , i.e continentName,
 * control value and its ownned countries
 */
public class ContinentModel {

    private String continentName;
    private int controlValue;
    private ArrayList<CountryModel> countries = new ArrayList<>();


    /**
     * constructor method for continent method
     * @param continentName name of the continent which is string
     * @param controlValue continents controlValue is used by player
     *                       when he/she acquires all the countries of the given continent
     */
    public ContinentModel(String continentName, int controlValue) {
        this.continentName = continentName;
        this.controlValue = controlValue;
    }

    /**
     * Getter method for continent name
     * @return the continent name
     */
    public String getContinentName() {
        return continentName;
    }


    /**
     * Setter method for continent name
     * @param continentName the name of the continent
     */
    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    /**
     * Getter method for Control value
     * @return the integer for control value of continent
     */
    public int getControlValue() {
        return controlValue;
    }

    /**
     * Setter method for control value
     * @param controlValue integer that is control value of the continent
     */
    public void setControlValue(int controlValue) {
        this.controlValue = controlValue;
    }

    /**
     * Getter method for countries inside the continent
     * @return the array list of countries that assign to that country
     */
    public ArrayList<CountryModel> getCountries() {
        return countries;
    }

    /**
     * Adding countries inside the continent
     * @param country the country name which we want to add to the list of countries assigned to the continent
     */
    public void addCountry(CountryModel country) {
        this.countries.add(country);
    }
}
