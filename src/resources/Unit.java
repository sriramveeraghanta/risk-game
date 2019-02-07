package resources;

import common.EnumClass.UnitType;

public class Unit {
	
	private UnitType type;
	private int unitNumber;

	/**
	 * @return the unitNumber
	 */
	public int getUnitNumber() {
		return unitNumber;
	}

	/**
	 * @param unitNumber the unitNumber to set
	 */
	public void setUnitNumber(int unitNumber) {
		this.unitNumber = unitNumber;
	}

	/**
	 * @return the type
	 */
	public UnitType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(UnitType type) {
		this.type = type;
	}
}
