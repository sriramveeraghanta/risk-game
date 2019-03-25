package test.main.helpers; 

import main.helpers.AttackPhase;
import main.helpers.MapBuilder;
import main.models.ContinentModel;
import main.models.CountryModel;
import main.models.GameModel;
import main.models.PlayerModel;
import main.utills.EnumHandler;
import main.utills.GameCommons;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.ArrayList;

import static junit.framework.Assert.assertTrue;

/** 
* AttackPhase Tester. 
* 
* @author <Authors name> 
* @since <pre>Mar 24, 2019</pre> 
* @version 1.0 
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

@Before
public void before() throws Exception {
    countryList = new ArrayList<>();
    playerList = new ArrayList<>();
    defenderPlayer = new PlayerModel(EnumHandler.Color.BLACK);
    defenderPlayer.setActive(false);
    attackerPlayer = new PlayerModel(EnumHandler.Color.BLUE);
    gameModel = new GameModel();
    gameCommons = new GameCommons();
    mapBuilder = new MapBuilder(gameModel);
    mapBuilder.readMapFile(null);
    attackerCountry = gameCommons.getCountryModelFromList(gameModel.getCountries(), "Alaska");
    defenderCountry = gameCommons.getCountryModelFromList(gameModel.getCountries(), "Alberta");
    attackerCountry.setNumberOfUnits(5);
    defenderCountry.setNumberOfUnits(4);
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

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getNumberAttackerDice() 
* 
*/ 
@Test
public void testGetNumberAttackerDice() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setNumberAttackerDice(int numberAttackerDice) 
* 
*/ 
@Test
public void testSetNumberAttackerDice() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getCountryAttacker() 
* 
*/ 
@Test
public void testGetCountryAttacker() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setCountryAttacker(CountryModel countryAttacker) 
* 
*/ 
@Test
public void testSetCountryAttacker() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getCountryDefender() 
* 
*/ 
@Test
public void testGetCountryDefender() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setcountryDefender(CountryModel countryDefender) 
* 
*/ 
@Test
public void testSetcountryDefender() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: checkIfPlayerCanAttackCountry() 
* 
*/ 
//@Test
//public void testCheckIfPlayerCanAttackCountry() throws Exception {
//    assertTrue(attackPhase.checkIfPlayerCanAttackCountry());
//}

/** 
* 
* Method: rollDice() 
* 
*/ 
@Test
public void testRollDice() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: attackingResult() 
* 
*/ 
@Test
public void testAttackingResult() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: checkMaxNumberOfDices() 
* 
*/ 
@Test
public void testCheckMaxNumberOfDices() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: roll(int numberOfDice) 
* 
*/ 
@Test
public void testRoll() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: assignCardToPlayer(PlayerModel player) 
* 
*/ 
@Test
public void testAssignCardToPlayer() throws Exception { 
//TODO: Test goes here... 
} 


/** 
* 
* Method: assignCountryToWinnerPlayer(PlayerModel winnerPlayer, PlayerModel loserPlayer, String countryName) 
* 
*/ 
@Test
public void testAssignCountryToWinnerPlayer() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = AttackPhase.getClass().getMethod("assignCountryToWinnerPlayer", PlayerModel.class, PlayerModel.class, String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: assignRemainingCardsToWinnerPlayer(PlayerModel winnerPlayer, PlayerModel loserPlayer) 
* 
*/ 
@Test
public void testAssignRemainingCardsToWinnerPlayer() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = AttackPhase.getClass().getMethod("assignRemainingCardsToWinnerPlayer", PlayerModel.class, PlayerModel.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: getDefender(String defenderCountryName) 
* 
*/ 
@Test
public void testGetDefender() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = AttackPhase.getClass().getMethod("getDefender", String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
