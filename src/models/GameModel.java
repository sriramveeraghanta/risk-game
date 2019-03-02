/**
 * 
 */
package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;  


/**
 * This is the main model file, containing all the required data to start a game 
 * and make operations over the whole game
 *
 */

@SuppressWarnings("deprecation")
public class GameModel extends Observable {

	private String title;
	private boolean visible;
	private int numberOfPlayers;
	private HashMap<String, ContinentModel> continents;
	private HashMap<String, CountryModel> countries;
	
	public GameModel() {
		// TODO Auto-generated constructor stub
	}
	 
	/**
	 * Getter method for Title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter method for title
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	public HashMap<String, CountryModel> getCountries() {
		return countries;
	}

	/**
	 * @param countries the countries to set
	 */
	public void setCountries(HashMap<String, CountryModel> countries) {
		this.countries = countries;
	}

	/**
	 * @return the continents
	 */
	public HashMap<String, ContinentModel> getContinents() {
		return continents;
	}

	/**
	 * @param continents the continents to set
	 */
	public void setContinents(HashMap<String, ContinentModel> continents) {
		this.continents = continents;
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayer) {
		this.numberOfPlayers = numberOfPlayer;
	}
}