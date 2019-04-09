package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.models.GameModel;
import main.utills.GameConstants;

import java.io.IOException;

/**
 * This class is a game controller class
 */
public class GameController {

    private GameModel gameModel;

    /**
     * Constructor of the game controller class
     */
    public GameController() {}

    /**
     * with Start Game new game starts
     */
    @FXML
    public void startNewGame() throws IOException {
        // Creating an Game Board
        Stage stage = new Stage();
        stage.setTitle(GameConstants.GAME_TITLE);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/NewGameDialogView.fxml"));
        Parent GameBoardPanel = loader.load();
        stage.setScene(new Scene(GameBoardPanel, 400, 400));
        stage.show();
    }

    /**
     * Loading the New Game to the user
     * @throws IOException if exception occur it throws IOException
     */
    @FXML
    public void createGame() throws IOException {
        Stage stage = new Stage();
        stage.setTitle(GameConstants.GAME_TITLE);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/CreateGame.fxml"));
        Parent createNewGamePanel = loader.load();
        CreateGameController createGameController = loader.getController();
        createGameController.setGameModel(this.gameModel);
        stage.setScene(new Scene(createNewGamePanel,1280, 768));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Loads a saved game from the file.
     * @throws IOException if exception occur it throws IOException.
     */
    @FXML
    public void loadGame() throws IOException {
        Stage stage = new Stage();
        stage.setTitle(GameConstants.GAME_TITLE);
        FXMLLoader loader = new FXMLLoader(GameController.class.getResource("/views/LoadGame.fxml"));
        Parent loadGamePanel = loader.load();
        stage.setScene(new Scene(loadGamePanel,400, 400));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Exit the current window that is opened
     * @param event type of ActionEvent
     */
    @FXML
    public void exitGame(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    /**
     *Tournament Action method with raises a popup and takes the input from the user.
     * */
    public void tournamentModeAction() throws IOException {
        Stage stage = new Stage();
        stage.setTitle(GameConstants.GAME_TITLE);
        FXMLLoader loader = new FXMLLoader(GameController.class.getResource("/views/TournamentMode.fxml"));
        Parent loadGamePanel = loader.load();
        stage.setScene(new Scene(loadGamePanel,500, 400));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Getter method to get the game model object
     * @return the object of game model
     */
    public GameModel getGameModel() {
        return gameModel;
    }

    /**
     * Setter method to set the game model
     * @param gameModel type of GameModel
     */
    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }


}
