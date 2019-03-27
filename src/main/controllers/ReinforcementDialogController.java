package main.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import main.helpers.ReinforcementPhase;
import main.models.CountryModel;
import main.models.GameModel;
import main.models.PlayerModel;
import main.utills.GameConstants;

import java.util.ArrayList;

/**
 * This class is the reinforcement phase controller
 */
public class ReinforcementDialogController {

    public AnchorPane ReinforcementPanel;
    public Label playerUnitsInHand;
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

    /**
     * Initializing the Reinforment phase required data
     *
     * */
    private void initializeReinforce() {

        PlayerModel currentPlayer = gameModel.getPlayers().get(gameModel.getCurrentPlayerIndex());
        setPlayerModel(currentPlayer);
        playerUnitsInHand.setText(""+getPlayerModel().getArmyInHand());

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

    @FXML
    public void addArmyAction() {
        if(getSelectedCountry() != null && !ArmyCountToPlace.getText().equals("")) {
            System.out.println("country name:"+ getSelectedCountry().getCountryName());
            System.out.println("Player:"+ getPlayerModel().getArmyInHand());
            int armyCount = 0;
            try {
                armyCount = Integer.parseUnsignedInt(ArmyCountToPlace.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(GameConstants.FORTIFY_INVALID_MSG);
                alert.showAndWait();
            }

            if(armyCount <= playerModel.getArmyInHand() ) {
                ReinforcementPhase reinforcePhase = new ReinforcementPhase(playerModel, gameModel);
                reinforcePhase.assignArmyUnitToCountry(selectedCountry, armyCount);
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Invalid Input data, please enter army count less than army count in your hand. Player units in hand:"+getPlayerModel().getArmyInHand());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(GameConstants.FORTIFY_INVALID_MSG);
            alert.showAndWait();
        }
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

    public void swapCardAction(ActionEvent actionEvent) {

    }
}
