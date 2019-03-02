package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.PrimitiveIterator.OfDouble;

import models.CardModel;
import models.CountryModel;
import utils.EnumClass;
import models.PlayerModel;
import models.UnitModel;
import utils.EnumClass.UnitType;
import models.GameModel;

public class StartUp {

	private GameModel game;
	private int numberOfPlayers;
	private EnumClass.Color[] colors = EnumClass.Color.values();

	private ArrayList<PlayerModel> players;
	private ArrayList<CardModel> cards;

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
	 * Initialise the game objects set players properties such countries,armies,
	 * Colour
	 * 
	 * @param numberOfPlayers
	 */
	public StartUp() {
		GameModel gameModel = new GameModel();
		int numberOfPlayers = gameModel.getNumberOfPlayers();
		this.init(numberOfPlayers);
	}
	

	private void init(int numberOfPlayers) {
		players = new ArrayList<PlayerModel>();
		this.setNumberOfPlayers(numberOfPlayers);
		for (int i = 0; i < getNumberOfPlayers(); i++) {
			PlayerModel player = new PlayerModel();
			this.assignColor(player);
			this.setInitialInfantry(player);
			players.add(player);
		}
		this.setPlayers(players);
		// this.loadCountries();
		this.assignCountriesToPlayers();
		this.createGameCards();
		
	}

	/**
	 * @return the players
	 */
	public ArrayList<PlayerModel> getPlayers() {
		return players;
	}

	/**
	 * @param players the players to set
	 */
	public void setPlayers(ArrayList<PlayerModel> players) {
		this.players = players;
	}

	/**
	 * @return the numberOfPlayers
	 */
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	/**
	 * @param numberOfPlayers the numberOfPlayers to set
	 */
	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	/**
	 * assign initial number of armies to the players at the initial phase *
	 * 
	 * @param player
	 * @return
	 */
	public UnitModel setInitialInfantry(PlayerModel player) {
		ArrayList<UnitModel> units = new ArrayList<UnitModel>();
		UnitModel unit = new UnitModel();
		unit.setType(UnitType.INFANTRY);
		switch (numberOfPlayers) {
		case 2:
			unit.setUnitNumber(40);
			units.add(unit);
			player.setArmy(units);
			return unit;
		case 3:
			unit.setUnitNumber(35);
			units.add(unit);
			player.setArmy(units);
			return unit;
		case 4:
			unit.setUnitNumber(30);
			units.add(unit);
			player.setArmy(units);
			return unit;

		case 5:
			unit.setUnitNumber(25);
			units.add(unit);
			player.setArmy(units);
			return unit;
		case 6:
			unit.setUnitNumber(20);
			units.add(unit);
			player.setArmy(units);
			return unit;
		default:
			return null;

		}
	}

	/**
	 * assigns a colour to the player randomly at the starting phase of the game *
	 * 
	 * @param player
	 */
	public void assignColor(PlayerModel player) {

		int currentIndex = -1;
		for (int i = 0; i < 6; i++) {
			currentIndex = new Random().nextInt(6);
			if (colors[currentIndex] != null) {
				player.setColor(this.colors[currentIndex]);
				this.colors[currentIndex] = null;
				return;
			}
		}
	}

	/**
	 * assigns countries to players randomly at the starting phase of the game
	 */
	public void assignCountriesToPlayers() {
		int currentIndex = -1;
		ArrayList<CountryModel> shuffeledcountries = new ArrayList<CountryModel>(game.getCountries());
		// shuffle the new list;
		Collections.shuffle(shuffeledcountries);
		int playerId = 0;
		while (shuffeledcountries.size() > 0) {
			currentIndex = new Random().nextInt(shuffeledcountries.size());
			playerId = playerId % (this.getNumberOfPlayers());
			this.getPlayers().get(playerId).addCountryToPlayer(shuffeledcountries.get(currentIndex));
			shuffeledcountries.remove(currentIndex);
			playerId++;
		}
	}

	/**
	 * Randomly generate the cards and assign a different unit type to each
	 */
	public void createGameCards() {
		EnumClass.UnitType unitTypes[] = EnumClass.UnitType.values();

		cards = new ArrayList<CardModel>();
		for (int i = 0; i < game.getCountries().size(); i++) {
			CardModel card = new CardModel(unitTypes[i % 3]);
			cards.add(card);
		}
		this.setCards(cards);

	}

}
