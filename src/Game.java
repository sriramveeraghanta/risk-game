import java.awt.EventQueue;
import java.io.IOException;

import controllers.GameController;
import controllers.MapController;
import controllers.PlayerController;
import models.GameModel;
import views.GameView;

public class Game {
	/**
	 * This is the main method.
	 * */
	public static void main(String args[]) throws IOException {
		
		GameModel gameModel = new GameModel();
		GameView gameView = new GameView(gameModel);
		
		// Map Generation
		MapController mapController = new MapController(gameModel);
		mapController.generateMap();
		
		// Game Controller
		GameController gameController = new GameController(gameModel, gameView);
		gameController.showHomeView();
		
	}
}

