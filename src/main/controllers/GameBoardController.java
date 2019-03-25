package main.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import main.models.GameModel;
import main.models.PlayerModel;
import main.utills.GameCommons;

import java.io.IOException;
import java.util.ArrayList;

public class GameBoardController {


    private GameModel gameModel;


    private ArrayList playerCardList = new ArrayList();

    @FXML
    public TilePane playerListPanel;
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
        ArrayList<PlayerModel> playerModelsList = getGameModel().getPlayers();
        gameModel.setCurrentPlayerIndex(0);
        for (int i = 0; i < playerModelsList.size(); i++) {
            //System.out.println(playerListPanel);
            GameCommons gameCommons = new GameCommons();
            // creating label
//            Label playerLabel = new Label("Player_" + playerModelsList.get(i).getColor().toString());
//            playerLabel.setTextFill(Color.WHITE);
//            playerLabel.setBackground(new Background(new BackgroundFill(gameCommons.getFXColor(playerModelsList.get(i).getColor().toString()), CornerRadii.EMPTY, Insets.EMPTY)));

            // Player Card
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/PlayerCard.fxml"));
            Parent playerCard = loader.load();
            PlayerCardController playerCardController = loader.getController();
            playerCardController.setPlayerModel(playerModelsList.get(i));
            // Assigning to the list
            playerCardList.add(playerCard);
            // Appending to the Flow pane
            playerListPanel.getChildren().add(playerCard);
        }
        currentPlayerLabel.setText(gameModel.getPlayers().get(gameModel.getCurrentPlayerIndex()).getColor().toString());
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
