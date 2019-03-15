package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import models.GameModel;

/**
 * This class creates GameBoardPanel with map and buttons
 * 
 */
public class GameBoardView extends JPanel {
	private GameModel gameModel;

	public GameBoardView(GameModel gameModel) {
		this.gameModel = gameModel;
		setLayout(new BorderLayout(0, 0));
		this.init();
	}
	
	public void init() {
		GameBoardToolbar gameBoardToolbar = new GameBoardToolbar(gameModel);
		GameBoardSideBar gameBoardSideBar = new GameBoardSideBar(gameModel);
		GameBoardMap gameBoardMap = new GameBoardMap();
		
		//adding the side bar, map and toolbar to the view 
		this.add(gameBoardToolbar.getPanel(), BorderLayout.NORTH);
		this.add(gameBoardSideBar.getPanel(), BorderLayout.WEST);
		this.add(gameBoardMap.getPanel(), BorderLayout.CENTER);
	}
	
	public JPanel getPanel() {
		return this;
	}
	
	
}
