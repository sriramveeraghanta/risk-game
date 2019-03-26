package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import main.helpers.MapBuilder;
import main.helpers.StartupPhase;
import main.models.GameModel;
import main.utills.GameConstants;
import main.utills.GameException;

import java.io.IOException;
import java.util.Optional;

/**
 * This class is a game controller class
 */
public class GameController {

    private GameModel gameModel;
    private boolean userMap;
    private String userMapFilePath;



    @FXML
    private Button newGameButton,loadGameButton, exitGameButton ;

    /**
     * Constructor of the game controller class
     */
    public GameController(){

    }

    /**
     * with Start Game new game starts
     * @param event type of ActionEvent
     * @throws IOException
     */
    @FXML
    public void startNewGame(ActionEvent event) throws IOException {
        this.playerCountDialog();
    }

    /**
     * this method is player count dialog
     */
    public void playerCountDialog(){
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
                    this.initGame(playerCount);


                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(GameConstants.INVALID_PLAYER_COUNT_ERROR);
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(GameConstants.PLAYER_COUNT_ERROR);
                alert.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (GameException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(GameConstants.INVALID_MAP_ERROR);
                alert.showAndWait();
            }
        });
    }

    /**
     * This method do all of the initialization of the game by getting the number of players  as a parameter
     * @param playerCount number of players
     * @throws GameException
     * @throws IOException
     */
    private void initGame(int playerCount) throws GameException, IOException {
        // TODO: Start Startup Phase

        System.out.println("count"+playerCount);
        getGameModel().setNumberOfPlayers(playerCount);
        System.out.println(playerCount);
        MapBuilder mapBuilder = new MapBuilder(this.getGameModel());
        if(this.isUserMap()){
           mapBuilder.readMapFile(this.userMapFilePath);
        }else {
            mapBuilder.readMapFile(null);
        }

        System.out.println(getGameModel().getContinents());

        StartupPhase startupPhase = new StartupPhase(this.getGameModel());
        startupPhase.initNewGame(playerCount);

        System.out.println(gameModel.getPlayers());

        // Creating an Game Board
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/GameBoard.fxml"));
        Parent GameBoardPanel = loader.load();
        GameBoardController gameBoardController = loader.getController();
        gameBoardController.setGameModel(this.gameModel);
        stage.setScene(new Scene(GameBoardPanel, 1280, 768));
        //stage.setResizable(false);
        stage.show();
    }

    /**
     * Loading the New Game to the user
     * @param event type of ActionEvent
     * @throws IOException
     */
    @FXML
    public void loadGame(ActionEvent event) throws IOException {
        System.out.println(getClass());
        Stage stage = (Stage) loadGameButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoadGame.fxml"));
        Parent loadGamePanel = loader.load();
        LoadGameController loadGameController = loader.getController();
        loadGameController.setGameModel(this.gameModel);
        loadGameController.setGameController(this);
        loadGameController.setStage(stage);
        stage.setScene(new Scene(loadGamePanel));
        stage.setResizable(false);
        stage.show();

    }

    /**
     * Exit the current window that is opened
     * @param event type of ActionEvent
     */
    @FXML
    public void exitGame(ActionEvent event) {
        System.out.println("Exit game");
        Stage stage = (Stage) exitGameButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Getter method to get the game model object
     * @return the objectof game model
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

    /**
     * this method check the user map and return true or false
     * @return boolean value
     */
    public boolean isUserMap() {
        return userMap;
    }

    /**
     * set the user map type of boolean
     * @param userMap boolean
     */
    public void setUserMap(boolean userMap) {
        this.userMap = userMap;
    }

    /**
     * get the user map file path and return it as a String
     * @return the String user map file path
     */
    public String getUserMapFilePath() {
        return userMapFilePath;
    }

    /**
     * Set user map file path and get the user map file path as a parameter
     * @param userMapFilePath type of String
     */
    public void setUserMapFilePath(String userMapFilePath) {
        this.userMapFilePath = userMapFilePath;
    }
}
