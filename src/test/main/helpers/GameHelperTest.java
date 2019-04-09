package test.main.helpers; 

import main.helpers.GameHelper;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import static junit.framework.TestCase.assertNotNull;

/** 
* GameHelper Tester. 
*
*/ 
public class GameHelperTest { 

    GameHelper gameHelper;
    /**
     * method which should run before all of the test methods
     * @throws Exception if exception occur throws Exception
     */
@Before
public void before() throws Exception {
    gameHelper = new GameHelper();
}
/** 
* 
* Method: startNewGame(int playerCount)
 * @throws Exception if exception occur throws Exception
* 
*/ 
@Test
public void testStartNewGame() throws Exception { 
    assertNotNull(gameHelper.startNewGame(3));
} 

/** 
* 
* Method: loadGame(String fileName)
 * @throws Exception if exception occur throws Exception
* 
*/ 
@Test
public void testLoadGame() throws Exception {
    //ToDo add file name
    assertNotNull(gameHelper.loadGame(""));
} 


} 
