/**
 * 
 */
package models;

import java.util.ArrayList;

import models.EnumClass.Color;
import models.EnumClass.UnitType;

/**
 * @author edosh
 *
 */
public class PlayerModel {

	private Color color;
	private ArrayList<CountryModel> countryModels;
	private ArrayList<ContinentModel> continentModels;
	private ArrayList<UnitModel> army;

	public PlayerModel() {
	}

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
	 * @param color the color to set
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

	public void addCountryToPlayer(CountryModel country) {
		if (this.getCountries() == null) {
			this.setCountries(new ArrayList<CountryModel>());
		}
		this.countryModels = this.getCountries();
		this.countryModels.add(country);
		this.setCountries(countryModels);
	}

	public void removeCountryFromPlayer(CountryModel country) {
		if (this.getCountries() != null) {
			this.countryModels = this.getCountries();
			this.countryModels.remove(country);
			this.setCountries(countryModels);
		}
	}

}