package views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.GameController;
import controllers.PlayerController;
import models.GameModel;
import utils.GameConstant;

/**
 * Creating Home Screen Panel
 * 
 * */

public class HomeView extends JPanel {
	public JPanel thisPanel;
	public GameController gameController;
	
	public HomeView() {
		thisPanel = this;
		// adding few properties to the panel
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.setLayout(new GridBagLayout());
	}

	public void buildPanel() {
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
			int numberOfPlayer = Integer.parseInt(JOptionPane.showInputDialog(thisPanel, "Enter the number of players?", null));
			
			gameController = new GameController();
			gameController.navigateToGameBoard(numberOfPlayer);
			
			
		}
	};

}
