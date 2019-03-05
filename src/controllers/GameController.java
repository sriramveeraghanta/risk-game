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
	public GameController() {
		initialize();
	}
	/**
	 * creating an object of view and model 
	 */
	private void initialize() {
		// create model3
		this.gameModel = new GameModel();
		// set first message for model
		gameModel.setTitle(GameConstant.PROJECT_TITLE);
		// create view
		this.gameView = new GameView(gameModel);

		// add view as observer for model
		//gameModel.addObserver(gameView);
	}
	/**
	 * Showing the home view of the game 
	 */
	public void showHomeView() {
		HomeView homeView = new HomeView();
		homeView.buildPanel();
		gameView.getFrame().add(homeView.getPanel());
		gameView.getFrame().setVisible(true);
	}
	
	/**
	 * assign the game board to the view
	 */
	public void navigateToGameBoard(int numberOfPlayers) {
		PlayerController playerController = new PlayerController();
		playerController.createPlayers(numberOfPlayers);
		
		// Removing Current existing pane
		gameView.getFrame().getContentPane().removeAll();
		GameBoardView gameBoardView = new GameBoardView();
		gameBoardView.buildPanel();
		//replacing them with Game Board View
		gameView.getFrame().add(gameBoardView.getPanel());
	}
}
