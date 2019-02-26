/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Observable;  


/**
 * @author edosh
 *
 */

@SuppressWarnings("deprecation")
public class GameModel extends Observable {

	private String title;
	private boolean visible;
	private ArrayList<CountryModel> countries;
	
	 

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 */
	public GameModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
		setChanged();
		notifyObservers("visible");
	}

	/**
	 * @return the countries
	 */
	public ArrayList<CountryModel> getCountries() {
		return countries;
	}

	/**
	 * @param countries the countries to set
	 */
	public void setCountries(ArrayList<CountryModel> countries) {
		this.countries = countries;
	}
	
	
//	public ArrayList<CountryModel> LoadCountries(String filePath){
//		
//		
//	}

}
