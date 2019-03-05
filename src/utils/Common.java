package utils;

import java.util.ArrayList;

import models.ContinentModel;
import models.CountryModel;
import models.GameModel;

public class Common {

	public Common() {
		// TODO Auto-generated constructor stub
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
