package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.controllers.GameController;
import main.models.GameModel;
import main.utills.GameConstants;


/**
 * This class is a Main Class
 */
public class Main extends Application {


    /**
     * This method starts the application
     * @param primaryStage type of Stage
     * @throws Exception if exception happened it throws exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        GameModel gameModel = new GameModel();
        gameModel.setTitle(GameConstants.GAME_TITLE);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Home.fxml"));
        Parent root = loader.load();

        GameController controller = loader.getController();
        controller.setGameModel(gameModel);

        primaryStage.setTitle(gameModel.getTitle());
        primaryStage.setScene(new Scene(root, 1280, 768));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    /**
     * This is the main method of the application
     * @param args the default parameter for main
     */
    public static void main(String[] args) {
        launch(args);
    }
}
