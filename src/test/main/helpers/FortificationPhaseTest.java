package test.main.helpers;

import main.helpers.AttackPhase;
import main.helpers.FortificationPhase;
import main.helpers.MapBuilder;
import main.models.CountryModel;
import main.models.GameModel;
import main.models.PlayerModel;
//import main.models.UnitModel;
import main.utills.EnumHandler;
import main.utills.GameCommons;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
* FortificationPhase Tester.
*
* @author <Authors name>
* @since <pre>Mar 23, 2019</pre>
* @version 1.0
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
    AttackPhase attackPhase;
    FortificationPhase fortify;
    //ArrayList<UnitModel> unit=new ArrayList<>();
    //UnitModel unitModel;
@Before
public void before() throws Exception {
    //unitModel = new UnitModel();
    countryList = new ArrayList<>();
    playerList = new ArrayList<>();
    gameModel = new GameModel();
    gameCommons = new GameCommons();
    mapBuilder = new MapBuilder(gameModel);
    mapBuilder.readMapFile(null);
//    attackerPlayer = new PlayerModel(EnumHandler.Color.BLUE);
    attackerCountryTo = gameCommons.getCountryModelFromList(gameModel.getCountries(), "Alaska");
    attackerCountryFrom = gameCommons.getCountryModelFromList(gameModel.getCountries(), "Alberta");
//    attackerCountryTo.setNumberOfUnits(5);
//    attackerCountryFrom.setNumberOfUnits(1);
    //unitModel.setType(EnumHandler.CardType.INFANTRY);
//    unitModel.setUnitNumber(5);
//    unit.add(unitModel);
//    attackerCountryFrom.setArmyInCountry(unit);
    countryList.add(attackerCountryTo);
    countryList.add(attackerCountryFrom);
    attackerPlayer.setCountries(countryList);
    playerList.add(attackerPlayer);
    gameModel.setPlayers(playerList);
    fortify = new FortificationPhase(attackerPlayer);
}

@After
public void after() throws Exception {
}

/**
*
* Method: getPlayerModel()
*
*/
@Test
public void testGetPlayerModel() throws Exception {
//TODO: Test goes here...
}

/**
*
* Method: setPlayerModel(PlayerModel playerModel)
*
*/
@Test
public void testSetPlayerModel() throws Exception {
//TODO: Test goes here...
}

/**
*
* Method: swapArmyUnitsBetweenCountries(String fromCountryName, String toCountryName, int numberOfArmyUnits)
*
*/
@Test
public void testSwapArmyUnitsBetweenCountries() throws Exception {
//    assertEquals("Operation Completed",
////            fortify.swapArmyUnitsBetweenCountries(attackerCountryFrom.getCountryName(),attackerCountryTo.getCountryName(),
////                     2));
}


}
