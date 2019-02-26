package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import model.EnumClass.UnitType;

/**
 * 
 * This Class will create and handle risk cards .
 * 
 * @author Harish Jayasankar
 *
 */

public class DeckModel {

	private int countryCount;
	private static ArrayList<PlayerModel> players;
	private CardModel drawCard;
	 static Stack<CardModel> stackOfCards;

	/**
	 * Creates all 42 cards, one for each territory. Each card has either a type of
	 * Infantry, Cavalry, or Artillery.
	 * 
	 **/
	public Stack<CardModel> assignValueToCards(ArrayList<CountryModel> countryModels) {

		stackOfCards = new Stack<CardModel>();

		for (countryCount = 0; countryCount < countryModels.size(); countryCount++) {
			CardModel card = new CardModel(UnitType.values()[countryCount / 14]);
			stackOfCards.push(card);
		}
		return stackOfCards;
	}

	/**
	 * Removes a card from the deck and return it
	 **/
	public CardModel draw() {

		drawCard = stackOfCards.get(0);
		stackOfCards.remove(0);

		return drawCard;
	}

	/**
	 * Add a card to the deck
	 **/
	public static void add(CardModel card) {

		stackOfCards.add(card);
	}

	/**
	 * Shuffle the deck of cards
	 **/
	public void shuffle() {

		Collections.shuffle(stackOfCards);
	}

	/// this method will assign card to players.
	public void assignCard(PlayerModel players) {
		CardModel card = stackOfCards.pop();
		players.setPlayercards(card);
	}

	/// this method will check whether the player is eligible for card turnin
	public static boolean checkCard(PlayerModel players) {
		boolean returnFlag = false;
		List<Integer> count = new ArrayList<Integer>();

		if (players.getPlayercards() != null && players.getPlayercards().size() >= 3) {
			returnFlag = true;
		}
		return returnFlag;

	}

	public void cardsArmySwapping(PlayerModel player) {

		player.setTurnInCount(player.getTurnInCount() + 1);
		UnitModel unit = null;
		unit.setUnitNumber((5 * player.getTurnInCount()));
		unit.setType(UnitType.INFANTRY);
		player.increaseArmies(unit);

		/// to remove cards from player and add it to deck

		for (CardModel getcards : player.getArmy_swapping_cards()) {
			for (CardModel card : player.getPlayercards().keySet()) {
				if (card.equals(getcards)) {
					DeckModel.add(card);
					int count = player.getPlayercards().get(card);
					player.getPlayercards().put(card, count - 1);
				}
			}

		}

	}

}