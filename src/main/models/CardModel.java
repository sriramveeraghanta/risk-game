package main.models;

import main.utills.EnumHandler;

import java.io.Serializable;

/**
 *
 * This is an entity class for CardModel
 *
 */
public class CardModel implements Serializable {

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
