package main.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import main.helpers.AttackPhase;
import main.helpers.FortificationPhase;
import main.helpers.ReinforcementPhase;
import main.models.CountryModel;
import main.models.GameModel;
import main.models.PlayerModel;
import main.utills.GameCommons;
import main.utills.GameConstants;

import java.util.ArrayList;

/**
 * This class is the reinforcement phase controller
 */
public class ReinforcementDialogController {

    private GameModel gameModel;
    private CountryModel selectedCountry = null;
    private PlayerModel playerModel=null;



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

    @FXML
    private ListView<CountryModel> PlayerCountriesList;

    @FXML
    private TextField ArmyCountToPlace;

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
        this.initializeReinforce();
    }

    @FXML
    public void addArmyAction() {
        if(selectedCountry != null) {
            System.out.println("country name:"+ selectedCountry.getCountryName());
            System.out.println("Player:"+ playerModel.getArmyInHand());
            int armyCount = 0;
            try {
                armyCount = Integer.parseUnsignedInt(ArmyCountToPlace.getText().toString());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(GameConstants.FORTIFY_INVALID_MSG);
                alert.showAndWait();
            }

            if(armyCount < playerModel.getArmyInHand() ) {
                ReinforcementPhase reinforcePhase = new ReinforcementPhase(playerModel, gameModel);
                reinforcePhase.assignArmyUnitToCountry(selectedCountry,armyCount);
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(GameConstants.FORTIFY_INVALID_MSG);
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(GameConstants.FORTIFY_INVALID_MSG);
            alert.showAndWait();
        }
    }

    private void initializeReinforce() {
        GameCommons gameCommons = new GameCommons();

        PlayerModel currentPlayer = gameModel.getPlayers().get(gameModel.getCurrentPlayerIndex());
        setPlayerModel(currentPlayer);
        System.out.println(currentPlayer.getArmyInHand());
        ArrayList<CountryModel> playerCountries = currentPlayer.getCountries();
        ObservableList<CountryModel> playerCountriesObservable = FXCollections.observableArrayList(playerCountries);
        PlayerCountriesList.setItems(playerCountriesObservable);
        PlayerCountriesList.setCellFactory(lv -> new ListCell<CountryModel>() {
            @Override
            public void updateItem(CountryModel item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item.getCountryName()+"-"+item.getArmyInCountry());
                }
            }
        });
        PlayerCountriesList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CountryModel>() {
            @Override
            public void changed(ObservableValue<? extends CountryModel> observable, CountryModel oldValue, CountryModel newValue) {
                setSelectedCountry(newValue);
            }
        });
    }

    public CountryModel getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(CountryModel selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public PlayerModel getPlayerModel() {
        return playerModel;
    }

    public void setPlayerModel(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }
}
