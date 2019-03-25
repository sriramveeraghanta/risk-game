package main.utills;

import javafx.scene.paint.Color;
import main.models.ContinentModel;
import main.models.CountryModel;

import java.util.ArrayList;

public class GameCommons {

    public GameCommons() {

    }

    public boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public ContinentModel getContinentModelFromList(ArrayList<ContinentModel> continentList, String continentName) {
        ContinentModel continentModel = null;
        try {
            continentModel = continentList.stream()
                    .filter(continent -> continent.getContinentName().contentEquals(continentName))
                    .findFirst().get();
        }catch (Exception e){
            System.out.println(e);
        }

        return continentModel;
    }

    public CountryModel getCountryModelFromList(ArrayList<CountryModel> countriesList, String countryName) {
        CountryModel countryModel = null;
        try {
            countryModel = countriesList.stream()
                    .filter(country -> country.getCountryName().contentEquals(countryName))
                    .findFirst().get();
        }catch (Exception e){
            System.out.println(e);
        }

        return countryModel;
    }

    public ArrayList<String> getCountryList(ArrayList<CountryModel> countryList) {
        ArrayList<String> countries = new ArrayList<String>();
        for (CountryModel country : countryList) {
            countries.add(country.getCountryName());
        }
        return countries;
    }

    public ArrayList<String> getContinentList(ArrayList<ContinentModel> continentList) {
        ArrayList<String> continents = new ArrayList<String>();
        for (ContinentModel continent : continentList) {
            continents.add(continent.getContinentName());
        }
        return continents;
    }

    public Color getFXColor(String color) {
        if(color.equals("BLACK")) {
            return Color.BLACK;
        } else if(color.equals("BLUE")){
            return Color.BLUE;
        }else if(color.equals("GREEN")){
            return Color.GREEN;
        } else if(color.equals("PINK")){
            return Color.PINK;
        }else if(color.equals("RED")){
            return Color.RED;
        }else if(color.equals("YELLOW")){
            return Color.YELLOW;
        } else {
            return Color.GREY;
        }
    }
}
