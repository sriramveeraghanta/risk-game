package main.utills;

import javafx.scene.paint.Color;
import main.models.ContinentModel;
import main.models.CountryModel;

import java.util.ArrayList;

/**
 * Some common methods like parsing , getting color
 */
public class GameCommons {
    /**
     * constructor for GameCommon class
     */
    public GameCommons() {

    }

    /**
     * casting the string to integer
     * @param value string
     * @return true or false if it is possible to parse it
     */
    public boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * getting the continent model according to the parameters
     * @param continentList array list of the continents
     * @param continentName name of the continent
     * @return the continent model
     */
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

    /**
     * Getting the country from the list according to the parameters
     * @param countriesList arraty list of countries
     * @param countryName name of the country
     * @return the country model
     */
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

    /**
     * Getting the array list of  countries
     * @param countryList array list of countries
     * @return the array list of countries
     */
    public ArrayList<String> getCountriesList(ArrayList<CountryModel> countryList) {
        ArrayList<String> countries = new ArrayList<String>();
        for (CountryModel country : countryList) {
            countries.add(country.getCountryName());
        }
        return countries;
    }

    /**
     * Getting the array list of continents
     * @param continentList array list of the continents
     * @return the array list of continents
     */
    public ArrayList<String> getContinentList(ArrayList<ContinentModel> continentList) {
        ArrayList<String> continents = new ArrayList<String>();
        for (ContinentModel continent : continentList) {
            continents.add(continent.getContinentName());
        }
        return continents;
    }

    /**
     * Getting the color of player
     * @param color string color name
     * @return the color type of Color
     */
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

    /**
     * Getting the attacker adjacent countries according to the parameters below
     * @param adjcentCountries array list of adjacent countries
     * @param playerCountries array list of players countries
     * @return array list of country model which are adjacent
     */
    public ArrayList<CountryModel> getAttackerAdjcentCountires(ArrayList<CountryModel> adjcentCountries, ArrayList<CountryModel> playerCountries) {
        ArrayList<CountryModel> adjacentList = new ArrayList<>();

        for(CountryModel country: adjcentCountries) {
            if(!playerCountries.contains(country)){
                adjacentList.add(country);
            }
        }
        return adjacentList;
    }
}
