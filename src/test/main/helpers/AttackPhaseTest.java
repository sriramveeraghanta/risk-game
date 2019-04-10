package test.main.helpers;

import main.helpers.StartupPhase;
import main.models.*;
import org.junit.Test;
import org.junit.Before;
import main.helpers.AttackPhase;
import main.helpers.MapBuilder;
import main.utills.EnumHandler;
import main.utills.GameCommon;
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
    CardModel cardModel;
    MapBuilder mapBuilder;
    GameModel gameModel;
    GameCommon gameCommons;
    ArrayList<CountryModel> countryList;
    ArrayList<PlayerModel> playerList;
    AttackPhase attackPhase;
    StartupPhase startupPhase;
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
        cardModel = new CardModel(EnumHandler.CardType.INFANTRY);
        defenderPlayer.setColor(EnumHandler.Color.BLACK);
        defenderPlayer.setActive(false);
        attackerPlayer.setColor(EnumHandler.Color.BLUE);
        gameModel = new GameModel();
        gameCommons = new GameCommon();
        mapBuilder = new MapBuilder(gameModel);
        mapBuilder.readMapFile("world.map");
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
        startupPhase = new StartupPhase(gameModel);
        startupPhase.createGameCards();
        attackPhase = new AttackPhase(gameModel, attackerCountry, defenderCountry);
        attackPhase.assignCardToPlayer(attackerPlayer);
    }

    /**
     *
     * Method: getNumberofAttackerDice()
     *@throws Exception if exception occur throws Exception
     */
    @Test
    public void testGetNumberofAttackerDice() throws Exception {
        assertEquals(3, attackPhase.getNumberOfDiceCount());
    }

    @Test
    public void testGetNumberofAttackerDiceLessThan3() throws Exception {
        attackerCountry.setArmyInCountry(2);
        assertEquals(2, attackPhase.getNumberOfDiceCount());
    }
    /**
     *
     * Method: getNumberofDefenderDice()
     *@throws Exception if exception occur throws Exception
     */
    @Test
    public void testGetNumberofDefenderDice() throws Exception {

        assertEquals(2, attackPhase.getNumberOfDiceCount()-1);
    }

    @Test
    public void testGetNumberofDefenderDiceLessThan2() throws Exception {
        attackerCountry.setArmyInCountry(1);
        assertEquals(1, attackPhase.getNumberOfDiceCount()-1);
    }
    /**
     *
     * Method: getAllDiceValues(int numberOfDice)
     *@throws Exception if exception occur throws Exception
     */
    @Test
    public void testGetAllDiceValues() throws Exception {
        assertNotNull(attackPhase.getAllDiceValues(2));
    }

} 
