/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;

import model.EnumClass.Color;

/**
 * @author edosh
 *
 */
public class PlayerModel {

	private String name;
	private Color color;
	private ArrayList<CountryModel> countryModels;
	private ArrayList<ContinentModel> continentModels;
	private UnitModel Unit;
	private ArrayList<UnitModel> army_in_hands;
	private int index;// to find the turn of the player
	private ArrayList<CardModel> cards;
	private int turnInCount;
	private HashMap<CardModel, Integer> playercards;
	private int total_army_count;
	private ArrayList<CardModel> army_swapping_cards;


	/**
	 * 
	 */
	public PlayerModel(String name, Color color, int index, UnitModel unit) {
		this.setIndex(index);
		this.setName(name);
		this.setColor(color);
		this.setUnit(unit);
		army_in_hands.add(unit);
		turnInCount = 0;
		total_army_count=unit.getUnitNumber();
	}

	/**
	 * @return the playercards
	 */
	public HashMap<CardModel, Integer> getPlayercards() {
		return playercards;
	}

	/**
	 * @param playercards the playercards to set
	 */
	public void setPlayercards(CardModel riskCard) {
		if (playercards == null) {
			playercards.put(riskCard, 1);
		} else {
			for (CardModel cardtype : playercards.keySet()) {
				if (cardtype.equals(riskCard)) {
					int cardcount = playercards.get(cardtype);
					playercards.put(riskCard, cardcount + 1);

				}
			}
		}
	}

	/**
	 * @return the unit
	 */
	public UnitModel getUnit() {
		return Unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(UnitModel unit) {
		Unit = unit;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the colour to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the countryModels
	 */
	public ArrayList<CountryModel> getCountries() {
		return countryModels;
	}

	/**
	 * @param countryModels the countryModels to set
	 */
	public void setCountries(String country) {
		countryModels.add(new CountryModel(country));
	}

	/**
	 * @return the continentModels
	 */
	public ArrayList<ContinentModel> getContinents() {
		return continentModels;
	}

	/**
	 * @param continentModels the continentModels to set
	 */
	public void setContinents(ArrayList<ContinentModel> continentModels) {
		this.continentModels = continentModels;
	}

	/**
	 * @return the army
	 */
	public ArrayList<UnitModel> getArmy_in_hands() {
		return army_in_hands;
	}

	/**
	 * @param army the army to set
	 */
	public void setArmy_in_hands(UnitModel armydetail) {
		army_in_hands.add(armydetail);

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
	public void setCards(CardModel card) {
		cards.add(card);
		// this.cards = cards;
	}

	public void addRiskCard(CardModel riskCard) {

		cards.add(riskCard);
	}

	/**
	 * @param turnInCount the turnInCount to set
	 */
	public void setTurnInCount(int turnInCount) {
		turnInCount++;
		this.turnInCount = turnInCount;
	}

	public int getTurnInCount() {
		return turnInCount;
	}

	public void decreaseArmies(UnitModel armydetail) {

		if (army_in_hands != null) {
			for (UnitModel units : army_in_hands) {
				if (units.getType().equals(armydetail.getType())) {
					units.setUnitNumber(units.getUnitNumber() - armydetail.getUnitNumber());
				}
			}
		}
	}

	

	public void increaseArmies(UnitModel armydetail) {
		int addcount = 0;
		if (army_in_hands != null) {
			for (UnitModel units : army_in_hands) {
				if (units.getType().equals(armydetail.getType())) {
					units.setUnitNumber(units.getUnitNumber() + armydetail.getUnitNumber());
					addcount++;
				}
			}
		}
		if (addcount == 0) {
			army_in_hands.add(armydetail);
		}
		
		total_army_count=total_army_count + armydetail.getUnitNumber();
	}
	
	/**
	 * @return the total_army_count
	 */
	public int getTotal_army_count() {
		return total_army_count;
	}

	/**
	 * @param total_army_count the total_army_count to set
	 */
	public void setTotal_army_count(int total_army_count) {
		this.total_army_count = total_army_count;
	}

	
	/**
	 * @return the army_swapping_cards
	 */
	public ArrayList<CardModel> getArmy_swapping_cards() {
		return army_swapping_cards;
	}

	/**
	 * @param army_swapping_cards the army_swapping_cards to set
	 */
	public void setArmy_swapping_cards(CardModel card) {
		army_swapping_cards.add(card);
	}
}
