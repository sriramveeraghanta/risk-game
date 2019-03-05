package controllers;

import models.GameModel;
import utils.GameConstant;
import views.GameBoardView;
import views.GameView;
import views.HomeView;

public class GameController {

	private GameModel gameModel;
	private GameView gameView;

	public GameController() {
		initialize();
	}

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

	public void showHomeView() {
		HomeView homeView = new HomeView();
		homeView.buildPanel();
		gameView.getFrame().add(homeView.getPanel());
		gameView.getFrame().setVisible(true);
	}

	public void navigateToGameBoard(int numberOfPlayers) {
		PlayerController playerController = new PlayerController(numberOfPlayers);
		playerController.createPlayers();
		
		// Removing Current existing pane
		gameView.getFrame().getContentPane().removeAll();
		GameBoardView gameBoardView = new GameBoardView();
		gameBoardView.buildPanel();
		//replacing them with Game Board View
		gameView.getFrame().add(gameBoardView.getPanel());
	}
}
