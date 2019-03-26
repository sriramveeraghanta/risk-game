package main.helpers;

import main.models.*;
import main.utills.EnumHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class StartupPhase {

    private int numberOfPlayers;

    private GameModel gameModel;
    private EnumHandler.Color[] colors = EnumHandler.Color.values();
    private ReinforcementPhase reinforcementPhase;

    /**
     * Initialise the game objects set players properties such countries,armies,
     * Colour
     *
     * @param gameModel
     */
    public StartupPhase(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    /**
     * Initialize the game objects set players properties such
     * countries,armies,Color
     *
     */
    public void initNewGame(int playerCount) {
        this.numberOfPlayers = playerCount;
        // setting number of players count to game model
        gameModel.setNumberOfPlayers(playerCount);
        gameModel.setPlayers(getNewPlayers());
        this.assignCountriesToPlayers();
        this.assignUnitsPerCountry();
        this.createGameCards();
    }


    /**
     * Creating new players for the game
     * */
    public ArrayList<PlayerModel> getNewPlayers() {
        ArrayList<PlayerModel> playersList = new ArrayList<>();
        // Creating new player objects for the count
        for (int i = 0; i < numberOfPlayers; i++) {
            EnumHandler.Color assignedColor = this.getAssignedColor();
            PlayerModel player = new PlayerModel();
            player.setColor(assignedColor);
            player.setActive(false);
            player.setArmyInHand(getInitialUnit());
            playersList.add(player);
        }
        return playersList;
    }

    /**
     * assigns a color to the player randomly at the starting phase of the game *
     *
     * @return the color which is assign to specific player
     */
    public EnumHandler.Color getAssignedColor() {
        EnumHandler.Color assignedColor = null;
        int currentIndex;
        for (int i = 0; i < 6; i++) {
            currentIndex = new Random().nextInt(6);
            if (this.colors[currentIndex] != null) {
                assignedColor = this.colors[currentIndex];
                this.colors[currentIndex] = null;
                return assignedColor;
            }
        }
        return null;
    }

    /**
     * assign initial number of armies to the players at the initial phase *
     *
     * @return the unit model which is contains both number and type of armies
     */
    public UnitModel getInitialUnit() {
        UnitModel unit = new UnitModel();
        unit.setType(EnumHandler.UnitType.INFANTRY);
        switch (numberOfPlayers) {
            case 2:
                unit.setUnitCount(40);
                return unit;
            case 3:
                unit.setUnitCount(35);
                return unit;
            case 4:
                unit.setUnitCount(30);
                return unit;
            case 5:
                unit.setUnitCount(25);
                return unit;
            case 6:
                unit.setUnitCount(20);
                return unit;
            default:
                return null;
        }
    }

    /**
     * assigns countries to players randomly at the starting phase of the game
     */
    public void assignCountriesToPlayers() {
        int currentIndex;
        // Creating a shuffled the new list;
        ArrayList<CountryModel> shuffledCountries = gameModel.getCountries();
        Collections.shuffle(shuffledCountries);
        int playerIndex = 0;
        while (shuffledCountries.size() > 0) {
            currentIndex = new Random().nextInt(shuffledCountries.size());
            playerIndex = playerIndex % (numberOfPlayers);
            gameModel.getPlayers().get(playerIndex).addCountry(shuffledCountries.get(currentIndex));
            shuffledCountries.remove(currentIndex);
            playerIndex++;
        }
    }

    /**
     * assigning armies to the country each player own in the way each country has
     * at least one army unit in it
     */
    public void assignUnitsPerCountry() {
        ArrayList<PlayerModel> playersList = gameModel.getPlayers();
        for (PlayerModel player : playersList) {
            for (CountryModel country : player.getCountries()) {
                country.addUnitsToCountry(1);
                //reinforcementPhase.assignArmyUnitToCountry(country, 1);
            }
        }
    }

    /**
     * Randomly generate the cards and assign a different unit type to each
     *
     * This should be rewritten in phase 2 only 3 unique Type cards are needed.
     *
     */
    public void createGameCards() {
        EnumHandler.UnitType unitTypes[] = EnumHandler.UnitType.values();

       ArrayList<CardModel> cards = new ArrayList<>();
        for (int i = 0; i < gameModel.getCountries().size(); i++) {
            CardModel card = new CardModel(unitTypes[i % 3]);
            cards.add(card);
        }
        this.gameModel.setCards(cards);
    }
}
