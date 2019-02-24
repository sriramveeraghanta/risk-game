/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Stack;
import java.util.stream.Collectors;
import model.EnumClass.Color;
import model.EnumClass.UnitType;
/**
 * @author edosh
 *
 */

@SuppressWarnings("deprecation")
public class GameModel extends Observable {

	private String title;
	private boolean visible;
	private ArrayList<PlayerModel> players;
	
	private  Stack<CardModel> stackOfCards= DeckModel.stackOfCards;
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 */
	public GameModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
		setChanged();
		notifyObservers("visible");
	}
	
	
	//read documents to set continents,countries,adjacencies
	public void loadMap() {
		
	}
	
	
	///assigning armies to players at initial level
	public void setPlayers(HashMap<String, Color> playerDetails) {
		int index=0;
		int player_count=playerDetails.size();
		UnitModel unitdetails = null;
		if(player_count==2) {
			unitdetails=new UnitModel(40,UnitType.INFANTRY);
		}else if(player_count ==3){
			unitdetails=new UnitModel(35,UnitType.INFANTRY);
		}else if(player_count ==4){
			unitdetails=new UnitModel(30,UnitType.INFANTRY);
		}else if(player_count ==5){
			unitdetails=new UnitModel(25,UnitType.INFANTRY);
		}else if(player_count ==6){
			unitdetails=new UnitModel(20,UnitType.INFANTRY);
			
		}
		
		players=new ArrayList<PlayerModel>();
		 for (String playerName : playerDetails.keySet()) {
			 index++;
			 players.add(new PlayerModel(playerName,playerDetails.get(playerName), index, unitdetails));
		     
		    }
		 index=0;
		
	}
	
	///assigning countries to players equally
	public void assignCountries(ArrayList<String>Countries) {
		Collections.shuffle(Countries); 
		int i;
		int count;
		int players_count=players.size();
		int countries_count;
		countries_count=Countries.size()/players_count;
		
		for(i=1;i<=players_count;i++) {
			count=0;
			for(String country : Countries) {
				count++;
				if(count <= countries_count) {
					players.get(i).setCountries(country);
					Countries.remove(country);
				}else {
					break;
				}
				
			}
		}
	}
	
	
	
	/*
	 * public void assignCard(int index) { CardModel card = stackOfCards.pop();
	 * players.get(index).setCards(card); }
	 */
	
	/*
	 * public void checkcard(int index) { boolean returnFlag=false; List<CardModel>
	 * Cards = players.get(index).getCards(); List<String>samecard=new
	 * ArrayList<String>(); List<String>differentcard=new ArrayList<String>(); if
	 * (Cards.size() >= 3) { int infantry = 0, cavalry = 0, artillery = 0; for
	 * (CardModel card : Cards) { if
	 * (card.getCardType().toString().equals(UnitType.INFANTRY.toString())) {
	 * 
	 * infantry++; } else if
	 * (card.getCardType().toString().equals(UnitType.CAVALRY.toString())) {
	 * cavalry++; } else if
	 * (card.getCardType().toString().equals(UnitType.ARTILLERY.toString())) {
	 * artillery++; } }
	 * 
	 * if ((infantry == 1 && cavalry == 1 && artillery == 1) || infantry == 3 ||
	 * cavalry == 3 || artillery == 3) { returnFlag = true; } }
	 * 
	 * if(returnFlag == true){ HashMap<String, Integer> findCard = new HashMap<>();
	 * for (CardModel card : Cards) { if
	 * (findCard.containsKey(card.getCardType().toString())) {
	 * findCard.put(card.getCardType().toString(),
	 * findCard.get(card.getCardType().toString())+1 ); } else {
	 * findCard.put(card.getCardType().toString(), 1); }
	 * 
	 * }
	 * 
	 * 
	 * for(String i : findCard.keySet()) { if(findCard.get(i) >= 3) {
	 * 
	 * samecard.add(i); }else { differentcard.add(i); }
	 * 
	 * }
	 * 
	 * if(samecard.size()>=1) { for(String i : findCard.keySet()) {
	 * if(findCard.get(i) >= 3) {
	 * 
	 * differentcard.add(i); }
	 * 
	 * } } } }
	 */
	
	
////Initial reinforcement phase-this methode has to be updated based on the view
	
	public void reinforcementPhase(int index,String country,UnitModel army) {
			
		//// deployment of armies
		for(CountryModel territory : players.get(index).getCountries()) {
			
			
			if(territory.getName().equalsIgnoreCase(country)) {
				territory.setArmy(army);
				ArrayList<UnitModel> unit;
				unit=players.get(index).getArmy();
				for(UnitModel unitdetails : unit) {    
					if(unitdetails.getType()==army.getType()) {
						
						players.get(index).decreaseArmies(army);  ////reducing the players army count after assigning

						
					}
				}
			}
		}
		
		
		
	}
	
	
	public void attackingPhase() {
		
		
	}
	

}
