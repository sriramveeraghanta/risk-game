package main.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.helpers.MapBuilder;
import main.models.GameModel;
import main.utills.GameConstants;
import main.utills.GameException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;

/**
 * this class is the controller of the game loader
 */
public class LoadGameController {

    private GameModel gameModel;
    private GameController gameController;
    private Stage stage;
    public String mapFilePath=null;


    @FXML
    private TextArea mapDataTextArea;
    @FXML
    private TextField mapFileNameTextField;

    /**
     * constructor of the load game controller
     */
    public LoadGameController() {

    }

    /**
     * this method create user map and get event which is type of ActionEvent as parameter
     * @param event type of ActionEvent
     * @throws IOException if exception occur it throws IOException
     */
    @FXML
    public void createUserMap(ActionEvent event) throws IOException {
        CharSequence fileName = mapFileNameTextField.getCharacters();
        ObservableList<CharSequence> paragraph = mapDataTextArea.getParagraphs();
        createMapFile(fileName, paragraph);
        MapBuilder mapBuilder = new MapBuilder(this.getGameModel());
        boolean isvalidMap=false;
        Alert alert=null;
        try {
            isvalidMap=mapBuilder.readMapFile(mapFilePath);
        }catch (GameException e) {
             alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(GameConstants.INVALID_MAP_ERROR);
            alert.showAndWait();
        }
        if(isvalidMap) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Risk Game");
            alert.setHeaderText(GameConstants.MAP_MSG);
            ButtonType buttonTypeOne = new ButtonType("Yes");
            ButtonType buttonTypeTwo = new ButtonType("No");
            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne) {
                this.getGameController().setUserMapValidated(true);;
                this.getGameController().playerCountDialog();
                this.getStage().close();
            }
        }
        alert.close();

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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(GameConstants.INVALID_MAP_ERROR);
            alert.showAndWait();
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

    /**
     * Getter method to get the game controller
     * @return the game controller object
     */
    public GameController getGameController() {
        return gameController;
    }

    /**
     * Setter method to set the game controller
     * @param gameController object of the game controller
     */
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Getter method to get the stage
     * @return the stage type of Stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Setter method to set the stage
     * @param stage type of Stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
