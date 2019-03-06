package views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import action.action;
import controllers.PlayerController;
import models.GameModel;
import models.PlayerModel;
import utils.GameConstant;

/**
 * Creating sidebar with reinforce, attack and fortify buttons 
 *
 */
public class GameBoardSideBar extends JPanel {
	
	private GameModel gameModel;
	
	JButton reinforceButton,
			attackButton,
			fortifyButton;
	
	public GameBoardSideBar(GameModel gameModel) {
		this.gameModel = gameModel;
		this.setLayout(new GridBagLayout());
		this.buildPanel();
	}

	private void buildPanel() {
		//PlayerModel activePlayer = str.getPlayers().get(0);
		PlayerModel player;
		PlayerController playerController = new PlayerController(gameModel);
		
		player = gameModel.getPlayers().get(0);
		// GridBag Layout contrainsts
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridwidth = gridBagConstraints.REMAINDER;
		gridBagConstraints.anchor = gridBagConstraints.CENTER;
		ActionListener actionListener=new action(player, gameModel, playerController);
		
		// Creating reinforcement button and calling specified methods on action
		reinforceButton = new JButton(GameConstant.REINFORCEMENT_BUTTON_TITLE);
		reinforceButton.setPreferredSize(new Dimension(150, 40));
		reinforceButton.setActionCommand(GameConstant.REINFORCEMENT_BUTTON_TITLE);
		reinforceButton.addActionListener(actionListener);
		
		// creating attack button and calling specified methods on action
		attackButton = new JButton(GameConstant.ATTACK_BUTTON_TITLE);
		attackButton.setPreferredSize(new Dimension(150, 40));
		attackButton.setActionCommand(GameConstant.ATTACK_BUTTON_TITLE);
		attackButton.addActionListener(actionListener);
		
		// create fortify button and calling specified methods on action
		fortifyButton = new JButton(GameConstant.FORTIFY_BUTTON_TITLE);
		fortifyButton.setPreferredSize(new Dimension(150, 40));
		fortifyButton.setActionCommand(GameConstant.FORTIFY_BUTTON_TITLE);
		fortifyButton.addActionListener(actionListener);
		
		//craeting dice selector combo-box to select no of dice
		Integer[] diceCount = {1,2,3};
		JComboBox<Integer> diceBox = new JComboBox<>(diceCount);
		
		//adding the buttons and combo box on panel
		this.add(reinforceButton, gridBagConstraints);
		this.add(attackButton, gridBagConstraints);
		this.add(fortifyButton, gridBagConstraints);
		this.add(new JLabel("Number of dice"));
		this.add(diceBox, gridBagConstraints);
	}

	public JPanel getPanel() {
		return this;
	}	
	
}	
