package controller;

import org.json.JSONException;

import model.GameModel;
import utils.GameConstant;
import view.GameView;

public class GameController {

	private GameModel gameModel;
	private GameView gameView;
	private GameConstant gameConstant;

	public GameController() throws JSONException {
		initialize();
	}

	private void initialize() throws JSONException {
		// create model
		this.gameModel = new GameModel();
		// set first message for model
		gameModel.setTitle(gameConstant.PROJECT_TITLE);
		// create view
		this.gameView = new GameView(gameModel);

		// add view as observer for model
		gameModel.addObserver(gameView);
	}

	public void showView() {
		gameView.getFrame().setVisible(true);
	}
}
