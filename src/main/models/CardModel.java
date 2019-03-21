package main.models;

import main.utills.EnumHandler;

/**
 *
 * This is an entity class for CardModel
 *
 */
public class CardModel {

    private int numberOfCards;
    /**
     * @return the numberOfCards
     */
    public int getNumberOfCards() {
        return numberOfCards;
    }

    /**
     * @param numberOfCards the numberOfCards to set
     */
    public void setNumberOfCards(int numberOfCards) {
        this.numberOfCards = numberOfCards;
    }

    /**
     * The @cardType .
     */
    private EnumHandler.UnitType cardType;

    /**
     * @param cardType the cardType to set
     */
    public CardModel(EnumHandler.UnitType cardType) {
        this.cardType = cardType;
    }

    /**
     * @return the cardType
     */
    public EnumHandler.UnitType getCardType() {
        return cardType;
    }

    /**
     * @param cardType the cardType to set
     */
    public void setCardType(EnumHandler.UnitType cardType) {
        this.cardType = cardType;
    }
}
