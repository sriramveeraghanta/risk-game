/**
 * 
 */
package model;

import java.util.ArrayList;

/**
 * @author edosh
 *
 */
public class CountryModel {

	private ArrayList<Unit> army;

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

	private String name;

	/**
	 * 
	 */
	public CountryModel(String name) {
		this.name = name;
	}

}
