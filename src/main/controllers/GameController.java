package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import main.helpers.GameHelper;
import main.models.GameModel;
import main.utills.GameConstants;
import main.utills.GameException;
import main.utills.DialogHandler;

import java.io.IOException;
import java.util.Optional;

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
    public void startNewGame() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(GameConstants.SELECT_PLAYERS);
        dialog.setTitle(getGameModel().getTitle());
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(playerCountString -> {
            int playerCount;
            try {
                playerCount = Integer.parseUnsignedInt(playerCountString);
                if (playerCount <= GameConstants.MAXIMUM_NUMBER_OF_PLAYERS && playerCount >= GameConstants.MINIMUM_NUMBER_OF_PLAYERS) {
                    // Initiating players and Creating new Map.
                    GameHelper gameHelper = new GameHelper();
                    this.setGameModel(gameHelper.startNewGame(playerCount));
                    //System.out.println(this.gameModel.getCountries());
                    // Creating an Game Board
                    Stage stage = new Stage();
                    stage.setTitle(GameConstants.GAME_TITLE);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/GameBoard.fxml"));
                    Parent GameBoardPanel = loader.load();
                    GameBoardController gameBoardController = loader.getController();
                    gameBoardController.setGameModel(this.gameModel);
                    stage.setScene(new Scene(GameBoardPanel, 1280, 768));
                    stage.show();
                    stage.setOnCloseRequest(event -> DialogHandler.saveGameDialog(stage, this.gameModel));
                } else {
                    DialogHandler.showWarningMessage(GameConstants.INVALID_PLAYER_COUNT_ERROR);
                }
            } catch (NumberFormatException | IOException e) {
                DialogHandler.showWarningMessage(GameConstants.PLAYER_COUNT_ERROR);
            } catch (GameException e) {
                DialogHandler.showWarningMessage(GameConstants.INVALID_MAP_ERROR);
            }
        });
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
