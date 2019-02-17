/**
 * 
 */
package model;

import java.util.ArrayList;

/**
 * @author edosh
 *
 */
public class Continent {

	private ArrayList<Country> countries;

	/**
	 * @return the countries
	 */
	public ArrayList<Country> getCountries() {
		return countries;
	}

	/**
	 * @param countries the countries to set
	 */
	public void setCountries(ArrayList<Country> countries) {
		this.countries = countries;
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
	public Continent(String name, int value) {
		this.value = value;
		this.name = name;
	}

}
