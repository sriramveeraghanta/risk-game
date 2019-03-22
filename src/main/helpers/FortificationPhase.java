package main.helpers;

import main.models.CountryModel;
import main.models.PlayerModel;
import main.models.UnitModel;
import main.utills.EnumHandler;

import java.util.List;

public class FortificationPhase {
    private PlayerModel playerModel;
    /**
     * Constructor which get player model as parameter
     * @param playerModel
     */
    public FortificationPhase(PlayerModel playerModel) {

        this.setPlayerModel(playerModel);
    }

    /**
     * getting the player model and return it
     * @return the playerModel
     */
    public PlayerModel getPlayerModel() {
        return playerModel;
    }

    /**
     * setting the player model
     * @param playerModel the playerModel to set
     */
    public void setPlayerModel(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }
    /**
     * swapping the particular number of armies between the countries the player owns for fortification and checking all the countries the
     * player own should have at least one army and countries should be adjacent
     * @param fromCountryName
     * @param toCountryName
     * @param numberOfArmyUnits
     * @return if swapping is done successfully or not
     */
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
            UnitModel soldier = units.stream().filter(a -> a.getType().equals(EnumHandler.UnitType.INFANTRY)).findFirst()
                    .get();
            soldier.setUnitNumber((fromCountry.getNumberOfUnits() - numberOfArmyUnits));

            // to Country
            units = toCountry.getArmyInCountry();
            soldier = units.stream().filter(a -> a.getType().equals(EnumHandler.UnitType.INFANTRY)).findFirst().get();
            soldier.setUnitNumber((toCountry.getNumberOfUnits() + numberOfArmyUnits));
            return "Operation Completed";
        }

    }

}
