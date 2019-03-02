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

	/**
	 * 
	 */
	public CountryModel(String name, int id) {
		this.name = name;
		this.id = id;
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

}
