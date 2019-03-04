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
import models.GameModel;

public class StartUp {

	private GameModel gameModel;
	private int numberOfPlayers;
	private EnumClass.Color[] colors = EnumClass.Color.values();

	private ArrayList<PlayerModel> players;
	private ArrayList<CardModel> cards;
	private  ReinforcementPhase reinforcementPhase;

	/**
	 * Initialise the game objects set players properties such countries,armies,
	 * Colour
	 * 
	 * @param numberOfPlayers
	 */
	public StartUp() {
		GameModel gameModel = new GameModel();
		this.numberOfPlayers = gameModel.getNumberOfPlayers();
		
		
		this.init();
	}

	private void init() {

		players = new ArrayList<PlayerModel>();
		// Creating new player objects for the count 
		for (int i = 0; i < numberOfPlayers; i++) {
			EnumClass.Color assingedColor = this.assignColor();
			PlayerModel player = new PlayerModel(assingedColor);
			this.setInitialInfantry(player);
			players.add(player);
		}
		// this.loadCountries();
		this.assignCountriesToPlayers();
		this.createGameCards();
		this.assignOneUnitPerCountry();

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
	 * assigns a color to the player randomly at the starting phase of the game *
	 * 
	 * @param player
	 * @return
	 */
	public EnumClass.Color assignColor() {
		EnumClass.Color assignedColor = null ;
		int currentIndex;
		for (int i = 0; i < 6; i++) {
			currentIndex = new Random().nextInt(6);
			if (colors[currentIndex] != null) {
				assignedColor = this.colors[currentIndex];
				this.colors[currentIndex] = null;
			}
		}
		return assignedColor;
	}

	/**
	 * assigns countries to players randomly at the starting phase of the game
	 */
	public void assignCountriesToPlayers() {
		int currentIndex = -1;
		ArrayList<CountryModel> shuffeledcountries = new ArrayList<CountryModel>();
		System.out.println("jhgdufyh"+gameModel.getCountries());
		shuffeledcountries.addAll(gameModel.getCountries());
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
	
	public void assignOneUnitPerCountry() {
		
		for(PlayerModel player: this.getPlayers()) {
			reinforcementPhase= new ReinforcementPhase(player, gameModel);
			for(CountryModel country: player.getCountries()) {
				reinforcementPhase.assignArmyUnitToCountry(country.getCountryName(), 1);
			}
		}		
	}

	/**
	 * Randomly generate the cards and assign a different unit type to each
	 */
	public void createGameCards() {
		EnumClass.UnitType unitTypes[] = EnumClass.UnitType.values();

		cards = new ArrayList<CardModel>();
		for (int i = 0; i < gameModel.getCountries().size(); i++) {
			CardModel card = new CardModel(unitTypes[i % 3]);
			cards.add(card);
		}
		this.setCards(cards);

	}

}
