package main.controllers;

import main.utills.GameCommon;
import main.utills.GameConstants;

import java.io.IOException;
import java.util.ArrayList;

public class LoadGameController {

    private ArrayList<String> fileNames;

    public LoadGameController() throws IOException {
        GameCommon gameCommon = new GameCommon();
        fileNames = gameCommon.getResourceFiles(GameConstants.USER_SAVED_GAMES_PATH);
        System.out.println(fileNames);
    }
}
