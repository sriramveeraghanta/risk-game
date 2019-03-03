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
	private int numberOfPlayers;
	private ArrayList<ContinentModel> continents;
	private ArrayList<CountryModel> countries;
	
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