package controllers;

import java.util.Observer;

import javax.swing.JFrame;

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
		init();
	}
	
	/**
	 * init method to add few info to the model/ 
	 * */
	private void init() {
		this.gameModel = new GameModel();
		this.gameView = new GameView(gameModel);
		gameModel.addObserver(gameView);
		gameModel.setTitle(GameConstant.PROJECT_TITLE);
	}
	
	/**
	 * Showing the home view of the game 
	 */
	public void showView() {
		HomeView homeView = new HomeView(gameModel);
		GameView.getFrame().add(homeView.getPanel());
		GameView.getFrame().setVisible(true);
	}
}
