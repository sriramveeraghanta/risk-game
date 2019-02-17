package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * 
 * This Class will be called ,after player choosing the dice.
 * @author Harish Jayasankar
 *
 */
 class DiceModel {

	private int roll;
	private Integer[] diceCount;    ///It will have number of DiceModel
    private Random die;             ///For random number generation from 1 to 6

    /**
     * roll method will returns an integer array of values between 1 and 6 representing the
     * outcome of rolling the dice.
     **/
    public Integer[] roll(int numberOfDice) {
	
		diceCount = new Integer[numberOfDice];
		
		for(int i = 0; i < diceCount.length; i++) {
			die = new Random();
			roll = die.nextInt(5) + 1;
			diceCount[i] = roll;
		}
		
		// Sorts in descending order 
		Arrays.sort(diceCount); 
		Arrays.sort(diceCount, Collections.reverseOrder()); 
		
		return diceCount;
    }
}
