package test.main.helpers; 

import main.helpers.MapBuilder;
import main.helpers.ReinforcementPhase;
import main.models.*;
import main.utills.EnumHandler;
import main.utills.GameCommons;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
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
    GameCommons gameCommons;
    CardModel cardModel;
    CardModel cardModel1;

@Before
public void before() throws Exception {
    playerModel = new PlayerModel();
    gameModel = new GameModel();
    reinforce = new ReinforcementPhase(playerModel, gameModel);
    mapBuilder = new MapBuilder(gameModel);
    mapBuilder.readMapFile(null);
    gameCommons = new GameCommons();
    cardModel = new CardModel(EnumHandler.UnitType.INFANTRY);
    cardModel.setNumberOfCards(4);
    cardModel1 = new CardModel(EnumHandler.UnitType.INFANTRY);
    cardModel1.setNumberOfCards(2);
    country1 = gameCommons.getCountryModelFromList(gameModel.getCountries(),"Venezuala");
    country2 = gameCommons.getCountryModelFromList(gameModel.getCountries(),"Peru");
    country3 = gameCommons.getCountryModelFromList(gameModel.getCountries(),"Brazil");
    country4 = gameCommons.getCountryModelFromList(gameModel.getCountries(),"Argentina");
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: swapCardsForArmyUnits() 
* 
*/ 
@Test
public void testSwapCardsForArmyUnitsForArmyGreaterThanZero() throws Exception {
    ArrayList<CardModel> cardModelList =new ArrayList<>();
    cardModelList.add(cardModel);
    playerModel.setDeck(cardModelList);
    assertEquals(reinforce.swapCardsForArmyUnits(), 5);
}

    @Test
    public void testSwapCardsForArmyUnitsForArmyEqualZero() throws Exception {
        ArrayList<CardModel> cardModelList =new ArrayList<>();
        cardModelList.add(cardModel1);
        playerModel.setDeck(cardModelList);
        assertEquals(reinforce.swapCardsForArmyUnits(), 0);
    }

    /**
* 
* Method: getArmyUnitsForConqueredContinent() 
* 
*/ 
@Test
public void testGetArmyUnitsForConqueredContinentForOneContinent() throws Exception {
    playerModel.addCountry(country1);
    playerModel.addCountry(country2);
    playerModel.addCountry(country3);
    playerModel.addCountry(country4);
    assertEquals(reinforce.getArmyUnitsForConqueredContinent(),2);
}

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
        assertEquals(reinforce.getArmyUnitsForConqueredContinent(),2);
    }

/**
* 
* Method: getArmyUnitsFromCountries() 
* 
*/ 
@Test
public void testGetArmyUnitsFromCountries() throws Exception {
    playerModel.addCountry(country1);
    playerModel.addCountry(country2);
    playerModel.addCountry(country3);
    playerModel.addCountry(country4);
    assertEquals(reinforce.getArmyUnitsFromCountries(), 3);
} 

/** 
* 
* Method: getNumberOfSimilarCards() 
* 
*/ 
@Test
public void testGetNumberOfSimilarCards() throws Exception {
    ArrayList<CardModel> cardModelList =new ArrayList<>();
    cardModelList.add(cardModel);
    playerModel.setDeck(cardModelList);
    assertEquals(reinforce.getNumberOfSimilarCards(), 1);
}
/** 
* 
* Method: getNumberOfDifferentCards() 
* 
*/ 
@Test
public void testGetNumberOfDifferentCards() throws Exception {
    ArrayList<CardModel> cardModelList =new ArrayList<>();
    cardModelList.add(cardModel);
    playerModel.setDeck(cardModelList);
    assertEquals(reinforce.getNumberOfDifferentCards(), 0);
} 

/** 
* 
* Method: getNumberCardTypeByCardType(List<CardModel> cards, EnumHandler.UnitType unitType) 
* 
*/ 
@Test
public void testGetNumberCardTypeByCardType() throws Exception {
    List<CardModel> card =new ArrayList<>();
    card.add(cardModel);
    assertEquals(reinforce.getNumberCardTypeByCardType(card, EnumHandler.UnitType.INFANTRY), 4);
}
} 
