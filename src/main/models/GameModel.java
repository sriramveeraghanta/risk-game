package main.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Game Model which has the countries ,continents ,players , cards , number of players and current player
 */
public class GameModel extends Observable implements Serializable {

    private String title;
    private int numberOfPlayers;
    private ArrayList<PlayerModel> players = new ArrayList<>();
    private ArrayList<ContinentModel> continents = new ArrayList<>();
    private ArrayList<CountryModel> countries = new ArrayList<>();
    private ArrayList<CardModel> cards = new ArrayList<>();
    private int currentPlayerIndex;

    /**
     * consructor for game model class
     */
    public GameModel() {

    }

    /**
     * this method is notify the reinforcement View
     */
    public void reinforce(){
        setChanged();
        notifyObservers("Reinforce");
    }

    public void cardSwap(){
        setChanged();
        notifyObservers("cardSwap");
    }

    /**
     * this method is notify the attack View
     */
    public void attackPhase(){
        setChanged();
        notifyObservers("Attack");
    }


    public void attackPhaseEnd(){
        setChanged();
        notifyObservers("Attack Finish");
    }
    /**
     * this method is notify the fortify View
     */

    public void fortify(){
        setChanged();
        notifyObservers("Fortify");
    }
    /**
     * Getter method for getting the arrary list of cards
     * @return the cards
     */
    public ArrayList<CardModel> getCards() {
        return cards;
    }

    /**
     * Setter method for setting the arraylist of cards
     * @param cards the cards to set
     */
    public void setCards(ArrayList<CardModel> cards) {
        this.cards = cards;
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
     * adding country to the list of country
     * @param country the countries to set
     */
    public void addCountry(CountryModel country) {
        this.countries.add(country);
    }

    /**
     * Getter method for getting the continents list
     * @return the continents
     */
    public ArrayList<ContinentModel> getContinents() {
        return continents;
    }

    /**
     * adding new continent to the list of continents
     * @param continent the continents to set
     */
    public void addContinent(ContinentModel continent) {
        this.continents.add(continent);
    }

    /**
     * Getter method for getting the number of players
     * @return the number of players in the game.
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * Setter method for setting the number of players
     * @param numberOfPlayer that are currently playing the game.
     */
    public void setNumberOfPlayers(int numberOfPlayer) {
        this.numberOfPlayers = numberOfPlayer;
    }

    /**
     * Getter method for getting the list of players
     * @return the player model
     */
    public ArrayList<PlayerModel> getPlayers() {
        return players;
    }

    /**
     * Setter method for setting the list of players
     * @param players of player models
     */
    public void setPlayers(ArrayList<PlayerModel> players) {
        this.players = players;
    }

    /**
     * Getter method for getting current index
     * @return an integer for current player index
     */
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    /**
     * Setter methos for setting the current player index
     * @param currentPlayerIndex integer index for current player
     */
    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }
}
