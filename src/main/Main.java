package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.controllers.GameController;
import main.models.GameModel;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GameModel gameModel = new GameModel();
        gameModel.setTitle("Risk Game");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Home.fxml"));
        Parent root = loader.load();
        GameController controller = loader.getController();
        controller.setGameModel(gameModel);
        primaryStage.setTitle(gameModel.getTitle());
        primaryStage.setScene(new Scene(root, 1280, 768));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
