package test.main.helpers;

import main.utills.GameConstants;
import org.junit.Test;
import org.junit.Before;
import main.helpers.FortificationPhase;
import main.helpers.MapBuilder;
import main.models.CountryModel;
import main.models.GameModel;
import main.models.PlayerModel;
import main.utills.EnumHandler;
import main.utills.GameCommon;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

/**
 * FortificationPhase Tester.
 *
 */
public class FortificationPhaseTest {
    PlayerModel attackerPlayer;
    CountryModel attackerCountryTo;
    CountryModel attackerCountryFrom;
    MapBuilder mapBuilder;
    GameModel gameModel;
    GameCommon gameCommons;
    ArrayList<CountryModel> countryList;
    ArrayList<PlayerModel> playerList;
    FortificationPhase fortify;
    /**
     * method which should run before all of the test methods
     * @throws Exception if exception occur throws Exception
     */
    @Before
    public void before() throws Exception {
        countryList = new ArrayList<>();
        playerList = new ArrayList<>();
        gameModel = new GameModel();
        gameCommons = new GameCommon();
        mapBuilder = new MapBuilder(gameModel);
        mapBuilder.readMapFile("world.map");
        attackerPlayer = new PlayerModel();
        attackerPlayer.setColor(EnumHandler.Color.BLUE);
        attackerCountryTo = gameCommons.getCountryModelFromList(gameModel.getCountries(), "Alberta");
        attackerCountryFrom = gameCommons.getCountryModelFromList(gameModel.getCountries(), "Alaska");
        attackerCountryFrom.setArmyInCountry(5);
        attackerCountryTo.setArmyInCountry(1);
        countryList.add(attackerCountryTo);
        countryList.add(attackerCountryFrom);
        attackerPlayer.setCountries(countryList);
        playerList.add(attackerPlayer);
        gameModel.setPlayers(playerList);
        fortify = new FortificationPhase(gameModel,attackerPlayer);
    }
    /**
     * Method: swapArmyUnitsBetweenCountries(String fromCountryName, String toCountryName, int numberOfArmyUnits)
     * Test when fromCountry has 5 armies and toCountry has 1, valid swap operation
     * @throws Exception if exception occur throws Exception
     */
    @Test
    public void testSwapArmyUnitsBetweenCountries() throws Exception {
        assertEquals(GameConstants.FORTIFY_VALID_MSG,
                fortify.swapArmyUnitsBetweenCountries(attackerCountryFrom,attackerCountryTo,2));
    }
    /**
     * Method: swapArmyUnitsBetweenCountries(String fromCountryName, String toCountryName, int numberOfArmyUnits)
     * Test post swap data, attackerCountryFrom should have (initialCount-numberOfArmyTransferred)
     * @throws Exception if exception occur throws Exception
     */
    @Test
    public void testSwapArmyUnitsBetweenCountriesPostOperationValueFrom() throws Exception {
        attackerCountryFrom.setArmyInCountry(5);
        fortify.swapArmyUnitsBetweenCountries(attackerCountryFrom,attackerCountryTo,2);
        assertEquals(3,attackerCountryFrom.getArmyInCountry());
    }
    /**
     * Method: swapArmyUnitsBetweenCountries(String fromCountryName, String toCountryName, int numberOfArmyUnits)
     * Test post swap data, attackerCountryTo should have (initialCount+numberOfArmyTransferred)
     * @throws Exception if exception occur throws Exception
     */
    @Test
    public void testSwapArmyUnitsBetweenCountriesPostOperationValueTo() throws Exception {
        attackerCountryFrom.setArmyInCountry(5);
        fortify.swapArmyUnitsBetweenCountries(attackerCountryFrom,attackerCountryTo,2);
        assertEquals(3,attackerCountryTo.getArmyInCountry());
    }
    /**
     * Method: swapArmyUnitsBetweenCountries(String fromCountryName, String toCountryName, int numberOfArmyUnits)
     * Test if armyCountInCountry is less than 2 , it does not allow to swap army units
     *  @throws Exception if exception occur throws Exception
     */
    @Test
    public void testSwapArmyUnitsBetweenCountriesArmyInCountryLessThan2() throws Exception {
        attackerCountryFrom.setArmyInCountry(1);
        assertEquals(GameConstants.FORTIFY_INVALID_MSG,
                fortify.swapArmyUnitsBetweenCountries(attackerCountryFrom,attackerCountryTo,1));
    }
    /**
     * Method: swapArmyUnitsBetweenCountries(String fromCountryName, String toCountryName, int numberOfArmyUnits)
     * Test if armyCountInCountry left is less than 1 , it does not allow to swap army units
     *  @throws Exception if exception occur throws Exception
     */
    @Test
    public void testSwapArmyUnitsBetweenCountriesArmyLeftInCountryLessThan1() throws Exception {
        attackerCountryFrom.setArmyInCountry(3);
        assertEquals(GameConstants.FORTIFY_INVALID_MSG,
                fortify.swapArmyUnitsBetweenCountries(attackerCountryFrom,attackerCountryTo,3));
    }
}
