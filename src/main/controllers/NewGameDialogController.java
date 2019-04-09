package main.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.helpers.GameHelper;
import main.models.GameModel;
import main.utills.DialogHandler;
import main.utills.GameConstants;
import main.utills.GameException;

import java.io.IOException;
import java.util.ArrayList;

public class NewGameDialogController {
    public ListView<String> mapsListView;
    public TextField playerCountTextField;
    private GameModel gameModel;

    @FXML
    public void initialize() throws IOException {
        GameHelper gameHelper = new GameHelper();
        ArrayList<String> fileNamesList = gameHelper.getResourceFiles(GameConstants.MAPS_DIR_PATH);
        System.out.println(fileNamesList);
        ObservableList<String> fileNamesObservableList = FXCollections.observableArrayList(fileNamesList);
        mapsListView.setItems(fileNamesObservableList);
    }

    public void startNewGame(ActionEvent event) {
        String fileName = mapsListView.getSelectionModel().getSelectedItem();
        String playerCountString = playerCountTextField.getText();
        if(fileName != null && playerCountString != null) {
            try{
                int playerCount = Integer.parseUnsignedInt(playerCountString);
                if(playerCount <= GameConstants.MAXIMUM_NUMBER_OF_PLAYERS && playerCount >= GameConstants.MINIMUM_NUMBER_OF_PLAYERS){
                    // Initiating players and Creating new Map.
                    GameHelper gameHelper = new GameHelper();
                    this.gameModel = gameHelper.startNewGame(playerCount, fileName);
                    System.out.println(gameModel);
                    // Creating an Game Board
                    Stage stage = new Stage();
                    stage.setTitle(GameConstants.GAME_TITLE);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/GameBoard.fxml"));
                    Parent GameBoardPanel = loader.load();
                    GameBoardController gameBoardController = loader.getController();
                    gameBoardController.setGameModel(this.gameModel);
                    stage.setScene(new Scene(GameBoardPanel, 1280, 768));
                    stage.show();
                    stage.setOnCloseRequest(windowEvent -> {
                        DialogHandler.saveGameDialog(stage, this.gameModel);
                    });
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                } else {
                    DialogHandler.showErrorMessage(GameConstants.INVALID_PLAYER_COUNT_ERROR);
                }
            } catch (NumberFormatException | IOException | GameException e) {
                DialogHandler.showErrorMessage(GameConstants.PLAYER_COUNT_ERROR);
            }
        } else {
            DialogHandler.showErrorMessage(GameConstants.INVALID_MAP_PLAYER_COUNT_ERROR);
        }
    }
}
