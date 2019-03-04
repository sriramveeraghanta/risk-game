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
import controllers.FortificationPhase;
import controllers.StartUp;
import models.CountryModel;
import models.GameModel;
import models.PlayerModel;
import models.UnitModel;
import utils.EnumClass;
import utils.GameConstant;

/**
 * Action clas is used by classes in View package, for the following game
 * funnctionality : Reinforcement, Attack, and Fortify action.java is called
 * from GameBoardSideBar.java
 *
 */
public class action implements ActionListener {

	private StartUp startup;
	private PlayerModel player;
	private GameModel gameModel;
	private CountryModel countryModel;

	public action(PlayerModel player, GameModel gameModel, StartUp startup) {
		this.startup = startup;
		this.player = player;
		this.gameModel = gameModel;
	}

	public action() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel(new GridLayout(0, 1));
		String phaseActionButton = e.getActionCommand();

		// Retreiving a players owned country
		/**
		 * @param countryListOfPlayer country array hold by player
		 */
		ArrayList<String> countryName = new ArrayList<>();
		ArrayList<CountryModel> countryModelListOfPlayer = player.getCountries();
		for (CountryModel country : countryModelListOfPlayer)
			countryName.add(country.getCountryName());
		String[] countryListOfPlayer = countryName.toArray(new String[countryName.size()]);

		// Checking phase action button event
		if (phaseActionButton.equalsIgnoreCase(GameConstant.REINFORCEMENT_BUTTON_TITLE)) {
			// TODO card swapping to implement
			ReinforcementPhase reinforce = new ReinforcementPhase(player, gameModel);
			int initialArmy = player.getNumberOfArmyUnitOnHand();
			JComboBox<String> playerCountryList = new JComboBox<>(countryListOfPlayer);

			/**
			 * Logic for displaying data in dialogue box
			 */
			while (initialArmy > 0) {
				JTextField noOfArmyToPlaceInCountry = new JTextField();
				JTextField noOfArmyLeft = new JTextField("Army left : " + initialArmy);
				panel.add(new JLabel("Country List"));
				panel.add(playerCountryList);
				panel.add(new JLabel("No of army you want to place"));
				panel.add(noOfArmyToPlaceInCountry);
				panel.add(noOfArmyLeft);
				JOptionPane.showConfirmDialog(null, panel, "GameConstant.REINFORCEMENT_BUTTON_TITLE",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				reinforce.assignArmyUnitToCountry(playerCountryList.getSelectedItem().toString(),
						Integer.parseInt(noOfArmyToPlaceInCountry.toString()));
				initialArmy = initialArmy - (Integer.parseInt(noOfArmyToPlaceInCountry.toString()));
			}
		} else if (phaseActionButton.equalsIgnoreCase(GameConstant.ATTACK_BUTTON_TITLE)) {
			// TODO dice roll needs to be implemented
			String defenderCountry = null;
			JComboBox<String> attackerCountry = new JComboBox<>(countryListOfPlayer);
			panel.add(new JLabel("Attacker Country"));
			panel.add(attackerCountry);
			ArrayList<String> adjacentCountryList = new ArrayList<>();
			/// getting attacking country name
			int result = JOptionPane.showConfirmDialog(null, panel, GameConstant.ATTACK_BUTTON_TITLE,
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (result == JOptionPane.OK_OPTION) {
				countryModel = gameModel.getCountries().stream()
						.filter(c -> c.getCountryName().contentEquals(attackerCountry.getSelectedItem().toString()))
						.findFirst().get();
				for (CountryModel country : countryModel.getAdjcentCountries()) {
					if (!countryName.contains(country.getCountryName()))
						adjacentCountryList.add(country.getCountryName());
				}

				/// Displaying the adjacent non-owned countries of player for attacking
				String[] adjacentCountryListForAttacker = adjacentCountryList
						.toArray(new String[adjacentCountryList.size()]);
				JComboBox<String> defendercountry = new JComboBox<>(adjacentCountryListForAttacker);
				panel.add(new JLabel("Attacking Country"));
				panel.add(defendercountry);
				defenderCountry = defendercountry.getSelectedItem().toString();
			}
			JOptionPane.showConfirmDialog(null, panel, GameConstant.ATTACK_BUTTON_TITLE, JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE);
			AttackPhase attack = new AttackPhase(player, startup, attackerCountry.getSelectedItem().toString(),
					defenderCountry);
			adjacentCountryList.clear();
		} else if (phaseActionButton.equalsIgnoreCase(GameConstant.FORTIFY_BUTTON_TITLE)) {
			String AdjacentCountry = null;
			int armyValue = 0;
			JComboBox<String> attackerCountry = new JComboBox<>(countryListOfPlayer);
			panel.add(new JLabel("Attacker Country"));
			panel.add(attackerCountry);
			ArrayList<String> adjacentCountryList = new ArrayList<>();
			/// getting th country name of player from where fortify needs to be done
			int result = JOptionPane.showConfirmDialog(null, panel, GameConstant.ATTACK_BUTTON_TITLE,
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (result == JOptionPane.OK_OPTION) {
				countryModel = gameModel.getCountries().stream()
						.filter(c -> c.getCountryName().contentEquals(attackerCountry.getSelectedItem().toString()))
						.findFirst().get();
				List<UnitModel> units = countryModel.getArmyInCountry();
				UnitModel soldier = units.stream().filter(a -> a.getType().equals(EnumClass.UnitType.INFANTRY))
						.findFirst().get();
				int armyCount = soldier.getUnitNumber();
				JTextField noOfArmyInCountry = new JTextField(armyCount);
				for (CountryModel country : countryModel.getAdjcentCountries()) {
					if (countryName.contains(country.getCountryName()))
						adjacentCountryList.add(country.getCountryName());
				}
				String[] adjacentCountryListForFortification = adjacentCountryList
						.toArray(new String[adjacentCountryList.size()]);
				/// displaying the adjacent countries of player

				JComboBox<String> playerOwnAdjacentCountry = new JComboBox<>(adjacentCountryListForFortification);
				panel.add(new JLabel("Army count should be less than the army available"));
				panel.add(noOfArmyInCountry);
				panel.add(new JLabel("Adjacent Country"));
				panel.add(playerOwnAdjacentCountry);
				AdjacentCountry = playerOwnAdjacentCountry.getSelectedItem().toString();
				armyValue = Integer.parseInt(noOfArmyInCountry.toString());
			}
			JOptionPane.showConfirmDialog(null, panel, "GameConstant.FORTIFY_BUTTON_TITLE",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			FortificationPhase fortify = new FortificationPhase(player);
			fortify.swapArmyUnitsBetweenCountries(attackerCountry.getSelectedItem().toString(), AdjacentCountry,
					armyValue);
		}
	}
}