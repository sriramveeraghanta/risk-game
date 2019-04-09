package test.main.helpers;

import main.helpers.MapBuilder;
import main.helpers.ReinforcementPhase;
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
PlayerModel playerModel;
GameModel gameModel;
ReinforcementPhase reinforce;
CountryModel country1,country2,country3,country4;
MapBuilder mapBuilder;
GameCommon gameCommons;
CardModel cardModel;
CardModel cardModel1;

/**
 * method which should run before all of the test methods
 * @throws Exception if exception occur throws Exception
 */
@Before
public void before() throws Exception {
    playerModel = new PlayerModel();
    gameModel = new GameModel();
    reinforce = new ReinforcementPhase(playerModel, gameModel);
    mapBuilder = new MapBuilder(gameModel);
    mapBuilder.readMapFile(null);
    gameCommons = new GameCommon();
    cardModel = new CardModel(EnumHandler.CardType.INFANTRY);
    cardModel.setNumberOfCards(4);
    cardModel1 = new CardModel(EnumHandler.CardType.INFANTRY);
    cardModel1.setNumberOfCards(2);
    country1 = gameCommons.getCountryModelFromList(gameModel.getCountries(),"Venezuala");
    country2 = gameCommons.getCountryModelFromList(gameModel.getCountries(),"Peru");
    country3 = gameCommons.getCountryModelFromList(gameModel.getCountries(),"Brazil");
    country4 = gameCommons.getCountryModelFromList(gameModel.getCountries(),"Argentina");
}

/**
 * Method: swapCardsForArmyUnits()
 * @throws Exception if exception occur throws Exception
 */
@Test
public void testSwapCardsForArmyUnitsForArmyGreaterThanZero() throws Exception {
    ArrayList<CardModel> cardModelList =new ArrayList<>();
    cardModelList.add(cardModel);
    playerModel.setDeck(cardModelList);
    assertEquals(5 ,reinforce.swapCardsForArmyUnits());
}

/**
 * Method: swapCardsForArmyUnits()
 * @throws Exception if exception occur throws Exception
 */
@Test
public void testSwapCardsForArmyUnitsForArmyEqualZero() throws Exception {
    ArrayList<CardModel> cardModelList =new ArrayList<>();
    cardModelList.add(cardModel1);
    playerModel.setDeck(cardModelList);
    assertEquals(0, reinforce.swapCardsForArmyUnits());
}

/**
 * Method: getArmyUnitsForConqueredContinent()
 * @throws Exception if exception occur throws Exception
 */
@Test
public void testGetArmyUnitsForConqueredContinentForOneContinent() throws Exception {
    playerModel.addCountry(country1);
    playerModel.addCountry(country2);
    playerModel.addCountry(country3);
    playerModel.addCountry(country4);
    assertEquals(2, reinforce.getArmyUnitsForConqueredContinent());
}

/**
 * Method: getArmyUnitsForConqueredContinent()
 * @throws Exception if exception occur throws Exception
 */
@Test
public void testGetArmyUnitsForConqueredContinentForSecondContinent() throws Exception {
    playerModel.addCountry(country1);
    playerModel.addCountry(country2);
    playerModel.addCountry(country3);
    playerModel.addCountry(country4);
    ContinentModel continentModel = gameCommons.getContinentModelFromList(gameModel.getContinents(),"Asia");
    ArrayList<ContinentModel> continentModelList =new ArrayList<>();
    continentModelList.add(continentModel);
    playerModel.setContinents(continentModelList);
    assertEquals(2,reinforce.getArmyUnitsForConqueredContinent());
}

/**
 * Method: getArmyUnitsFromCountries()
 * @throws Exception if exception occur throws Exception
 */
@Test
public void testGetArmyUnitsFromCountries() throws Exception {
    playerModel.addCountry(country1);
    playerModel.addCountry(country2);
    playerModel.addCountry(country3);
    playerModel.addCountry(country4);
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
    playerModel.setDeck(cardModelList);
    assertEquals(1, reinforce.getNumberOfSimilarCards());
}

/**
 * Method: getNumberOfDifferentCards()
 * @throws Exception if exception occur throws Exception
 */
@Test
public void testGetNumberOfDifferentCards() throws Exception {
    ArrayList<CardModel> cardModelList =new ArrayList<>();
    cardModelList.add(cardModel);
    playerModel.setDeck(cardModelList);
    assertEquals(0, reinforce.getNumberOfDifferentCards());
}

/**
 * Method: getNumberCardTypeByCardType(List<CardModel> cards, EnumHandler.CardType unitType)
 * @throws Exception if exception occur throws Exception
 */
@Test
public void testGetNumberCardTypeByCardType() throws Exception {
    List<CardModel> card =new ArrayList<>();
    card.add(cardModel);
    assertEquals(4, reinforce.getNumberCardTypeByCardType(card, EnumHandler.CardType.INFANTRY));
}
}
