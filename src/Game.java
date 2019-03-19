import controllers.GameController;
import models.GameModel;
import views.GameView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application {
	/**
	 * This is the main method.
	 * */
	public static void main(String args[]) {
		
//		GameView gameView = new GameView();
//		GameModel gameModel = new GameModel();
//		gameModel.addObserver(gameView);
//		
//		GameController gameController = new GameController();
//		gameController.setGameModel(gameModel);
//		gameController.setGameView(gameView);
//
// 		gameView.addController(gameController);
 		//gameView.startGame();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		 Parent root = FXMLLoader.load(getClass().getResource("/views/home.fxml"));
	     primaryStage.setTitle("Risk Game");
	     primaryStage.setScene(new Scene(root, 1280, 768));
	     primaryStage.show();
	}
}

