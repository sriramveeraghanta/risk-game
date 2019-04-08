package main.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import main.models.ContinentModel;
import main.models.CountryModel;
import main.models.GameModel;
import main.models.PlayerModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * This class is a controller for game board
 */
public class GameBoardController implements Observer {

    private GameModel gameModel;

    @FXML
    public TilePane playerListPanel, mapInfoPanel;
    @FXML
    public Button attackButton, fortifyButton, reinforceButton, nextPlayerButton;
    @FXML
    private Label currentPlayerLabel;

    /**
     * Constructor of the game board controller
     */
    public GameBoardController() {
    }

    /**
     * This method initialize the data
     * @throws IOException User input exception
     */
    private void initData() throws IOException {
        gameModel.setCurrentPlayerIndex(0);
        gameModel.addObserver(this);
        getGameModel().getPlayers().get(getGameModel().getCurrentPlayerIndex()).setArmyInHand(3);
        currentPlayerLabel.setText(gameModel.getPlayers().get(gameModel.getCurrentPlayerIndex()).getColor().toString());
        this.renderPlayersInfo();
        this.renderMapInfo();
    }

    /**
     * This method renders the Map info
     * @throws IOException User input exception
     */
    private void renderMapInfo() throws IOException {
        ArrayList<ContinentModel> continents = getGameModel().getContinents();
        for(ContinentModel continent: continents) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ContinentInfo.fxml"));
            Parent continentInfoPanel = loader.load();
            ContinentInfoController continentInfoController = loader.getController();
            continentInfoController.setContinentModel(continent);
            gameModel.addObserver(continentInfoController);
            mapInfoPanel.getChildren().add(continentInfoPanel);
        }
    }

    /**
     * This method renders the player info
     * @throws IOException User input exception
     */
    private void renderPlayersInfo() throws IOException {
        ArrayList<PlayerModel> playerModelsList = getGameModel().getPlayers();
        for (PlayerModel player: playerModelsList) {
            // Player Card
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/PlayerInfo.fxml"));
            Parent playerCard = loader.load();
            PlayerInfoController playerInfoController = loader.getController();
            playerInfoController.setPlayerModel(player);
            gameModel.addObserver(playerInfoController);
            // Appending to the Flow pane
            playerListPanel.getChildren().add(playerCard);
        }
    }

    /**
     * This is a action method for attacking phase which creates the attack phase view
     */
    @FXML
    private void attackPhase() {
        // Creating an Attack Phase
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AttackDialogView.fxml"));
            Parent LoadGamePanel = loader.load();
            AttackPhaseDialogController attackPhaseDialogController = loader.getController();
            attackPhaseDialogController.setGameModel(this.gameModel);
            gameModel.addObserver(attackPhaseDialogController);
            stage.setScene(new Scene(LoadGamePanel, 600, 500));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is a private method for fortify phase which creates the fortify phase
     */
    @FXML
    private void fortifyPhase() {
        // Creating an Fortify Dialog Phase
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/FortifyView.fxml"));
            Parent LoadGamePanel = loader.load();
            FortifyDialogController FortifyDialogController = loader.getController();
            FortifyDialogController.setGameModel(this.gameModel);
            gameModel.addObserver(FortifyDialogController);
            stage.setScene(new Scene(LoadGamePanel, 600, 400));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method is a private method for reinforcement phase which creates the reinforcement phase
     */
    @FXML
    private void reinforcementPhase() {
        // Creating an Attack Phase
        try {

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ReinforcementDialogView.fxml"));
            Parent reinforcementPanel = loader.load();
            ReinforcementDialogController ReinforcementDialogController = loader.getController();
            ReinforcementDialogController.setGameModel(this.gameModel);
            gameModel.addObserver(ReinforcementDialogController);
            stage.setScene(new Scene(reinforcementPanel, 600, 400));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method is a private method which is getting the next player
     */
    @FXML
    private void getNextPlayer(){
        if(gameModel.getNumberOfPlayers() == gameModel.getCurrentPlayerIndex()+1){
            gameModel.setCurrentPlayerIndex(0);
            gameModel.getPlayers().get(gameModel.getCurrentPlayerIndex()).setArmyInHand(3);
        }
        else{
            gameModel.setCurrentPlayerIndex(gameModel.getCurrentPlayerIndex()+1);
            gameModel.getPlayers().get(gameModel.getCurrentPlayerIndex()).setArmyInHand(3);
        }
        currentPlayerLabel.setText(gameModel.getPlayers().get(gameModel.getCurrentPlayerIndex()).getColor().toString());
    }

    /**
     * Getter method to get the object of game model
     * @return the object of game model
     */
    private GameModel getGameModel() {
        return gameModel;
    }

    /**
     * Setter method to set the game model
     * @param gameModel object of game model
     * @throws IOException User input exceptions
     */
    void setGameModel(GameModel gameModel) throws IOException {
        this.gameModel = gameModel;
        this.initData();
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
