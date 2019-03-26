package test.main.helpers; 

import main.helpers.MapBuilder;
import main.models.ContinentModel;
import main.models.CountryModel;
import main.models.GameModel;
import main.utills.GameCommons;
import org.junit.*;
import org.junit.Before; 
import org.junit.After;

import static junit.framework.Assert.*;

/** 
* MapBuilder Tester. 
* 
*/
public class MapBuilderTest {
    String continentName;
    String countryName;
    int continentControlValue;
    int countryXaxis;
    int countryYaxis;
    GameModel gameModel;
    GameCommons gameCommons;
    MapBuilder mapBuilder;

    @Before
public void before() throws Exception {
    gameCommons = new GameCommons();
    gameModel= new GameModel();
    mapBuilder = new MapBuilder(gameModel);
    mapBuilder.readMapFile(null);

}
@After
public void after() throws Exception { 
} 

///**
//*
//* Method: readMapFile(String mapFilePath)
//*
//*/
//@Test
//public void testReadMapFile() throws Exception {
////TODO: Test goes here...
//
//
//}

///**
//*
//* Method: loadContinentMapData(int initial, int last)
//*
//*/
//@Test
//public void testLoadContinentMapData() throws Exception {
////TODO: Test goes here...
//}

///**
//*
//* Method: loadCountryMapData(int initial, int last)
//*
//*/
//@Test
//public void testLoadCountryMapData() throws Exception {
////TODO: Test goes here...
//}

///**
//*
//* Method: addAdjcentCountriesToCountry(ArrayList<String[]> countryMapDataList)
//*
//*/
//@Test
//public void testAddAdjcentCountriesToCountry() throws Exception {
////TODO: Test goes here...
//}

/** 
* 
* Method: createOrUpdateContinent(String continentName, int controlValue) 
* 
*/ 
//@Test
//public void testCreateOrUpdateContinent() throws Exception {
////TODO: Test goes here...
//    continentName="North America";
//    continentControlValue=9;
//    mapBuilder.createOrUpdateContinent(continentName,continentControlValue);
//    ContinentModel continent = gameCommons.getContinentModelFromList(gameModel.getContinents(), continentName);
//    assertEquals(continentControlValue,continent.getControlValue());
//}

///**
//*
//* Method: addNewCountry(String countryName, int xCoordinate, int yCoordinate, String continentName)
//*
//*/
//@Test
//public void testAddNewCountry() throws Exception {
////TODO: Test goes here...
//    countryName="Atlantis";
//    continentName="North America";
//    countryXaxis=100;
//    countryYaxis=100;
//    mapBuilder.addNewCountry(countryName,countryXaxis,countryYaxis,continentName);
//    CountryModel country=gameCommons.getCountryModelFromList(gameModel.getCountries(),countryName);
//    assertEquals(countryName,country.getCountryName());
//}

///**
//*
//* Method: removeCountry(String countryName)
//*
//*/
//@Test
//public void testRemoveCountry() throws Exception {
////TODO: Test goes here...
//}

/** 
* 
* Method: updateCountry(String countryName, int xAxis, int yAxis, String continentName) 
* 
*/ 
//@Test
//public void testUpdateCountry() throws Exception {
////TODO: Test goes here...
//    countryName="Alaska";
//    continentName="North America";
//    countryXaxis=100;
//    countryYaxis=100;
//    mapBuilder.updateCountry(countryName,countryXaxis,countryYaxis,continentName);
//    CountryModel country=gameCommons.getCountryModelFromList(gameModel.getCountries(),countryName);
//    assertEquals(countryXaxis,country.getxAxis());
//    assertEquals(countryYaxis,country.getyAxis());
//}


    /**
     * Method: assignCountryToContinent(String countryName, String continentName)
     * @throws Exception if exception occurs it throws Exception
     */
    @Test
public void testAssignCountryToContinent() throws Exception {
//TODO: Test goes here...
    continentName = "North America";
    countryName = "Alaska";
    //mapBuilder.assignCountryToContinent(countryName,continentName);
    ContinentModel continent = gameCommons.getContinentModelFromList(gameModel.getContinents(), continentName);
    for (CountryModel country : continent.getCountries()) {
        if (countryName.equalsIgnoreCase(country.getCountryName())) {
            assertEquals(country.getCountryName(), countryName);
        }
    }
}

///**
//*
//* Method: addAdjacentCountries()
//*
//*/
//@Test
//public void testAddAdjacentCountries() throws Exception {
////TODO: Test goes here...
//}


    /**
     * Method: validateCountriesBelongToOneContinent()
     * @throws Exception if exception occur it throws Exception
     */
    @Test
public void testValidateCountriesBelongToOneContinent() throws Exception { 
//TODO: Test goes here...
    assertTrue(mapBuilder.validateCountriesBelongToOneContinent());
} 

///**
//*
//* Method: validateContinentData(int initial, int last)
//*
//*/
//@Test
//public void testValidateContinentData() throws Exception {
////TODO: Test goes here...
////mapBuilder.validateContinentData();
//}

///**
//*
//* Method: validateCountryData(int initial, int last)
//*
//*/
//@Test
//public void testValidateCountryData() throws Exception {
////TODO: Test goes here.
//}


    /**
     * Method: validateIfContinentExists(String continentName)
     * @throws Exception if exception occur it throws exception
     */
    @Test
public void testValidateIfContinentExists() throws Exception { 
//TODO: Test goes here...
    continentName="North America";
    assertTrue(mapBuilder.validateIfContinentExists(continentName));
    String inavlidCOntinentName="Wakanda";
    assertFalse(mapBuilder.validateIfContinentExists(inavlidCOntinentName));

} 


    /**
     * Method: validateIfCountriesAreAdjacent
     * @throws Exception if exception occur it throws Exception
     */
    @Test
public void testValidateIfCountriesAreAdjacent() throws Exception { 
//TODO: Test goes here... 
assertTrue(mapBuilder.validateIfCountriesAreAdjacent());
}


@Test
public void testValidateContinentsAreAdjacent() throws Exception {
    assertTrue(mapBuilder.validateContinentsAreAdjacent());
}



} 
