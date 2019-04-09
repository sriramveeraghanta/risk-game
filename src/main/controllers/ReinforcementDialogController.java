package main.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.helpers.ReinforcementPhase;
import main.models.CountryModel;
import main.models.GameModel;
import main.models.PlayerModel;
import main.utills.GameConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * This class is the reinforcement phase controller
 */
public class ReinforcementDialogController implements Observer {

    @FXML
    public Label playerCardsCountLabel, playerUnitsInHandLabel;
    @FXML
    public ListView playerCountriesListView;

    private GameModel gameModel;
    private CountryModel selectedCountry = null;
    private PlayerModel currentPlayerModel;
    private ReinforcementPhase reinforcePhase;

    @FXML
    public AnchorPane ReinforcementPanel;
    @FXML
    private TextField ArmyCountToPlace;

    /**
     * Getter method to get the game model object
     *
     * @return object of game model
     */
    public GameModel getGameModel() {
        return gameModel;
    }

    /**
     * Setter method to setting the game model
     *
     * @param gameModel object of game model
     */
    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
        this.initializeReinforce();
    }

    /**
     * Initializing the Reinforment phase required data
     */
    private void initializeReinforce() {
        this.currentPlayerModel = gameModel.getPlayers().get(gameModel.getCurrentPlayerIndex());

        reinforcePhase = new ReinforcementPhase(this.currentPlayerModel, gameModel);

        // Setting army to playerModel
        this.currentPlayerModel.setArmyInHand(this.currentPlayerModel.getArmyInHand() + reinforcePhase.getArmyUnitsForConqueredContinent());
        // Display
        playerUnitsInHandLabel.setText("" + this.currentPlayerModel.getArmyInHand());
        playerCardsCountLabel.setText("" + this.currentPlayerModel.getDeck().size());
        // Rendering Player Countries
        ObservableList<CountryModel> playerCountriesObservable = FXCollections.observableArrayList(currentPlayerModel.getCountries());
        playerCountriesListView.setItems(playerCountriesObservable);
        playerCountriesListView.setCellFactory(lv -> new ListCell<CountryModel>() {
            @Override
            public void updateItem(CountryModel item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item.getCountryName() + "-" + item.getArmyInCountry());
                }
            }
        });
        playerCountriesListView.getSelectionModel().selectedItemProperty().addListener((ChangeListener<CountryModel>) (observable, oldValue, newValue) -> {
            setSelectedCountry(newValue);
        });
    }

    /**
     * Action when we are trying to add army to the country
     */
    @FXML
    public void addArmyAction() {
        if (currentPlayerModel.getDeck().size() < 5) {
            if (getSelectedCountry() != null && !ArmyCountToPlace.getText().equals("")) {
                int armyCount = 0;
                try {
                    armyCount = Integer.parseUnsignedInt(ArmyCountToPlace.getText());
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(GameConstants.FORTIFY_INVALID_MSG);
                    alert.showAndWait();
                }
                if (armyCount <= currentPlayerModel.getArmyInHand()) {
                    reinforcePhase.assignArmyUnitToCountry(selectedCountry, armyCount);
                    playerUnitsInHandLabel.setText("" + currentPlayerModel.getArmyInHand());
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Invalid Input data, please enter army count less than army count in your hand. Player units in hand:" + currentPlayerModel.getArmyInHand());
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(GameConstants.FORTIFY_INVALID_MSG);
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Please Swap Card before reinforcing");
            alert.showAndWait();
        }
    }

    /**
     * SwapCards Action Listener.
     */
    @FXML
    public void swapCardAction() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/CardExchangeView.fxml"));
            Parent cardViewPanel = loader.load();
            CardSwapDialogController cardSwapDialogController = loader.getController();
            cardSwapDialogController.setGameModel(this.gameModel);
            gameModel.addObserver(cardSwapDialogController);
            stage.setScene(new Scene(cardViewPanel, 600, 400));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter method for getting the current Selected Country;
     *
     * @return Country Model
     */
    public CountryModel getSelectedCountry() {
        return selectedCountry;
    }

    /**
     * Setter method for getting the current Selected Country;
     *
     * @param selectedCountry of Country Model which is selected by the user.
     */
    public void setSelectedCountry(CountryModel selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    /**
     * this method is to update the view
     */
    public void update(Observable o, Object arg) {
        String phase = (String) arg;
        if (phase.equalsIgnoreCase("reinforce")) {
            playerCountriesListView.refresh();
            playerUnitsInHandLabel.setText("" + this.currentPlayerModel.getArmyInHand());
        } else if (phase.equalsIgnoreCase("cardSwap")) {
            playerCardsCountLabel.setText("" + this.currentPlayerModel.getDeck().size());
        }
    }
}
