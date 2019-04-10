package test.main.helpers;

import main.helpers.AttackPhase;
import main.helpers.MapBuilder;
import main.helpers.ReinforcementPhase;
import main.helpers.StartupPhase;
import main.models.*;
import main.utills.EnumHandler;
import main.utills.GameCommon;
import org.junit.Test;
import org.junit.Before;
import static junit.framework.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ReinforcementPhase Tester.
 *
 */
public class ReinforcementPhaseTest {
    PlayerModel playerModel1;
    PlayerModel playerModel2;
    GameModel gameModel;
    ReinforcementPhase reinforce;
    CountryModel country1,country2,country3,country4;
    MapBuilder mapBuilder;
    GameCommon gameCommons;
    CardModel cardModel;
    CardModel cardModel1;
    AttackPhase attackPhase;
    ArrayList<CountryModel> countryList;
    ArrayList<PlayerModel> playerList;
    StartupPhase startupPhase;
    /**
     * method which should run before all of the test methods
     * @throws Exception if exception occur throws Exception
     */
    @Before
    public void before() throws Exception {
        countryList = new ArrayList<>();
        playerList = new ArrayList<>();
        playerModel1 = new PlayerModel();
        playerModel2 = new PlayerModel();
        gameModel = new GameModel();
        playerModel2.setColor(EnumHandler.Color.BLACK);
        playerModel2.setActive(false);
        playerModel1.setColor(EnumHandler.Color.BLUE);
        mapBuilder = new MapBuilder(gameModel);
        mapBuilder.readMapFile("world.map");
        gameCommons = new GameCommon();
        cardModel = new CardModel(EnumHandler.CardType.INFANTRY);
        cardModel1 = new CardModel(EnumHandler.CardType.INFANTRY);
        country1 = gameCommons.getCountryModelFromList(gameModel.getCountries(),"Venezuala");
        country2 = gameCommons.getCountryModelFromList(gameModel.getCountries(),"Peru");
        country3 = gameCommons.getCountryModelFromList(gameModel.getCountries(),"Brazil");
        country4 = gameCommons.getCountryModelFromList(gameModel.getCountries(),"Argentina");
        country1.setArmyInCountry(4);
        country2.setArmyInCountry(2);
        countryList.add(country1);
        playerModel1.setCountries(countryList);
        countryList.clear();
        countryList.add(country2);
        playerModel2.setCountries(countryList);
        playerList.add(playerModel1);
        playerList.add(playerModel2);
        gameModel.setPlayers(playerList);
        startupPhase = new StartupPhase(gameModel);
        startupPhase.createGameCards();
        attackPhase = new AttackPhase(gameModel, country1, country2);
        attackPhase.assignCardToPlayer(playerModel1);
        reinforce = new ReinforcementPhase(playerModel1, gameModel);
    }

    /**
     * Method: swapCardsForArmyUnits()
     * @throws Exception if exception occur throws Exception
     */
    @Test
    public void testSwapCardsForArmyUnitsForArmyGreaterThanZero() throws Exception {
        ArrayList<CardModel> cardModelList =new ArrayList<>();
        cardModelList.add(cardModel);
        playerModel1.setDeck(cardModelList);
        assertEquals(0 ,reinforce.swapCardsForArmyUnits(cardModelList));
    }

    /**
     * Method: swapCardsForArmyUnits()
     * @throws Exception if exception occur throws Exception
     */
    @Test
    public void testSwapCardsForArmyUnitsForArmyEqualZero() throws Exception {
        ArrayList<CardModel> cardModelList =new ArrayList<>();
        cardModelList.add(cardModel1);
        playerModel1.setDeck(cardModelList);
        assertEquals(0, reinforce.swapCardsForArmyUnits(cardModelList));
    }

    /**
     * Method: getArmyUnitsForConqueredContinent()
     * @throws Exception if exception occur throws Exception
     */
    @Test
    public void testGetArmyUnitsForConqueredContinentForOneContinent() throws Exception {
        playerModel1.addCountry(country1);
        playerModel1.addCountry(country2);
        playerModel1.addCountry(country3);
        playerModel1.addCountry(country4);
        assertEquals(2, reinforce.getArmyUnitsForConqueredContinent());
    }

    /**
     * Method: getArmyUnitsForConqueredContinent()
     * @throws Exception if exception occur throws Exception
     */
    @Test
    public void testGetArmyUnitsForConqueredContinentForSecondContinent() throws Exception {
        playerModel1.addCountry(country1);
        playerModel1.addCountry(country2);
        playerModel1.addCountry(country3);
        playerModel1.addCountry(country4);
        ContinentModel continentModel = gameCommons.getContinentModelFromList(gameModel.getContinents(),"Asia");
        ArrayList<ContinentModel> continentModelList =new ArrayList<>();
        continentModelList.add(continentModel);
        playerModel1.setContinents(continentModelList);
        assertEquals(2,reinforce.getArmyUnitsForConqueredContinent());
    }

    /**
     * Method: getArmyUnitsFromCountries()
     * @throws Exception if exception occur throws Exception
     */
    @Test
    public void testGetArmyUnitsFromCountries() throws Exception {
        playerModel1.addCountry(country1);
        playerModel1.addCountry(country2);
        playerModel1.addCountry(country3);
        playerModel1.addCountry(country4);
        assertEquals(3, reinforce.getArmyUnitsFromCountries());
    }

    /**
     *  Method: getNumberOfSimilarCards()
     * @throws Exception if exception occur throws Exception
     */
    @Test
    public void testGetNumberOfSimilarCards() throws Exception {
        ArrayList<CardModel> cardModelList =new ArrayList<>();
        cardModelList.add(cardModel);
        playerModel1.setDeck(cardModelList);
        assertEquals(0, reinforce.getNumberOfSimilarCards(cardModelList));
    }

    /**
     * Method: getNumberOfDifferentCards()
     * @throws Exception if exception occur throws Exception
     */
    @Test
    public void testGetNumberOfDifferentCards() throws Exception {
        ArrayList<CardModel> cardModelList =new ArrayList<>();
        cardModelList.add(cardModel);
        playerModel1.setDeck(cardModelList);
        assertEquals(0, reinforce.getNumberOfDifferentCards(cardModelList));
    }

    /**
     * Method: getNumberCardTypeByCardType(List<CardModel> cards, EnumHandler.CardType unitType)
     * @throws Exception if exception occur throws Exception
     */
    @Test
    public void testGetNumberCardTypeByCardType() throws Exception {
        ArrayList<CardModel> card =new ArrayList<>();
        card.add(cardModel);
        assertEquals(1, reinforce.getNumberCardTypeByCardType(card, EnumHandler.CardType.INFANTRY));
    }
}
