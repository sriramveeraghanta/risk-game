package test.main.helpers;

import main.helpers.MapBuilder;
import main.helpers.StartupPhase;
import main.models.CountryModel;
import main.models.GameModel;
import main.models.PlayerModel;
import main.utills.GameCommon;
import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;

import static junit.framework.TestCase.*;

/**
 * StartupPhase Tester.
 */
public class StartupPhaseTest {
    GameModel gameModel;
    StartupPhase startup;
    ArrayList<CountryModel> countryList;
    ArrayList<PlayerModel> playerList;
    GameCommon gameCommons;
    MapBuilder mapBuilder;
    ArrayList<String> playerTypes;
    ArrayList<String> playerTypes2;

    /**
     * method which should run before all of the test methods
     *
     * @throws Exception if exception occur throws Exception
     */
    @Before
    public void before() throws Exception {
        countryList = new ArrayList<>();
        playerList = new ArrayList<>();
        playerTypes = new ArrayList<>();
        playerTypes2 = new ArrayList<>();
        playerTypes2.add("HUMAN");
        playerTypes.add("HUMAN");
        playerTypes.add("BENEVOLENT");
        gameModel = new GameModel();
        gameCommons = new GameCommon();
        mapBuilder = new MapBuilder(gameModel);
        mapBuilder.readMapFile("world.map");
        startup = new StartupPhase(gameModel);
    }

    /**
     * Method: getAssignedColor()
     */
    @Test
    public void testAssignColor() throws Exception {
        assertNotNull(startup.getAssignedColor());
    }

    /**
     * Method: setInitialInfantry(PlayerModel player)
     */
    @Test
    public void testSetInitialInfantryFor2Player() throws Exception {
        startup.initNewGame(playerTypes);
        assertEquals(40, startup.getInitialUnit());
    }

    @Test
    public void testSetInitialInfantryDefault() throws Exception {
        startup.initNewGame(playerTypes2);
        assertEquals(0, startup.getInitialUnit());
    }

    /**
     * Method: assignCountriesToPlayers()
     */
    @Test
    public void testAssignCountriesToPlayers() throws Exception {
        startup.initNewGame(playerTypes);
        playerList = gameModel.getPlayers();
        assertNotNull(playerList.get(0).getCountries());
    }

    @Test
    public void testAssignCountriesToOnePlayer() throws Exception {
        startup.initNewGame(playerTypes2);
        playerList = gameModel.getPlayers();
        assertNotNull(playerList.get(0).getCountries());
    }

    /**
     * Method: assignUnitsToPlayerCountries()
     */
    @Test
    public void testAssignUnitsToPlayerCountries() {
        startup.initNewGame(playerTypes);
        playerList = gameModel.getPlayers();
        assertEquals(2, playerList.get(0).getCountries().get(0).getArmyInCountry());
    }

    /**
     * Method: assignUnitsToPlayerCountries()
     */
    @Test
    public void testAssignUnitsToPlayer() {
        startup.initNewGame(playerTypes);
        playerList = gameModel.getPlayers();
        assertEquals(0, playerList.get(0).getArmyInHand());
    }
}
