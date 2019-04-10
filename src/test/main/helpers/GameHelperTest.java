package test.main.helpers; 

import main.helpers.GameHelper;
import main.models.GameModel;
import main.models.PlayerModel;
import main.utills.EnumHandler;
import main.utills.GameConstants;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.io.IOException;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static junit.framework.TestCase.*;

/** 
* GameHelper Tester. 
*
*/ 
public class GameHelperTest {
    GameHelper gameHelper;
    GameModel gameModel;

    /**
     * method which should run before all of the test methods
     * @throws Exception if exception occur throws Exception
     */
@Before
public void before() throws Exception {
    gameHelper = new GameHelper();
    gameModel = new GameModel();
} 

/** 
* 
* Method: startNewGame(int playerCount, String fileName)
 * @throws Exception if exception occur throws Exception
* 
*/ 
@Test
public void testStartNewGame() throws Exception {
    assertNotNull(gameHelper.startNewGame(2, "world.map" ));
}

/**
 *
 * Method: saveGame(GameModel gameModel)
 * @throws Exception if exception occur throws Exception
 *
 */
@Test
public void testSaveGame() throws IOException {
    gameHelper.saveGame(gameModel);
    StringBuffer stringBuffer = new StringBuffer();
    Date now = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ssZ");
    simpleDateFormat.format(now, stringBuffer, new FieldPosition(0));
    String filename = stringBuffer+".ser";
    ArrayList<String> fileList = gameHelper.getResourceFiles(GameConstants.SAVED_GAMES_PATH);
    assertTrue(fileList.contains(filename));
}
/** 
* 
* Method: loadGame(String fileName)
 * @throws Exception if exception occur throws Exception
* 
*/ 
@Test
public void testLoadGame() throws Exception {
    gameHelper.saveGame(gameModel);
    StringBuffer stringBuffer = new StringBuffer();
    Date now = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ssZ");
    simpleDateFormat.format(now, stringBuffer, new FieldPosition(0));
    assertNotNull(gameHelper.loadGame(stringBuffer+".ser"));
}

/** 
* 
* Method: switchPlayerControl(GameModel gameModel)
 * @throws Exception if exception occur throws Exception
* 
*/ 
@Test
public void testSwitchPlayerControl() throws Exception {
    gameModel = gameHelper.startNewGame(2, "world.map" );
    int previous_active = gameModel.getCurrentPlayerIndex();
    gameHelper.switchPlayerControl(gameModel);
    int current_active = gameModel.getCurrentPlayerIndex();
    assertNotSame(previous_active, current_active);

} 

/** 
* 
* Method: getResourceFiles(String path)
 * @throws Exception if exception occur throws Exception
* 
*/ 
@Test
public void testGetResourceFiles() throws Exception {
    assertNotNull(gameHelper.getResourceFiles(GameConstants.SAVED_GAMES_PATH));
}
} 
