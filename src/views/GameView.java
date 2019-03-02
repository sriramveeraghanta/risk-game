package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import models.EnumClass;
import models.GameModel;
import models.PhaseStartUpModel;
import models.UnitModel;
import utils.GameConstant;

/**
 * This class creates the User interface by using observers
 * 
 * @author Sriram Veeraghanta
 * 
 */

public class GameView extends JFrame {
	public static JFrame mainFrame;

	private PhaseStartUpModel startUp;

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