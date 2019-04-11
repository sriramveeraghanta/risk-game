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
/** 
* 
* Method: randomNumberGenerator(ArrayList<CountryModel> CountryModel) 
* 
*/ 
@Test
public void testRandomNumberGenerator() throws Exception { 
    assertNotNull(tournamentMode.randomNumberGenerator(countryList));
} 

}
