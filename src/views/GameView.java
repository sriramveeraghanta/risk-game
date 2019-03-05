package views;

import javax.swing.JFrame;

import controllers.PlayerController;
import models.GameModel;
import utils.GameConstant;

/**
 * This class creates the User interface by using observers
 * 
 * 
 */

public class GameView extends JFrame {
	public static JFrame mainFrame;

	private PlayerController startUp;

	public GameView(GameModel gameModel) {
		this.initialize(gameModel);
	}
	
	/**
	 * Creating Game Panels. buttons and containers necessary across the game.
	 * */
	private void initialize(GameModel gameModel) {
		// Creating Main frame.
		mainFrame = new JFrame();
		mainFrame.setTitle(GameConstant.GAME_TITLE);
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// setting grid layout to the frame.
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