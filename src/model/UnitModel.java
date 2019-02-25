package model;

import model.EnumClass.Color;
import model.EnumClass.UnitType;

public class UnitModel {
	
	private UnitType type;
	private int unitNumber;

	public UnitModel(int unitNumber,UnitType type) {
		this.setUnitNumber(unitNumber);
		this.setType(type);
	}
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
