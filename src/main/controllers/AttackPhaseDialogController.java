package main.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import main.helpers.AttackPhase;
import main.models.CountryModel;
import main.models.GameModel;
import main.models.PlayerModel;
import main.utills.GameCommon;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 *This class is a controller for attack phase dialog
 */
public class AttackPhaseDialogController implements Observer {
    public TextField attackerDiceCountTextField,defenderDiceCountTextField,armyCount;
    private GameModel gameModel;
    private CountryModel attackingCountry = null;
    private CountryModel defendingCountry = null;
    private PlayerModel currentPlayerModel=null;
    private String defendingCountryName=null;



    @FXML
    private ListView<CountryModel> attackingCountryListView, defendingCountryListView;

    /**
     * this method do all action that should be done in attack phase
     */
    public void attackAction(ActionEvent event) {
        if(attackingCountry != null && defendingCountry != null) {
            AttackPhase attackPhase = new AttackPhase(gameModel, attackingCountry, defendingCountry);
            int currentPlayerCountrySize=getCurrentPlayerModel().getCountries().size();
            if(attackingCountry.getArmyInCountry() >= 2) {
                try {

                    int attackerDiceCount = Integer.parseInt(attackerDiceCountTextField.getText());
                    int defenderDiceCount = Integer.parseInt(defenderDiceCountTextField.getText());
                    if(attackerDiceCount !=0 && defenderDiceCount!=0 && attackerDiceCount <=attackingCountry.getArmyInCountry() &&
                            defenderDiceCount <= attackingCountry.getArmyInCountry() && attackerDiceCount<=3 && defenderDiceCount<=2 )
                    {
                        attackPhase.attackCountry(attackerDiceCount,defenderDiceCount);
                        if(currentPlayerCountrySize != getCurrentPlayerModel().getCountries().size()){
                           System.out.println("Inside disable condition");
                            armyCount.setDisable(false);
                        }
                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Input Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Enter proper dice values");
                        alert.showAndWait();
                    }
                   /// ((Node)(event.getSource())).getScene().getWindow().hide();
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Input Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Something Went Wrong, Please enter dice count value.");
                    alert.showAndWait();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Please Select Attacking and Defending country");
            alert.setHeaderText(null);
            alert.setContentText("Something Went Wrong, make sure you select it.");
            alert.showAndWait();
        }
    }

    public void armyMoveAction(ActionEvent event) {
        System.out.println("defender name:"+getDefendingCountryName());
        GameCommon gameCommons = new GameCommon();
       CountryModel defender= gameCommons.getCountryModelFromList(getCurrentPlayerModel().getCountries(),getDefendingCountryName());
        if(defender!=null){
            try{
                int armyUnitCount = Integer.parseInt(armyCount.getText());
                AttackPhase attackPhase = new AttackPhase(gameModel, attackingCountry, defender);
                String message = attackPhase.swapArmyBetweenCountries(armyUnitCount);
                if (message != null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Fortification Phase");
                    alert.setHeaderText(null);
                    alert.setContentText(message);
                    alert.showAndWait();
                }
            }catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Input Error");
                alert.setHeaderText(null);
                alert.setContentText("Something Went Wrong, Please enter proper army count.");
                alert.showAndWait();
            }

        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("You are not allowed to do this action");
            alert.showAndWait();
        }


    }

    /**
     * this method do allout action in attack phase
     */

    public void allOutMode(ActionEvent event) {
        if(attackingCountry != null && defendingCountry != null) {
            AttackPhase attackPhase = new AttackPhase(gameModel, attackingCountry, defendingCountry);
            int currentPlayerCountrySize=getCurrentPlayerModel().getCountries().size();
            attackPhase.allOutMode();
            if(currentPlayerCountrySize != getCurrentPlayerModel().getCountries().size()){
                armyCount.setDisable(false);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Please Select Attacking and Defending country");
            alert.setHeaderText(null);
            alert.setContentText("Something Went Wrong, make sure you select it.");
            alert.showAndWait();
        }

    }


    /**
     * Getter method to get game model object
     * @return gameModel object
     */
    public GameModel getGameModel() {
        return gameModel;
    }

    /**
     * Setter method to set the game model according to the parameter it gets and initialize the attack phase
     * @param gameModel game model object
     */
    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
        this.initializeAttack();
    }

    /**
     * this method initialize attack phase by selecting the attacker and defender country according to the map file
     * and graph of countries
     */
    private void initializeAttack() {
        GameCommon gameCommons = new GameCommon();
        armyCount.setDisable(true);
        PlayerModel currentPlayer = gameModel.getPlayers().get(gameModel.getCurrentPlayerIndex());
        setCurrentPlayerModel(currentPlayer);
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
                    setText(item.getCountryName()+" - "+item.getArmyInCountry());
                }
            }
        });
        attackingCountryListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CountryModel>() {
            @Override
            public void changed(ObservableValue<? extends CountryModel> observable, CountryModel oldValue, CountryModel newValue) {
                //System.out.println("Selected Country: " + newValue.getCountryName());
                setAttackingCountry(newValue);

                ArrayList<CountryModel> defendingCountyModels = gameCommons.getAttackerAdjcentCountires(newValue.getAdjacentCountries(), playerCountries);
                ObservableList<CountryModel> defendingCountries = FXCollections.observableArrayList(defendingCountyModels);
                defendingCountryListView.setItems(defendingCountries);
                defendingCountryListView.setCellFactory(lv -> new ListCell<CountryModel>() {
                    @Override
                    public void updateItem(CountryModel item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(item.getCountryName()+" - "+item.getArmyInCountry());
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

    /**
     * this method is to update the view
     */
    public void update(Observable o, Object arg) {
        String phase = (String) arg;
        if(phase.equalsIgnoreCase("attack")) {
            attackingCountryListView.refresh();
            defendingCountryListView.refresh();
        }else if(phase.equalsIgnoreCase("attack finish")){
            countriesUpdate();
        }
    }

    private void countriesUpdate() {
        System.out.println("update method :1");
        GameCommon gameCommons = new GameCommon();
        ArrayList<CountryModel> attackingPlayerCountries =getCurrentPlayerModel().getCountries();
        ObservableList<CountryModel> defendingCountriesList=null;
        System.out.println("countrySize:"+attackingPlayerCountries.size());
        ObservableList<CountryModel> playerCountriesObservableList = FXCollections.observableArrayList(attackingPlayerCountries);
        attackingCountryListView.setItems(playerCountriesObservableList);
        attackingCountryListView.setCellFactory(lv -> new ListCell<CountryModel>() {
            @Override
            public void updateItem(CountryModel item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item.getCountryName()+" - "+item.getArmyInCountry());
                }
            }
        });
    if(getCurrentPlayerModel().getCountries().contains(getAttackingCountry())) {
        ArrayList<CountryModel> defendingCountyModel = gameCommons.getAttackerAdjcentCountires(getAttackingCountry().getAdjacentCountries(), attackingPlayerCountries);
        defendingCountriesList = FXCollections.observableArrayList(defendingCountyModel);
        defendingCountryListView.setItems(defendingCountriesList);
        defendingCountryListView.setCellFactory(lv -> new ListCell<CountryModel>() {
            @Override
            public void updateItem(CountryModel item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item.getCountryName() + " - " + item.getArmyInCountry());
                }
            }
        });
    }
    else{
        defendingCountryListView.setItems(defendingCountriesList);
    }


    }

    /**
     * Getter method to get the attacking country
     * @return the attacking country object
     */
    public CountryModel getAttackingCountry() {
        return attackingCountry;
    }

    /**
     * Setter method to set the attacker country object
     * @param attackingCountry which setted for attacker country
     */
    public void setAttackingCountry(CountryModel attackingCountry) {
        this.attackingCountry = attackingCountry;
    }

    /**
     * Getter method to get defender country object
     * @return the defender country object
     */
    public CountryModel getDefendingCountry() {
        return defendingCountry;
    }
    /**
     * Setter method to set the defender country object
     * @param defendingCountry which setted for defender country
     */
    public void setDefendingCountry(CountryModel defendingCountry) {
        this.defendingCountry = defendingCountry;
        setDefendingCountryName(defendingCountry.getCountryName());
    }

    public PlayerModel getCurrentPlayerModel() {
        return currentPlayerModel;
    }

    public void setCurrentPlayerModel(PlayerModel currentPlayerModel) {
        this.currentPlayerModel = currentPlayerModel;
    }

    public String getDefendingCountryName() {
        return defendingCountryName;
    }

    public void setDefendingCountryName(String defendingCountryName) {
        this.defendingCountryName = defendingCountryName;
    }

}
