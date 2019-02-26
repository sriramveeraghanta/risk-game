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

	public GameView(GameModel model) {
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
	private void _initialize(GameModel model) {

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

//		for (int i = 0; i < startUp.getPlayers().size(); i++) {
//			System.out.println(startUp.getPlayers().get(i).getColor().toString());
//			for (int j = 0; j < startUp.getPlayers().get(i).getArmy().size(); j++)
//				System.out.println("unit type " + startUp.getPlayers().get(i).getArmy().get(j).getType().toString()
//						+ " " + "unit number " + startUp.getPlayers().get(i).getArmy().get(j).getUnitNumber());
//			System.out.println( "                   ");
//			for (int j = 0; j < startUp.getPlayers().get(i).getCountries().size(); j++)
//				System.out.println("country Id " + startUp.getPlayers().get(i).getCountries().get(j).getId()
//						+ " " + "country Name " + startUp.getPlayers().get(i).getCountries().get(j).getName());
//		}
//		System.out.println( "                   ");
//		for (int i = 0; i < startUp.getCountries().size(); i++) {
//			System.out.println(" country id  " + startUp.getCountries().get(i).getId() + " " + "country Name "
//					+ startUp.getCountries().get(i).getName());
//		}
//		
	}
}
