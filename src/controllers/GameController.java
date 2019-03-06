package controllers;

import models.GameModel;
import utils.GameConstant;
import views.GameBoardView;
import views.GameView;
import views.HomeView;
/**
 * 
 * representing some initialization and main operations like connecting view and model
 *
 */
public class GameController {

	private GameModel gameModel;
	private GameView gameView;
	
	/**
	 * Constructor 
	 */
	public GameController(GameModel gameModel, GameView gameView) {
		this.gameModel = gameModel;
		this.gameView = gameView;
		init();
	}
	
	/**
	 * init method to add few info to the model/ 
	 * */
	private void init() {
		gameModel.setTitle(GameConstant.PROJECT_TITLE);
	}
	
	/**
	 * Showing the home view of the game 
	 */
	public void showHomeView() {
		HomeView homeView = new HomeView(gameModel, gameView);
		homeView.buildPanel();
		gameView.getFrame().add(homeView.getPanel());
		gameView.getFrame().setVisible(true);
	}
}
