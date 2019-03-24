package main.helpers;

import main.models.ContinentModel;
import main.models.CountryModel;
import main.models.GameModel;
import main.utills.GameCommons;
import main.utills.GameConstants;
import main.utills.GameException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MapBuilder {

    private GameModel gameModel;
    private GameCommons gameCommon;
    private ArrayList<String> mapDataList = new ArrayList<String>();
    private HashMap<String, ContinentModel> continents = new HashMap<String, ContinentModel>();
    FileReader mapFileReader;
    String mapLineData;

    public MapBuilder(GameModel gameModel) {
        this.gameModel = gameModel;
        this.gameCommon = new GameCommons();
    }

    /**
     * Processing of World.map file starts here
     *
     * @param mapFilePath This parameter will contain the path of map file
     * @throws GameException
     */
    public boolean readMapFile(String mapFilePath) throws GameException {
        if (mapFilePath == null) {
            mapFilePath = MapBuilder.class.getResource("/maps/world.map").getFile();
        }
        try {
            mapFileReader = new FileReader(mapFilePath);
            BufferedReader mapDataReader = new BufferedReader(mapFileReader);
            String mapLineData;
            while ((mapLineData = mapDataReader.readLine()) != null) {
                if (!mapLineData.equals("")) {
                    mapDataList.add(mapLineData);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadContinentMapData(mapDataList.indexOf("[Continents]"), mapDataList.indexOf("[Territories]"));
        loadCountryMapData(mapDataList.indexOf("[Territories]"), mapDataList.size());

        int isValid = 0;
        if (!validateCountriesBelongToOneContinent()) {
            System.out.println("Countries do not belong to one Continent");
            isValid++;
        }

        if(!validateContinentHasMinimumOneCountry()){
            System.out.println("A Continent should have at least one Country");
            isValid++;
        }

        if (!validateIfCountriesAreAdjacent()) {
            System.out.println("Countries are not adjacent");
            isValid++;
        }

        if (!validateContinentsAreAdjacent()) {
            System.out.println("Continent are not adjacent");
            isValid++;
        }

        return (isValid == 0);

    }

    /**
     * This Method will extract the Continent data from the map file
     *
     * @param initial This parameter holds the starting index of continents data in
     *                mapDataList
     * @param last    This parameter holds the ending index of continents data in
     *                mapDataList
     * @throws GameException
     */

    public void loadContinentMapData(int initial, int last) throws GameException {

        if (initial < 0) {
            throw new GameException("[Continents] attribute is missing for map file");
        }

        if (last < 0) {
            throw new GameException("[Continents] attribute is missing for map file");
        }

        int hasError = validateContinentData(initial, last);
        if (hasError >= 0) {
            throw new GameException("Invalid input at line number: [" + (hasError - 1) + "]");
        }
        for (int index = initial + 1; index < last; index++) {
            String continentData = mapDataList.get(index);
            String[] continentDataList = continentData.split(GameConstants.CONTINENT_DATA_SPLITTER);
            // Adding Continents to the list
            gameModel.addContinent(new ContinentModel(continentDataList[0], Integer.parseInt(continentDataList[1])));
        }
    }

    /**
     * This Method will extract the Country data from the map file
     *
     * @param initial This parameter holds the starting index of Territories data in
     *                mapDataList
     * @param last    This parameter holds the ending index of Territories data in
     *                mapDataList
     * @throws GameException
     */

    public void loadCountryMapData(int initial, int last) throws GameException {

        if (initial < 0) {
            throw new GameException("[Territories] attribute is missing for map file");
        }

        if (last < 0) {
            throw new GameException("[Territories] attribute is missing for map file");
        }

        int hasError = validateCountryData(initial, last);
        if (hasError >= 0) {
            throw new GameException("Invalid input at line number: [" + (hasError - 1) + "]");
        }
        ArrayList<String[]> countryMapDataList = new ArrayList<String[]>();
        int xAxis = 0;
        int yAxis = 0;
        ContinentModel continent;
        for (int index = initial + 1; index < last; index++) {
            // Get Each Line of the country
            String countryMapLine = mapDataList.get(index);
            String[] countryDataList = countryMapLine.split(GameConstants.COUNTRY_DATA_SPLITTER);
            if (gameCommon.tryParseInt(countryDataList[1])) {
                xAxis = Integer.parseInt(countryDataList[1]);
            } else {
                xAxis = 0;
            }
            if (gameCommon.tryParseInt(countryDataList[2])) {
                yAxis = Integer.parseInt(countryDataList[2]);
            } else {
                yAxis = 0;
            }
            continent = gameCommon.getContinentModelFromList(gameModel.getContinents(), countryDataList[3]);
            if (continent == null) {
                throw new GameException("Invalid Continent name at line number: [" + (index + 1) + "]");
            }
            gameModel.addCountry(new CountryModel(countryDataList[0], xAxis, yAxis, continent));
            countryMapDataList.add(countryDataList);
        }
        this.addAdjacentCountriesToCountry(countryMapDataList);
    }

    public void addAdjacentCountriesToCountry(ArrayList<String[]> countryMapDataList) throws GameException {
        /**
         * We are taking the adjacent countries from the list, which is starting from
         * the 4th index.
         */
        int startValue = 4;

        for (int i = 0; i < countryMapDataList.size(); i++) {
            ArrayList<CountryModel> countriesList = new ArrayList<CountryModel>();
            String[] countryMapData = countryMapDataList.get(i);

            String[] adjcentCountriesStringsList = Arrays.asList(countryMapData)
                    .subList(startValue, countryMapData.length).toArray(new String[0]);

            for (String countryString : adjcentCountriesStringsList) {

                CountryModel countryModel = gameCommon.getCountryModelFromList(gameModel.getCountries(), countryString);
                if (countryModel == null) {
                    throw new GameException("Country [" + countryString + "] does not exist");
                }
                countriesList.add(countryModel);
            }

            CountryModel countryModel = gameCommon.getCountryModelFromList(gameModel.getCountries(),
                    countryMapDataList.get(i)[0]);
            countryModel.setAdjcentCountries(countriesList);
        }

    }

    public int validateContinentData(int initial, int last) {
        for (int index = initial + 1; index < last; index++) {
            String continentData = mapDataList.get(index);
            String[] continentDataList = continentData.split(GameConstants.CONTINENT_DATA_SPLITTER);
            // validate that line has 2 valid elements the continent name and value;
            if (continentDataList.length != 2 || !gameCommon.tryParseInt(continentDataList[1])) {
                return index;
            }
        }
        return -1;
    }

    public int validateCountryData(int initial, int last) {
        for (int index = initial + 1; index < last; index++) {
            String countryMapLine = mapDataList.get(index);
            String[] countryDataList = countryMapLine.split(GameConstants.COUNTRY_DATA_SPLITTER);
            // validate that line has at least 1 adjacent country and
            // the x and y coordinates are valid;
            if (countryDataList.length < 5 || !gameCommon.tryParseInt(countryDataList[1])
                    || !gameCommon.tryParseInt(countryDataList[2])) {
                return index;
            }

            if (!validateIfContinentExists(countryDataList[3])) {
                return index;
            }

        }
        return -1;
    }

    public boolean validateIfContinentExists(String continentName) {
        if (gameCommon.getContinentModelFromList(gameModel.getContinents(), continentName) != null) {
            return true;
        }
        return false;
    }

    public boolean validateIfCountriesAreAdjacent() {
        ArrayList<String> countries = gameCommon.getCountriesList(gameModel.getCountries());
        int[][] countryAdjacencyValidation = new int[gameModel.getCountries().size()][gameModel.getCountries().size()];
        //filling countryAdjacencyValidation;
        for (int i = 0; i < gameModel.getCountries().size(); i++) {
            CountryModel country = gameModel.getCountries().get(i);
            for (int j = 0; j < country.getAdjcentCountries().size(); j++) {
                CountryModel adjacentCountry = country.getAdjcentCountries().get(j);
                //find index of adjacent country
                int x = countries.indexOf(adjacentCountry.getCountryName());

                countryAdjacencyValidation[i][x] = 1;
            }
        }
        //validate
        for (int i = 0; i < gameModel.getCountries().size(); i++) {
            for (int j = 0; j < gameModel.getCountries().size(); j++) {
                if (countryAdjacencyValidation[i][j] != countryAdjacencyValidation[j][i])
                    return false;
            }
        }
        // validate that no self relation exist
        for (int i = 0; i < gameModel.getCountries().size(); i++) {
            if (countryAdjacencyValidation[i][i] == 1)
                return false;
        }
        return true;
    }

    public boolean validateContinentsAreAdjacent() {
        int[] areAdjacent = new int[gameModel.getContinents().size()];
        for (int i = 0; i < gameModel.getContinents().size(); i++) {
            ArrayList<String> continentCountries = gameCommon.getCountriesList(gameModel.getContinents().get(i).getCountries());
            ContinentModel continent = gameModel.getContinents().get(i);
            for (CountryModel country : continent.getCountries()) {
                ArrayList<String> adjacentCountries = gameCommon.getCountriesList(country.getAdjcentCountries());
                if (!continentCountries.containsAll(adjacentCountries)) {
                    areAdjacent[i] = +1;
                }
            }
        }
        for (int x = 0; x < areAdjacent.length; x++) {
            if (areAdjacent[x] == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean validateCountriesBelongToOneContinent() {
        for (int i = 0; i < gameModel.getContinents().size() - 1; i++) {
            List<String> fromCountryNames = gameModel.getContinents().get(i).getCountries().stream()
                    .map(c -> c.getCountryName()).collect(Collectors.toList());
            Set<String> intersect = new HashSet<String>(fromCountryNames);
            for (int j = i + 1; j < gameModel.getContinents().size(); j++) {
                List<String> toCountryNames = gameModel.getContinents().get(j).getCountries().stream()
                        .map(c -> c.getCountryName()).collect(Collectors.toList());
                intersect.retainAll(toCountryNames);

                if (intersect.size() > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean validateContinentHasMinimumOneCountry() {
        for (int i = 0; i < gameModel.getContinents().size(); i++) {
            if (gameModel.getContinents().get(i).getCountries().size() == 0)
                return false;
        }
        return true;
    }

}
