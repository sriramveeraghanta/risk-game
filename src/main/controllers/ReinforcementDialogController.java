package main.controllers;

import main.models.GameModel;

/**
 * This class is the reinforcement phase controller
 */
public class ReinforcementDialogController {

    private GameModel gameModel;

    /**
     * action of adding army
     */
    public void addArmyAction() {
    }

    /**
     * action of swapping cards
     */
    public void swapCardAction() {
    }

    /**
     * Getter method to get the game model object
     * @return object of game model
     */
    public GameModel getGameModel() {
        return gameModel;
    }

    /**
     * Setter method to setting the game model
     * @param gameModel object of game model
     */
    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }
}
