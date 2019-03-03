package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import utils.GameConstant;

public class action implements ActionListener {

	public action() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String phaseActionButton = e.getActionCommand();
		if (phaseActionButton.equalsIgnoreCase(GameConstant.REINFORCEMENT_BUTTON_TITLE)) {
			System.out.println("Reinforce");
		} else if (phaseActionButton.equalsIgnoreCase(GameConstant.ATTACK_BUTTON_TITLE)) {
			System.out.println("Attack");
		} else if (phaseActionButton.equalsIgnoreCase(GameConstant.FORTIFY_BUTTON_TITLE)) {
			System.out.println("Foritfy");
		}
	}
}
