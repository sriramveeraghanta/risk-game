package main.helpers;

import main.models.CountryModel;
import main.models.GameModel;
import main.models.PlayerModel;
import main.utills.GameConstants;

import java.util.List;

/**
 * This class contains all of the fortification phase method
 */
public class FortificationPhase {
    private PlayerModel playerModel;
    private GameModel gameModel;


    /**
     * Constructor which get player model as parameter
     * @param playerModel an object of player model
     */
    public FortificationPhase(GameModel gameModel,PlayerModel playerModel)
    {   this.setGameModel(gameModel);
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
     * @param fromCountryModel Country Model from which we are changing
     * @param toCountryModel country Model to which we are changing the army
     * @param numberOfArmyUnits number of units we are trying to place
     * @return if swapping is done successfully or not
     */
    public String swapArmyUnitsBetweenCountries(CountryModel fromCountryModel,CountryModel toCountryModel, int numberOfArmyUnits) {
        List<CountryModel> countries = this.getPlayerModel().getCountries();
        CountryModel fromCountry = fromCountryModel;
        CountryModel toCountry = toCountryModel;

        if ((fromCountry.getArmyInCountry() < 2) || ((fromCountry.getArmyInCountry() - numberOfArmyUnits) <1)) {
            return GameConstants.FORTIFY_INVALID_MSG;
        } else {
            fromCountry.setArmyInCountry((fromCountry.getArmyInCountry() - numberOfArmyUnits));
            toCountry.setArmyInCountry((toCountry.getArmyInCountry() + numberOfArmyUnits));
            getGameModel().fortify();
            return GameConstants.FORTIFY_VALID_MSG;
        }

   }

    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

}
