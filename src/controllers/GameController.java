package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import javax.swing.JOptionPane;

import models.GameModel;
import models.PlayerModel;
import utils.GameConstant;
import utils.GameException;
import views.GameView;

/**
 * 
 * representing some initialization and main operations like connecting view and
 * model
 *
 */
public class GameController implements ActionListener {

	private GameModel gameModel;
	private GameView gameView;

	/**
	 * Constructor
	 */
	public GameController() {

	}

	public GameView getGameView() {
		return this.gameView;
	}

	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}

	public GameModel getGameModel() {
		return this.gameModel;
	}

	public void setGameModel(GameModel gameModel) {
		this.gameModel = gameModel;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// System.out.println(event);

		if (event.getActionCommand().equals(GameConstant.NEW_GAME_BUTTON_TITLE)) {
			// MapBuilder mapBuilder = new MapBuilder();
			int playerCount;

			while (true) {
				String playerCountString = JOptionPane.showInputDialog(null, "Enter the number of players?", null);
				try {
					playerCount = Integer.parseUnsignedInt(playerCountString);
					if (playerCount <= GameConstant.MAXIMUM_NUMBER_OF_PLAYERS
							&& playerCount >= GameConstant.MINIMUM_NUMBER_OF_PLAYERS) {
						gameModel.setNumberOfPlayers(playerCount);
						startDefaultGameBoard();
						break;
					} else {
						JOptionPane.showMessageDialog(null, GameConstant.PLAYER_COUNT_ERROR, "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, GameConstant.INVALID_PLAYER_COUNT_ERROR, "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} catch (GameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	private void startDefaultGameBoard() throws GameException {
		MapBuilder mapBuilder = new MapBuilder(gameModel);
		mapBuilder.readMapFile(null);
		StartUp startUp = new StartUp(gameModel);
		System.out.println("END");
	}
}
