package main.utills;

import javafx.scene.control.Alert;

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
}
