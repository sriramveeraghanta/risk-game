package views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.GameController;
import controllers.StartUp;
import models.GameModel;
import utils.Common;
import utils.GameConstant;

/**
 * Creating Home Screen Panel
 * 
 * */

public class HomeView extends JPanel {
	
	private GameModel gameModel;
	private GameView gameView;
	public JPanel thisPanel;
	private StartUp startUp;
	
	public HomeView(GameModel model) {
		this.gameModel = model;
		thisPanel = this;
		init();
	}

	private void init() {
		// adding few properties to the panel
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.setLayout(new GridBagLayout());
		
		// Creating a Label on the top
		Label homeTitleLabel  = new Label();
		homeTitleLabel.setAlignment(Label.CENTER);
		homeTitleLabel.setText(GameConstant.GAME_TITLE);
		homeTitleLabel.setFont(new Font(homeTitleLabel.getName(), Font.PLAIN, 35));
		
		// Creating new Game Button
		JButton newGameButton = new JButton(GameConstant.NEW_GAME_BUTTON_TITLE);
		newGameButton.setPreferredSize(new Dimension(300,150));
		newGameButton.addActionListener(onNewGameButtonClick);
		
		// Creating exit game button
		JButton exitGameButton = new JButton(GameConstant.EXIT_GAME_BUTTON_TITLE); 
		exitGameButton.setPreferredSize(new Dimension(300,150));
		exitGameButton.addActionListener(onExitGameButtonClick);
		
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
		this.add(homeTitleLabel, gridBagConstraints);
		this.add(buttonsPanel, gridBagConstraints);
	}
	
	/**
	 * Getting method for getting the panel information.
	 * @return JPanel 
	 * */
	public JPanel getPanel() {
		return this;
	}
	
	/**
	 * Action Lister For New Game Button
	 **/
	public ActionListener onNewGameButtonClick = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int playerCount;
			
			while(true) {
				String playerCountString = JOptionPane.showInputDialog(thisPanel, "Enter the number of players?", null);
				try {
					playerCount = Integer.parseUnsignedInt(playerCountString);
					if(playerCount <= GameConstant.MAXIMUM_NUMBER_OF_PLAYERS && playerCount >= GameConstant.MINIMUM_NUMBER_OF_PLAYERS) {
						gameModel.setNumberOfPlayers(playerCount);
						startGameBoard();
						break;
					} else  {
						JOptionPane.showMessageDialog(thisPanel, GameConstant.PLAYER_COUNT_ERROR, "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException exception) {  
					JOptionPane.showMessageDialog(thisPanel, GameConstant.INVALID_PLAYER_COUNT_ERROR, "ERROR", JOptionPane.ERROR_MESSAGE);
			    }
			}
		}

		private void startGameBoard() {
			StartUp startUp = new StartUp(gameModel);
			startUp.createPlayers();
		}

		private void navigateToGameBoard(int numberOfPlayer) {
			gameView.getFrame().getContentPane().removeAll();
			GameBoardView gameBoardView = new GameBoardView(gameModel);
			gameBoardView.buildPanel();
			//replacing them with Game Board View
			gameView.getFrame().add(gameBoardView.getPanel());
		}
	};
	
	public ActionListener onExitGameButtonClick = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);

		}
	};


}
