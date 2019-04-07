package main.helpers;

import main.models.GameModel;
import main.utills.GameConstants;
import main.utills.GameException;
import main.utills.DialogHandler;

import java.io.*;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameHelper {
    /**
     * This method do all of the initialization of the game by getting the number of players  as a parameter
     * @param playerCount number of players.
     * @throws GameException, Game exceptions for Game related Exception Handling.
     * @throws IOException, Input out exception from user.
     */
    public GameModel startNewGame(int playerCount) throws GameException, IOException {
        // creating new Game Model
        GameModel gameModel = new GameModel();
        gameModel.setNumberOfPlayers(playerCount);
        // Creating New Map
        MapBuilder mapBuilder = new MapBuilder(gameModel);
        mapBuilder.readMapFile(null);
        // Startup phase
        StartupPhase startupPhase = new StartupPhase(gameModel);
        startupPhase.initNewGame(playerCount);
        return gameModel;
    }

    public void saveGame(GameModel gameModel) {
        // Getting Current DataTime as String.
        StringBuffer stringBuffer = new StringBuffer();
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ssZ");
        simpleDateFormat.format(now, stringBuffer, new FieldPosition(0));
        try {
            String filePath = GameConstants.USER_SAVED_GAMES_PATH + stringBuffer+".ser";
            // write object to file
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(gameModel);
            oos.close();
        } catch (IOException e) {
            DialogHandler.showErrorMessage(GameConstants.SAVE_GAME_ERROR);
        }
    }

    public GameModel loadGame(String fileName){
        GameModel gameModel = null;
        try {
            // read object from file
            FileInputStream fis = new FileInputStream(GameConstants.USER_SAVED_GAMES_PATH+fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            gameModel = (GameModel) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            DialogHandler.showErrorMessage(GameConstants.LOAD_GAME_ERROR);
        }
        return gameModel;
    }
}
