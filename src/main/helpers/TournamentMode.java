package main.helpers;

import main.models.GameModel;
import main.models.PlayerModel;
import main.utills.GameException;

import java.util.ArrayList;

public class TournamentMode {

    int turnCount;
    int gameCount;
    String mapFileName;
    ArrayList<String> playerTypes;

    public TournamentMode(int turnsCount, int gameCount, String mapFileName, ArrayList<String> playersTypes) {
        this.turnCount = turnsCount;
        this.gameCount = gameCount;
        this.mapFileName = mapFileName;
        this.playerTypes = playersTypes;
    }

    public void initMode() throws GameException {
        ArrayList<String> results = new ArrayList<>();

        for(int i=0; i <= this.gameCount; i++){
            GameHelper gameHelper = new GameHelper();
            GameModel gameModel = gameHelper.startNewGame(mapFileName, this.playerTypes);
            String result = this.playGame(gameModel);
            results.add(result);
        }
    }

    private String playGame(GameModel gameModel) {
        String result;
        for(int i = 0; i< this.turnCount; i++){
            for(PlayerModel player: gameModel.getPlayers()){
                System.out.println(player.getPlayerType());
                if(player.getPlayerType().toString().equals("AGGRESSIVE")){
                    aggressivePlay(player, gameModel);
                }
                if(player.getPlayerType().toString().equals("BENEVOLENT")){
                    benevolentPlay(player, gameModel);
                }
                if(player.getPlayerType().toString().equals("RANDOM")){
                    randomPlay(player, gameModel);
                }
                if(player.getPlayerType().toString().equals("CHEATER")){
                    cheaterPlay(player, gameModel);
                }
            }
        }

        return "AGGRESSIVE Player won";
    }

    private void cheaterPlay(PlayerModel playerModel, GameModel gameModel) {
        ReinforcementPhase reinforcementPhase = new ReinforcementPhase(playerModel, gameModel);
        playerModel.setArmyInHand(reinforcementPhase.getArmyUnitsFromCountries() + reinforcementPhase.getArmyUnitsForConqueredContinent());
        System.out.println(playerModel.getArmyInHand());
    }

    private void randomPlay(PlayerModel playerModel, GameModel gameModel) {
        ReinforcementPhase reinforcementPhase = new ReinforcementPhase(playerModel, gameModel);
        playerModel.setArmyInHand(reinforcementPhase.getArmyUnitsFromCountries() + reinforcementPhase.getArmyUnitsForConqueredContinent());
        System.out.println(playerModel.getArmyInHand());
    }

    private void benevolentPlay(PlayerModel playerModel, GameModel gameModel) {
        ReinforcementPhase reinforcementPhase = new ReinforcementPhase(playerModel, gameModel);
        playerModel.setArmyInHand(reinforcementPhase.getArmyUnitsFromCountries() + reinforcementPhase.getArmyUnitsForConqueredContinent());
        System.out.println(playerModel.getArmyInHand());
    }

    private void aggressivePlay(PlayerModel playerModel, GameModel gameModel) {
        ReinforcementPhase reinforcementPhase = new ReinforcementPhase(playerModel, gameModel);
        playerModel.setArmyInHand(reinforcementPhase.getArmyUnitsFromCountries() + reinforcementPhase.getArmyUnitsForConqueredContinent());
        System.out.println(playerModel.getArmyInHand());
        // TODO: Attack Player
        // TODO: Fortify
        // TODO: Check Player won
    }
}
