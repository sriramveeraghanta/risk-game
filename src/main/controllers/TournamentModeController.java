package main.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import main.helpers.GameHelper;
import main.utills.GameConstants;

import java.io.IOException;
import java.util.ArrayList;

public class TournamentModeController {

    public CheckBox aggressiveCheckbox;
    public CheckBox randomCheckbox;
    public CheckBox benevolentCheckbox;
    public CheckBox cheaterCheckbox;

    @FXML
    public TextField gameTurnCountTextField, gameCountTextField;
    @FXML
    public ListView<String> mapListView;

    /**
     * Initializes the Controller with pre defined data.
     * */
    @FXML
    public void initialize() throws IOException {
        GameHelper gameHelper = new GameHelper();
        ArrayList<String> fileNamesList = gameHelper.getResourceFiles(GameConstants.MAPS_DIR_PATH);
        ObservableList<String> fileNamesObservableList = FXCollections.observableArrayList(fileNamesList);
        mapListView.setItems(fileNamesObservableList);
    }

    /**
     * Start Tounmanent Action
     * */
    @FXML
    public void startTournamentMode() {
        String mapFileName = mapListView.getSelectionModel().getSelectedItem();
        String turnsCountString = gameTurnCountTextField.getText();
        String gameCountTextField = gameTurnCountTextField.getText();

        if(mapFileName != null && turnsCountString != null && gameCountTextField != null && validateCheckboxes()){

        } else {

        }
    }

    private boolean validateCheckboxes() {
        boolean isAggressiveChecked = aggressiveCheckbox.isSelected();
        boolean isBenevolentCheckout = benevolentCheckbox.isSelected();
        boolean isCheaterCheckout = cheaterCheckbox.isSelected();
        boolean israndomCheckout = randomCheckbox.isSelected();



        return false;
    }
}
