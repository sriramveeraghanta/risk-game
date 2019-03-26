package main.helpers;

import main.models.CountryModel;
import main.models.PlayerModel;

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
//    public String swapArmyUnitsBetweenCountries(String fromCountryName, String toCountryName, int numberOfArmyUnits) {
//        List<CountryModel> countries = this.getPlayerModel().getCountries();
//        for (CountryModel country1 : countries) {
//            System.out.println("countryName:" + country1.getCountryName());
//            List<UnitModel> unitss = new ArrayList<UnitModel>();
//            unitss = country1.getArmyInCountry();
//            for (UnitModel uni : unitss) {
//                System.out.println("armyUnit:" + uni.getType());
//            }
//        }
//        CountryModel fromCountry = countries.stream().filter(c -> c.getCountryName().contentEquals(fromCountryName))
//                .findFirst().get();
//        CountryModel toCountry = countries.stream().filter(c -> c.getCountryName().contentEquals(toCountryName))
//                .findFirst().get();
//
//        if ((fromCountry.getNumberOfUnits() < 2) || ((fromCountry.getNumberOfUnits() - numberOfArmyUnits) < 1)) {
//            return "Invalid Operation";
//        } else {
//            /// from Country;
//            List<UnitModel> units = fromCountry.getArmyInCountry();
//            System.out.println("Unit Size:" + units.size());
//            UnitModel soldier = units.stream().filter(a -> a.getType().equals(EnumHandler.CardType.INFANTRY)).findFirst()
//                    .get();
//            soldier.setUnitNumber((fromCountry.getNumberOfUnits() - numberOfArmyUnits));
//
//            // to Country
//            units = toCountry.getArmyInCountry();
//            soldier = units.stream().filter(a -> a.getType().equals(EnumHandler.CardType.INFANTRY)).findFirst().get();
//            soldier.setUnitNumber((toCountry.getNumberOfUnits() + numberOfArmyUnits));
//            return "Operation Completed";
//        }
//
//    }

}
