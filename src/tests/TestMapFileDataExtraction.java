package tests;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.MapFileDataExtraction;

/**
 * 
 * Testing the mapFileDataExtraction functionality
 *
 */
public class TestMapFileDataExtraction {
	MapFileDataExtraction map;
	
	@BeforeEach
	public void beforeTest() {
		map = new MapFileDataExtraction();
	}
	
	//rename the map file and try to load with previous name
	@Test
	public void testMapFilePocessingInvalid() {
		assertThrows(FileNotFoundException.class, ()->{map.mapFilePocessing();});
	}
	
	//remove the [continent] tag from .map file
	@Test
	public void testContinentMapData() {
		assertThrows(NullPointerException.class, ()->{map.mapFilePocessing();});
	}
	
	@AfterEach
	public void afterTest() {
		
	}
}

