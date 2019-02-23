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
	
	private int id;
	

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

	private ArrayList<UnitModel> army;

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
	public CountryModel(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	

	
	

}
