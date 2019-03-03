package action;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.CountryModel;
import models.PlayerModel;
import utils.GameConstant;

public class action implements ActionListener {

	public action() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String phaseActionButton = e.getActionCommand();
		if (phaseActionButton.equalsIgnoreCase(GameConstant.REINFORCEMENT_BUTTON_TITLE)) {
			//TODO country list if required
//			ArrayList<String> countryListOfAttacker = new ArrayList<>();
////			JTextField activePlayer = new JTextField("Active Player" +);
////			ArrayList<CountryModel> countryListOfAttacker = 
//			for(CountryModel country : activePlayer.getCountries())
//				    countryListOfAttacker.add(country.getCountryName());
//			countryListOfAttacker.toArray(new String[countryListOfAttacker.size()]);
			String[] countryListOfAttacker = {"One", "Two"};
			JComboBox<String> attackerCountry = new JComboBox<>(countryListOfAttacker);
			//TODO armies attacker wants to place
			String[] countryListToAttack = {"Three", "Four"};
			JComboBox<String> attackingCountry = new JComboBox<>(countryListToAttack);
			JPanel panel = new JPanel(new GridLayout(0, 1));
			panel.add(new JLabel("Attacker Country"));
			panel.add(attackerCountry);
			panel.add(new JLabel("Attacking Country"));
			panel.add(attackingCountry);
			JOptionPane.showConfirmDialog(null, panel, "Test",
		            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			
			System.out.println(attackerCountry.getSelectedItem() + "  "+attackingCountry.getSelectedItem());
		} else if (phaseActionButton.equalsIgnoreCase(GameConstant.ATTACK_BUTTON_TITLE)) {
			String[] countryListOfAttacker = {"five", "six"};
			JComboBox<String> attackerCountry = new JComboBox<>(countryListOfAttacker);
			//TODO remove attacker country from attacking list
			String[] countryListToAttack = {"seven", "eight"};
			JComboBox<String> attackingCountry = new JComboBox<>(countryListToAttack);
			JPanel panel = new JPanel(new GridLayout(0, 1));
			panel.add(new JLabel("Attacker Country"));
			panel.add(attackerCountry);
			panel.add(new JLabel("Attacking Country"));
			panel.add(attackingCountry);
			JOptionPane.showConfirmDialog(null, panel, "Test",
		            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			
			System.out.println(attackerCountry.getSelectedItem() + "  "+attackingCountry.getSelectedItem());
		} else if (phaseActionButton.equalsIgnoreCase(GameConstant.FORTIFY_BUTTON_TITLE)) {
			System.out.println("Foritfy");
		}
	}
}