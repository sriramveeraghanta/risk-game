package main.controllers;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import main.models.ContinentModel;
import main.models.CountryModel;
import main.utills.GameCommon;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class ContinentInfoController implements Observer {
    @FXML
    public Label continentNameLabel;
    @FXML
    public TableView<CountryModel> countriesTableView;
    @FXML
    public TableColumn<CountryModel, String> countryNameTableColumn;
    @FXML
    public TableColumn<CountryModel, String> adjacentCountriesTableColumn;

    private ContinentModel continentModel;

    void setContinentModel(ContinentModel continent) {
        this.continentModel = continent;
        continentNameLabel.setText(this.continentModel.getContinentName());
        ObservableList<CountryModel> playerCountriesObservable = FXCollections.observableArrayList(this.continentModel.getCountries());
        countryNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("countryName"));
        adjacentCountriesTableColumn.setCellValueFactory(new PropertyValueFactory<>("adjacentCountriesDisplay"));
        countriesTableView.setItems(playerCountriesObservable);
    }

    /**
     * this method is to update the view
     */
    public void update(Observable o, Object arg) {
        countriesTableView.refresh();
    }
}
