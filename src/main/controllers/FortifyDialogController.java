package main.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import main.helpers.FortificationPhase;
import main.models.CountryModel;
import main.models.GameModel;
import main.models.PlayerModel;
import main.utills.DialogHandler;
import main.utills.GameCommon;
import main.utills.GameConstants;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * This class is a controller class for fortify dialog
 */
public class FortifyDialogController implements Observer {


    private GameModel gameModel;
    private CountryModel playerFromCountry = null;
    private CountryModel playerOwnedAdjacentToCountry = null;
    private PlayerModel currentPlayer = null;
    private boolean fortifyEligibility=false;
    private GameBoardController gameBoardController;
    @FXML
    private ListView<CountryModel> playerOwnedAdjacentCountryList, playerCountryList;

    @FXML
    private TextField armyCountToFortify;


    /**
     * Setter method to set country
     *
     * @return game type of game model
     */
    public GameModel getGameModel() {
        return gameModel;
    }

    /**
     * Setter method to set the game model object
     *
     * @param gameModel type of game model
     */
    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
        this.initializeFortify();
    }

    public void setGameController (GameBoardController gameBoardController) {
       this.gameBoardController=gameBoardController;
    }

    /**
     * Fortify action listener
     */
    @FXML
    public void fortifyAction(ActionEvent event) {
        if(fortifyEligibility){
        try {
            if (getPlayerFromCountry() != null && getPlayerOwnedAdjacentToCountry() != null) {
                int armyCount = 0;
                FortificationPhase fortifyPhase = new FortificationPhase(getGameModel(),getCurrentPlayer());
                try {
                    armyCount = Integer.parseUnsignedInt(armyCountToFortify.getText());
                } catch (NumberFormatException e) {
                    invalidMsg();
                }
                String message = fortifyPhase.swapArmyUnitsBetweenCountries(getPlayerFromCountry(), getPlayerOwnedAdjacentToCountry(), armyCount);
                if (message != null) {
                    if(message .equalsIgnoreCase(GameConstants.FORTIFY_VALID_MSG)){
                        fortifyEligibility=false;
                        gameBoardController.switchPlayerControlAction();
                        ((Node)(event.getSource())).getScene().getWindow().hide();
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Fortification Phase");
                    alert.setHeaderText(null);
                    alert.setContentText(message);
                    alert.showAndWait();
                }

            } else {
                invalidMsg();
            }
        } catch (Exception e) {
            invalidMsg();
        }
        }else{
            DialogHandler.showWarningMessage("Your fortification turn over");
        }
    }

    /**
     * Alert for invalid message.
     */
    public void invalidMsg() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(GameConstants.FORTIFY_INVALID_MSG);
        alert.showAndWait();
    }

    /**
     * Initializing the fortification phase
     */
    private void initializeFortify() {
        if(gameModel.getPlayers().get(gameModel.getCurrentPlayerIndex()).getCountries().size() == gameModel.getCountries().size()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("You Won the Match");
            alert.showAndWait();
        }else {

            GameCommon gameCommons = new GameCommon();
            fortifyEligibility = true;
            PlayerModel currentPlayer = gameModel.getPlayers().get(gameModel.getCurrentPlayerIndex());
            setCurrentPlayer(currentPlayer);
            ArrayList<CountryModel> playerCountries = currentPlayer.getCountries();
            ObservableList<CountryModel> playerCountriesObservable = FXCollections.observableArrayList(playerCountries);
            playerCountryList.setItems(playerCountriesObservable);
            playerCountryList.setCellFactory(lv -> new ListCell<CountryModel>() {
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
            playerCountryList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CountryModel>() {
                @Override
                public void changed(ObservableValue<? extends CountryModel> observable, CountryModel oldValue, CountryModel newValue) {
                    //System.out.println("Selected Country: " + newValue.getCountryName());
                    setPlayerFromCountry(newValue);

                    ArrayList<CountryModel> adjacentCountryModels = gameCommons.getPlayerOwnedAdjcentCountires(newValue.getAdjacentCountries(), playerCountries);
                    ObservableList<CountryModel> adjacentCountries = FXCollections.observableArrayList(adjacentCountryModels);
                    playerOwnedAdjacentCountryList.setItems(adjacentCountries);
                    playerOwnedAdjacentCountryList.setCellFactory(lv -> new ListCell<CountryModel>() {
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
                    playerOwnedAdjacentCountryList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CountryModel>() {
                        @Override
                        public void changed(ObservableValue<? extends CountryModel> observable, CountryModel oldValue, CountryModel newValue) {
                            setPlayerOwnedAdjacentToCountry(newValue);
                        }
                    });
                }
            });
        }
    }

    /**
     * this method is to update the view
     */
    public void update(Observable o, Object arg) {
        String phase = (String) arg;
        if(phase.equalsIgnoreCase("fortify")) {
            System.out.println("update method");
            playerCountryList.refresh();
            playerOwnedAdjacentCountryList.refresh();
        }
    }

    /**
     * Getter method to set country
     *
     * @return country type of country model
     */
    public CountryModel getPlayerFromCountry() {
        return playerFromCountry;
    }

    /**
     * Setter method to set country
     *
     * @param playerFromCountry type of game model
     */
    public void setPlayerFromCountry(CountryModel playerFromCountry) {
        this.playerFromCountry = playerFromCountry;
    }


    /**
     * Getter method for getting adjacent Owned countries
     *
     * @return Country Model
     */
    public CountryModel getPlayerOwnedAdjacentToCountry() {
        return playerOwnedAdjacentToCountry;
    }

    /**
     * Setter method to set country
     *
     * @param playerOwnedAdjacentToCountry type of country model
     */
    public void setPlayerOwnedAdjacentToCountry(CountryModel playerOwnedAdjacentToCountry) {
        this.playerOwnedAdjacentToCountry = playerOwnedAdjacentToCountry;
    }

    /**
     * Setter method to set Player
     *
     * @return gets the current player who is playing
     */
    public PlayerModel getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Setter method to set Player
     *
     * @param currentPlayer type of Player model
     */
    public void setCurrentPlayer(PlayerModel currentPlayer) {
        this.currentPlayer = currentPlayer;
    }


}
