	package view;

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

import org.json.JSONException;

import model.EnumClass;
import model.GameModel;
import model.PhaseStartUpModel;
import model.UnitModel;

/**
 * This class creates the User interface by using observers
 * 
 * @author Sriram Veeraghanta
 * 
 */

@SuppressWarnings("deprecation")
public class GameView implements Observer {

	static JFrame mainFrame;
	public String PROJECT_TITLE = new String("Risk Game- G-40");
	public String GAME_TITLE = new String("RISK GAME");
	public String NEW_GAME_BUTTON_TITLE = new String("New Game");

	private PhaseStartUpModel startUp;

	public GameView(GameModel model) throws JSONException {
		this._initialize(model);
	}

	/**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return mainFrame;
	}

	/**
	 * @param frame the frame to set
	 */
	public void setFrame(JFrame frame) {
		this.mainFrame = frame;
		// this.mainFrame.setExtendedState(frame.getExtendedState() |
		// JFrame.MAXIMIZED_BOTH);
	}

	@Override
	public void update(Observable o, Object obj) {
		if (o instanceof GameModel) {
			GameModel model = (GameModel) o;
			this.mainFrame.setVisible(model.isVisible());
		}
	}

	/*
	 * Creating Game Panels. buttons and containers necessary across the game.
	 */
	private void _initialize(GameModel model) throws JSONException {

		mainFrame = new JFrame();
		mainFrame.setTitle(PROJECT_TITLE);
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		HomePanel homePanelClass = new HomePanel();
		JPanel homePanel = homePanelClass.getPanel();

		mainFrame.add(homePanel);
		// setting grid layout to the frame.
		mainFrame.pack();
		mainFrame.setVisible(true);

		startUp = new PhaseStartUpModel(4);

	}
}