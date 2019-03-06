package views;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.PlayerController;
import models.GameModel;
import models.PlayerModel;
import models.UnitModel;
import utils.EnumClass;

/**
 * Creating tool bar panel
 *
 */
public class GameBoardToolbar extends JPanel {
	private GameModel gameModel;
	
	public GameBoardToolbar(GameModel gameModel) {
		this.gameModel = gameModel;
		this.buildPanel();
	}
	
	JLabel playerLabelColor;

	private void buildPanel() {	
		GameModel gameModel =new GameModel();
		for(PlayerModel player : gameModel.getPlayers()) {
				playerLabelColor = new JLabel();
				playerLabelColor.setPreferredSize(new Dimension(120, 40));
				playerLabelColor.setOpaque(true);
				playerLabelColor.setBackground(playerColor(player.getColor().toString()));
				this.add(playerLabelColor);
		}
	}
	public Color playerColor(String color) {
		if(Color.BLACK.toString().equals(color)) {
			return Color.BLACK;
		}else if(Color.BLUE.toString().equals(color)) {
			return Color.BLUE;
		}else if(Color.GREEN.toString().equals(color)) {
			return Color.GREEN;
		}else if(Color.PINK.toString().equals(color)) {
			return Color.PINK;
		}else if(Color.RED.toString().equals(color)) {
			return Color.RED;
		}else {
			return Color.YELLOW;
		}
	}

	public JPanel getPanel() {
		return this;
	}	
}
