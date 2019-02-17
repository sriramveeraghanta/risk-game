/**
 * 
 */
package model;

import java.util.ArrayList;

/**
 * @author edosh
 *
 */
public class ContinentModel {

	private ArrayList<CountryModel> countryModels;

	/**
	 * @return the countryModels
	 */
	public ArrayList<CountryModel> getCountries() {
		return countryModels;
	}

	/**
	 * @param countryModels the countryModels to set
	 */
	public void setCountries(ArrayList<CountryModel> countryModels) {
		this.countryModels = countryModels;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	private String name;
	private int value;

	/**
	 * 
	 */
	public ContinentModel(String name, int value) {
		this.value = value;
		this.name = name;
	}

}
