package main.models;

import main.utills.EnumHandler;

import java.io.Serializable;

/**
 *
 * This is an entity class for CardModel
 *
 */
public class CardModel implements Serializable {

    private int numberOfCards;
    /**
     * Getter method to get number of cards
     * @return the numberOfCards
     */
    public int getNumberOfCards() {
        return numberOfCards;
    }

    /**
     * Setter method to set the number of cards
     * @param numberOfCards the numberOfCards to set
     */
    public void setNumberOfCards(int numberOfCards) {
        this.numberOfCards = numberOfCards;
    }

    /**
     * The @cardType.
     */
    private EnumHandler.CardType cardType;

    /**
     * Constructor for card model class
     * @param cardType the cardType to set
     */
    public CardModel(EnumHandler.CardType cardType) {
        setCardType(cardType);
    }

    /**
     * getting the number of cards
     * @return the cardType
     */
    public EnumHandler.CardType getCardType() {
        return cardType;
    }

    /**
     * stting the number of cards
     * @param cardType the cardType to set
     */
    public void setCardType(EnumHandler.CardType cardType) {
        this.cardType = cardType;
    }
}
