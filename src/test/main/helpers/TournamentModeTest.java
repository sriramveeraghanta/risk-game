package test.main.helpers; 

import main.helpers.TournamentMode;
import main.models.CountryModel;
import main.models.GameModel;
import main.utills.GameCommon;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.ArrayList;

import static junit.framework.TestCase.assertNotNull;

/** 
* TournamentMode Tester.
*/ 
public class TournamentModeTest {
    TournamentMode tournamentMode;
    ArrayList<String> playerTypes;
    GameModel gameModel;
    ArrayList<CountryModel> countryList;
    GameCommon gameCommons;

@Before
public void before() throws Exception {
    countryList = new ArrayList<>();
    playerTypes = new ArrayList<>();
    playerTypes.add("AGGRESSIVE");
    playerTypes.add("BENEVOLENT");
    gameModel = new GameModel();
    gameCommons = new GameCommon();
    countryList.add(gameCommons.getCountryModelFromList(gameModel.getCountries(), "Alaska"));
    countryList.add(gameCommons.getCountryModelFromList(gameModel.getCountries(), "Alberta"));
    tournamentMode = new TournamentMode(2,2,"world.map",playerTypes);
} 

/** 
* 
* Method: initMode() 
* 
*/ 
@Test
public void testInitMode() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: randomNumberGenerator(ArrayList<CountryModel> CountryModel) 
* 
*/ 
@Test
public void testRandomNumberGenerator() throws Exception { 
    assertNotNull(tournamentMode.randomNumberGenerator(countryList));
} 


/** 
* 
* Method: playGame(GameModel gameModel) 
* 
*/ 
@Test
public void testPlayGame() throws Exception {
    tournamentMode.initMode();
    //System.out.println(tournamentMode.playGame(gameModel));
}

/** 
* 
* Method: cheaterPlay(PlayerModel playerModel, GameModel gameModel) 
* 
*/ 
@Test
public void testCheaterPlay() throws Exception {
} 

/** 
* 
* Method: randomPlay(PlayerModel playerModel, GameModel gameModel) 
* 
*/ 
@Test
public void testRandomPlay() throws Exception { 
//TODO: Test goes here...
} 

/** 
* 
* Method: benevolentPlay(PlayerModel playerModel, GameModel gameModel) 
* 
*/ 
@Test
public void testBenevolentPlay() throws Exception { 
//TODO: Test goes here...
} 

/** 
* 
* Method: aggressivePlay(PlayerModel playerModel, GameModel gameModel) 
* 
*/ 
@Test
public void testAggressivePlay() throws Exception { 
//TODO: Test goes here...
} 

} 
