package controller;

import model.GameModel;
import view.GameView;

public class GameController {

	private GameModel model;
	private GameView view;

	public GameController() {
		initialize();
	}

	private void initialize() {
		// create model
		this.model = new GameModel();
		// set first message for model
		model.setTitle("Risk - SOEN 6441 Porject - Group # 40");
		// create view
		this.view = new GameView(model);

		// ad view as observer for model
		model.addObserver(view);
	}

	public void showView() {
		view.getFrame().setVisible(true);
	}
}
