/**
 * 
 */
package models;

import java.util.ArrayList;
import java.util.List;

import utils.EnumClass;

/**
 *
 */
public class CountryModel {
	
	private String countryName;
	private int xAxis;
	private int yAxis;
	private int numberOfUnits;
	
	private ContinentModel assignedContinent;
	
	private ArrayList<UnitModel> armyInCountry;
	private ArrayList<CountryModel> adjcentCountries;
	
	/**
	 * 
	 */
	public CountryModel(String contryName, int xAxis,int yAxis, ContinentModel assignedContinent) {
		this.setCountryName(contryName);
		this.setxAxis(xAxis);
		this.setyAxis(yAxis);
		this.setAssignedContinent(assignedContinent);
		this.setAdjcentCountries(adjcentCountries);
	}
	
	/**
	 * Getter and setter methods for Country Name
	 * */
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	/**
	 * Getter and setter methods for x axis
	 * */
	public int getxAxis() {
		return xAxis;
	}

	public void setxAxis(int xAxis) {
		this.xAxis = xAxis;
	}
	
	/**
	 * Getter and setter methods for Y-Axis
	 * */
	public int getyAxis() {
		return yAxis;
	}
	public void setyAxis(int yAxis) {
		this.yAxis = yAxis;
	}
	
	/**
	 * Getter and setter methods for Number of Units
	 * */
	public int getNumberOfUnits() {
		return numberOfUnits;
	}
	public void setNumberOfUnits(int numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
	}

	/**
	 * Getter and setter methods for Continent
	 * */
	public ContinentModel getAssignedContinent() {
		return assignedContinent;
	}
	public void setAssignedContinent(ContinentModel assignedContinent) {
		this.assignedContinent = assignedContinent;
	}

	/**
	 * Getter and setter methods for 
	 * */
	public ArrayList<UnitModel> getArmyInCountry() {
		return armyInCountry;
	}
	public void setArmyInCountry(ArrayList<UnitModel> armyInCountry) {
		this.armyInCountry = armyInCountry;
	}
	
	/**
	 * Getter and setter methods for Adjacent Countries
	 * */
	public ArrayList<CountryModel> getAdjcentCountries() {
		return adjcentCountries;
	}
	public void setAdjcentCountries(ArrayList<CountryModel> adjcentCountries) {
		this.adjcentCountries = adjcentCountries;
	}
	
	public void setArmy(ArrayList<UnitModel> army) {
		this.armyInCountry = army;
		List<UnitModel> armyList = this.armyInCountry;
		UnitModel soldier = armyList.stream().filter(a -> a.getType().equals(EnumClass.UnitType.INFANTRY)).findFirst().get();
		if (soldier != null) {
			setNumberOfUnits(soldier.getUnitNumber());
		}
	}
}
