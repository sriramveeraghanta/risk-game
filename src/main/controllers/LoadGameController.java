package main.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.models.GameModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class LoadGameController {

    private GameModel gameModel;

    @FXML
    private TextArea mapEditorTextArea;
    @FXML
    private TextField fileNameTextField;

    public LoadGameController() {

    }

    @FXML
    public void createUserMap(ActionEvent event) {
        CharSequence fileName = fileNameTextField.getCharacters();
        ObservableList<CharSequence> paragraph = mapEditorTextArea.getParagraphs();
        createMapFile(fileName, paragraph);
    }

    /**
     * Reads the relative path to the resource directory from the <code>data</code> file located in
     * <code>/resources/</code>
     * @return the relative path to the <code>resources</code> in the file system
     */
    private void createMapFile(CharSequence fileName, ObservableList<CharSequence> mapData) {
        Iterator<CharSequence> iterator = mapData.iterator();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(fileName.toString()+".map")));
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
     * Reads the relative path to the resource directory from the <code>data</code> file located in
     * <code>/resources/</code>
     * @return the relative path to the <code>resources</code> in the file system, or
     *         <code>null</code> if there was an error
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
}
