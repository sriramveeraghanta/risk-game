package main.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import main.helpers.AttackPhase;
import main.models.CountryModel;
import main.models.GameModel;
import main.models.PlayerModel;
import main.utills.GameCommons;

import java.util.ArrayList;

public class AttackPhaseDialogController {
    private GameModel gameModel;
    private CountryModel attackingCountry = null;
    private CountryModel defendingCountry = null;

    @FXML
    private ListView<CountryModel> attackingCountryListView, defendingCountryListView;


    public void attackAction() {
        System.out.println(attackingCountry.getCountryName());
        System.out.println(defendingCountry.getCountryName());
        if(attackingCountry != null && defendingCountry != null) {
            AttackPhase attackPhase = new AttackPhase(gameModel, attackingCountry, defendingCountry);
            System.out.println(attackingCountry.getNumberOfUnits());
            if(attackingCountry.getNumberOfUnits() > 2) {

                String message = attackPhase.attackCountry();
                System.out.println(message);
                if(message != null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(message);
                    alert.setHeaderText(null);
                    alert.setContentText("Attacker won and occupied the defender country");
                    alert.showAndWait();
                }
            }
        } else {
            System.out.println("IN else");
        }
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
        this.initializeAttack();
    }

    private void initializeAttack() {
        GameCommons gameCommons = new GameCommons();

        PlayerModel currentPlayer = gameModel.getPlayers().get(gameModel.getCurrentPlayerIndex());
        ArrayList<CountryModel> playerCountries = currentPlayer.getCountries();
        ObservableList<CountryModel> playerCountriesObservable = FXCollections.observableArrayList(playerCountries);
        attackingCountryListView.setItems(playerCountriesObservable);
        attackingCountryListView.setCellFactory(lv -> new ListCell<CountryModel>() {
            @Override
            public void updateItem(CountryModel item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item.getCountryName());
                }
            }
        });
        attackingCountryListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CountryModel>() {
            @Override
            public void changed(ObservableValue<? extends CountryModel> observable, CountryModel oldValue, CountryModel newValue) {
                //System.out.println("Selected Country: " + newValue.getCountryName());
                setAttackingCountry(newValue);

                ArrayList<CountryModel> defendingCountyModels = gameCommons.getAttackerAdjcentCountires(newValue.getAdjcentCountries(), playerCountries);
                ObservableList<CountryModel> defendingCountries = FXCollections.observableArrayList(defendingCountyModels);
                defendingCountryListView.setItems(defendingCountries);
                defendingCountryListView.setCellFactory(lv -> new ListCell<CountryModel>() {
                    @Override
                    public void updateItem(CountryModel item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(item.getCountryName());
                        }
                    }
                });
                defendingCountryListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CountryModel>(){
                    @Override
                    public void changed(ObservableValue<? extends CountryModel> observable, CountryModel oldValue, CountryModel newValue) {
                        setDefendingCountry(newValue);
                    }
                });
            }
        });
    }

    public CountryModel getAttackingCountry() {
        return attackingCountry;
    }

    public void setAttackingCountry(CountryModel attackingCountry) {
        this.attackingCountry = attackingCountry;
    }

    public CountryModel getDefendingCountry() {
        return defendingCountry;
    }

    public void setDefendingCountry(CountryModel defendingCountry) {
        this.defendingCountry = defendingCountry;
    }
}
