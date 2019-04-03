package main.helpers;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import main.models.CardModel;
import main.models.CountryModel;
import main.models.GameModel;
import main.models.PlayerModel;
import main.utills.GameConstants;

import java.util.*;

/**
 * This class contains all of the methods that we need in attack phase
 */
public class AttackPhase {

    public PlayerModel attackingPlayer, defendingPlayer;
    public CountryModel attackingCountry, defendingCountry;
    public GameModel gameModel;


    /**
     * This method is constructor for attack phase
     * @param gameModel object of game model
     * @param attackingCountry which is an object of country model
     * @param defendingCountry which is an object of country model
     */
    public AttackPhase(GameModel gameModel, CountryModel attackingCountry, CountryModel defendingCountry) {
        this.gameModel = gameModel;
        this.attackingCountry = attackingCountry;
        this.defendingCountry = defendingCountry;
        this.attackingPlayer = gameModel.getPlayers().get(gameModel.getCurrentPlayerIndex());
        this.defendingPlayer = getDefender(defendingCountry);
    }

    /**
     * This method do all the processes it should be done in attacking phase like rolling dice check result
     */
    public void attackCountry(int diceCount){
        ArrayList<Integer> attackerDiceValues;
        ArrayList<Integer> defenderDiceValues;
        if(diceCount > 1) {
            attackerDiceValues = getAllDiceValues(diceCount);
            defenderDiceValues = getAllDiceValues(diceCount - 1);
        } else {
            attackerDiceValues = getAllDiceValues(1);
            defenderDiceValues = getAllDiceValues(1);
        }

        for(int i=0; i< defenderDiceValues.size(); i++) {
            if (Collections.max(attackerDiceValues) < Collections.max(defenderDiceValues)) {
                attackingCountry.setArmyInCountry(attackingCountry.getArmyInCountry() - 1);
            } else {
                defendingCountry.setArmyInCountry(defendingCountry.getArmyInCountry() - 1);
            }
            attackerDiceValues.remove(attackerDiceValues.indexOf(Collections.max(attackerDiceValues)));
            defenderDiceValues.remove(defenderDiceValues.indexOf(Collections.max(defenderDiceValues)));
        }

        System.out.println(attackingCountry.getArmyInCountry());
        System.out.println(defendingCountry.getArmyInCountry());
        // if attacker looses
        if (attackingCountry.getArmyInCountry() <= 1) {
            this.assignCardToPlayer(this.defendingPlayer);
            this.assignCountryToWinnerPlayer(this.defendingPlayer, this.attackingPlayer, this.attackingCountry);
            this.assignRemainingCardsToWinnerPlayer(this.defendingPlayer, this.attackingPlayer);
            // Alert to the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("You have attacked the defending country");
            alert.setHeaderText(null);
            alert.setContentText("Attacker Lost");
            alert.showAndWait();
        }
        // if defender looses
        else if (defendingCountry.getArmyInCountry() <= 0) {
            this.assignCardToPlayer(this.attackingPlayer);
            this.assignCountryToWinnerPlayer(this.attackingPlayer, this.defendingPlayer, this.defendingCountry);
            this.assignRemainingCardsToWinnerPlayer(this.attackingPlayer, this.defendingPlayer);
            // Alert to the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("You Lost");
            alert.setHeaderText(null);
            alert.setContentText("Defender won and occupied the attacking country");
            alert.showAndWait();
        } else {
            // Alert to the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("You have attacked the defending country");
            alert.setHeaderText(null);
            alert.setContentText("Attacking Complete");
            alert.showAndWait();
        }
    }

    /**
     * this method getting the number of attacker dice and set and return the number of dices according to the
     * player army in that specific country
     * @return the number of dices according to the attacker army in that specific country
     */
    public int getNumberOfDiceCount() {
        if (attackingCountry.getArmyInCountry() >= 3) {
            return 3;
        } else {
            return 2;
        }
    }

    /**
     * rolling the dice by getting the number of dices
     * @param numberOfDice number of dices attacker or defender wants to roll
     * @return an array list of dice face numbers which is sorted
     */
    public ArrayList<Integer> getAllDiceValues(int numberOfDice) {
        ArrayList<Integer> diceValues = new ArrayList<>();
        for (int i = 0; i < numberOfDice; i++) {
            Random rand = new Random();
            int diceCount = rand.nextInt(6) + 1;
            diceValues.add(diceCount);
        }
        // Sorts in descending order
        Collections.sort(diceValues, Collections.reverseOrder());
        return diceValues;
    }

    /**
     * assign the cards to the player (deck) by getting the player as the parameter
     * @param player which is an object of player model
     */
    public void assignCardToPlayer(PlayerModel player) {
        System.out.println(this.gameModel.getCards());
        int index = new Random().nextInt(this.gameModel.getCards().size());
        ArrayList<CardModel> deck = player.getDeck();
        deck.add(gameModel.getCards().get(index));
        player.setDeck(deck);
    }

    /**
     * finding the defender player by getting the country name
     * @param defendingCountry object of the defender country
     * @return the player who is defending
     */
    private PlayerModel getDefender(CountryModel defendingCountry) {
        for (PlayerModel player : this.gameModel.getPlayers()) {
            if (player.getColor() != this.attackingPlayer.getColor()) {
                List<CountryModel> countries = player.getCountries();
                if (countries.contains(defendingCountry)) {
                    return player;
                }
            }
        }
        return null;
    }

    /**
     * adding or removing the country to or from the countrylist after battle according to the result
     * @param winnerPlayer object of the player who won
     * @param loserPlayer object of the player who lost
     * @param lostCountry object of the country which the loser lost it
     */
    private void assignCountryToWinnerPlayer(PlayerModel winnerPlayer, PlayerModel loserPlayer, CountryModel lostCountry) {
        // loser
        List<CountryModel> loserCountries = loserPlayer.getCountries();
        loserCountries.remove(lostCountry);
        // Winner
        List<CountryModel> winnerCountries = winnerPlayer.getCountries();
        winnerCountries.add(lostCountry);
    }

    /**
     * checking if the loser do not have any other countries and own any card then
     * it should be given to the winner player
     * @param winnerPlayer object of the player who won
     * @param loserPlayer object of the player who lost
     */
    public void assignRemainingCardsToWinnerPlayer(PlayerModel winnerPlayer, PlayerModel loserPlayer) {
        if (loserPlayer.getCountries() != null || loserPlayer.getCountries().size() == 0) {
            if (loserPlayer.getDeck() != null || loserPlayer.getDeck().size() > 0) {
                ArrayList<CardModel> winnerDeck = winnerPlayer.getDeck();
                winnerDeck.addAll(loserPlayer.getDeck());
                loserPlayer.setActive(false);
            }
        }
    }
}
