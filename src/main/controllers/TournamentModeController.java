package main.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import main.helpers.GameHelper;
import main.helpers.TournamentMode;
import main.utills.DialogHandler;
import main.utills.GameConstants;
import main.utills.GameException;

import java.io.IOException;
import java.util.ArrayList;

public class TournamentModeController {

    @FXML
    public CheckBox aggressiveCheckbox;
    @FXML
    public CheckBox randomCheckbox;
    @FXML
    public CheckBox benevolentCheckbox;
    @FXML
    public CheckBox cheaterCheckbox;
    @FXML
    public TextField gameTurnCountTextField, gameCountTextField;
    @FXML
    public ListView<String> mapListView;

    public ArrayList<String> playersTypes = new ArrayList<>();

    /**
     * Initializes the Controller with pre defined data.
     * @throws IOException input expection from the map
     * */
    @FXML
    public void initialize() throws IOException {
        GameHelper gameHelper = new GameHelper();
        ArrayList<String> fileNamesList = gameHelper.getResourceFiles(GameConstants.MAPS_DIR_PATH);
        ObservableList<String> fileNamesObservableList = FXCollections.observableArrayList(fileNamesList);
        mapListView.setItems(fileNamesObservableList);
    }

    /**
     * Start Tournament Action
     * */
    @FXML
    public void startTournamentMode() {
        String mapFileName = mapListView.getSelectionModel().getSelectedItem();
        String turnsCountString = gameTurnCountTextField.getText();
        String gameCountString = gameCountTextField.getText();

        if(mapFileName != null && turnsCountString != null && gameCountTextField != null && validateCheckboxes()){
            try{
                int turnsCount = Integer.parseUnsignedInt(turnsCountString);
                int gameCount = Integer.parseUnsignedInt(gameCountString);
                if(turnsCount > 10 && turnsCount < 50 && gameCount > 0){
                    TournamentMode tournamentMode = new TournamentMode(turnsCount, gameCount, mapFileName, playersTypes);
                    tournamentMode.initMode();
                } else {
                    DialogHandler.showWarningMessage(GameConstants.INVALID_NUMBER_TOURNAMENT);
                }
            } catch (NumberFormatException e){
                DialogHandler.showWarningMessage(GameConstants.INVALID_NUMBER_TOURNAMENT);
            } catch (GameException e) {
                DialogHandler.showWarningMessage(GameConstants.INVALID_MAP_ERROR);
            }
        } else {
            DialogHandler.showWarningMessage(GameConstants.INVALID_TOURNAMENT_DATA);
        }
    }

    private boolean validateCheckboxes() {
        boolean isAggressiveChecked = aggressiveCheckbox.isSelected();
        boolean isBenevolentChecked = benevolentCheckbox.isSelected();
        boolean isCheaterChecked = cheaterCheckbox.isSelected();
        boolean isRandomChecked = randomCheckbox.isSelected();

        int counter = 0;
        if(isAggressiveChecked){
            playersTypes.add("AGGRESSIVE");
            counter = counter + 1;
        }
        if(isBenevolentChecked){
            playersTypes.add("BENEVOLENT");
            counter = counter + 1;
        }
        if(isCheaterChecked){
            playersTypes.add("CHEATER");
            counter = counter + 1;
        }
        if(isRandomChecked){
            playersTypes.add("RANDOM");
            counter = counter + 1;
        }

        if(counter < 2 && counter > 4){
            return false;
        } else {
            return true;
        }
    }
}
