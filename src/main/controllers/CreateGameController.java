package main.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.helpers.GameHelper;
import main.helpers.MapBuilder;
import main.models.GameModel;
import main.utills.GameConstants;
import main.utills.GameException;
import main.utills.Utills;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

/**
 * this class is the controller of the game loader
 */
public class CreateGameController {

    private GameModel gameModel;
    private String mapFilePath=null;

    @FXML
    private TextArea mapDataTextArea;
    @FXML
    private TextField mapFileNameTextField;

    /**
     * constructor of the load game controller
     */
    public CreateGameController() {

    }

    /**
     * this method create user map and get event which is type of ActionEvent as parameter
     */
    @FXML
    public void createUserMap(ActionEvent event) {
        if(mapDataTextArea.getParagraphs().size() != 0 && mapFileNameTextField.getCharacters().length() != 0 ){
            CharSequence fileName = mapFileNameTextField.getCharacters();
            ObservableList<CharSequence> paragraph = mapDataTextArea.getParagraphs();
            // creating a map file in the file System
            createMapFile(fileName, paragraph);
            // Map Builder
            MapBuilder mapBuilder = new MapBuilder(this.getGameModel());
            boolean isMapValid = false;
            try {
                isMapValid = mapBuilder.readMapFile(mapFilePath);
            }catch (GameException e) {
                Utills.showWarningMessage(GameConstants.INVALID_MAP_ERROR);
            }

            if(isMapValid) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Risk Game");
                alert.setHeaderText(GameConstants.MAP_MSG);
                ButtonType yesButton = new ButtonType("Yes");
                ButtonType noButton = new ButtonType("No");
                alert.getButtonTypes().setAll(yesButton, noButton);
                // If User checks for Yes
                Optional<ButtonType> result = alert.showAndWait();
                if(result.isPresent()){
                    if (result.get() == yesButton) {
                        this.startGame();
                    } else if(result.get() == noButton) {
                        ((Node)(event.getSource())).getScene().getWindow().hide();
                    }
                }
            }
        } else {
            Utills.showWarningMessage(GameConstants.USER_NO_INPUT);
        }
    }

    private void startGame() {
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
                    // Creating an Game Board
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/GameBoard.fxml"));
                    Parent GameBoardPanel = loader.load();
                    GameBoardController gameBoardController = loader.getController();
                    gameBoardController.setGameModel(this.gameModel);
                    stage.setScene(new Scene(GameBoardPanel, 1280, 768));
                    stage.show();

                } else {
                    Utills.showWarningMessage(GameConstants.INVALID_PLAYER_COUNT_ERROR);
                }
            } catch (NumberFormatException | IOException e) {
                Utills.showWarningMessage(GameConstants.PLAYER_COUNT_ERROR);
            } catch (GameException e) {
                Utills.showWarningMessage(GameConstants.INVALID_MAP_ERROR);
            }
        });
    }


    /**
     * Create map file according to the file name and map data which gets as  parameters
     * @param fileName this parameter is the file name type of  CharSequence
     * @param mapData this method is an observable list of CharSequence
     */
    private void createMapFile(CharSequence fileName, ObservableList<CharSequence> mapData) {
        Iterator<CharSequence> iterator = mapData.iterator();
        try {
            mapFilePath = GameConstants.USER_MAP_FILE_PATH + fileName.toString()+".map";
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(mapFilePath)));
            while(iterator.hasNext()) {
                CharSequence seq = iterator.next();
                bufferedWriter.append(seq);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            Utills.showWarningMessage(GameConstants.INVALID_MAP_ERROR);
        }
    }


    /**
     * Getter method to get the game model
     * @return an object of game model
     */
    public GameModel getGameModel() {
        return gameModel;
    }

    /**
     * Setter method to set the game model
     * @param gameModel an object of game model
     */
    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }
}
