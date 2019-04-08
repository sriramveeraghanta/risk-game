package main.controllers;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.models.ContinentModel;
import main.models.CountryModel;
import main.utills.GameCommon;

import java.util.ArrayList;


public class ContinentInfoController {
    @FXML
    public Label continentNameLabel;
    @FXML
    public TableView<CountryModel> countriesTableView;
    @FXML
    public TableColumn<CountryModel, String> countryNameTableColumn;
    @FXML
    public TableColumn<CountryModel, String> adjacentCountriesTableColumn;

    private ContinentModel continentModel;
//    ObservableList<CountryModel> adjacentList = FXCollections.observableArrayList(param.getValue().getAdjacentCountries());
//    ListView<CountryModel> adjacentListView = new ListView<>();
//                adjacentListView.setItems(adjacentList);

    void setContinentModel(ContinentModel continent) {
        this.continentModel = continent;
        continentNameLabel.setText(this.continentModel.getContinentName());
        ObservableList<CountryModel> playerCountriesObservable = FXCollections.observableArrayList(this.continentModel.getCountries());
        countryNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("countryName"));
        adjacentCountriesTableColumn.setCellValueFactory(param -> {
            GameCommon common = new GameCommon();
            ArrayList<String> adjcentCountries = common.getCountriesList(param.getValue().getAdjacentCountries());
            ObservableValue<String> ff= new ObservableValue<String>() {
                @Override
                public void addListener(InvalidationListener listener) {

                }

                @Override
                public void removeListener(InvalidationListener listener) {

                }

                @Override
                public void addListener(ChangeListener listener) {

                }

                @Override
                public void removeListener(ChangeListener listener) {

                }

                @Override
                public String getValue() {
                    return adjcentCountries.toString();
                }
            };
            return ff;
        });
        countriesTableView.setItems(playerCountriesObservable);
    }
}
