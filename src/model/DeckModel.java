package model;

import java.util.ArrayList;
import java.util.Collections;
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

	private CardModel drawCard;
	private Stack<CardModel> stackOfCards;

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
			card.setTerritory(countryModels.get(countryCount));
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
	public void add(CardModel card) {

		stackOfCards.add(card);
	}

	/**
	 * Shuffle the deck of cards
	 **/
	public void shuffle() {

		Collections.shuffle(stackOfCards);
	}
}