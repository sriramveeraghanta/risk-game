/**
 * 
 */
package player;

import common.EnumClass.Color;
import resources.Continent;
import resources.Country;
import resources.Unit;

import java.util.ArrayList;

/**
 * @author edosh
 *
 */
public class Player {

	private Color color;
	private ArrayList<Country> countries;
	private ArrayList<Continent> continents;
	private ArrayList<Unit> army;

	/**
	 * 
	 */
	public Player(Color color) {
		this.setColor(color);
	}

	/**
	 * @return the colour
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the colour to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

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
	 * @return the continents
	 */
	public ArrayList<Continent> getContinents() {
		return continents;
	}

	/**
	 * @param continents the continents to set
	 */
	public void setContinents(ArrayList<Continent> continents) {
		this.continents = continents;
	}

	/**
	 * @return the army
	 */
	public ArrayList<Unit> getArmy() {
		return army;
	}

	/**
	 * @param army the army to set
	 */
	public void setArmy(ArrayList<Unit> army) {
		this.army = army;
	}

}
