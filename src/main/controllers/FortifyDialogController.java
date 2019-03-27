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
import main.models.CountryModel;
import main.models.GameModel;
import main.models.PlayerModel;
import main.utills.GameCommons;
import main.utills.GameConstants;

import java.util.ArrayList;

/**
 * This class is a controller class for fortify dialog
 */
public class FortifyDialogController {


    private GameModel gameModel;
    private CountryModel playerFromCountry = null;
    private CountryModel playerOwnedAdjacentToCountry = null;
    private PlayerModel currentPlayer=null;



    @FXML
    private ListView<CountryModel> playerOwnedAdjacentCountryList,playerCountryList;

    @FXML
    private TextField armyCountToFortify;


    @FXML
    public  void fortifyAction() {
        try {
            if (playerFromCountry != null && playerOwnedAdjacentToCountry != null) {
                int armyCount = 0;
                FortificationPhase fortifyPhase = new FortificationPhase(currentPlayer);
                try {
                    armyCount = Integer.parseUnsignedInt(armyCountToFortify.getText().toString());
                } catch (NumberFormatException e) {
                    invalidMsg();
                }
                String message = fortifyPhase.swapArmyUnitsBetweenCountries(playerFromCountry, playerOwnedAdjacentToCountry, armyCount);
                if (message != null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Fortification Phase");
                    alert.setHeaderText(null);
                    alert.setContentText(message);
                    alert.showAndWait();
                }

            } else {
                invalidMsg();
            }
        }
        catch(Exception e){
            invalidMsg();
        }
    }

    public void invalidMsg() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(GameConstants.FORTIFY_INVALID_MSG);
        alert.showAndWait();
    }
    public GameModel getGameModel() {
        return gameModel;
    }

    /**
     * Setter method to set the game model object
     * @param gameModel type of game model
     */
    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
        this.initializeFortify();
    }

    private void initializeFortify() {

        GameCommons gameCommons = new GameCommons();

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
                    setText(item.getCountryName()+"-"+item.getArmyInCountry());
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
                            setText(item.getCountryName()+"-"+item.getArmyInCountry());
                        }
                    }
                });
                playerOwnedAdjacentCountryList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CountryModel>(){
                    @Override
                    public void changed(ObservableValue<? extends CountryModel> observable, CountryModel oldValue, CountryModel newValue) {
                        setPlayerOwnedAdjacentToCountry(newValue);
                    }
                });
            }
        });
    }


    public CountryModel getPlayerFromCountry() {
        return playerFromCountry;
    }

    public void setPlayerFromCountry(CountryModel playerFromCountry) {
        this.playerFromCountry = playerFromCountry;
    }

    public CountryModel getPlayerOwnedAdjacentToCountry() {
        return playerOwnedAdjacentToCountry;
    }

    public void setPlayerOwnedAdjacentToCountry(CountryModel playerOwnedAdjacentToCountry) {
        this.playerOwnedAdjacentToCountry = playerOwnedAdjacentToCountry;
    }

    public PlayerModel getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(PlayerModel currentPlayer) {
        this.currentPlayer = currentPlayer;
    }


}
