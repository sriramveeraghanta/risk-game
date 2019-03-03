package models;

import utils.EnumClass.UnitType;

/**
 * 
 * This is an entity class for CardModel
 * 
 */

public class CardModel {
	/**
	 * The @cardType .
	 */
	private UnitType cardType;

	/**
	 * @param cardType the cardType to set
	 */
	public CardModel(UnitType cardType) {
		this.cardType = cardType;
	}

	/**
	 * @return the cardType
	 */
	public UnitType getCardType() {
		return cardType;
	}

	/**
	 * @param cardType the cardType to set
	 */
	public void setCardType(UnitType cardType) {
		this.cardType = cardType;
	}

}
