package utils;

import java.util.ArrayList;

import models.ContinentModel;
import models.GameModel;

public class Common {

	public Common() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean tryParseInt(String value) {  
	     try {  
	         Integer.parseInt(value);  
	         return true;  
	      } catch (NumberFormatException e) {  
	         return false;  
	      }  
	}
	
	private ContinentModel getContinentModelFromList() {
		GameModel gameModel = new GameModel();
		ArrayList<ContinentModel> continentList = gameModel.getContinents();
		
		return null;
	}

}
