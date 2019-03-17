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
	private ArrayList<PlayerModel> players = new ArrayList<PlayerModel>();
	private ArrayList<ContinentModel> continents = new ArrayList<ContinentModel>();
	private ArrayList<CountryModel> countries = new ArrayList<CountryModel>();
	private ArrayList<CardModel> cards = new ArrayList<CardModel>();

	public GameModel() {

	}

	/**
	 * @return the cards
	 */
	public ArrayList<CardModel> getCards() {
		return cards;
	}

	/**
	 * @param cards the cards to set
	 */
	public void setCards(ArrayList<CardModel> cards) {
		this.cards = cards;
	}

	/**
	 * Getter method for Title
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter method for title
	 * 
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
	public void addCountry(CountryModel country) {
		this.countries.add(country);
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
	public void addContinent(ContinentModel continent) {
		this.continents.add(continent);
	}

	/**
	 * @return the number of players in the game.
	 */
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	/**
	 * @param numberOfPlayer that are currently playing the game.
	 */
	public void setNumberOfPlayers(int numberOfPlayer) {
		this.numberOfPlayers = numberOfPlayer;
	}

	/**
	 * @return the player model
	 */
	public ArrayList<PlayerModel> getPlayers() {
		return players;
	}

	/**
	 * @param list of player models
	 */
	public void setPlayers(ArrayList<PlayerModel> players) {
		this.players = players;
	}
}