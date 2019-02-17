package resources;

import resources.EnumClass.UnitType;
import model.Country;
/**
 * 
 * This is an entity class for Cards
 * @author Harish Jayasankar
 *
 */

public class Cards {
	/**
	 * The @cardType .
	 */
	private UnitType cardType;

	/**
	 * The @territory .
	 */
	private Country territory;

     /**
	 * @return the cardType
	 */
	public UnitType getCardType() {
		return cardType;
	}

	/**
	 * @param cardType the cardType to set
	 */
	public Cards(UnitType cardType) {
		this.cardType = cardType;
	}

	/**
	 * @return the territory
	 */
	public Country getTerritory() {
		return territory;
	}

	/**
	 * @param territory the territory to set
	 */
	public void setTerritory(Country territory) {
		this.territory = territory;
	}


	
	
}
