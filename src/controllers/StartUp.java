package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.PrimitiveIterator.OfDouble;

import models.CardModel;
import models.CountryModel;
import utils.EnumClass;
import models.PlayerModel;
import models.UnitModel;
import models.GameModel;
import controllers.MapBuilder;

/**
 * Before any other phases in start up phase should set and initialize some
 * properties
 */
public class StartUp {
	private GameModel gameModel;
	private int numberOfPlayers;
	private EnumClass.Color[] colors = EnumClass.Color.values();
	private ArrayList<PlayerModel> players;
	private ArrayList<CardModel> cards;
	private ReinforcementPhase reinforcementPhase;
	private MapBuilder mapBuilder;

	/**
	 * Initialise the game objects set players properties such countries,armies,
	 * Colour
	 * 
	 * @param numberOfPlayers
	 */
	public StartUp(GameModel gameModel) {
		this.gameModel = gameModel;
		this.numberOfPlayers = gameModel.getNumberOfPlayers();
		// Map Generation
		this.mapBuilder = new MapBuilder(gameModel);
	}

	/**
	 * Initialize the game objects set players properties such
	 * countries,armies,Color
	 * 
	 * @param numberOfPlayers should be get as a parameter and initialize some
	 *                        properties for each player
	 */
	public void createPlayers() {
		// setting number of players count to game model
		gameModel.setNumberOfPlayers(this.numberOfPlayers);
		// players list of player models
		players = new ArrayList<PlayerModel>();
		// Creating new player objects for the count
		for (int i = 0; i < numberOfPlayers; i++) {
			EnumClass.Color assingedColor = this.assignColor();
			PlayerModel player = new PlayerModel(assingedColor);
			this.setInitialInfantry(player);
			players.add(player);
		}
		gameModel.setPlayers(players);
		try {
			this.mapBuilder.generateMap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.assignCountriesToPlayers();
		this.createGameCards();
		this.assignOneUnitPerCountry();

	}

	/**
	 * assigns a color to the player randomly at the starting phase of the game *
	 * 
	 * @param player for assigning color to each player
	 * @return the color which is assign to specific player
	 */
	public EnumClass.Color assignColor() {
		EnumClass.Color assignedColor = null;
		int currentIndex;
		for (int i = 0; i < 6; i++) {
			currentIndex = new Random().nextInt(6);
			if (this.colors[currentIndex] != null) {
				assignedColor = this.colors[currentIndex];
				this.colors[currentIndex] = null;
				return assignedColor;
			}
		}
		return null;
	}

	/**
	 * assign initial number of armies to the players at the initial phase *
	 * 
	 * @param player needs to assign the armies to the player in start up phase
	 * @return the unit model which is contains both number and type of armies
	 */
	public UnitModel setInitialInfantry(PlayerModel player) {
		ArrayList<UnitModel> units = new ArrayList<UnitModel>();
		UnitModel unit = new UnitModel();
		unit.setType(EnumClass.UnitType.INFANTRY);
		switch (numberOfPlayers) {
		case 2:
			unit.setUnitNumber(40);
			units.add(unit);
			player.setArmy(units);
			player.setNumberOfArmyUnitOnHand(40);
			return unit;
		case 3:
			unit.setUnitNumber(35);
			units.add(unit);
			player.setArmy(units);
			player.setNumberOfArmyUnitOnHand(35);
			return unit;
		case 4:
			unit.setUnitNumber(30);
			units.add(unit);
			player.setArmy(units);
			player.setNumberOfArmyUnitOnHand(30);
			return unit;

		case 5:
			unit.setUnitNumber(25);
			units.add(unit);
			player.setArmy(units);
			player.setNumberOfArmyUnitOnHand(25);
			return unit;
		case 6:
			unit.setUnitNumber(20);
			units.add(unit);
			player.setArmy(units);
			player.setNumberOfArmyUnitOnHand(20);
			return unit;
		default:
			return null;
		}
	}

	/**
	 * assigns countries to players randomly at the starting phase of the game
	 */
	public void assignCountriesToPlayers() {
		int currentIndex = -1;
		ArrayList<CountryModel> shuffeledcountries = new ArrayList<CountryModel>();
		shuffeledcountries.addAll(gameModel.getCountries());
		// shuffle the new list;
		Collections.shuffle(shuffeledcountries);
		int playerId = 0;
		while (shuffeledcountries.size() > 0) {
			currentIndex = new Random().nextInt(shuffeledcountries.size());
			playerId = playerId % (numberOfPlayers);
			gameModel.getPlayers().get(playerId).addCountryToPlayer(shuffeledcountries.get(currentIndex));
			shuffeledcountries.remove(currentIndex);
			playerId++;
		}
	}

	/**
	 * assigning armies to the country each player own in the way each country has
	 * at least one army unit in it
	 */
	public void assignOneUnitPerCountry() {
		ArrayList<PlayerModel> playersList = gameModel.getPlayers();

		for (PlayerModel player : playersList) {
			reinforcementPhase = new ReinforcementPhase(player, gameModel);
			for (CountryModel country : player.getCountries()) {
				reinforcementPhase.assignArmyUnitToCountry(country.getCountryName(), 1);
			}
		}
	}

	/**
	 * Randomly generate the cards and assign a different unit type to each
	 * 
	 * This should be rewritten in phase 2 only 3 unique Type cards are needed.
	 * 
	 */
	public void createGameCards() {
		EnumClass.UnitType unitTypes[] = EnumClass.UnitType.values();

		cards = new ArrayList<CardModel>();
		for (int i = 0; i < gameModel.getCountries().size(); i++) {
			CardModel card = new CardModel(unitTypes[i % 3]);
			cards.add(card);
		}
		this.setCards(cards);
		this.gameModel.setCards(cards);

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

}
