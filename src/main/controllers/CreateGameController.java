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
import main.utills.DialogHandler;

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
            boolean isMapValid;
            try {
                // Map Builder
                MapBuilder mapBuilder = new MapBuilder(this.getGameModel());
                isMapValid = mapBuilder.readMapFile(fileName+".map");
                if(isMapValid) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Risk Game");
                    alert.setHeaderText(GameConstants.VALID_MAP_FILE_SAVE);
                    alert.showAndWait();
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                }
            }catch (GameException e) {
                DialogHandler.showWarningMessage(GameConstants.INVALID_MAP_ERROR);
            }
        } else {
            DialogHandler.showWarningMessage(GameConstants.USER_NO_INPUT);
        }
    }

    /**
     * Create map file according to the file name and map data which gets as  parameters
     * @param fileName this parameter is the file name type of  CharSequence
     * @param mapData this method is an observable list of CharSequence
     */
    private void createMapFile(CharSequence fileName, ObservableList<CharSequence> mapData) {
        Iterator<CharSequence> iterator = mapData.iterator();
        String mapFilePath = null;
        try {
            mapFilePath = GameConstants.MAPS_DIR_PATH + fileName.toString()+".map";
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(mapFilePath)));
            while(iterator.hasNext()) {
                CharSequence seq = iterator.next();
                bufferedWriter.append(seq);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            DialogHandler.showWarningMessage(GameConstants.INVALID_MAP_ERROR);
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
