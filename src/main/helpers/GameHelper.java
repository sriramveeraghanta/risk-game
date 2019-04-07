package main.helpers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.controllers.GameBoardController;
import main.models.GameModel;
import main.utills.GameException;

import java.io.IOException;

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
}
