package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.ContinentModel;
import models.GameModel;
import models.PlayerModel;
import utils.EnumClass.Color;

/**
 * Testing the continent model
 * 
 */
public class TestContinentModel {
	GameModel game;
	PlayerModel player;
	Color color;
	ContinentModel continent;
	
	@BeforeEach
	public void beforeTest() {
		game=new GameModel();
		player=new PlayerModel(color);
		continent=new ContinentModel("North America", 5);
	}
	
	@Test
	public void TestContinentModel() {
		assertEquals(continent.getContinentName() , game.getContinents().get(0).getContinentName() );
	}
	
	@AfterEach
	public void afterTest() {
		
	}
}
