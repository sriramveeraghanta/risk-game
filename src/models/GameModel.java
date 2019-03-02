/**
 * 
 */
package models;

import java.util.ArrayList;
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
	private ArrayList<CountryModel> countries;
	private ArrayList<ContinentModel> continents;
	
	 

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

	/**
	 * @return the continents
	 */
	public ArrayList<ContinentModel> getContinents() {
		return continents;
	}

	/**
	 * @param continents the continents to set
	 */
	public void setContinents(ArrayList<ContinentModel> continents) {
		this.continents = continents;
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayer) {
		this.numberOfPlayers = numberOfPlayer;
	}
}