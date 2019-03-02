package views;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import utils.GameConstant;

public class GameBoardSideBar extends JPanel {
	
	JButton reinforceButton,
			attackButton,
			fortifyButton;
	
	public GameBoardSideBar() {
		this.setLayout(new GridBagLayout());
		this.buildPanel();
	}

	private void buildPanel() {
		// GridBag Layout contrainsts
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridwidth = gridBagConstraints.REMAINDER;
		gridBagConstraints.anchor = gridBagConstraints.CENTER;
		
		// Creating reinforcement button
		reinforceButton = new JButton(GameConstant.REINFORCEMENT_BUTTON_TITLE);
		reinforceButton.setPreferredSize(new Dimension(150, 40));
		// creating attack button
		attackButton = new JButton(GameConstant.ATTACK_BUTTON_TITLE);
		attackButton.setPreferredSize(new Dimension(150, 40));
		// create fortify button
		fortifyButton = new JButton(GameConstant.FORTIFY_BUTTON_TITLE);
		fortifyButton.setPreferredSize(new Dimension(150, 40));
		
		this.add(reinforceButton, gridBagConstraints);
		this.add(attackButton, gridBagConstraints);
		this.add(fortifyButton, fortifyButton);
		
	}

	public JPanel getPanel() {
		return this;
	}	
	
}	
