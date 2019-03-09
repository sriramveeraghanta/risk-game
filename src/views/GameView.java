package views;

import javax.swing.JFrame;

import controllers.StartUp;
import models.GameModel;
import utils.GameConstant;

/**
 * This class creates the User interface by using observers
 * 
 * 
 */

public class GameView extends JFrame {
	private GameModel gameModel;
	public static JFrame mainFrame;

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
		mainFrame.setVisible(true);
	}
	
	/**
	 * Getter method to get main frame.
	 * @return the frame
	 */
	public JFrame getFrame() {
		return mainFrame;
	}

	/**
	 * Setter method to set main frame. 
	 * @param frame the frame to set
	 */
	public void setFrame(JFrame frame) {
		GameView.mainFrame = frame;
	}
}