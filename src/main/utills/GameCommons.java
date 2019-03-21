package main.utills;

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
        continentModel = continentList.stream()
                .filter(continent -> continent.getContinentName().contentEquals(continentName))
                .findFirst().get();
        return continentModel;
    }

    public CountryModel getCountryModelFromList(ArrayList<CountryModel> countriesList, String countryName) {
        CountryModel countryModel = null;
        countryModel = countriesList.stream()
                .filter(country -> country.getCountryName().contentEquals(countryName))
                .findFirst().get();
        return countryModel;
    }
}
