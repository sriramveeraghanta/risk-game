package main.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.models.ContinentModel;
import main.models.CountryModel;


public class ContinentInfoController {
    @FXML
    public Label continentNameLabel;
    @FXML
    public TableView<CountryModel> countriesTableView;
    @FXML
    public TableColumn<CountryModel, String> countryNameTableColumn;
    @FXML
    public TableColumn<CountryModel, Integer> countryUnitsTableColumn;

    private ContinentModel continentModel;

    void setContinentModel(ContinentModel continent) {
        this.continentModel = continent;
        continentNameLabel.setText(this.continentModel.getContinentName());
        ObservableList<CountryModel> playerCountriesObservable = FXCollections.observableArrayList(this.continentModel.getCountries());
        countryNameTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("countryName"));
        countryUnitsTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("armyInCountry"));
        countriesTableView.setItems(playerCountriesObservable);
    }
}
