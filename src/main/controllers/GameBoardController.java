package main.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import main.models.GameModel;
import main.models.PlayerModel;

import java.util.ArrayList;

public class GameBoardController {

    public GameModel gameModel;

    @FXML
    public ListView<String> playersListView;

    public GameBoardController() {
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
        this.initData();
    }

    private void initData() {
        System.out.println(getGameModel());
        ArrayList<PlayerModel> playerModelsList = getGameModel().getPlayers();
        ArrayList<String> playersListStrings = new ArrayList<>();

        for (int i = 0; i < playerModelsList.size(); i++) {
            System.out.println(playerModelsList.get(i));
            playersListStrings.add("Player_"+playerModelsList.get(i).getColor().toString());
        }

        ObservableList<String> playersList = FXCollections.observableList(playersListStrings);
        playersListView.setOrientation(Orientation.HORIZONTAL);
        playersListView.getItems().setAll(playersList);
        playersListView.setMouseTransparent(true);
        playersListView.setFocusTraversable(false);

    }


}
