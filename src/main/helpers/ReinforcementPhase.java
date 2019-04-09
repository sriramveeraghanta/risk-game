package main.helpers;

import main.models.*;
import main.utills.EnumHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * this class contains all the methods we need in reinforcement phase
 */
public class ReinforcementPhase {

    private PlayerModel playerModel;
    private GameModel gameModel;
    private boolean updatePlayerDeck=false;

    /**
     * Constructor Reinforcement Phase
     * @param playerModel the player
     * @param gameModel   the object of gameModel
     */
    public ReinforcementPhase(PlayerModel playerModel, GameModel gameModel) {
        this.playerModel = playerModel;
        this.gameModel = gameModel;
    }

    /**
     * calculating the number of armies players can get for each round by default which should
     * be the number of countries divided by 3
     * rounded down
     * @return the number of armies each player can by default
     */
    public int getArmyUnitsFromCountries() {
        int numberOfUnits;
        int numberOfCountries = this.playerModel.getCountries().size();
        if (numberOfCountries < 11) {
            numberOfUnits = 3;
        } else {
            numberOfUnits = Math.floorDiv(numberOfCountries, 3);
        }
        return numberOfUnits;
    }

    /**
     * assigning the number of armies which calculated to the country of the player
     * @param country   which country we want to place the armies
     * @param numberOfUnits number of armies assign to the country
     * @return boolean if it goes in catch it return false
     */
    public boolean assignArmyUnitToCountry(CountryModel country, int numberOfUnits) {
        if (this.playerModel.getArmyInHand() >=numberOfUnits) {
            country.setArmyInCountry(country.getArmyInCountry() + numberOfUnits);
            this.playerModel.setArmyInHand(this.playerModel.getArmyInHand() - numberOfUnits);
            gameModel.reinforce();
            return true;
        }
        return false;
    }


    /**
     * this method calculates the number of army units player get according to the cards if they are similar of they are different
     * and the number will increase 5 times more each time he swap the cards with armies
     * @return the total number of army units he can get for cards
     */
    public int swapCardsForArmyUnits(ArrayList<CardModel>  playerCardsList) {
        int totalNumberOfArmyUnits = getNumberOfSimilarCards(playerCardsList) + getNumberOfDifferentCards(playerCardsList);
        if (totalNumberOfArmyUnits > 0) {
                this.playerModel.setSuccessfulCardSwapCounter(this.playerModel.getSuccessfulCardSwapCounter() + 1);
            if(isUpdatePlayerDeck()) {
                gameModel.cardSwap();
            }
            return (totalNumberOfArmyUnits * 5) *(this.playerModel.getSuccessfulCardSwapCounter());
        }

        return 0;
    }


    /**
     * getting the continent control value which shows the number of armies player can get after owning all the
     * countries in a continent
     * @return the number of control value of specific continent
     */
    public int getArmyUnitsForConqueredContinent() {
        int totalContinentValues = 0;
        for (ContinentModel continent : gameModel.getContinents()) {
            if (checkIfPlayerCountriesHaveAllContinentCountries(continent, this.playerModel.getCountries())) {
                List<String> continentList = this.playerModel.getContinents().stream().map(c -> c.getContinentName())
                        .collect(Collectors.toList());
                if (!continentList.contains(continent.getContinentName())) {
                    ArrayList<ContinentModel> playerContinents = this.playerModel.getContinents();
                    if (playerContinents == null) {
                        playerContinents = new ArrayList<>();
                    }
                    playerContinents.add(continent);
                    this.playerModel.setContinents(playerContinents);
                    totalContinentValues += continent.getControlValue();
                }
            }
        }
        return totalContinentValues;
    }

    /**
     * checking the cards of the player if they are similar types and return the number of units he can get
     * @return the number of units he can get for similar cards
     */
    public int getNumberOfSimilarCards(ArrayList<CardModel>  playerCardsList) {
        System.out.println("Similar card method") ;
        int numberOfUnits = 0;
        int infantryCardNumber = 0;
        int artilaryCardNumber = 0;
        int cavalaryCardNumber = 0;
        ArrayList<CardModel> cards = playerCardsList;

        infantryCardNumber = getNumberCardTypeByCardType(cards, EnumHandler.CardType.INFANTRY);
        artilaryCardNumber = getNumberCardTypeByCardType(cards, EnumHandler.CardType.ARTILLERY);
        cavalaryCardNumber = getNumberCardTypeByCardType(cards, EnumHandler.CardType.CAVALRY);

        numberOfUnits += infantryCardNumber / 3;
        numberOfUnits += artilaryCardNumber / 3;
        numberOfUnits += cavalaryCardNumber / 3;

        if (infantryCardNumber / 3 > 0 && isUpdatePlayerDeck()) {
            setPlayerDeckByCardType(cards, EnumHandler.CardType.INFANTRY);
        }
        if (artilaryCardNumber / 3 > 0 && isUpdatePlayerDeck()) {
            setPlayerDeckByCardType(cards, EnumHandler.CardType.ARTILLERY);
        }
        if (cavalaryCardNumber / 3 > 0 && isUpdatePlayerDeck()) {
            setPlayerDeckByCardType(cards, EnumHandler.CardType.CAVALRY);
        }

        return numberOfUnits;
    }

    /**
     * checking the cards of the player if they are different types and return the number of units he can get
     * @return the number of units he can get for different cards
     */
    public int getNumberOfDifferentCards(ArrayList<CardModel>  playerCardsList) {
        System.out.println("different card method") ;
        int numberOfUnits = 0;
        int infantryCardNumber = 0;
        int artilaryCardNumber = 0;
        int cavalaryCardNumber = 0;

        ArrayList<CardModel> playerCards = playerCardsList;

        infantryCardNumber = getNumberCardTypeByCardType(playerCards, EnumHandler.CardType.INFANTRY);
        artilaryCardNumber = getNumberCardTypeByCardType(playerCards , EnumHandler.CardType.ARTILLERY);
        cavalaryCardNumber = getNumberCardTypeByCardType(playerCards , EnumHandler.CardType.CAVALRY);

        numberOfUnits = Math.min(infantryCardNumber, Math.min(artilaryCardNumber, cavalaryCardNumber));

        if (numberOfUnits > 0 && isUpdatePlayerDeck()) {
            setPlayerDeckByCardType(playerCards, EnumHandler.CardType.INFANTRY, numberOfUnits);
            setPlayerDeckByCardType(playerCards, EnumHandler.CardType.ARTILLERY, numberOfUnits);
            setPlayerDeckByCardType(playerCards, EnumHandler.CardType.CAVALRY, numberOfUnits);
        }

        return numberOfUnits;

    }

    /**
     * checking the cards type and return the number of each card type.
     * @param playerCards    list of all cards player own
     * @param cardType getting the unit type and return the number of it
     * @return the number of specific card type
     */
    public int getNumberCardTypeByCardType(ArrayList<CardModel> playerCards, EnumHandler.CardType cardType) {
        //System.out.println(playerCards.stream().filter(card -> card.getCardType().equals(cardType)).);
        ArrayList<CardModel> cardsByCardType = new ArrayList<>();
        for(CardModel card: playerCards) {
            if(card.getCardType() == cardType){
                cardsByCardType.add(card);
            }
        }
        return cardsByCardType.size();
    }

    /**
     * setting the player deck by getting the list of cards and unit type of it
     * @param cards    a list of cards
     * @param cardType which is the type of cards
     */



    private void setPlayerDeckByCardType(List<CardModel> cards, EnumHandler.CardType cardType) {
        ArrayList<CardModel> playerCards=playerModel.getDeck();
        ArrayList<CardModel> playerCardsToRemove=new ArrayList<CardModel>();
        int cardCounter=0;
        int cardsLimit=(3 * (cards.size()/ 3));
        if(cards!=null){

            for (int i = 0; i < playerCards.size(); i++){
                if(playerCards.get(i).getCardType() == cardType && cardCounter<cardsLimit){
                    playerCardsToRemove.add(playerCards.get(i));
                    cardCounter=cardCounter+1;
                }
            }
            if(playerCardsToRemove.size() >0) {
                playerCards.removeAll(playerCardsToRemove);
            }
            cardCounter=0;
        }
    }

    /**
     * setting the player deck by getting the list of cards and unit type of it
     * @param cards        a list of cards
     * @param cardType     which is the type of cards
     * @param numbeOfUnits the number of each card type player has
     */
    public void setPlayerDeckByCardType(List<CardModel> cards, EnumHandler.CardType cardType, int numbeOfUnits) {
        ArrayList<CardModel> playerCards=playerModel.getDeck();
        ArrayList<CardModel> playerCardsToRemove=new ArrayList<CardModel>();
        int cardCounter=0;
        if(cards!=null){

            for (int i = 0; i < playerCards.size(); i++){
                if(playerCards.get(i).getCardType() == cardType && cardCounter<numbeOfUnits){
                    playerCardsToRemove.add(playerCards.get(i));
                    cardCounter=cardCounter+1;
                }
            }
            if(playerCardsToRemove.size() > 0) {
                playerCards.removeAll(playerCardsToRemove);
            }
            cardCounter=0;
        }


       /// CardModel card = cards.stream().filter(x -> x.getCardType().equals(cardType)).findFirst().get();
//        if (card != null) {
//            //card.setNumberOfCards(card.getNumberOfCards() - numbeOfUnits);
//        }

    }

    /**
     * checking if all the countries in a continent occupied by the player and return the control value of that continent
     * @param continent the object of continent
     * @param countries a list of countries player own
     * @return if player can get whole continent return true
     */
    public boolean checkIfPlayerCountriesHaveAllContinentCountries(ContinentModel continent, List<CountryModel> countries) {
        List<String> continentCountryNames = continent.getCountries().stream().map(c -> c.getCountryName())
                .collect(Collectors.toList());
        List<String> countryNames = countries.stream().map(c -> c.getCountryName()).collect(Collectors.toList());
        if (continentCountryNames.size() == 0 || countryNames.size() == 0)
            return false;
        if (countryNames.containsAll(continentCountryNames)) {
            return true;
        }
        return false;
    }

    public boolean isUpdatePlayerDeck() {
        return updatePlayerDeck;
    }

    public void setUpdatePlayerDeck(boolean updatePlayerDeck) {
        this.updatePlayerDeck = updatePlayerDeck;
    }
}
