/**
 * 
 */
package models;

import java.util.ArrayList;

/**
 *Continent Model stores the data specific to continent ,
 *i.e continentName, control value and its ownned countries
 */
public class ContinentModel {

	private String continentName;
	
	/**
	 * @param controlvalue continents controlValue is used by player
	 *  when he acquires all the countries of the given continent
	 */
	private int controlValue;
	private ArrayList<CountryModel> countries;

	public ContinentModel(String continentName, int controlValue) {
		this.continentName = continentName;
		this.controlValue = controlValue;
	}

	/**
	 * Getter method for continent name
	 */
	public String getContinentName() {
		return continentName;
	}

	/**
	 * Setter method for continent name
	 */
	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	/**
	 * Getter method for Control value
	 */
	public int getControlValue() {
		return controlValue;
	}

	/**
	 * Setter method for control value
	 */
	public void setControlValue(int controlValue) {
		this.controlValue = controlValue;
	}

	/**
	 * Getter method for countries inside the continent
	 */
	public ArrayList<CountryModel> getCountries() {
		return countries;
	}

	/**
	 * Setter method for settings countries inside the continent
	 */
	public void setCountries(ArrayList<CountryModel> countries) {
		this.countries = countries;
	}
}
