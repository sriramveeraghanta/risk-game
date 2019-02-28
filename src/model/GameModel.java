/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Observable;  


/**
 * This is the main model file, containing all the required data to start a game 
 * and make operations over the whole game
 * 
 * @author edosh
 *
 */

@SuppressWarnings("deprecation")
public class GameModel extends Observable {
	
	private int numberOfPlayers = 0;

	private String title;
	private boolean visible;
	private ArrayList<CountryModel> countries;
	
	public GameModel() {
		
	}
	
	/**
	 * Getter method for getting number of player playing the game.
	 * */
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	
	/**
	 * Setter method for setting model with number of player collected in the User Interface.
	 * */
	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

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
}