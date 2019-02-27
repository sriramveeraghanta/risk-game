package controller;

import org.json.JSONException;

import model.GameModel;
import view.GameView;

public class GameController {

	private GameModel model;
	private GameView view;

	public GameController() throws JSONException {
		initialize();
	}

	private void initialize() throws JSONException {
		// create model
		this.model = new GameModel();
		// set first message for model
		model.setTitle("Risk - SOEN 6441 Porject - Group # 40");
		// create view
		this.view = new GameView(model);

		// add view as observer for model
		model.addObserver(view);
	}

	public void showView() {
		view.getFrame().setVisible(true);
	}
}
