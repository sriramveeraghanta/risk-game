package main.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.text.Text;
import main.models.CountryModel;
import main.models.GameModel;
import main.models.PlayerModel;
import main.utills.GameCommon;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * This class is the controller of player cards
 */
public class PlayerInfoController implements Observer {

    public PlayerModel playerModel;
    public GameModel gameModel;

    @FXML
    public Label playerLabel;
    @FXML
    public TableView<CountryModel> playerTableView;
    @FXML
    public TableColumn<CountryModel, String> playerCountryNameTableColumn;
    @FXML
    public TableColumn<CountryModel, Integer> unitsCountTableColumn;
    @FXML
    public AnchorPane playerInfoPane;
    public Text playerCardsTextField;
    public Text playerArmyTextField;

    /**
     * Setter method to set the player model
     * @param playerModel object of player model
     */
    public void setPlayerModel(PlayerModel playerModel) {
        this.playerModel = playerModel;
        initializeComponent();
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel=gameModel;
    }

    private void initializeComponent() {
        // Setting label
        GameCommon gameCommons = new GameCommon();
        playerLabel.setText("Player: "+ this.playerModel.getColor().toString());
        playerLabel.setBackground(new Background(new BackgroundFill(gameCommons.getFXColor(this.playerModel.getColor().toString()), CornerRadii.EMPTY, Insets.EMPTY)));
        // Player info text
        int army = this.playerModel.getArmyInHand();
        playerArmyTextField.textProperty().bind(new SimpleIntegerProperty(army).asString());
        playerCardsTextField.setText(""+this.playerModel.getDeck().size());
        // Rendering List
        ObservableList<CountryModel> playerCountriesObservable = FXCollections.observableArrayList(this.playerModel.getCountries());
        playerCountryNameTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("countryName"));
        unitsCountTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("armyInCountry"));
        playerTableView.setItems(playerCountriesObservable);
    }

    @Override
    public void update(Observable o, Object arg) {

            playerTableView.refresh();

    }
}
