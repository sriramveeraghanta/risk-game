package main.models;

import java.util.ArrayList;

public class GameModel {
    private String title;
    private int numberOfPlayers;
    private ArrayList<PlayerModel> players = new ArrayList<PlayerModel>();
    private ArrayList<ContinentModel> continents = new ArrayList<ContinentModel>();
    private ArrayList<CountryModel> countries = new ArrayList<CountryModel>();
    private ArrayList<CardModel> cards = new ArrayList<CardModel>();
}
