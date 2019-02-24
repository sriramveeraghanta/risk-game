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
	private static HashMap<CardModel, Integer> findingcard = new HashMap<>();

	/**
	 * Creates all 42 cards, one for each territory. Each card has either a type of
	 * Infantry, Cavalry, or Artillery.
	 * 
	 **/
	public Stack<CardModel> assignValueToCards(ArrayList<CountryModel> countryModels) {

		stackOfCards = new Stack<CardModel>();

		for (countryCount = 0; countryCount < countryModels.size(); countryCount++) {
			/// CardModel card = new CardModel(UnitType.values()[(int) (Math.random() *
			/// UnitType.values().length)]);
			// deck.add(new Card(typesArray[i / 14], countries.get(i)));
			CardModel card = new CardModel(UnitType.values()[countryCount / 14]);
			//// card.setTerritory(countryModels.get(countryCount)); need to be checked
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
	public void assignCard(int index) {
		CardModel card = stackOfCards.pop();
		players.get(index).setPlayercards(card);
	}

	/// this method will check whether the player is eligible for card turnin
	public static boolean checkCard(int index) {
		boolean returnFlag = false;
		findingcard = players.get(index).getPlayercards();
		/// List<String>samecard=new ArrayList<String>();
		boolean different_card = false;
		int different_card_combination;
		List<Integer> count = new ArrayList<Integer>();

		if (findingcard != null && findingcard.size() >= 3) {
			different_card = true;
			for (CardModel i : findingcard.keySet()) {
				count.add(findingcard.get(i));
			}

			different_card_combination = Collections.min(count);
			returnFlag = true;
		}
		return returnFlag;

	}

	public void cardsArmySwapping(ArrayList<CardModel> cards, PlayerModel player) {
         
		    player.setTurnInCount(player.getTurnInCount() + 1);
			UnitModel unit = null;
			unit.setUnitNumber((5 * player.getTurnInCount()));
			unit.setType(UnitType.INFANTRY);
			player.increaseArmies(unit);
			
		

		/// to remove cards from player and add it to deck

		for (CardModel getcards : cards) {
			for (CardModel i : findingcard.keySet()) {
				if (i.equals(getcards)) {
					DeckModel.add(i);
					int count = findingcard.get(i);
					findingcard.put(i, count - 1);
				}
			}

		}

	}

	/*
	 * private HashMap<String, Integer> findcards(List<CardModel> cards) {
	 * 
	 * if (cards.size() >= 3) { int infantry = 0, cavalry = 0, artillery = 0;
	 * 
	 * for (CardModel card : cards) { if
	 * (card.getCardType().toString().equals(UnitType.INFANTRY.toString())) {
	 * 
	 * infantry++; findingcard.put(card.getCardType().toString(), infantry); } else
	 * if (card.getCardType().toString().equals(UnitType.CAVALRY.toString())) {
	 * cavalry++; findingcard.put(card.getCardType().toString(), cavalry); } else if
	 * (card.getCardType().toString().equals(UnitType.ARTILLERY.toString())) {
	 * artillery++; findingcard.put(card.getCardType().toString(), artillery); } }
	 * 
	 * 
	 * 
	 * 
	 * } return findingcard; }
	 */

}
