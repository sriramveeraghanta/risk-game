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

public class GameBoardController {


    private GameModel gameModel;


    private ArrayList playerCardList = new ArrayList();

    @FXML
    public TilePane playerListPanel, mapInfoPanel;
    @FXML
    public Button attackButton, fortifyButton, reinforceButton, nextPlayerButton;
    @FXML
    private Label currentPlayerLabel;

    public GameBoardController() {
    }

    private GameModel getGameModel() {
        return gameModel;
    }

    void setGameModel(GameModel gameModel) throws IOException {
        this.gameModel = gameModel;
        this.initData();
    }

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
            System.out.println(player);
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

    @FXML
    private void getNextPlayer(){
        if(gameModel.getNumberOfPlayers()==gameModel.getCurrentPlayerIndex()+1){
            gameModel.setCurrentPlayerIndex(0);
        }
        else{
            gameModel.setCurrentPlayerIndex(gameModel.getCurrentPlayerIndex()+1);
        }
        currentPlayerLabel.setText(gameModel.getPlayers().get(gameModel.getCurrentPlayerIndex()).getColor().toString());
    }


}
