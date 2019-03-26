package main.models;

import main.utills.EnumHandler;

public class UnitModel {
    private EnumHandler.UnitType type;
    private int unitCount;

    /**
     * Getter method for getting the unit count
     * @return the unitCount
     */
    public int getUnitCount() {
        return unitCount;
    }

    /**
     * Setter method for setting the unit count
     * @param unitCount the unitCount to set
     */
    public void setUnitCount(int unitCount) {
        this.unitCount = unitCount;
    }

    /**
     * Getter method for getting the type
     * @return the type
     */
    public EnumHandler.UnitType getType() {
        return type;
    }

    /**
     * Setter method for setting the type
     * @param type the type to set
     */
    public void setType(EnumHandler.UnitType type) {
        this.type = type;
    }
}
