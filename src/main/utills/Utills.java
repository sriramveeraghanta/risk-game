package main.utills;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import main.helpers.GameHelper;
import main.models.GameModel;

import java.util.Optional;

public class Utills {

    /**
     * This method triggers a warning alert view
     * @param message this message will be displayed on the alert
     * */
    public static void showWarningMessage(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public static void showErrorMessage(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public static void saveGameDialog(Stage stage, GameModel gameModel){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(GameConstants.SAVE_GAME_CONFIRMATION);
        alert.setHeaderText(GameConstants.SAVE_GAME_CONFIRMATION_DESCRIPTION);
        ButtonType buttonTypeSave = new ButtonType("Save");
        ButtonType buttonTypeCancel = new ButtonType("Don't Save");
        alert.getButtonTypes().setAll(buttonTypeSave, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent()){
            if (result.get() == buttonTypeSave){
                GameHelper gameHelper = new GameHelper();
                gameHelper.saveGame(gameModel);
            } else if(result.get() == buttonTypeCancel){
                stage.close();
            }
        }
    }
}
