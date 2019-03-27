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

public class LoadGameController {

    private GameModel gameModel;
    private GameController gameController;
    private Stage stage;
    public String mapFilePath=null;


    @FXML
    private TextArea mapDataTextArea;
    @FXML
    private TextField mapFileNameTextField;

    public LoadGameController() {

    }

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
     * Reads the relative path to the resource directory from the <code>data</code> file located in
     * <code>/resources/</code>
     * @return the relative path to the <code>resources</code> in the file system
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
     * Reads the relative path to the resource directory from the <code>data</code> file located in
     * <code>/resources/</code>
     * @return gameModel
     */
    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
