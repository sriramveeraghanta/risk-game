package views;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import action.action;
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
		ActionListener actionListener=new action();
		
		// Creating reinforcement button
		reinforceButton = new JButton(GameConstant.REINFORCEMENT_BUTTON_TITLE);
		reinforceButton.setPreferredSize(new Dimension(150, 40));
		reinforceButton.setActionCommand(GameConstant.REINFORCEMENT_BUTTON_TITLE);
		reinforceButton.addActionListener(actionListener);
		
		// creating attack button
		attackButton = new JButton(GameConstant.ATTACK_BUTTON_TITLE);
		attackButton.setPreferredSize(new Dimension(150, 40));
		attackButton.setActionCommand(GameConstant.ATTACK_BUTTON_TITLE);
		attackButton.addActionListener(actionListener);
		
		// create fortify button
		fortifyButton = new JButton(GameConstant.FORTIFY_BUTTON_TITLE);
		fortifyButton.setPreferredSize(new Dimension(150, 40));
		fortifyButton.setActionCommand(GameConstant.FORTIFY_BUTTON_TITLE);
		fortifyButton.addActionListener(actionListener);
		
		this.add(reinforceButton, gridBagConstraints);
		this.add(attackButton, gridBagConstraints);
		this.add(fortifyButton, gridBagConstraints);
		
	}

	public JPanel getPanel() {
		return this;
	}	
	
}	
