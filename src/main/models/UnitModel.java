package main.models;

import main.utills.EnumHandler;

public class UnitModel {
    private EnumHandler.UnitType type;
    private int unitCount;

    /**
     * @return the unitCount
     */
    public int getUnitCount() {
        return unitCount;
    }

    /**
     * @param unitCount the unitCount to set
     */
    public void setUnitCount(int unitCount) {
        this.unitCount = unitCount;
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
