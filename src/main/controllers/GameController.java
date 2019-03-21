package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import main.utills.GameConstants;

import java.io.IOException;
import java.util.Optional;

public class GameController {

    @FXML
    private Button newGameButton,loadGameButton, exitGameButton ;

    /**
     * Start Game
     * @param event
     * @throws IOException
     */
    @FXML
    public void startNewGame(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(GameConstants.SELECT_PLAYERS);
        dialog.setTitle("Players Count");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(playerCountString -> {
            int playerCount;
            try {
                playerCount = Integer.parseUnsignedInt(playerCountString);
                if (playerCount <= GameConstants.MAXIMUM_NUMBER_OF_PLAYERS
                        && playerCount >= GameConstants.MINIMUM_NUMBER_OF_PLAYERS) {
                    // TODO: Start Startup Phase
                    System.out.println(playerCount);
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(GameConstants.INVALID_PLAYER_COUNT_ERROR);
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(GameConstants.PLAYER_COUNT_ERROR);
                alert.showAndWait();
            }
        });
    }

    /**
     * Loading the New Game to the user
     * @param event
     * @throws IOException
     */
    @FXML
    public void loadGame(ActionEvent event) throws IOException {
        System.out.println(getClass());
        Stage stage = (Stage) loadGameButton.getScene().getWindow();
        Parent LoadGamePanel = FXMLLoader.load(getClass().getResource("/views/LoadGame.fxml"));
        stage.setScene(new Scene(LoadGamePanel));
        stage.show();
    }

    /**
     * Exit the current window that is opened
     * @param event
     */
    @FXML
    public void exitGame(ActionEvent event) {
        System.out.println("Exit game");
        Stage stage = (Stage) exitGameButton.getScene().getWindow();
        stage.close();
    }
}
