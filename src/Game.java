import controllers.GameController;
import models.GameModel;
import views.GameView;

public class Game {
	/**
	 * This is the main method.
	 * */
	public static void main(String args[]) {
		
		GameView gameView = new GameView();
		GameModel gameModel = new GameModel();
		gameModel.addObserver(gameView);
		
		GameController gameController = new GameController();
		gameController.setGameModel(gameModel);
		gameController.setGameView(gameView);

 		gameView.addController(gameController);
 		//gameView.startGame();
	}
}

