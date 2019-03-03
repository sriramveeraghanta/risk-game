package action;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.AttackPhase;
import controllers.ReinforcementPhase;
import controllers.StartUp;
import models.CountryModel;
import models.GameModel;
import models.PlayerModel;
import utils.GameConstant;

public class action implements ActionListener {

	private StartUp startup;
	private PlayerModel player;
	private GameModel gameModel;
	public action(PlayerModel player,GameModel gameModel, StartUp startup) {
		this.startup = startup;
		this.player = player;
		this.gameModel=gameModel;
	}
	public action() {
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel(new GridLayout(0, 1));
		String phaseActionButton = e.getActionCommand();
		if (phaseActionButton.equalsIgnoreCase(GameConstant.REINFORCEMENT_BUTTON_TITLE)) {
			//TODO country list if required
//			ArrayList<String> countryListOfAttacker = new ArrayList<>();
////			JTextField activePlayer = new JTextField("Active Player" +);
////			ArrayList<CountryModel> countryListOfAttacker = 
//			for(CountryModel country : activePlayer.getCountries())
//				    countryListOfAttacker.add(country.getCountryName());
//			countryListOfAttacker.toArray(new String[countryListOfAttacker.size()]);
			//TODO proper player model needs to eb updated
			ReinforcementPhase reinforce=new ReinforcementPhase(player,gameModel);
			int initialArmy = 5;
			String[] countryListOfAttacker = {"Alberta", "Alaska", "Columbia"};
			JComboBox<String> playerCountryList = new JComboBox<>(countryListOfAttacker);
			while(initialArmy>0) { 
				Integer[] noOfArmyToPlace = {1,2,3,4,5};
				JComboBox<Integer> noOfArmyToPlaceInCountry = new JComboBox<>(noOfArmyToPlace);
				JTextField noOfArmy = new JTextField("Army left : "+initialArmy);
				panel.add(new JLabel("Country List"));
				panel.add(playerCountryList);
				panel.add(new JLabel("No of army you want to place"));
				panel.add(noOfArmyToPlaceInCountry);
				panel.add(noOfArmy);
				JOptionPane.showConfirmDialog(null, panel, "Test",
			            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//				reinforce.assignArmyUnitToCountry(playerCountryList.getSelectedItem().toString(),
//						Integer.parseInt(noOfArmyToPlaceInCountry.getSelectedItem().toString()));
//				System.out.println(playerCountryList.getSelectedItem() + "  "+noOfArmyToPlaceInCountry.getSelectedItem());
				initialArmy = initialArmy- (int)noOfArmyToPlaceInCountry.getSelectedItem();
			}
		} 
		else if (phaseActionButton.equalsIgnoreCase(GameConstant.ATTACK_BUTTON_TITLE)) {
			//TODO sending data to some method
			String defenderCountry ; 
			HashMap<String , ArrayList<String>> countryContinent = 
					new HashMap<>();
			ArrayList<String> str = new ArrayList<>();
			str.add("Two");
			str.add("Five");
			str.add("Six");
			countryContinent.put("One", str);
			ArrayList<String> str2 = new ArrayList<>();
			str2.add("Four");
			str2.add("Three");
			countryContinent.put("Two", str2);
			String[] countryListOfAttacker = {"One", "Two", "Three"};
			JComboBox<String> attackerCountry = new JComboBox<>(countryListOfAttacker);
			panel.add(new JLabel("Attacker Country"));
			panel.add(attackerCountry);
			int result = JOptionPane.showConfirmDialog(null, panel, "Test",
		            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (result == JOptionPane.OK_OPTION) {
				ArrayList<String> adjacentCountryList = countryContinent.get(attackerCountry.getSelectedItem());
				for (String country : countryListOfAttacker ) {
					if (adjacentCountryList.contains(country))
						adjacentCountryList.remove(country);
				}
				String[] adjacentCountryListForAttacker = 
						adjacentCountryList.toArray(new String[adjacentCountryList.size()]);
				JComboBox<String> defendercountry = new JComboBox<>(adjacentCountryListForAttacker);
				panel.add(new JLabel("Attacking Country"));
				panel.add(defendercountry);
				defenderCountry = defendercountry.getSelectedItem().toString();
		    }
			//ArrayList of adjacent countries of the selected countries
			//{one: Two, Five , Two: Four Three}
			//TODO getAdjacentCountry(attackerCountry.getSelectedItem)
//			AttackPhase attack = new AttackPhase
//					(player, startup, attackerCountry.getSelectedItem().toString(),defenderCountry);
			JOptionPane.showConfirmDialog(null, panel, "Test",
		            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			
//			System.out.println(attackerCountry.getSelectedItem() + "  "+defendercountry.getSelectedItem());
		} else if (phaseActionButton.equalsIgnoreCase(GameConstant.FORTIFY_BUTTON_TITLE)) {
			System.out.println("Foritfy");
		}
	}
}