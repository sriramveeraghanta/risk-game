package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import model.EnumClass.UnitType;

public class PhaseStartUpModel {

	private int numberOfPlayers;
	private EnumClass.Color[] colors = { EnumClass.Color.BLACK, EnumClass.Color.BLUE, EnumClass.Color.GREEN,
			EnumClass.Color.PINK, EnumClass.Color.RED, EnumClass.Color.YELLOW };

	private ArrayList<PlayerModel> players;
	private ArrayList<CountryModel> countries;

	public PhaseStartUpModel() {
	}

	public PhaseStartUpModel(int numberOfPlayers) throws JSONException {
		players = new ArrayList<PlayerModel>();
		this.setNumberOfPlayers(numberOfPlayers);
		for (int i = 0; i < getNumberOfPlayers(); i++) {
			PlayerModel player = new PlayerModel();
			this.assignColor(player);
			this.setInitialInfantry(player);
			players.add(player);
		}
		this.setPlayers(players);
		this.loadCountries();
		this.assignCountriesToPlayers();
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

	/*
	 * Load countries JSON file and set the countries array list accordingly
	 */
	public void loadCountries() throws JSONException {

		String jsonFilePath;
		try {
			jsonFilePath = this._jsonFilePath();
			String resourceName = jsonFilePath + "\\map_data.json";

			InputStream inputstream = new FileInputStream(resourceName);
			JSONTokener tokener = new JSONTokener(inputstream);
			JSONObject object = new JSONObject(tokener);
			JSONArray countriesJSON = object.getJSONArray("countries");

			this.countries = new ArrayList<CountryModel>();

			for (int i = 0; i < countriesJSON.length(); i++) {
				JSONObject countryJsonObject = (JSONObject) countriesJSON.get(i);
				CountryModel country = new CountryModel(countryJsonObject.getString("name"),
						countryJsonObject.getInt("id"));
				this.countries.add(country);
			}
			this.setCountries(this.countries);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void assignCountriesToPlayers() {
		int currentIndex = -1;
		ArrayList<CountryModel> shuffeledcountries = new ArrayList<CountryModel>(this.getCountries());
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

	private String _jsonFilePath() throws IOException {
		String jsonDirectory = System.getProperty("user.dir") + "\\src\\resources";
		return jsonDirectory;
	}

}
