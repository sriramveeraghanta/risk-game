package controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import models.ContinentModel;
import models.CountryModel;
import models.GameModel;
import utils.Common;
import utils.GameConstant;

/**
 * In this file ,we will retrieve the map data from World.map file and we will
 * set those values in model classes
 * 
 */
public class MapBuilder {

	private GameModel gameModel;
	private Common gameCommon;
	private ArrayList<String> mapDataList = new ArrayList<String>();
	FileReader mapFileReader;
	String mapLineData;

	public MapBuilder(GameModel gameModel) {
		this.gameModel = gameModel;
		gameCommon = new Common();
	}

	/**
	 * Processing of World.map file starts here
	 * 
	 * @param mapFilePath This parameter will contain the path of map file
	 *
	 */
	public void readMapFile(String mapFilePath) {
		if (mapFilePath == null) {
			mapFilePath = GameConstant.MAP_FILE_PATH;
		}

		try {
			mapFileReader = new FileReader(mapFilePath);
			BufferedReader mapDataReader = new BufferedReader(mapFileReader);
			while ((mapLineData = mapDataReader.readLine()) != null) {
				if (!mapLineData.equals("")) {
					mapDataList.add(mapLineData);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		continentMapData(mapDataList.indexOf("[Continents]"), mapDataList.indexOf("[Territories]"));

		countryMapData(mapDataList.indexOf("[Territories]"), mapDataList.size());

	}

	/**
	 * This Method will extract the Continent data from the map file
	 * 
	 * @param mapDataList This parameter contains all the map data as a List
	 * @param initial     This parameter holds the starting index of continents data
	 *                    in mapDataList
	 * @param last        This parameter holds the ending index of continents data
	 *                    in mapDataList
	 * @param spliter     This parameter holds "=" as splitter value for splitting
	 *                    continent names and values
	 */

	public void continentMapData(int initial, int last) {
		ArrayList<ContinentModel> continentsList = new ArrayList<ContinentModel>();

		for (int range = initial + 1; range < last; range++) {
			// gets the the data from the list (North America=5)
			String continentData = mapDataList.get(range);
			// Splitting the string using "="
			String[] continentDataList = continentData.split(GameConstant.CONTINENT_DATA_SPLITTER);
			// Creating New Continent
			ContinentModel continentModel = new ContinentModel(continentDataList[0],
					Integer.parseInt(continentDataList[1]));
			// Adding Continents to the list
			continentsList.add(continentModel);
		}
		gameModel.setContinents(continentsList);
	}

	/**
	 * This Method will extract the Continent data from the map file
	 * 
	 * @param initial This parameter holds the starting index of Territories data in
	 *                mapDataList
	 * @param last    This parameter holds the ending index of Territories data in
	 *                mapDataList
	 * @param spliter This parameter holds "," as splitter value for splitting
	 *                countries and its further details like coordinates.
	 */

	public void countryMapData(int initial, int last) {
		// CountryModel countryModel;
		ArrayList<CountryModel> countryArrayList = new ArrayList<CountryModel>();
		ArrayList<String[]> countryMapDataList = new ArrayList<String[]>();
		/**
		 * This for loop is for extracting each single line in the file. Each line will
		 * have specific country details.
		 */
		for (int range = initial + 1; range < last; range++) {
			// Get Each Line of the country
			String countryMapLine = mapDataList.get(range);
			// Splitting with ","
			String[] countryDataList = countryMapLine.split(GameConstant.COUNTRY_DATA_SPLITTER);
			/**
			 * On the 4th position of the line we have continent string value Using the
			 * string value we are getting the continent Object from the list of Continents.
			 */
			ContinentModel continent = gameCommon.getContinentModelFromList(gameModel.getContinents(),
					countryDataList[3]);
			CountryModel countryModel = new CountryModel(countryDataList[0], Integer.parseInt(countryDataList[1]),
					Integer.parseInt(countryDataList[2]), continent);
			countryArrayList.add(countryModel);
			countryMapDataList.add(countryDataList);
		}
		gameModel.setCountries(countryArrayList);
		this.addAdjcentCountriesToCountry(countryMapDataList);
	}

	private void addAdjcentCountriesToCountry(ArrayList<String[]> countryMapDataList) {

		/**
		 * We are taking the adjacent countries from the list, which is starting from
		 * the 4th index.
		 */
		int startValue = 4;

		for (int i = 0; i < countryMapDataList.size(); i++) {
			ArrayList<CountryModel> countriesList = new ArrayList<CountryModel>();
			// System.out.println(countryMapDataList.get(i)[0]);
			String[] countryMapData = countryMapDataList.get(i);

			String[] adjcentCountriesStringsList = Arrays.asList(countryMapData)
					.subList(startValue, countryMapData.length).toArray(new String[0]);

			for (String countryString : adjcentCountriesStringsList) {
				CountryModel countryModel = gameCommon.getCountryModelFromList(gameModel.getCountries(), countryString);
				countriesList.add(countryModel);
			}

			CountryModel countryModel = gameCommon.getCountryModelFromList(gameModel.getCountries(),
					countryMapDataList.get(i)[0]);
			countryModel.setAdjcentCountries(countriesList);
		}

	}
}