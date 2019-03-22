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
     *
     */
    public void readMapFile(String mapFilePath) throws GameException {
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
     * This Method will extract the Continent data from the map file
     *
     * @param initial This parameter holds the starting index of Territories data in
     *                mapDataList
     * @param last    This parameter holds the ending index of Territories data in
     *                mapDataList
     * @throws GameException
     */

    public void loadCountryMapData(int initial, int last) throws GameException {
        int hasError = validateCountryData(initial, last);
        if (hasError >= 0) {
            throw new GameException("Invalid input at line number: [" + (hasError - 1) + "]");
        }
        ArrayList<String[]> countryMapDataList = new ArrayList<String[]>();
        for (int index = initial + 1; index < last; index++) {
            // Get Each Line of the country
            String countryMapLine = mapDataList.get(index);
            String[] countryDataList = countryMapLine.split(GameConstants.COUNTRY_DATA_SPLITTER);
            gameModel.addCountry(new CountryModel(countryDataList[0], Integer.parseInt(countryDataList[1]),
                    Integer.parseInt(countryDataList[2]),
                    gameCommon.getContinentModelFromList(gameModel.getContinents(), countryDataList[3])));
            countryMapDataList.add(countryDataList);
        }
        this.addAdjcentCountriesToCountry(countryMapDataList);
    }

    public void addAdjcentCountriesToCountry(ArrayList<String[]> countryMapDataList) throws GameException {
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

    public void createOrUpdateContinent(String continentName, int controlValue) {
        ContinentModel continent = gameCommon.getContinentModelFromList(gameModel.getContinents(), continentName);
        if (continent == null) {
            continent = new ContinentModel(continentName, controlValue);
        } else {
            ContinentModel theContinent = gameModel.getContinents().get(gameModel.getContinents().indexOf(continent));
            theContinent.setControlValue(controlValue);
        }
    }

    public void addNewCountry(String countryName, int xCoordinate, int yCoordinate, String continentName)
            throws GameException {
        if (gameCommon.getCountryModelFromList(gameModel.getCountries(), countryName) != null) {
            throw new GameException("Country already exists");
        }
        gameModel.addCountry(new CountryModel(countryName, xCoordinate, yCoordinate,
                gameCommon.getContinentModelFromList(gameModel.getContinents(), continentName)));
    }

    public void removeCountry(String countryName) throws GameException {
        CountryModel country = gameCommon.getCountryModelFromList(gameModel.getCountries(), countryName);
        if (country == null) {
            throw new GameException("Country [" + countryName + "] does not exist");
        }
        gameModel.getCountries().remove(country);
    }

    public void updateCountry(String countryName, int xAxis, int yAxis, String continentName) throws GameException {
        CountryModel country = gameCommon.getCountryModelFromList(gameModel.getCountries(), countryName);
        if (country == null) {
            throw new GameException("Country [" + countryName + "] does not exist");
        } else {
            CountryModel theCountry = gameModel.getCountries().get(gameModel.getCountries().indexOf(country));
            theCountry.setxAxis(xAxis);
            theCountry.setyAxis(yAxis);
        }
    }

    public void assignCountryToContinent(String countryName, String continentName) throws GameException {
        ContinentModel continent = gameCommon.getContinentModelFromList(gameModel.getContinents(), continentName);
        CountryModel country = gameCommon.getCountryModelFromList(gameModel.getCountries(), countryName);
        if (country == null) {
            throw new GameException("Country [" + countryName + "] does not exist");
        }
        if (continent == null) {
            throw new GameException("Continent [" + continentName + "] does not exist");
        }
        ContinentModel theContinent = gameModel.getContinents().get(gameModel.getContinents().indexOf(continent));
        theContinent.addCountry(country);
    }

    public void addAdjacentCountries() {

    }

    private int validateContinentData(int initial, int last) {
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

    private int validateCountryData(int initial, int last) {
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

    private boolean validateIfContinentExists(String continentName) {
        if (gameCommon.getContinentModelFromList(gameModel.getContinents(), continentName) != null) {
            return true;
        }
        return false;
    }

    private boolean validateIfCountriesAreAdjacent(ArrayList<String[]> countryMapDataList) {
        return false;
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

}
