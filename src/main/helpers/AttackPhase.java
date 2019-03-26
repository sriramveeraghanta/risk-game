package main.helpers;

import main.models.CardModel;
import main.models.CountryModel;
import main.models.GameModel;
import main.models.PlayerModel;

import java.util.*;

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
     * checking the number of dices which attacker can roll based on the number of
     * armies for that country
     */
    public String attackCountry(){
        ArrayList<Integer> attackerDiceValues = getAllDiceValues(getNumberOfAttackerDice());
        ArrayList<Integer> defenderDiceValues = getAllDiceValues(getNumberOfDefenderDice());

        for (int i = 0; i < defenderDiceValues.size(); i++) {
            if (attackerDiceValues.get(i) < defenderDiceValues.get(i)) {
                attackingCountry.setArmyInCountry(attackingCountry.getArmyInCountry() - 1);
            } else {
                defendingCountry.setArmyInCountry(defendingCountry.getArmyInCountry() - 1);
            }
        }
        System.out.println(attackingCountry.getArmyInCountry());
        // if attacker looses
        if (attackingCountry.getArmyInCountry() < 0) {
            this.assignCardToPlayer(this.defendingPlayer);
            this.assignCountryToWinnerPlayer(this.defendingPlayer, this.attackingPlayer, this.attackingCountry);
            this.assignRemainingCardsToWinnerPlayer(this.defendingPlayer, this.attackingPlayer);
            return "You Lost";
        }
        // if defender looses
        if (defendingCountry.getArmyInCountry() < 0) {
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
     * checking the number of dices which attacker can roll based on the number of
     * armies for that country
     */
    public int getNumberOfAttackerDice() {
        if (attackingCountry.getArmyInCountry() > 3) {
            return 3;
        } else {
            return 2;
        }
    }

    /**
     * checking the number of dices which attacker can roll based on the number of
     * armies for that country
     */
    public int getNumberOfDefenderDice() {
        if (defendingCountry.getArmyInCountry() >= 2) {
            return 2;
        } else {
            return 1;
        }
    }

    /**
     * rolling the dice by getting the number of dices
     *
     * @param numberOfDice
     * @return an array of dice face numbers which is sorted
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
     *
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
     *
     * @param defendingCountry
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
     * adding or removing the country to or from the countrylist after battle
     *
     * @param winnerPlayer
     * @param loserPlayer
     * @param lostCountry
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
     *
     * @param winnerPlayer
     * @param loserPlayer
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
