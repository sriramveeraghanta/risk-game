package main.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import main.models.ContinentModel;
import main.models.GameModel;
import main.models.PlayerModel;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is a controller for game board
 */
public class GameBoardController {


    private GameModel gameModel;


    private ArrayList playerCardList = new ArrayList();

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
     * Getter method to get the object of game model
     * @return the object of game model
     */
    private GameModel getGameModel() {
        return gameModel;
    }

    /**
     * Setter method to set the game model
     * @param gameModel object of game model
     * @throws IOException
     */
    void setGameModel(GameModel gameModel) throws IOException {
        this.gameModel = gameModel;
        this.initData();
    }

    /**
     * This method initialize the data
     * @throws IOException
     */
    private void initData() throws IOException {
        //System.out.println(getGameModel());
        gameModel.setCurrentPlayerIndex(0);
        currentPlayerLabel.setText(gameModel.getPlayers().get(gameModel.getCurrentPlayerIndex()).getColor().toString());
        this.renderPlayersInfo();
        this.renderMapInfo();
    }

    private void renderMapInfo() throws IOException {
        ArrayList<ContinentModel> continents = getGameModel().getContinents();
        for(ContinentModel continent: continents) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ContinentInfo.fxml"));
            Parent continentInfoPanel = loader.load();
            ContinentInfoController continentInfoController = loader.getController();
            continentInfoController.setContinentModel(continent);
            mapInfoPanel.getChildren().add(continentInfoPanel);
        }
    }

    private void renderPlayersInfo() throws IOException {
        ArrayList<PlayerModel> playerModelsList = getGameModel().getPlayers();
        for (PlayerModel player: playerModelsList) {
            // Player Card
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/PlayerInfo.fxml"));
            Parent playerCard = loader.load();
            PlayerInfoController playerInfoController = loader.getController();
            playerInfoController.setPlayerModel(player);
            // Assigning to the list
            playerCardList.add(playerCard);
            // Appending to the Flow pane
            playerListPanel.getChildren().add(playerCard);
        }
    }

    /**
     * This method is a private method for attacking phase which creates the attack phase
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
            stage.setScene(new Scene(LoadGamePanel, 600, 400));
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
            Parent LoadGamePanel = loader.load();
            ReinforcementDialogController ReinforcementDialogController = loader.getController();
            ReinforcementDialogController.setGameModel(this.gameModel);
            stage.setScene(new Scene(LoadGamePanel, 600, 400));
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
        if(gameModel.getNumberOfPlayers()==gameModel.getCurrentPlayerIndex()+1){
            gameModel.setCurrentPlayerIndex(0);
            gameModel.getPlayers().get(0).setArmyInHand(3);
        }
        else{
            gameModel.setCurrentPlayerIndex(gameModel.getCurrentPlayerIndex()+1);
            gameModel.getPlayers().get(gameModel.getCurrentPlayerIndex()+1).setArmyInHand(3);
        }
        currentPlayerLabel.setText(gameModel.getPlayers().get(gameModel.getCurrentPlayerIndex()).getColor().toString());
    }


}
