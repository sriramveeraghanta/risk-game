/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Stack;
import java.util.stream.Collectors;
import model.EnumClass.Color;
import model.EnumClass.UnitType;

/**
 * @author edosh
 *
 */

@SuppressWarnings("deprecation")
public class GameModel extends Observable {

	private String title;
	private boolean visible;
	private ArrayList<PlayerModel> players;

	private Stack<CardModel> stackOfCards = DeckModel.stackOfCards;

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

	// read documents to set continents,countries,adjacencies
	public void loadMap() {

	}

	/// assigning armies to players at initial level
	public void setPlayers(HashMap<String, Color> playerDetails) {
		int index = 0;
		int player_count = playerDetails.size();
		UnitModel unitdetails = null;
		if (player_count == 2) {
			unitdetails = new UnitModel(40, UnitType.INFANTRY);
		} else if (player_count == 3) {
			unitdetails = new UnitModel(35, UnitType.INFANTRY);
		} else if (player_count == 4) {
			unitdetails = new UnitModel(30, UnitType.INFANTRY);
		} else if (player_count == 5) {
			unitdetails = new UnitModel(25, UnitType.INFANTRY);
		} else if (player_count == 6) {
			unitdetails = new UnitModel(20, UnitType.INFANTRY);

		}

		players = new ArrayList<PlayerModel>();
		for (String playerName : playerDetails.keySet()) {
			index++;
			players.add(new PlayerModel(playerName, playerDetails.get(playerName), index, unitdetails));

		}
		index = 0;

	}

	/// assigning countries to players equally
	public void assignCountries(ArrayList<String> Countries) {
		Collections.shuffle(Countries);
		int count;
		int players_count = players.size();
		int countries_count;
		countries_count = Countries.size() / players_count;

		for (int i = 1; i <= players_count; i++) {
			count = 0;
			for (String country : Countries) {
				count++;
				if (count <= countries_count) {
					players.get(i).setCountries(country);
					Countries.remove(country);
				} else {
					break;
				}

			}
		}
	}

////Initial reinforcement phase-this method has to be updated based on the view

	public void reinforcementPhase(PlayerModel players, String country, UnitModel army) {

		DeckModel deck = null;
		boolean card_swap_eligibiltiy=deck.checkCard(players);
		
		if(card_swap_eligibiltiy)
		{
			deck.cardsArmySwapping(players);    //need to be updated
		}
		for (CountryModel territory : players.getCountries()) {

			if (territory.getName().equalsIgnoreCase(country)) {
				territory.setArmy(army);
				ArrayList<UnitModel> unit;
				unit = players.getArmy_in_hands();
				for (UnitModel unitdetails : unit) {
					if (unitdetails.getType() == army.getType()) {

						players.decreaseArmies(army); 

					}
				}
			}
		}

	}

	public void attackingPhase() {

	}

}
