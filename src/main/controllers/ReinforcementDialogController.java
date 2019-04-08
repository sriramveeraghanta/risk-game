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


    public Label playerUnitsInHand;
    public Label cards;
    private GameModel gameModel;
    private CountryModel selectedCountry = null;
    private PlayerModel playerModel=null;

    private ReinforcementPhase reinforcePhase=null;



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
    public AnchorPane ReinforcementPanel;

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
        cards.setText(""+getPlayerModel().getDeck().size());
        reinforcePhase = new ReinforcementPhase(currentPlayer, gameModel);
        getPlayerModel().setArmyInHand(getPlayerModel().getArmyInHand()+reinforcePhase.getArmyUnitsForConqueredContinent());
        playerUnitsInHand.setText(""+getPlayerModel().getArmyInHand());
        setReinforcePhase(reinforcePhase);
        reinforcePhase.validateNewContinentOccupation();
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

    /**
     * Action when we are trying to add army to the country
     *
     * */
    @FXML
    public void addArmyAction() {
        if(getPlayerModel().getDeck().size() < 5) {
            if (getSelectedCountry() != null && !ArmyCountToPlace.getText().equals("")) {
                System.out.println("country name:" + getSelectedCountry().getCountryName());
                System.out.println("Player:" + getPlayerModel().getArmyInHand());
                int armyCount = 0;
                try {
                    armyCount = Integer.parseUnsignedInt(ArmyCountToPlace.getText());
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(GameConstants.FORTIFY_INVALID_MSG);
                    alert.showAndWait();
                }

                if (armyCount <= playerModel.getArmyInHand()) {

                    getReinforcePhase().assignArmyUnitToCountry(selectedCountry, armyCount);
                    playerUnitsInHand.setText("" + getPlayerModel().getArmyInHand());
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Invalid Input data, please enter army count less than army count in your hand. Player units in hand:" + getPlayerModel().getArmyInHand());
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(GameConstants.FORTIFY_INVALID_MSG);
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Please Swap Card before reinforcing");
            alert.showAndWait();
        }
    }


    /**
     * SwapCards Action Listener.
     *
     * */
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
     * @return Country Model
     * */
    public CountryModel getSelectedCountry() {
        return selectedCountry;
    }

    /**
     * Setter method for getting the current Selected Country;
     * @param selectedCountry of Country Model which is selected by the user.
     * */
    public void setSelectedCountry(CountryModel selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    /**
     * Getter method for player model
     * @return player model
     * */
    public PlayerModel getPlayerModel() {
        return playerModel;
    }

    /**
     * Setter method for getting the current Selected Country;
     * @param playerModel Player model
     * */
    public void setPlayerModel(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }



    public ReinforcementPhase getReinforcePhase() {
        return reinforcePhase;
    }

    public void setReinforcePhase(ReinforcementPhase reinforcePhase) {
        this.reinforcePhase = reinforcePhase;
    }

    /**
     * this method is to update the view
     */
    public void update(Observable o, Object arg) {
        String phase = (String) arg;
        if(phase.equalsIgnoreCase("reinforce")) {
            PlayerCountriesList.refresh();
            playerUnitsInHand.setText(""+getPlayerModel().getArmyInHand());
            //initializeReinforce();
        }else     if(phase.equalsIgnoreCase("cardSwap")) {
            cards.setText(""+getPlayerModel().getDeck().size());
        }
      }
}
