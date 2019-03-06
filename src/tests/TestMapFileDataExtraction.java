package tests;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.nio.channels.NonWritableChannelException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.MapController;
import models.GameModel;

/**
 * 
 * Testing the mapFileDataExtraction functionality
 *
 */
public class TestMapFileDataExtraction {
	MapController mapController;
	
	@BeforeEach
	public void beforeTest() {
		GameModel gameModel = new GameModel();
		mapController = new MapController(gameModel);
	}
	
	//rename the map file and try to load with previous name
	@Test
	public void testMapFilePocessingInvalid() {
		assertThrows(FileNotFoundException.class, ()->{mapController.generateMap();});
	}
	
	//remove the [continent] tag from .map file
	@Test
	public void testContinentMapData() {
		assertThrows(NullPointerException.class, ()->{mapController.generateMap();});
	}
	
	@AfterEach
	public void afterTest() {
		
	}
}

