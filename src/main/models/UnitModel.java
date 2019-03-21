package main.models;

import main.utills.EnumHandler;

public class UnitModel {

    private EnumHandler.UnitType type;
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
    public EnumHandler.UnitType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(EnumHandler.UnitType type) {
        this.type = type;
    }
}
