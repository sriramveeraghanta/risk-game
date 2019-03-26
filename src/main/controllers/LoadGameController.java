package main.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.models.GameModel;
import main.utills.GameConstants;

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

    @FXML
    public void createUserMap(ActionEvent event) throws IOException {
        CharSequence fileName = mapFileNameTextField.getCharacters();
        ObservableList<CharSequence> paragraph = mapDataTextArea.getParagraphs();
        createMapFile(fileName, paragraph);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Risk Game");
        alert.setHeaderText("Do you want to Start a Game with new map ?");
        ButtonType buttonTypeOne = new ButtonType("Yes");
        ButtonType buttonTypeTwo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeOne,buttonTypeTwo);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            this.getGameController().setUserMap(true);
            this.getGameController().setUserMapFilePath(mapFilePath);
            this.getGameController().playerCountDialog();
        }
        alert.close();
        this.getStage().close();

    }


    /**
     * Create map file according to the file name and map data which gets as  parameters
     * @param fileName this parameter is the file name type of  CharSequence
     * @param mapData this method is an observable list of CharSequence
     */
    private void createMapFile(CharSequence fileName, ObservableList<CharSequence> mapData) {
        Iterator<CharSequence> iterator = mapData.iterator();
         mapFilePath = GameConstants.USER_MAP_FILE_PATH + fileName.toString()+".map";

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(mapFilePath)));
            while(iterator.hasNext()) {
                CharSequence seq = iterator.next();
                bufferedWriter.append(seq);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getting the resource path
     * @return the path as a String
     */
    private static String getResourcePath() {
        try {
            URI resourcePathFile = System.class.getResource("/data").toURI();
            String resourcePath = Files.readAllLines(Paths.get(resourcePathFile)).get(0);
            URI rootURI = new File("").toURI();
            URI resourceURI = new File(resourcePath).toURI();
            URI relativeResourceURI = rootURI.relativize(resourceURI);
            return relativeResourceURI.getPath();
        } catch (Exception e) {
            return null;
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
