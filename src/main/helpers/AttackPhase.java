package main.helpers;

import javafx.scene.control.Alert;
import main.models.CardModel;
import main.models.CountryModel;
import main.models.GameModel;
import main.models.PlayerModel;
import main.utills.GameConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This class contains all of the methods that we need in attack phase
 */
public class AttackPhase {

    private PlayerModel attackingPlayer, defendingPlayer;
    private CountryModel attackingCountry, defendingCountry;
    public GameModel gameModel;
    private boolean attackEnd = false;
    private boolean allOutModeFlag = false;


    /**
     * This method is constructor for attack phase
     *
     * @param gameModel        object of game model
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
     * This method handles all out attack
     */
    public void allOutMode() {
        int min = 1;
        int defenderMax, attackerMax;
        while (!isAttackEnd()) {
            if (attackingCountry.getArmyInCountry() >= 3) {
                attackerMax = 3;
                defenderMax = 2;
            } else {
                attackerMax = attackingCountry.getArmyInCountry();
                defenderMax = attackingCountry.getArmyInCountry();
            }
            int getAttackerDiceCount = diceCount(attackerMax, min);
            int getDefenderDiceCount = diceCount(defenderMax, min);
            if (attackingCountry.getArmyInCountry() >= 2 && defendingCountry.getArmyInCountry() >= 1) {
                attackCountry(getAttackerDiceCount, getDefenderDiceCount);
            } else {
                //attackResult();
                setAttackEnd(true);
            }
        }
    }

    /**
     * this method will give dice counts
     */
    private int diceCount(int max, int min) {
        Random r = new Random();
        int dice = r.nextInt((max - min) + 1) + min;
        return dice;
    }

    /**
     * This method do all the processes it should be done in attacking phase like rolling dice check result
     */
    public void attackCountry(int attackerDiceCount, int defenderDiceCount) {
        ArrayList<Integer> attackerDiceValues;
        ArrayList<Integer> defenderDiceValues;
        attackerDiceValues = getAllDiceValues(attackerDiceCount);
        defenderDiceValues = getAllDiceValues(defenderDiceCount);
        List<String> copyAttackerDiceValues =  new ArrayList<>();
        List<String> copyDefenderDiceValues = new ArrayList<>();

        for(int i : attackerDiceValues ) copyAttackerDiceValues.add(String.valueOf(i));
        for(int i : defenderDiceValues ) copyDefenderDiceValues.add(String.valueOf(i));

        int originalAttackerArmyCount=  attackingCountry.getArmyInCountry();
        int originalDefenderArmyCount= defendingCountry.getArmyInCountry();

        for (int i = 0; i < defenderDiceValues.size(); i++) {
            if (attackerDiceValues.get(i) < defenderDiceValues.get(i)) {
                attackingCountry.setArmyInCountry(attackingCountry.getArmyInCountry() - 1);
            } else {
                defendingCountry.setArmyInCountry(defendingCountry.getArmyInCountry() - 1);
            }
        }
        System.out.println(attackingCountry.getArmyInCountry());
        System.out.println(defendingCountry.getArmyInCountry());
        if (!isAllOutModeFlag()) {
            attackResult( String.join(" - ", copyAttackerDiceValues),String.join(" - ", copyDefenderDiceValues),originalAttackerArmyCount,originalDefenderArmyCount );
        }

    }

    /**
     * this method will give attack result
     */
    private void attackResult(String attackerDices, String defenderDices, int originalAttackerArmyCount, int originalDefenderArmyCount) {
        String outputText =  "Player " + attackingPlayer.getColor().toString().toUpperCase() + " of country " + this.attackingCountry.getCountryName() +" got the dice values "+ attackerDices +"\n" +
                "Player " + defendingPlayer.getColor().toString().toUpperCase() + " of country " + this.defendingCountry.getCountryName() +"  got the dice values "+ defenderDices +"\n" +
                "Player "+ attackingPlayer.getColor().toString().toUpperCase()+ " " + this.returnWonOrLost(originalAttackerArmyCount,this.attackingCountry.getArmyInCountry()) +" " + Math.abs(originalAttackerArmyCount -this.attackingCountry.getArmyInCountry()) +"\n" +
                "Player "+ defendingPlayer.getColor().toString().toUpperCase()+ " " + this.returnWonOrLost(originalDefenderArmyCount,this.defendingCountry.getArmyInCountry()) +" "  + Math.abs(originalDefenderArmyCount -this.defendingCountry.getArmyInCountry())  +"\n";

        // if attacker looses
        if (attackingCountry.getArmyInCountry() <= 1) {
            this.assignCardToPlayer(this.defendingPlayer);
            this.assignCountryToWinnerPlayer(this.defendingPlayer, this.attackingPlayer, this.attackingCountry);
            this.assignRemainingCardsToWinnerPlayer(this.defendingPlayer, this.attackingPlayer);
            // Alert to the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("You have attacked the defending country");
            alert.setHeaderText(null);
            alert.setContentText( attackingPlayer.getColor().toString().toUpperCase()+" Attacker Lost");
            alert.showAndWait();
            gameModel.attackPhaseEnd();
        }
        // if defender looses
        else if (defendingCountry.getArmyInCountry() <= 0) {
            this.assignCardToPlayer(this.attackingPlayer);
            this.assignCountryToWinnerPlayer(this.attackingPlayer, this.defendingPlayer, this.defendingCountry);
            this.assignRemainingCardsToWinnerPlayer(this.attackingPlayer, this.defendingPlayer);
            // Alert to the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Defender Lost");
            alert.setHeaderText(null);
            alert.setContentText("Attacker won and occupied the attacking country,Attacker can move the army");
            alert.showAndWait();
            gameModel.attackPhaseEnd();
        } else {
            // Alert to the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle( attackingPlayer.getColor().toString().toUpperCase() + " attack on "+  defendingPlayer.getColor().toString().toUpperCase());
            alert.setHeaderText(null);
            alert.setContentText(outputText);
            alert.showAndWait();
            gameModel.attackPhase();
        }
    }

    /**
     * this method getting the number of attacker dice and set and return the number of dices according to the
     * player army in that specific country
     *
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
     *
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
     *
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
     *
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
     *
     * @param winnerPlayer object of the player who won
     * @param loserPlayer  object of the player who lost
     * @param lostCountry  object of the country which the loser lost it
     */
    private void assignCountryToWinnerPlayer(PlayerModel winnerPlayer, PlayerModel loserPlayer, CountryModel lostCountry) {
        // loser
        List<CountryModel> loserCountries = loserPlayer.getCountries();
        loserCountries.remove(lostCountry);
        // Winner
        System.out.println("COuntries of cuurent player before adding:" + winnerPlayer.getCountries().size());
        List<CountryModel> winnerCountries = winnerPlayer.getCountries();
        winnerCountries.add(lostCountry);
        System.out.println("COuntries of cuurent player after adding:" + winnerPlayer.getCountries().size());
    }

    /**
     * checking if the loser do not have any other countries and own any card then
     * it should be given to the winner player
     *
     * @param winnerPlayer object of the player who won
     * @param loserPlayer  object of the player who lost
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

    public String swapArmyBetweenCountries(int numberOfArmyUnits) {
        CountryModel fromCountry = this.attackingCountry;
        CountryModel toCountry = this.defendingCountry;
        if ((fromCountry.getArmyInCountry() < 2) || ((fromCountry.getArmyInCountry() - numberOfArmyUnits) < 1)) {
            return GameConstants.ATTACK_ARMY_SWAP_INVALID_MSG;
        } else {
            fromCountry.setArmyInCountry((fromCountry.getArmyInCountry() - numberOfArmyUnits));
            toCountry.setArmyInCountry((toCountry.getArmyInCountry() + numberOfArmyUnits));
            this.gameModel.attackPhase();
            return GameConstants.ATTACK_ARMY_SWAP_VALID_MSG;
        }

    }

    public boolean isAllOutModeFlag() {
        return allOutModeFlag;
    }

    public void setAllOutModeFlag(boolean allOutModeFlag) {
        this.allOutModeFlag = allOutModeFlag;
    }

    public boolean isAttackEnd() {
        return attackEnd;
    }

    public void setAttackEnd(boolean attackEnd) {
        this.attackEnd = attackEnd;
    }

    private String returnWonOrLost(int beforeValue, int afterValue){
        if(beforeValue < afterValue)
            return " lost ";
        else
        return " won ";
    }
}
