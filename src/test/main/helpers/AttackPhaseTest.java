package test.main.helpers; 

import main.models.*;
import org.junit.Test;
import org.junit.Before;
import main.helpers.AttackPhase;
import main.helpers.MapBuilder;
import main.utills.EnumHandler;
import main.utills.GameCommons;
import java.util.ArrayList;

import static junit.framework.Assert.*;
import static junit.framework.TestCase.assertEquals;

/**
* AttackPhase Tester. 
*
*/ 
public class AttackPhaseTest {
    PlayerModel attackerPlayer;
    PlayerModel defenderPlayer;
    CountryModel attackerCountry;
    CountryModel defenderCountry;
    MapBuilder mapBuilder;
    GameModel gameModel;
    GameCommons gameCommons;
    ArrayList<CountryModel> countryList;
    ArrayList<PlayerModel> playerList;
    AttackPhase attackPhase;
    /**
     * method which should run before all of the test methods
     * @throws Exception if exception occur throws Exception
     */
@Before
public void before() throws Exception {
    countryList = new ArrayList<>();
    playerList = new ArrayList<>();
    defenderPlayer = new PlayerModel();
    attackerPlayer = new PlayerModel();
    defenderPlayer.setColor(EnumHandler.Color.BLACK);
    defenderPlayer.setActive(false);
    attackerPlayer.setColor(EnumHandler.Color.BLUE);
    gameModel = new GameModel();
    gameCommons = new GameCommons();
    mapBuilder = new MapBuilder(gameModel);
    mapBuilder.readMapFile(null);
    attackerCountry = gameCommons.getCountryModelFromList(gameModel.getCountries(), "Alaska");
    defenderCountry = gameCommons.getCountryModelFromList(gameModel.getCountries(), "Alberta");
    attackerCountry.setArmyInCountry(4);
    defenderCountry.setArmyInCountry(2);
    countryList.add(attackerCountry);
    attackerPlayer.setCountries(countryList);
    countryList.clear();
    countryList.add(defenderCountry);
    defenderPlayer.setCountries(countryList);
    playerList.add(attackerPlayer);
    playerList.add(defenderPlayer);
    gameModel.setPlayers(playerList);
    attackPhase = new AttackPhase(gameModel, attackerCountry, defenderCountry);
} 

/** 
* 
* Method: getNumberofAttackerDice() 
* 
*/ 
@Test
public void testGetNumberofAttackerDice() throws Exception {
    assertEquals(3, attackPhase.getNumberOfAttackerDice());
}

@Test
public void testGetNumberofAttackerDiceLessThan3() throws Exception {
    attackerCountry.setArmyInCountry(2);
    assertEquals(2, attackPhase.getNumberOfAttackerDice());
}
    /**
* 
* Method: getNumberofDefenderDice() 
* 
*/ 
@Test
public void testGetNumberofDefenderDice() throws Exception {
    assertEquals(2, attackPhase.getNumberOfDefenderDice());
}

@Test
public void testGetNumberofDefenderDiceLessThan2() throws Exception {
    defenderCountry.setArmyInCountry(1);
    assertEquals(1, attackPhase.getNumberOfDefenderDice());
}
/**
* 
* Method: getAllDiceValues(int numberOfDice) 
* 
*/ 
@Test
public void testGetAllDiceValues() throws Exception {
    assertNotNull(attackPhase.getAllDiceValues(2));
}
} 
