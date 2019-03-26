package main.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import main.models.ContinentModel;
import main.models.CountryModel;
import main.utills.GameCommons;

public class ContinentInfoController {
    @FXML
    public Label continentNameLabel;
    @FXML
    public TableView countriesTableView;
    @FXML
    public TableColumn countryNameTableColumn;
    @FXML
    public TableColumn countryUnitsTableColumn;

    public ContinentModel continentModel;

    public void setContinentModel(ContinentModel continent) {
        this.continentModel = continent;

        continentNameLabel.setText(this.continentModel.getContinentName());
        ObservableList<CountryModel> playerCountriesObservable = FXCollections.observableArrayList(this.continentModel.getCountries());
        countryNameTableColumn.setCellValueFactory(
                new PropertyValueFactory<CountryModel, String>("countryName"));
        countryUnitsTableColumn.setCellValueFactory(
                new PropertyValueFactory<CountryModel, Integer>("armyInCountry"));
        countriesTableView.setItems(playerCountriesObservable);

    }
}
