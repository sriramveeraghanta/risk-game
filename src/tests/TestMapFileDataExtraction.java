package tests;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.nio.channels.NonWritableChannelException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.MapBuilder;
import models.GameModel;

/**
 * 
 * Testing the mapFileDataExtraction functionality
 *
 */
public class TestMapFileDataExtraction {
	MapBuilder mapBuilder;
	
	@BeforeEach
	public void beforeTest() {
		GameModel gameModel = new GameModel();
		//mapBuilder = new MapBuilder(gameModel);
	}
	
	/*
	 * //rename the map file and try to load with previous name
	 * 
	 * @Test public void testMapFilePocessingInvalid() {
	 * assertThrows(FileNotFoundException.class, ()->{mapBuilder.generateMap();}); }
	 * 
	 * //remove the [continent] tag from .map file
	 * 
	 * @Test public void testContinentMapData() {
	 * assertThrows(NullPointerException.class, ()->{mapBuilder.generateMap();}); }
	 */
	
	@AfterEach
	public void afterTest() {
		
	}
}

