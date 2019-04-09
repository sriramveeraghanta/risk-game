package main.helpers;

import main.models.GameModel;
import main.models.PlayerModel;
import main.utills.DialogHandler;
import main.utills.GameConstants;
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
        for(int i=0; i <= this.gameCount; i++){
            GameHelper gameHelper = new GameHelper();
            gameHelper.startNewGame(mapFileName, this.playerTypes);
        }
    }
}
