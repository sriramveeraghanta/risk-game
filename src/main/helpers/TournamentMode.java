package main.helpers;

import javafx.scene.control.Alert;
import main.models.CountryModel;
import main.models.GameModel;
import main.models.PlayerModel;
import main.utills.GameCommon;
import main.utills.GameException;

import java.util.*;

public class TournamentMode {

    int turnCount;
    int gameCount;
    String mapFileName;
    ArrayList<String> playerTypes;
    String gameResult;

    public TournamentMode(int turnsCount, int gameCount, String mapFileName, ArrayList<String> playersTypes) {
        this.turnCount = turnsCount;
        this.gameCount = gameCount;
        this.mapFileName = mapFileName;
        this.playerTypes = playersTypes;
    }

    public void initMode() throws GameException {
        ArrayList<String> results = new ArrayList<>();

        for(int i=0; i <this.gameCount; i++){
            GameHelper gameHelper = new GameHelper();
            GameModel gameModel = gameHelper.startNewGame(mapFileName, this.playerTypes);
            String result = this.playGame(gameModel);
            results.add(result);
        }
        for(String result :results){
            System.out.println("result ----:"+result);

        }

        if(results.size()>0){
            for(String result :results) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");

                alert.setHeaderText("Result :" + result);
                alert.showAndWait();
            }
        }
    }

    private String playGame(GameModel gameModel) {
        String result="draw";
        for(int i = 0; i< this.turnCount; i++){
            System.out.println("Turn Count :-------------------"+i);
            for(PlayerModel player: gameModel.getPlayers()){
                System.out.println(player.getPlayerType());
                if(player.getPlayerType().toString().equals("AGGRESSIVE")){
                    System.out.println("Aggreassive mode Start");
                    aggressivePlay(player, gameModel);
                    System.out.println("Aggreassive mode Stop:");
                    if(player.getCountries().size() == gameModel.getCountries().size()){
                        return "Aggresive Player Won" ;
                    }

                }
                if(player.getPlayerType().toString().equals("BENEVOLENT")){
                    System.out.println("Benevolent mode Start");
                    benevolentPlay(player, gameModel);
                    System.out.println("Benevolent mode Stop");
                    if(player.getCountries().size() == gameModel.getCountries().size()){
                        return "BENEVOLENT Player Won" ;
                    }
                }
                if(player.getPlayerType().toString().equals("RANDOM")){
                    System.out.println("Random mode Start");
                    randomPlay(player, gameModel);
                    System.out.println("Random mode Stop");
                    if(player.getCountries().size() == gameModel.getCountries().size()){
                        return "RAndom Player Won" ;
                    }
                }
                if(player.getPlayerType().toString().equals("CHEATER")){
                    System.out.println("Cheater mode Start");
                    cheaterPlay(player, gameModel);
                    System.out.println("Cheater mode Stop");
                    if(player.getCountries().size() == gameModel.getCountries().size()){
                        return "CheaterPlayer Won" ;
                    }
                }
            }
        }

        return result;
    }

    private void cheaterPlay(PlayerModel playerModel, GameModel gameModel) {
        if(playerModel.getCountries().size() >0 ) {
            ///Reinfocement phase
            System.out.println("Reinforce --------------Start");
            ReinforcementPhase reinforcementPhase = new ReinforcementPhase(playerModel, gameModel);
            ///Setting army in player hand
            playerModel.setArmyInHand(reinforcementPhase.getArmyUnitsFromCountries() + reinforcementPhase.getArmyUnitsFromCountries() + reinforcementPhase.getArmyUnitsForConqueredContinent());
            ArrayList<CountryModel> countriesList = playerModel.getCountries();
            //doubling the army count in country
            if(countriesList.size() >0) {
                for (CountryModel country : countriesList) {
                    country.setArmyInCountry(country.getArmyInCountry() * 2);
                }
            }
            System.out.println("Reinforce --------------END");
            ///Attack phase

            System.out.println("Attack --------------Start");
            GameHelper gameHelper = new GameHelper();
            ///getting enemy adjacent countries
            Iterator<CountryModel> currentPlayerCountryList = countriesList.iterator();

            AttackPhase attack;
            ArrayList<PlayerModel> defender=new ArrayList<PlayerModel>();
            ArrayList<CountryModel> defendingCountryList=new ArrayList<CountryModel>();
            if (countriesList.size() != 0) {
                while(currentPlayerCountryList.hasNext()){
                      CountryModel country=currentPlayerCountryList.next();
                    ArrayList<CountryModel> defendingCountriesList = gameHelper.getAttackerAdjacentCounties(country.getAdjacentCountries(), playerModel.getCountries());
                    if (defendingCountriesList.size() != 0) {
                        Iterator<CountryModel> defendingCountryModel = defendingCountriesList.iterator();
                        while(defendingCountryModel.hasNext()){
                            CountryModel defendCountry=defendingCountryModel.next();
                             attack = new AttackPhase(gameModel, country, defendCountry);
                          PlayerModel defendingPlayer = attack.getDefender(defendCountry);
                          if(!defender.contains(defendingPlayer)){
                              defender.add(defendingPlayer);
                          }
                            defendingCountryList.add(defendCountry);
                             attack.setTournamentModeFlag(true);

                        }
                    }
                }
            }

            if(defender.size()>0) {
                for(PlayerModel player : defender){
                    if(defendingCountryList.size()>0){
                        for (CountryModel country : defendingCountryList){
                            if(player.getCountries().contains(country)){
                                player.getCountries().remove(country);
                                countriesList.add(country);
                            }
                        }
                    }

                }
            }
            System.out.println("Attack --------------end");

            System.out.println("Fortify--------------Start");
            ///doubling the army count
            if (playerModel.getCountries().size() != 0) {
                for (CountryModel country : playerModel.getCountries()) {

                    ArrayList<CountryModel> defendingCountriesList = gameHelper.getAttackerAdjacentCounties(country.getAdjacentCountries(), playerModel.getCountries());
                    if (defendingCountriesList.size() != 0) {
                        country.setArmyInCountry(country.getArmyInCountry()*2);
                    }
                }
            }


                System.out.println("Fortify--------------END");

        }
        GameHelper gameHelper=new GameHelper();
        gameHelper.switchPlayerControl(gameModel);
    }

    private void randomPlay(PlayerModel playerModel, GameModel gameModel) {
        if(playerModel.getCountries().size() >0 ) {
            ///Reinfocement phase
            System.out.println("Reinforce --------------Start");
            ReinforcementPhase reinforcementPhase = new ReinforcementPhase(playerModel, gameModel);
            ///Setting army in player hand
            playerModel.setArmyInHand(reinforcementPhase.getArmyUnitsFromCountries() + reinforcementPhase.getArmyUnitsFromCountries() + reinforcementPhase.getArmyUnitsForConqueredContinent());
            List<CountryModel> countriesList = playerModel.getCountries();
            CountryModel reinforceCountry = null;
            if(playerModel.getDeck().size()>=3){
                reinforcementPhase.setUpdatePlayerDeck(true);
                int armyCount= reinforcementPhase.swapCardsForArmyUnits(playerModel.getDeck());
                playerModel.setArmyInHand(playerModel.getArmyInHand()+armyCount);
            }

            ///selecting  country in random way
            int countryIndex =randomNumberGenerator(playerModel.getCountries());


            reinforcementPhase.assignArmyUnitToCountry(playerModel.getCountries().get(countryIndex), playerModel.getArmyInHand());

            System.out.println("Reinforce --------------END");
            ///Attack phase
            GameCommon gameCommons = new GameCommon();
            GameHelper gameHelper = new GameHelper();
            for(int i=0 ;i<=countryIndex ; i++) {

                System.out.println("Attack --------------Start");


                int attackerMaxDice = 0, defenderMaxDice = 0, minDice = 1;
                ;
                ///selecting  country in random way
                int attackingCountryIndex = randomNumberGenerator(playerModel.getCountries());
                ArrayList<CountryModel> defendingCountriesList = gameHelper.getAttackerAdjacentCounties(playerModel.getCountries().get(attackingCountryIndex).getAdjacentCountries(), playerModel.getCountries());
                if (defendingCountriesList.size() > 0 && playerModel.getCountries().get(attackingCountryIndex).getArmyInCountry() >= 2) {
                    int defendingCountryIndex = randomNumberGenerator(defendingCountriesList);
                    CountryModel attackingCountryModel = playerModel.getCountries().get(attackingCountryIndex);
                    CountryModel defendingCountryModel = defendingCountriesList.get(defendingCountryIndex);
                    if (attackingCountryModel.getArmyInCountry() > 3) {
                        attackerMaxDice = 3;
                        defenderMaxDice = 2;
                    } else {
                        attackerMaxDice = attackingCountryModel.getArmyInCountry() - 1;
                        defenderMaxDice = defendingCountryModel.getArmyInCountry();
                    }
                    String defendingCountryName = defendingCountryModel.getCountryName();
                    AttackPhase attackPhase = new AttackPhase(gameModel, attackingCountryModel, defendingCountryModel);
                    attackPhase.setTournamentModeFlag(true);
                    int attackerDices = attackPhase.diceCount(attackerMaxDice, minDice);
                    int defenderDices = attackPhase.diceCount(defenderMaxDice, minDice);
                    attackPhase.attackCountry(attackerDices, defenderDices);
                    CountryModel defender = gameCommons.getCountryModelFromList(playerModel.getCountries(), defendingCountryName);
                    if (defender != null) {
                        attackPhase.swapArmyBetweenCountries(attackerDices);
                    }
                    attackPhase.setTournamentModeFlag(false);
                }
            }

            System.out.println("Fortify--------------Start");

            int toCountryIndex=randomNumberGenerator(playerModel.getCountries());
            ArrayList<CountryModel> adjacentOwnedCountriesList = gameCommons.getPlayerOwnedAdjcentCountires(playerModel.getCountries().get(toCountryIndex).getAdjacentCountries(),playerModel.getCountries());
           if(adjacentOwnedCountriesList.size() !=0) {
               int fromCountryIndex=randomNumberGenerator(adjacentOwnedCountriesList);
               FortificationPhase fortificationPhase = new FortificationPhase(gameModel, playerModel);
               fortificationPhase.setTournamentModeFlag(true);
               if (adjacentOwnedCountriesList.get(fromCountryIndex).getArmyInCountry() >= 2) {
                   fortificationPhase.swapArmyUnitsBetweenCountries(adjacentOwnedCountriesList.get(fromCountryIndex), playerModel.getCountries().get(toCountryIndex), adjacentOwnedCountriesList.get(fromCountryIndex).getArmyInCountry() - 1);
               }
               fortificationPhase.setTournamentModeFlag(false);
           }

            System.out.println("Fortify--------------End");
        }
        GameHelper gameHelper=new GameHelper();
        gameHelper.switchPlayerControl(gameModel);
    }



    public int randomNumberGenerator(ArrayList<CountryModel> CountryModel) {
        Random random = new Random();
        int countryIndex = random.nextInt((CountryModel.size() - 1) + 1);
        if (countryIndex >= CountryModel.size() && CountryModel.size() != 0) {
            countryIndex = CountryModel.size()-1;

        }
        return countryIndex;

    }

    private void benevolentPlay(PlayerModel playerModel, GameModel gameModel) {
        if(playerModel.getCountries().size()>0) {
            ///Reinfocement phase
            System.out.println(" Reinforce --------------Start");
            ReinforcementPhase reinforcementPhase = new ReinforcementPhase(playerModel, gameModel);
            playerModel.setArmyInHand(reinforcementPhase.getArmyUnitsFromCountries() + reinforcementPhase.getArmyUnitsFromCountries() + reinforcementPhase.getArmyUnitsForConqueredContinent());
            reinforcementPhase.setTournamentModeFlag(true);
          if(playerModel.getDeck().size()>=3){
              reinforcementPhase.setUpdatePlayerDeck(true);
             int armyCount= reinforcementPhase.swapCardsForArmyUnits(playerModel.getDeck());
             playerModel.setArmyInHand(playerModel.getArmyInHand()+armyCount);
          }
            List<CountryModel> countriesList = playerModel.getCountries();
            CountryModel reinforceCountry = null;
            ArrayList<Integer> units = new ArrayList<Integer>();
            for (CountryModel country : countriesList) {
                units.add(country.getArmyInCountry());
            }
            Collections.sort(units);
            ///getting the weakest country
            reinforceCountry= countriesList.stream()
                    .filter(countryModel -> countryModel.getArmyInCountry()==(units.get(0)))
                    .findFirst().get();
            if(reinforceCountry!=null){
                reinforcementPhase.assignArmyUnitToCountry(reinforceCountry, playerModel.getArmyInHand());
            }
          /*  for (CountryModel country : countriesList) {
                if (country.getArmyInCountry() == units.get(0)) {

                    reinforceCountry = country;
                    reinforcementPhase.assignArmyUnitToCountry(reinforceCountry, playerModel.getArmyInHand());
                    break;
                }
            }*/
            System.out.println(" Reinforce --------------Stop");
            GameCommon gameCommons = new GameCommon();
            System.out.println(" fortify --------------Start");
            units.clear();
            for (CountryModel country : playerModel.getCountries()) {
                units.add(country.getArmyInCountry());
            }
            CountryModel fortifyCountry = null, adjacentCountryToGetArmy = null;
            Collections.sort(units);
            fortifyCountry= countriesList.stream()
                    .filter(countryModel -> countryModel.getArmyInCountry()==(units.get(0)))
                    .findFirst().get();
         /*   for (CountryModel country : countriesList) {
                if (country.getArmyInCountry() == units.get(0)) {
                    fortifyCountry = country;
                    break;
                }
            }*/
            ArrayList<CountryModel> adjacentOwnedCountriesList = gameCommons.getPlayerOwnedAdjcentCountires(fortifyCountry.getAdjacentCountries(), playerModel.getCountries());
            units.clear();
            if (adjacentOwnedCountriesList.size() > 0) {
                for (CountryModel country : adjacentOwnedCountriesList) {
                    units.add(country.getArmyInCountry());
                }


                Collections.sort(units, Collections.reverseOrder());
                adjacentCountryToGetArmy= adjacentOwnedCountriesList.stream()
                        .filter(countryModel -> countryModel.getArmyInCountry()==(units.get(0)))
                        .findFirst().get();
              /*  for (CountryModel country : adjacentOwnedCountriesList) {
                    if (country.getArmyInCountry() == units.get(0)) {
                        adjacentCountryToGetArmy = country;
                        break;
                    }
                }*/
                FortificationPhase fortificationPhase = new FortificationPhase(gameModel, playerModel);
                if (adjacentCountryToGetArmy.getArmyInCountry() >= 2) {
                    fortificationPhase.swapArmyUnitsBetweenCountries(adjacentCountryToGetArmy, fortifyCountry, adjacentCountryToGetArmy.getArmyInCountry() - 1);
                }
            }
            System.out.println(" fortify --------------Stop");
        }
        GameHelper gameHelper=new GameHelper();
       gameHelper.switchPlayerControl(gameModel);
    }
  /*  public PlayerModel getDefender(CountryModel defendingCountry,GameModel gameModel,PlayerModel playerModel) {

        for (PlayerModel player : gameModel.getPlayers()) {
            System.out.println("player model detail:"+player.getColor().toString());
            if (player.getColor() != playerModel.getColor()) {
                List<CountryModel> countries = player.getCountries();
                for(CountryModel cou:player.getCountries()){
                    System.out.println("country name:"+cou.getCountryName());
                }
                if (countries.contains(defendingCountry)) {
                    return player;
                }
            }
        }
        return null;
    }*/

    private void aggressivePlay(PlayerModel playerModel, GameModel gameModel) {
        if(playerModel.getCountries().size() >0 ) {
            ///Reinfocement phase
            System.out.println("Reinforce --------------Start");
            ReinforcementPhase reinforcementPhase = new ReinforcementPhase(playerModel, gameModel);
            ///Setting army in player hand
            playerModel.setArmyInHand(reinforcementPhase.getArmyUnitsFromCountries() + reinforcementPhase.getArmyUnitsFromCountries() + reinforcementPhase.getArmyUnitsForConqueredContinent());
            List<CountryModel> countriesList = playerModel.getCountries();
            CountryModel reinforceCountry = null;
            if(playerModel.getDeck().size()>=3){
                reinforcementPhase.setUpdatePlayerDeck(true);
                int armyCount= reinforcementPhase.swapCardsForArmyUnits(playerModel.getDeck());
                playerModel.setArmyInHand(playerModel.getArmyInHand()+armyCount);
            }
            ArrayList<Integer> units = new ArrayList<Integer>();
            for (CountryModel country : countriesList) {
                units.add(country.getArmyInCountry());
            }
            Collections.sort(units, Collections.reverseOrder());
            //getting the strongest country
            reinforceCountry= countriesList.stream()
                    .filter(countryModel -> countryModel.getArmyInCountry()==(units.get(0)))
                    .findFirst().get();

            if(reinforcementPhase!=null){
                reinforcementPhase.assignArmyUnitToCountry(reinforceCountry, playerModel.getArmyInHand());
            }
        /*    System.out.println("Stream check:"+coun.getCountryName());
            for (CountryModel country : countriesList) {
                if (country.getArmyInCountry() == units.get(0)) {
                    ///getting strong country
                    reinforceCountry = country;
                    reinforcementPhase.assignArmyUnitToCountry(reinforceCountry, playerModel.getArmyInHand());
                    break;
                }
            }*/
            System.out.println("Reinforce --------------END");
            ///Attack phase
            GameCommon gameCommons = new GameCommon();
            System.out.println("Attack --------------Start");
            GameHelper gameHelper = new GameHelper();
            System.out.println("reinfore adjacent country list size:" + reinforceCountry.getAdjacentCountries().size());
            ///getting enemy adjacent countries
            ArrayList<CountryModel> defendingCountriesList = gameHelper.getAttackerAdjacentCounties(reinforceCountry.getAdjacentCountries(), playerModel.getCountries());
            System.out.println("Defending Country List"  + defendingCountriesList.size());
            if (defendingCountriesList.size() != 0) {
                System.out.println("Defending country list size:" + defendingCountriesList.size());
                Random rand = new Random();
                ///selecting defending country in random way
                int countryIndex = rand.nextInt((defendingCountriesList.size() - 1) + 1);
                if (countryIndex >= defendingCountriesList.size() && defendingCountriesList.size() != 0) {
                    countryIndex = 0;

                }
                AttackPhase attackPhase = new AttackPhase(gameModel, reinforceCountry, defendingCountriesList.get(countryIndex));
                String defendingCountyName = defendingCountriesList.get(countryIndex).getCountryName();
                System.out.println("Defending Country name in attack phase:" + defendingCountyName);
                boolean attackEligible = true;

                attackPhase.setTournamentModeFlag(true);
                while (attackEligible) {
                    CountryModel defender = gameCommons.getCountryModelFromList(playerModel.getCountries(), defendingCountyName);

                    ///defendingCountriesList = gameHelper.getAttackerAdjacentCounties(reinforceCountry.getAdjacentCountries(), playerModel.getCountries());
                    if (reinforceCountry.getArmyInCountry() >= 2 && defender == null) {
                        int diceCount = attackPhase.allOutMode();
                        defender = gameCommons.getCountryModelFromList(playerModel.getCountries(), defendingCountyName);
                       ///if attacker conquered the defending country swaping army
                        if (defender != null) {
                            attackPhase.swapArmyBetweenCountries(diceCount);
                        }
                    } else {
                        attackEligible = false;
                    }

                }
                attackPhase.setTournamentModeFlag(false);
            }
            System.out.println("Attack --------------end");
            units.clear();
            System.out.println("Fortify--------------Start");

            for (CountryModel country : playerModel.getCountries()) {
                units.add(country.getArmyInCountry());
            }
            CountryModel fortifyCountry = null, adjacentCountryToGetArmy = null;
            Collections.sort(units, Collections.reverseOrder());
            fortifyCountry= countriesList.stream()
                    .filter(countryModel -> countryModel.getArmyInCountry()==(units.get(0)))
                    .findFirst().get();
           /* for (CountryModel country : countriesList) {
                if (country.getArmyInCountry() == units.get(0)) {
                    fortifyCountry = country;
                    break;
                }
            }*/
            ArrayList<CountryModel> adjacentOwnedCountriesList = gameCommons.getPlayerOwnedAdjcentCountires(fortifyCountry.getAdjacentCountries(), playerModel.getCountries());
            units.clear();
            if (adjacentOwnedCountriesList.size() != 0) {
                for (CountryModel country : adjacentOwnedCountriesList) {
                    units.add(country.getArmyInCountry());
                }

                Collections.sort(units, Collections.reverseOrder());

                adjacentCountryToGetArmy= adjacentOwnedCountriesList.stream()
                        .filter(countryModel -> countryModel.getArmyInCountry()==(units.get(0)))
                        .findFirst().get();
               /* for (CountryModel country : adjacentOwnedCountriesList) {
                    if (country.getArmyInCountry() == units.get(0)) {
                        adjacentCountryToGetArmy = country;
                        break;
                    }
                }*/
                FortificationPhase fortificationPhase = new FortificationPhase(gameModel, playerModel);
                if (adjacentCountryToGetArmy.getArmyInCountry() >= 2) {
                    fortificationPhase.swapArmyUnitsBetweenCountries(adjacentCountryToGetArmy, fortifyCountry, adjacentCountryToGetArmy.getArmyInCountry() - 1);
                }
                System.out.println("Fortify--------------END");
            }
        }
        GameHelper gameHelper=new GameHelper();
        gameHelper.switchPlayerControl(gameModel);
    }
}
