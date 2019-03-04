package controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import models.ContinentModel;
import models.CountryModel;
import models.GameModel;
import utils.GameConstant;

/**
 * In this file ,we will retrieve the map data from World.map file and we will
 * set those values in model classes
 * 
 */
public class MapFileDataExtraction {

	private GameModel gameModel;
	private ArrayList<String> mapDataList;

	public MapFileDataExtraction() {
		this.gameModel = new GameModel();
	}

	/**
	 * Processing of World.map file starts here
	 * 
	 * @param mapFilePath This parameter will contain the path of map file
	 *
	 */
	public void mapFilePocessing() throws IOException {
		mapDataList = new ArrayList<String>();
		FileReader mapFileReader = new FileReader(GameConstant.MAP_FILE_PATH);
		BufferedReader mapDataReader = new BufferedReader(mapFileReader);
		String mapData;

		while ((mapData = mapDataReader.readLine()) != null) {
			if (!mapData.equals("")) {
				mapDataList.add(mapData);
			}
		}
		continentMapData(mapDataList.indexOf("[Continents]"), mapDataList.indexOf("[Territories]"),
				GameConstant.CONTINENT_DATA_SPLITTER);

		countryMapData(mapDataList.indexOf("[Territories]"), mapDataList.size(), GameConstant.COUNTRY_DATA_SPLITTER);
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

	public void continentMapData(int initial, int last, String spliter) {
		String continent;
		ContinentModel continentModel;
		ArrayList<ContinentModel> continentsList = new ArrayList<ContinentModel>();
		for (int range = initial + 1; range < last; range++) {
			continent = mapDataList.get(range);
			String[] continent_data = continent.split(spliter);
			if (spliter.equals("=")) {
				continentModel = new ContinentModel(continent_data[0], Integer.parseInt(continent_data[1]));
				continentsList.add(continentModel);
			}
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

	public void countryMapData(int initial, int last, String spliter) {
		// CountryModel countryModel;
		ArrayList<CountryModel> countryDetailList = new ArrayList<CountryModel>();
		ArrayList<CountryModel> adjacentcountryList = null;
		ArrayList<String> countryArrayList = null;
		/**
		 * This for loop is for extracting each single line in the file. Each line will
		 * have specific country details.
		 */
		for (int range = initial + 1; range < last; range++) {
			String country = mapDataList.get(range);
			String[] country_data = country.split(spliter);
			countryArrayList = new ArrayList<String>();
			for (String countryDetails : country_data) {
				countryArrayList.add(countryDetails);
			}
			if (countryArrayList != null && countryArrayList.size() > 0) {
				for (ContinentModel continent : gameModel.getContinents()) {
					if (countryArrayList.get(3).equalsIgnoreCase(continent.getContinentName())) {
						CountryModel countryModel = new CountryModel(countryArrayList.get(0),
								Integer.parseInt(countryArrayList.get(1)), Integer.parseInt(countryArrayList.get(2)),
								continent);
						countryDetailList.add(countryModel);
					}
				}
			}
		}
		//// Setting countries list in game model
		if (countryDetailList != null && countryDetailList.size() > 0) {
			gameModel.setCountries(countryDetailList);
		}
		//// Updating countries model object with its adjacent countries list
		for (int range = initial + 1; range < last; range++) {
			String country = mapDataList.get(range);
			String[] country_data = country.split(spliter);
			countryArrayList = new ArrayList<String>();
			adjacentcountryList = new ArrayList<CountryModel>();
			for (String countryDetails : country_data) {
				countryArrayList.add(countryDetails);
			}
			if (countryArrayList != null && countryArrayList.size() > 0) {
				for (CountryModel countriesDetails : gameModel.getCountries()) {
					if (countryArrayList.get(0).equalsIgnoreCase(countriesDetails.getCountryName())) {
						//// This loop is for fetching adjacent country details from the list,Since the
						//// adjacent country details starts from index 4,we are iterating from 4[i=4]
						for (int i = 4; i < countryArrayList.size(); i++) {
							for (CountryModel adjacentCountryDetails : gameModel.getCountries()) {
								if (countryArrayList.get(i).equalsIgnoreCase(adjacentCountryDetails.getCountryName())) {
									adjacentcountryList.add(adjacentCountryDetails);
								}
							}
						}
						if (adjacentcountryList != null && adjacentcountryList.size() > 0)
							countriesDetails.setAdjcentCountries(adjacentcountryList);
					}
				}
			}
		}
	}
}