package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controllers.GameController;
import models.GameModel;
import utils.GameConstant;
import views.GameBoardView;
import views.GameView;

public class StartNewGame implements ActionListener {
	GameModel gameModel;
	GameView gameView;
	GameController gameController;

	public StartNewGame() {
		this.gameController = new GameController();
		this.gameModel = gameController.getGameModel();
		this.gameView = gameController.getGameView();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int playerCount;
		
		while(true) {
			String playerCountString = JOptionPane.showInputDialog(null, "Enter the number of players?", null);
			try {
				playerCount = Integer.parseUnsignedInt(playerCountString);
				if(playerCount <= GameConstant.MAXIMUM_NUMBER_OF_PLAYERS && playerCount >= GameConstant.MINIMUM_NUMBER_OF_PLAYERS) {
					startDefaultGameBoard();
					break;
				} else  {
					JOptionPane.showMessageDialog(null, GameConstant.PLAYER_COUNT_ERROR, "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException exception) {  
				JOptionPane.showMessageDialog(null, GameConstant.INVALID_PLAYER_COUNT_ERROR, "ERROR", JOptionPane.ERROR_MESSAGE);
		    }
		}
	}

	private void startDefaultGameBoard() {
		GameBoardView gameBoardView = new GameBoardView(gameModel);
		gameView.getFrame().add(gameBoardView.getPanel());
		gameView.getFrame().setVisible(true);
	}
}
