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

import model.GameModel;

/** 
 * This class creates the User interface by using observers
 * @author Sriram Veeraghanta 
 * 
 */
public class GameView {
	
	static JFrame mainFrame;
	public static String PROJECT_TITLE = new String("Risk Game- G-40");
	public static String GAME_TITLE = new String("RISK GAME");
	public static String NEW_GAME_BUTTON_TITLE = new String("New Game"); 

	public GameView(GameModel model) {
		//this._initialize(model);
	}
	
	/*
	 * Creating Game Panels. buttons and containers necessary across the game.
	 * */
	public static void main(String args[]) {
		
		createHomeUI();
	}

	private static void createHomeUI() {
		
		mainFrame = new JFrame();
		mainFrame.setTitle(PROJECT_TITLE);
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		HomePanel homePanelClass = new HomePanel();
		JPanel homePanel = homePanelClass.getPanel(); 
		
		mainFrame.add(homePanel);
		// setting grid layout to the frame.
		mainFrame.pack();
		mainFrame.setVisible(true);
		
	}
}