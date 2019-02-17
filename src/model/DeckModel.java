package resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import resources.EnumClass.UnitType;
import model.Country;
/**
 * 
 * This Class will create and handle risk cards .
 * @author Harish Jayasankar
 *
 */

public class Deck {

	
	private int countryCount;
	
	
	private Cards drawCard;
	private Stack<Cards> stackOfCards;
	/**
	* Creates all 42 cards, one for each territory. Each card has either 
	* a type of Infantry, Cavalry, or Artillery. 
	 * 
	**/
	public Stack<Cards> assignValueToCards (ArrayList<Country> countries) {		
	
		stackOfCards= new Stack<Cards>();

		
		for (countryCount = 0; countryCount < countries.size(); countryCount++) {
			///Cards card = new Cards(UnitType.values()[(int) (Math.random() * UnitType.values().length)]);
			//deck.add(new Card(typesArray[i / 14], countries.get(i)));
			Cards card = new Cards(UnitType.values()[countryCount / 14]);
			card.setTerritory(countries.get(countryCount));
			stackOfCards.push(card);
		}
		
		return stackOfCards;
	}

	/**
	* Removes a card from the deck and return it
	**/
	public Cards draw() {
	
		drawCard = stackOfCards.get(0);
		stackOfCards.remove(0);
		
		return drawCard;
	}

	/**
	* Add a card to the deck
	**/
	public void add(Cards card) {
	
		stackOfCards.add(card);
	}

	/**
	* Shuffle the deck of cards
	**/
	public void shuffle() {
	
		Collections.shuffle(stackOfCards);
	}
}
