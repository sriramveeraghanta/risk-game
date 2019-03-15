package views;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.GameController;
import models.GameModel;
import utils.GameConstant;

/**
 * This class creates the User interface by using observers
 * 
 * 
 */

public class GameView implements Observer{
	
	// UI elements
	private JFrame mainFrame;
	private JPanel mainPanel;
	private Label homeTitleLabel;
	private JButton newGameButton, exitGameButton;
	
	
	private GameModel gameModel;

	public GameView() {
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
		
		// Creating Main Panel
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainPanel.setLayout(new GridBagLayout());
		
		// Creating a Label on the top
		homeTitleLabel  = new Label();
		homeTitleLabel.setAlignment(Label.CENTER);
		homeTitleLabel.setText(GameConstant.GAME_TITLE);
		homeTitleLabel.setFont(new Font(homeTitleLabel.getName(), Font.PLAIN, 35));
		
		// Creating new Game Button
		newGameButton = new JButton(GameConstant.NEW_GAME_BUTTON_TITLE);
		newGameButton.setPreferredSize(new Dimension(300,150));
		
		// Creating exit game button
		exitGameButton = new JButton(GameConstant.EXIT_GAME_BUTTON_TITLE); 
		exitGameButton.setPreferredSize(new Dimension(300,150));
		exitGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		// GridBag Layout for this panel
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridwidth = gridBagConstraints.REMAINDER;
		gridBagConstraints.anchor = gridBagConstraints.NORTH;
		gridBagConstraints.weighty = 1;
				
		// Button panel for making button. 
		JPanel buttonsPanel = new JPanel(new GridBagLayout());
				
		// Adding button to the panel
		buttonsPanel.add(newGameButton, gridBagConstraints);
		buttonsPanel.add(exitGameButton, gridBagConstraints);
				
		// Adding Label and Button panel to the home panel
		mainPanel.add(homeTitleLabel, gridBagConstraints);
		mainPanel.add(buttonsPanel, gridBagConstraints);
		
		// Adding Main Panel to Main frame
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
	}

	public void addController(GameController gameController) {
		newGameButton.addActionListener(gameController);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
	}
	

	
}