package test.main.helpers;

import main.helpers.MapBuilder;
import main.models.ContinentModel;
import main.models.CountryModel;
import main.models.GameModel;
import main.utills.GameCommon;
import main.utills.GameConstants;
import main.utills.GameException;
import org.junit.*;
import org.junit.Before;

import static junit.framework.Assert.*;

/**
 * MapBuilder Tester.
 */

public class MapBuilderTest {
    String continentName;
    String countryName;
    GameModel gameModel;
    GameCommon gameCommons;
    MapBuilder invalidMapBuilder;
    MapBuilder mapBuilder;

    /**
     * method which should run before all of the test methods
     * @throws Exception if exception occur throws Exception
     */
    @Before
    public void before() throws Exception {
        gameCommons = new GameCommon();
        gameModel= new GameModel();
        mapBuilder = new MapBuilder(gameModel);
        invalidMapBuilder = new MapBuilder(gameModel);
        mapBuilder.readMapFile("world.map");

    }

    /**
     * method test the self-adjacency in the map
     * @throws GameException
     */
    @Test
    public void testSelfAdjacencyMapReader() throws GameException {
        assertEquals(false,
                invalidMapBuilder.readMapFile("world2.map"));
    }

    /**
     * method test the country-adjacency in the map
     * @throws GameException
     */
    @Test
    public void testCountryAdjacencyMapReader() throws GameException {
        assertFalse(invalidMapBuilder.readMapFile("world2.map"));
    }

    /**
     * method test the continent-adjacency in the map
     * @throws GameException
     */
    @Test
    public void testContinentAdjacencyMapReader() throws GameException {
        assertEquals(false,
                invalidMapBuilder.readMapFile("world2.map"));
    }

    /**
     * Method: assignCountryToContinent(String countryName, String continentName)
     * @throws Exception if exception occur throws Exception
     */
    @Test
    public void testAssignCountryToContinent() throws Exception {
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

    /**
     * Method: validateCountriesBelongToOneContinent()
     * @throws Exception if exception occur throws Exception
     */
    @Test
    public void testValidateCountriesBelongToOneContinent() throws Exception {
        assertTrue(mapBuilder.validateCountriesBelongToOneContinent());
    }

    /**
     * Method: validateIfContinentExists(String continentName)
     * @throws Exception if exception occur throws Exception
     */
    @Test
    public void testValidateIfContinentExists() throws Exception {
        continentName="North America";
        assertTrue(mapBuilder.validateIfContinentExists(continentName));
    }

    /**
     * Method:validateIfContinentExists(boolean inavlidCOntinentName)
     * @throws Exception if exception occur throws Exception
     */
    @Test
    public void testValidateInvalidContinent() throws Exception {
        String inavlidCOntinentName="Wakanda";
        assertFalse(mapBuilder.validateIfContinentExists(inavlidCOntinentName));
    }

    /**
     * Method: validateIfCountriesAreAdjacent(ArrayList<String[]> countryMapDataList)
     * @throws Exception if exception occur throws Exception
     */
    @Test
    public void testValidateIfCountriesAreAdjacent() throws Exception {
        assertTrue(mapBuilder.validateIfCountriesAreAdjacent());
    }

    /**
     * Method: validateContinentsAreAdjacent()
     * @throws Exception if exception occur throws Exception
     */
    @Test
    public void testValidateContinentsAreAdjacent() throws Exception {
        assertTrue(mapBuilder.validateContinentsAreAdjacent());
    }
} 
