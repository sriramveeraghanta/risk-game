/**
 * 
 */
package models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author edosh
 *
 */
public class CountryModel {

	private int id;
	private String name;
	private ArrayList<UnitModel> army;
	private int numberOfInfantry;
	private int xAxis;
	private int yAxis;
	private ArrayList<String> adjacentCountries;
	private String continent;

	/**
	 * 
	 */
	public CountryModel(String name, int xAxis,int yAxis, ArrayList<String> adjacentCountries,String continent) {
		this.name = name;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.adjacentCountries = adjacentCountries;
		this.continent = continent;
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
		List<UnitModel> armyList = this.army;
		UnitModel soldier = armyList.stream().filter(a -> a.getType().equals(EnumClass.UnitType.INFANTRY)).findFirst().get();
		if (soldier != null) {
			setNumberOfInfantry(soldier.getUnitNumber());
		}
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
	 * @return the numberOfInfantry
	 */
	public int getNumberOfInfantry() {
		return numberOfInfantry;
	}

	/**
	 * @param numberOfInfantry the numberOfInfantry to set
	 */
	public void setNumberOfInfantry(int numberOfInfantry) {
		this.numberOfInfantry = numberOfInfantry;
	}

	/**
	 * @return the xAxis
	 */
	public int getxAxis() {
		return xAxis;
	}

	/**
	 * @param xAxis the xAxis to set
	 */
	public void setxAxis(int xAxis) {
		this.xAxis = xAxis;
	}

	/**
	 * @return the yAxis
	 */
	public int getyAxis() {
		return yAxis;
	}

	/**
	 * @param yAxis the yAxis to set
	 */
	public void setyAxis(int yAxis) {
		this.yAxis = yAxis;
	}

	/**
	 * @return the adjacentCountries
	 */
	public ArrayList<String> getAdjacentCountries() {
		return adjacentCountries;
	}

	/**
	 * @param adjacentCountries the adjacentCountries to set
	 */
	public void setAdjacentCountries(ArrayList<String> adjacentCountries) {
		this.adjacentCountries = adjacentCountries;
	}

	/**
	 * @return the continent
	 */
	public String getContinent() {
		return continent;
	}

	/**
	 * @param continent the continent to set
	 */
	public void setContinent(String continent) {
		this.continent = continent;
	}

}
