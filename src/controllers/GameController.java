package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;


import models.GameModel;
import utils.GameConstant;
import views.GameView;
/**
 * 
 * representing some initialization and main operations like connecting view and model
 *
 */
public class GameController implements ActionListener {

	private GameModel gameModel;
	private GameView gameView;
	
	/**
	 * Constructor 
	 */
	public GameController() {
		
	}

	public GameView getGameView() {
		return this.gameView;
	}
	
	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}
	
	public GameModel getGameModel() {
		return this.gameModel;
	}
	
	public void setGameModel(GameModel gameModel) {
		this.gameModel = gameModel;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println(event);
		
		if(event.getActionCommand().equals(GameConstant.NEW_GAME_BUTTON_TITLE)) {
			//MapBuilder mapBuilder = new MapBuilder();
			
		}
	}
	
	
}
