package main.controllers;

import main.models.GameModel;

/**
 * This class is a controller class for fortify dialog
 */
public class FortifyDialogController {

    private GameModel gameModel;

    public  void attackAction() {}

    /**
     * Getter method to get the game model object
     * @return the game model object
     */
    public GameModel getGameModel() {
        return gameModel;
    }

    /**
     * Setter method to set the game model object
     * @param gameModel type of game model
     */
    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }
}
