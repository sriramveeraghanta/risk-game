package main.helpers;

import main.models.CardModel;
import main.models.CountryModel;
import main.models.GameModel;
import main.models.PlayerModel;

import java.util.*;

/**
 * This class contains all of the methods that we need in attack phase
 */
public class AttackPhase {

    public PlayerModel attackingPlayer, defendingPlayer;
    public CountryModel attackingCountry, defendingCountry;
    public GameModel gameModel;


    /**
     * checking the number of dices which attacker can roll based on the number of
     * armies for that country
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
     * @return the string which is a message that shws to the user if he lost or won
     */
    public String attackCountry(){
        ArrayList<Integer> attackerDiceValues = getAllDiceValues(getNumberofAttackerDice());
        ArrayList<Integer> defenderDiceValues = getAllDiceValues(getNumberofDefenderDice());

        for (int i = 0; i < defenderDiceValues.size(); i++) {
            if (attackerDiceValues.get(i) < defenderDiceValues.get(i)) {
                attackingCountry.setNumberOfUnits(attackingCountry.getNumberOfUnits() - 1);
            } else {
                defendingCountry.setNumberOfUnits(defendingCountry.getNumberOfUnits() - 1);
            }
        }
        System.out.println(attackingCountry.getNumberOfUnits());
        // if attacker looses
        if (attackingCountry.getNumberOfUnits() < 0) {
            this.assignCardToPlayer(this.defendingPlayer);
            this.assignCountryToWinnerPlayer(this.defendingPlayer, this.attackingPlayer, this.attackingCountry);
            this.assignRemainingCardsToWinnerPlayer(this.defendingPlayer, this.attackingPlayer);
            return "You Lost";
        }
        // if defender looses
        if (defendingCountry.getNumberOfUnits() < 0) {
            this.assignCardToPlayer(this.attackingPlayer);
            this.assignCountryToWinnerPlayer(this.attackingPlayer, this.defendingPlayer, this.defendingCountry);
            this.assignRemainingCardsToWinnerPlayer(this.attackingPlayer, this.defendingPlayer);
            return "You Won";
        }
        System.out.println(attackingCountry.getCountryName());
        System.out.println(defendingCountry.getCountryName());
        return  null;
    }

    /**
     * this method getting the number of attacker dice and set and return the number of dices according to the
     * player army in that specific country
     * @return the number of dices according to the attacker army in that specific country
     */
    public int getNumberofAttackerDice() {
        if (attackingCountry.getNumberOfUnits() > 3) {
            return 3;
        } else {
            return 2;
        }
    }

    /**
     * this method getting the number of defender dice and set and return the number of dices according to the
     * player army in that specific country
     * @return the number of dices according to the defender army in that specific country
     */
    public int getNumberofDefenderDice() {
        if (defendingCountry.getNumberOfUnits() >= 2) {
            return 2;
        } else {
            return 1;
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
     * @param player
     */
    public void assignCardToPlayer(PlayerModel player) {

        int index = new Random().nextInt(gameModel.getCards().size());
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
