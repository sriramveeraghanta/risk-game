package main.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import main.helpers.GameHelper;
import main.utills.GameConstants;

import java.io.IOException;
import java.util.ArrayList;

public class TournamentModeController {

    private ArrayList<String> fileNamesList;

    @FXML
    public TextField gameTurnCountTextField, gameCountTextField;
    @FXML
    public ListView mapListView;

    /**
     * Initializes the Controller with pre defined data.
     * */
    @FXML
    public void initialize() throws IOException {
        GameHelper gameHelper = new GameHelper();
        fileNamesList = gameHelper.getResourceFiles(GameConstants.USER_MAP_FILE_PATH);
        ObservableList<String> fileNamesObservableList = FXCollections.observableArrayList(fileNamesList);
        mapListView.setItems(fileNamesObservableList);
    }

    /**
     * Start Tounmanent Action
     * */
    @FXML
    public void startTournamentMode() {

    }
}
