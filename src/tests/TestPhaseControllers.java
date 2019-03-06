package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.AttackPhase;
import controllers.MapController;
import controllers.PlayerController;
import models.ContinentModel;
import models.CountryModel;
import models.GameModel;
import models.PlayerModel;
import models.UnitModel;
import utils.EnumClass.Color;
import utils.EnumClass.UnitType;

/**
 * 
 * Testing the reinforcement phase functionality
 *
 */

public class TestPhaseControllers {
	CountryModel country1;
	CountryModel country2;
	GameModel game;
	Color color;
	PlayerModel player;
	controllers.ReinforcementPhase testReinforcement;
	ContinentModel continent;
	MapController testMap;
	List<String> mapData;
	PlayerController startup;
	models.UnitModel unit;
	controllers.AttackPhase testAttack;
	
	@BeforeEach
	public void beforeTest() {
	game=new GameModel();
	color=Color.YELLOW;
	player=new PlayerModel(color);
	testReinforcement=new controllers.ReinforcementPhase(player, game);
	continent=new ContinentModel("North America", 5);
	country1=new CountryModel("Alberta", 10, 20, continent);
	country2=new CountryModel("Alaska", 10, 30, continent);
	testMap=new MapController();
	mapData=new ArrayList<String>(3);
	startup=new StartUp();
	unit=new UnitModel();
	testAttack=new AttackPhase(player,startup , "Alberta", "Alaska");
	}
	
	@Test
	public void testAssignArmyUnitToCountry() {
		assertTrue(testReinforcement.assignArmyUnitToCountry(player.getCountries().get(0).getCountryName(),10));
	}
	
	@Test
	public void testCheckIfPlayerCountriesHaveAllContinentCountriesTrue() {
		
		player.addCountryToPlayer(country1);
		player.addCountryToPlayer(country2);
		assertTrue(testReinforcement.checkIfPlayerCountriesHaveAllContinentCountries(continent,player.getCountries()));
	}
	
	@Test
	public void testCheckIfPlayerCountriesHaveAllContinentCountriesFalse() {
		
		player.addCountryToPlayer(country2);
		assertFalse(testReinforcement.checkIfPlayerCountriesHaveAllContinentCountries(continent,player.getCountries()));
	}
	
	@Test
	public void testCheckIfPlayerCanAttackCountryTrue() {
		unit.setType(UnitType.INFANTRY);
		unit.setUnitNumber(4);
		ArrayList<UnitModel> unitArrayList=new ArrayList<UnitModel>();
		unitArrayList.add(unit);
		country1.setArmyInCountry(unitArrayList);
		assertEquals(true,testAttack.checkIfPlayerCanAttackCountry());
	}
	
	@Test
	public void testCheckIfPlayerCanAttackCountryFalse() {
		unit.setType(UnitType.INFANTRY);
		unit.setUnitNumber(1);
		ArrayList<UnitModel> unitArrayList=new ArrayList<UnitModel>();
		unitArrayList.add(unit);
		country1.setArmyInCountry(unitArrayList);
		assertEquals(false,testAttack.checkIfPlayerCanAttackCountry());
	}

	@Test
	public void testSetInitialInfantryValid() {
		startup.setNumberOfPlayers(2);
		unit.setType(UnitType.INFANTRY);
		unit.setUnitNumber(40);
		assertEquals(unit,startup.setInitialInfantry(player));
	}
	@Test
	public void testSetInitialInfantryInvalid() {
		startup.setNumberOfPlayers(3);
		unit.setType(UnitType.INFANTRY);
		unit.setUnitNumber(40);
		assertNotEquals(unit,startup.setInitialInfantry(player));
	}
	//TODO invalid map file needs to be done
	@Test
	/*public void testMapFileDataExtraction() throws IOException {
		MapFileDataExtraction mp = new MapFileDataExtraction();
		mp.mapFilePocessing();
	}*/
	@AfterEach
	public void afterTest() {
	
	}	
}