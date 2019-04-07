package test.main.helpers; 

import org.junit.Test;
import org.junit.Before;
import main.helpers.FortificationPhase;
import main.helpers.MapBuilder;
import main.models.CountryModel;
import main.models.GameModel;
import main.models.PlayerModel;
import main.utills.EnumHandler;
import main.utills.GameCommons;
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
    GameCommons gameCommons;
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
    gameCommons = new GameCommons();
    mapBuilder = new MapBuilder(gameModel);
    mapBuilder.readMapFile(null);
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
* 
* Method: swapArmyUnitsBetweenCountries(String fromCountryName, String toCountryName, int numberOfArmyUnits) 
* 
*/ 
@Test
public void testSwapArmyUnitsBetweenCountries() throws Exception {
    assertEquals("Fortification Completed",
            fortify.swapArmyUnitsBetweenCountries(attackerCountryFrom,attackerCountryTo,2));
}

@Test
public void testSwapArmyUnitsBetweenCountriesInvalidCase() throws Exception {
    assertEquals("Fortification Completed",
            fortify.swapArmyUnitsBetweenCountries(attackerCountryFrom,attackerCountryTo,2));
}
}
