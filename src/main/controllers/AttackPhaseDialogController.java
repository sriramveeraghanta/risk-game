package main.controllers;

import main.models.GameModel;
import main.models.PlayerModel;

public class AttackPhaseDialogController {
    private GameModel gameModel;
    private PlayerModel currentPlayer;

    public void attackAction() {

    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public PlayerModel getCurrentPlayer() {
        return currentPlayer;
    }

    public void setGameModel(GameModel gameModel, PlayerModel currentPlayer) {
        this.gameModel = gameModel;
        this.currentPlayer = currentPlayer;
    }
}
