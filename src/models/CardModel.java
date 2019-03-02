package models;

import models.EnumClass.UnitType;

/**
 * 
 * This is an entity class for CardModel
 * 
 * @author Harish Jayasankar
 *
 */

public class CardModel {
	/**
	 * The @cardType .
	 */
	private UnitType cardType;

	/**
	 * The @territory .
	 */
	private CountryModel territory;

	/**
	 * @return the cardType
	 */
	public UnitType getCardType() {
		return cardType;
	}

	/**
	 * @param cardType the cardType to set
	 */
	public CardModel(UnitType cardType) {
		this.cardType = cardType;
	}

	/**
	 * @return the territory
	 */
	public CountryModel getTerritory() {
		return territory;
	}

	/**
	 * @param territory the territory to set
	 */
	public void setTerritory(CountryModel territory) {
		this.territory = territory;
	}

}
