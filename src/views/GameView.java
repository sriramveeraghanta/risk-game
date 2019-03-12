package views;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import models.GameModel;
import utils.GameConstant;

/**
 * This class creates the User interface by using observers
 * 
 * 
 */

public class GameView implements Observer{
	
	public static JFrame mainFrame;
	private GameModel gameModel;

	public GameView(GameModel gameModel) {
		this.gameModel = gameModel;
		this.init();
	}
	
	/**
	 * Creating Game Panels. buttons and containers necessary across the game.
	 * */
	private void init() {
		// Creating Main frame.
		mainFrame = new JFrame();
		mainFrame.setTitle(GameConstant.GAME_TITLE);
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.pack();
	}
	
	/**
	 * Getter method to get Home frame.
	 * @return the frame
	 */
	public static JFrame getFrame() {
		return mainFrame;
	}

	/**
	 * Setter method to set Home frame. 
	 * @param frame the frame to set
	 */
	public static void setFrame(JFrame frame) {
		GameView.mainFrame = frame;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}