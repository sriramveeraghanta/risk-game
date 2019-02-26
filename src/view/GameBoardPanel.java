package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 * This class creates GameBoardPanel with map and buttons
 * 
 * @author Harish Jayasankar
 * 
 */
public class GameBoardPanel extends JFrame {

	static JFrame mainFrame;
	public JButton reinforceButton;
	public JButton attackButton;
	public JButton fortifyButton;
	public JButton diceButton;
	public static String PROJECT_TITLE = new String("Risk Game- G-40");
	public JPanel phaseButtonPanel;
	public JPanel playerColorPanel;
	public JPanel mapViewPanel;
	public JPanel optionsViewPanel;
	public JLabel playerLabelColor;
	public JComboBox diceCount;
	public GridLayout phaseButtonLayout;
	public Integer attackerDiceCount[] = {1,2,3};
	public Integer defendeDiceCount[] = {1, 2};
	public Color playerColor[] = { Color.GREEN, Color.red, Color.yellow, Color.white, Color.pink, Color.magenta };
	public int playersCount;
	public String actionReinforce = "Reinforcement";
	public String actionAttack = "Attack";
	public String actionfortify = "Fortify";
	public String actionDice = "Dice";

	public GameBoardPanel(int numberOfPlayer) {
		playersCount = numberOfPlayer;
	}

	/**
	 * mainframe panel with all the buttons and map
	 */
	public void createGameBoardUI() {
		mainFrame = new JFrame();
		mainFrame.setTitle(PROJECT_TITLE);
		// create a splitpane
		JSplitPane splitboard_button = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, phaseButton(), mapPanel());
		// set Orientation for slider
		splitboard_button.setOrientation(SwingConstants.VERTICAL);

		JSplitPane splitboard_color = new JSplitPane(JSplitPane.VERTICAL_SPLIT, playerColor(), splitboard_button);
		splitboard_color.setOrientation(SwingConstants.HORIZONTAL);

		/*
		 * JSplitPane splitboard_option = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
		 * splitboard_color, optionsPanel());
		 * splitboard_option.setOrientation(SwingConstants.VERTICAL);
		 */

		mainFrame.add(splitboard_color);
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	/**
	 * Jpanel for setting phase action buttons like reinforcement,attack,etc..
	 */
	private JPanel phaseButton() {

		// Sets Layout
		phaseButtonLayout = new GridLayout();
		// Creates the panel
		phaseButtonPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridwidth = gridBagConstraints.REMAINDER;
		gridBagConstraints.anchor = gridBagConstraints.NORTHWEST;
		// Creates buttons
		reinforceButton = new JButton(actionReinforce);
		reinforceButton.setPreferredSize(new Dimension(150, 40));
		reinforceButton.setActionCommand(actionReinforce);
		reinforceButton.addActionListener(new PlayerPhaseAction());

		attackButton = new JButton(actionAttack);
		attackButton.setPreferredSize(new Dimension(150, 40));
		attackButton.setActionCommand(actionAttack);
		attackButton.addActionListener(new PlayerPhaseAction());

		fortifyButton = new JButton(actionfortify);
		fortifyButton.setPreferredSize(new Dimension(150, 40));
		fortifyButton.setActionCommand(actionfortify);
		fortifyButton.addActionListener(new PlayerPhaseAction());

		// TODO:number of dice need to be displayed based on player role like attacker
		// and defender.
		// This logic need to be updated according to the input
		JLabel diceMsg = new JLabel("Player Dice :");
		diceMsg.setPreferredSize(new Dimension(150, 40));
		diceCount = new JComboBox(attackerDiceCount);
		diceCount.addItemListener(new DiceCountAction());
		diceCount.setPreferredSize(new Dimension(150, 40));

		// Adds buttons to mainPanel
		phaseButtonPanel.add(reinforceButton, gridBagConstraints);
		phaseButtonPanel.add(attackButton, gridBagConstraints);
		phaseButtonPanel.add(fortifyButton, gridBagConstraints);
		phaseButtonPanel.add(diceMsg, gridBagConstraints);
		phaseButtonPanel.add(diceCount, gridBagConstraints);

		return phaseButtonPanel;
	}

	/**
	 * Jpanel for displaying player colors with label
	 */
	private JPanel playerColor() {
		//TODO:Label allignment need to be done
		// Creates the panel
		playerColorPanel = new JPanel();
		playerColorPanel.setPreferredSize(new Dimension(600, 70));
		String playerno;
		// set the border of this component
		for (int count = 1; count <= playersCount; count++) {
			playerno = "PlayerNo:" + (Integer.toString(count));
			playerLabelColor = new JLabel(playerno, JLabel.CENTER);
			playerLabelColor.setPreferredSize(new Dimension(120, 40));
			playerLabelColor.setOpaque(true);
			playerLabelColor.setBackground(playerColor[count - 1]);
			playerColorPanel.add(playerLabelColor);

		}
		return playerColorPanel;
	}

	/**
	 * Jpanel for displaying map
	 */
	private JPanel mapPanel() {
		// TODO:map needs to be included
		mapViewPanel = new JPanel();
		mapViewPanel.setPreferredSize(new Dimension(700, 100));
		return mapViewPanel;
	}

	/*
	 * private JPanel optionsPanel() { //// need to updated
	 * System.out.println("optionsPanel"); // Creates the panel optionsViewPanel =
	 * new JPanel(); optionsViewPanel.setPreferredSize(new Dimension(300, 600));
	 * 
	 * int cards =5; ///for testing purpose String cardsInHand
	 * ="Number of Cards:"+Integer.toString(cards); JLabel playerCards = new
	 * JLabel(cardsInHand); JButton turnInCard=new JButton("Turn IN Card");
	 * optionsViewPanel.add(playerCards); optionsViewPanel.add(turnInCard); return
	 * optionsViewPanel; }
	 */

	/**
	 * Action Listener For phase buttons
	 */
	class PlayerPhaseAction implements ActionListener {
		// TODO:action methods needs to be included
		@Override
		public void actionPerformed(ActionEvent evt) {
			String actionEvent = evt.getActionCommand();
			if (actionEvent.equalsIgnoreCase(actionReinforce)) {
				System.out.println(actionEvent);
			} else if (actionEvent.equalsIgnoreCase(actionAttack)) {

			} else if (actionEvent.equalsIgnoreCase(actionfortify)) {

			} else if (actionEvent.equalsIgnoreCase(actionDice)) {

			}
		}
	}

	/**
	 * Listener for getting players dice choice
	 */
	class DiceCountAction implements ItemListener {

		public void itemStateChanged(ItemEvent evt) {
			System.out.println(evt.getItem());

		}
	}
}
