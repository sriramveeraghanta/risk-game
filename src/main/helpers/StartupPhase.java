package main.helpers;

import main.models.*;
import main.utills.EnumHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This class contains the methods and variables we need in start up phase
 */
public class StartupPhase {

    private int numberOfPlayers;
    private GameModel gameModel;
    private EnumHandler.Color[] colors = EnumHandler.Color.values();
    private EnumHandler.PlayerType[] genericPlayerTypes = EnumHandler.PlayerType.values();

    /**
     * Initialise the game objects set players properties such countries,armies,
     * Colour
     *
     * @param gameModel is an object of game model
     */
    public StartupPhase(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    /**
     * Initialize the game objects set players properties such
     * countries,armies,Color
     *
     * @param playerTypes the number of player with their behaviour Types
     */
    public void initNewGame(ArrayList<String> playerTypes) {
        this.numberOfPlayers = playerTypes.size();
        // setting number of players count to game model
        this.gameModel.setNumberOfPlayers(playerTypes.size());
        this.gameModel.setPlayers(getNewPlayers(playerTypes));
        this.assignCountriesToPlayers();
        this.assignUnitsToPlayerCountries();
        this.createGameCards();
    }

    /**
     * getting the new players and returning the array list of player model
     * @return array list of player model
     * @param playerTypes
     */
    public ArrayList<PlayerModel> getNewPlayers(ArrayList<String> playerTypes) {
        ArrayList<PlayerModel> playersList = new ArrayList<>();
        // Creating new player objects for the count
        for (String playerType: playerTypes) {
            PlayerModel player = new PlayerModel();
            player.setColor(this.getAssignedColor());
            player.setActive(false);
            player.setArmyInHand(getInitialUnit());
            if(EnumHandler.PlayerType.HUMAN.toString().equals(playerType)){
                player.setPlayerType(EnumHandler.PlayerType.HUMAN);
            }
            if(EnumHandler.PlayerType.AGGRESSIVE.toString().equals(playerType)){
                player.setPlayerType(EnumHandler.PlayerType.AGGRESSIVE);
            }
            if(EnumHandler.PlayerType.BENEVOLENT.toString().equals(playerType)){
                player.setPlayerType(EnumHandler.PlayerType.BENEVOLENT);
            }
            if(EnumHandler.PlayerType.CHEATER.toString().equals(playerType)){
                player.setPlayerType(EnumHandler.PlayerType.CHEATER);
            }
            if(EnumHandler.PlayerType.RANDOM.toString().equals(playerType)){
                player.setPlayerType(EnumHandler.PlayerType.RANDOM);
            }

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
     * assign initial number of armies to the players at the initial phase
     *
     * @return the unit model which is contains both number and type of armies
     */
    public int getInitialUnit() {
        switch (numberOfPlayers) {
            case 2:
                return 40;
            case 3:
                return 35;
            case 4:
                return 30;
            case 5:
                return 25;
            case 6:
                return 20;
            default:
                return 0;
        }
    }

    /**
     * assigns countries to players randomly at the starting phase of the game
     */
    public void assignCountriesToPlayers() {
        int currentIndex;
        // Creating a shuffled the new list;
        ArrayList<CountryModel> shuffledCountries = (ArrayList) gameModel.getCountries().clone();
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
    public void assignUnitsToPlayerCountries() {
        ArrayList<PlayerModel> playersList = gameModel.getPlayers();
        for (PlayerModel player : playersList) {
            while (player.getArmyInHand() > 0) {
                for (CountryModel country : player.getCountries()) {
                    if (player.getArmyInHand() > 0) {
                        country.setArmyInCountry(country.getArmyInCountry() + 1);
                        player.setArmyInHand(player.getArmyInHand() - 1);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    /**
     * Randomly generate the cards and assign a different unit type to each
     * This should be rewritten in phase 2 only 3 unique Type cards are needed.
     */
    public void createGameCards() {
        EnumHandler.CardType cardTypes[] = EnumHandler.CardType.values();
        ArrayList<CardModel> cards = new ArrayList<>();
        for (int i = 0; i < gameModel.getCountries().size(); i++) {
            CardModel card = new CardModel(cardTypes[i % cardTypes.length]);
            cards.add(card);
        }
        gameModel.setCards(cards);
    }
}
