package controllers;

import models.PlayerModel;
import models.UnitModel;
import utils.EnumClass;

import java.util.ArrayList;
import java.util.List;

import models.CountryModel;

public class FortificationPhase {

	private PlayerModel playerModel;

	public FortificationPhase(PlayerModel playerModel) {

		this.setPlayerModel(playerModel);
	}

	/**
	 * @return the playerModel
	 */
	public PlayerModel getPlayerModel() {
		return playerModel;
	}

	/**
	 * @param playerModel the playerModel to set
	 */
	public void setPlayerModel(PlayerModel playerModel) {
		this.playerModel = playerModel;
	}

	public String swapArmyUnitsBetweenCountries(String fromCountryName, String toCountryName, int numberOfArmyUnits) {
		List<CountryModel> countries = this.getPlayerModel().getCountries();

		CountryModel fromCountry = countries.stream().filter(c -> c.getCountryName().contentEquals(fromCountryName))
				.findFirst().get();
		CountryModel toCountry = countries.stream().filter(c -> c.getCountryName().contentEquals(toCountryName))
				.findFirst().get();

		if ((fromCountry.getNumberOfUnits() < 2) || ((fromCountry.getNumberOfUnits() - numberOfArmyUnits) < 1)) {
			return "Invalid Operation";
		} else {
			/// from Country;
			List<UnitModel> units = fromCountry.getArmyInCountry();
			UnitModel soldier = units.stream().filter(a -> a.getType().equals(EnumClass.UnitType.INFANTRY)).findFirst()
					.get();
			soldier.setUnitNumber((fromCountry.getNumberOfUnits() - numberOfArmyUnits));

			// to Country
			units = toCountry.getArmyInCountry();
			soldier = units.stream().filter(a -> a.getType().equals(EnumClass.UnitType.INFANTRY)).findFirst().get();
			soldier.setUnitNumber((toCountry.getNumberOfUnits() + numberOfArmyUnits));
			return "Operation Completed";
		}

	}

}
