package utils;

/**
 * 
 * Checking if integer value of a given string
 * can be formed or not
 *
 */
public class Common {

	public Common() {
	}
	
	public boolean tryParseInt(String value) {  
	     try {  
	         Integer.parseInt(value);  
	         return true;  
	      } catch (NumberFormatException e) {  
	         return false;  
	      }  
	}

}
