package main.models;

import main.utills.EnumHandler;

import java.util.ArrayList;
import java.util.List;

public class CountryModel {

    private String countryName;
    private int xAxis;
    private int yAxis;
    private ContinentModel assignedContinent;
    private UnitModel armyInCountry;
    private ArrayList<CountryModel> adjacentCountries;

    /**
     * CountryModel constructor to create countryModel object for each initialisation
     */
    public CountryModel(String contryName, int xAxis,int yAxis, ContinentModel assignedContinent) {
        this.setCountryName(contryName);
        this.setxAxis(xAxis);
        this.setyAxis(yAxis);
        this.setAssignedContinent(assignedContinent);
    }

    /**
     * Getter and setter methods for Country Name
     * */
    public String getCountryName() {
        return countryName;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Getter and setter methods for x axis
     * */
    public int getxAxis() {
        return xAxis;
    }
    public void setxAxis(int xAxis) {
        this.xAxis = xAxis;
    }

    /**
     * Getter and setter methods for Y-Axis
     * */
    public int getyAxis() {
        return yAxis;
    }
    public void setyAxis(int yAxis) {
        this.yAxis = yAxis;
    }


    /**
     * Getter and setter methods for Continent
     * */
    public ContinentModel getAssignedContinent() {
        return assignedContinent;
    }
    public void setAssignedContinent(ContinentModel assignedContinent) {
        this.assignedContinent = assignedContinent;
    }

    /**
     * Getter and setter methods for
     * */
    public UnitModel getArmyInCountry() {
        return armyInCountry;
    }
    public void setArmyInCountry(UnitModel armyInCountry) {
        this.armyInCountry = armyInCountry;
    }

    /**
     * Getter and setter methods for Adjacent Countries
     * */
    public ArrayList<CountryModel> getAdjacentCountries() {
        return adjacentCountries;
    }
    public void setAdjacentCountries(ArrayList<CountryModel> adjacentCountries) {
        this.adjacentCountries = adjacentCountries;
    }

    public void addUnitsToCountry(int unitsCount){
        this.getArmyInCountry().setUnitCount(this.getArmyInCountry().getUnitCount() + unitsCount);
    }


//    public void setArmy(ArrayList<UnitModel> army) {
//        this.armyInCountry = army;
//        List<UnitModel> armyList = this.armyInCountry;
//        UnitModel soldier = armyList.stream().filter(a -> a.getType().equals(EnumHandler.UnitType.INFANTRY)).findFirst().get();
//        if (soldier != null) {
//            setNumberOfUnits(soldier.getUnitCount());
//        }
//    }
}
