package main.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import main.models.CountryModel;
import main.models.PlayerModel;
import main.utills.GameCommons;

/**
 * This class is the controller of player cards
 */
public class PlayerCardController {

    public PlayerModel playerModel;

    @FXML
    public Label playerLabel;
    @FXML
    public TableView playerTableView;
    @FXML
    public TableColumn countryNameTableColumn, unitsCountTableColumn;

    /**
     * Setter method to set the player model
     * @param playerModel object of player model
     */
    public void setPlayerModel(PlayerModel playerModel) {
        this.playerModel = playerModel;

        GameCommons gameCommons = new GameCommons();
        playerLabel.setText("Player: "+playerModel.getColor().toString());
        playerLabel.setBackground(new Background(new BackgroundFill(gameCommons.getFXColor(this.playerModel.getColor().toString()), CornerRadii.EMPTY, Insets.EMPTY)));

        ObservableList<CountryModel> playerCountriesObservable = FXCollections.observableArrayList(this.playerModel.getCountries());

        countryNameTableColumn.setCellValueFactory(
                new PropertyValueFactory<CountryModel, String>("countryName"));

        unitsCountTableColumn.setCellValueFactory(
                new PropertyValueFactory<CountryModel, Integer>("numberOfUnits"));

        playerTableView.setItems(playerCountriesObservable);

    }
}
