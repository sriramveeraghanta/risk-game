package main.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import main.helpers.GameHelper;
import main.models.GameModel;
import main.utills.GameCommon;
import main.utills.GameConstants;
import main.utills.DialogHandler;
import main.utills.GameException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class LoadGameController {

    GameModel gameModel;

    @FXML
    public ListView<String> fileNamesListView;
    @FXML
    public Button loadGameButton;

    public ArrayList<String> fileNamesList;

    @FXML
    public void initialize() throws IOException {
        GameHelper gameHelper = new GameHelper();
        fileNamesList = gameHelper.getResourceFiles(GameConstants.USER_SAVED_GAMES_PATH);
        ObservableList<String> fileNamesObservableList = FXCollections.observableArrayList(fileNamesList);
        fileNamesListView.setItems(fileNamesObservableList);
    }

    @FXML
    public void loadGameAction(ActionEvent event) throws IOException {
        String fileName = fileNamesListView.getSelectionModel().getSelectedItem();
        if(fileName != null) {
            GameHelper gameHelper = new GameHelper();
            this.gameModel = gameHelper.loadGame(fileName);
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
            DialogHandler.showWarningMessage(GameConstants.LOAD_GAME_SELECT_WARNING);
        }
    }
}
