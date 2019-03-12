import java.awt.EventQueue;
import java.io.IOException;

import controllers.GameController;
import models.GameModel;
import views.GameView;

public class Game {
	/**
	 * This is the main method.
	 * */
	public static void main(String args[]) throws IOException {
		
		System.out.println("start");
		GameController gameController = new GameController();
		gameController.showView();
		System.out.println("end");
		
//		GameModel gameModel = new GameModel();
//		GameView gameView = new GameView(gameModel);
//		// Game Controller
//		GameController gameController = new GameController(gameModel, gameView);
//		gameController.showHomeView();
		
	}
}

