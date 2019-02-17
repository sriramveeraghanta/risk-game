/**
 * 
 */
package model;

import java.util.ArrayList;

import model.EnumClass.Color;

/**
 * @author edosh
 *
 */
public class PlayerModel {

	private Color color;
	private ArrayList<CountryModel> countryModels;
	private ArrayList<ContinentModel> continentModels;
	private ArrayList<UnitModel> army;

	/**
	 * 
	 */
	public PlayerModel(Color color) {
		this.setColor(color);
	}

	/**
	 * @return the color
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
	 * @return the continentModels
	 */
	public ArrayList<ContinentModel> getContinents() {
		return continentModels;
	}

	/**
	 * @param continentModels the continentModels to set
	 */
	public void setContinents(ArrayList<ContinentModel> continentModels) {
		this.continentModels = continentModels;
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

}
