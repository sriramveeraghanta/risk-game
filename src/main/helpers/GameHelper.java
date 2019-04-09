package main.helpers;

import main.models.CountryModel;
import main.models.GameModel;
import main.utills.GameConstants;
import main.utills.GameException;
import main.utills.DialogHandler;

import java.io.*;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GameHelper {
    /**
     * This method do all of the initialization of the game by getting the number of players  as a parameter
     * @param playerCount number of players.
     * @throws GameException, Game exceptions for Game related Exception Handling.
     * @throws IOException, Input out exception from user.
     */
    public GameModel startNewGame(int playerCount) throws GameException, IOException {
        // creating new Game Model
        GameModel gameModel = new GameModel();
        gameModel.setNumberOfPlayers(playerCount);
        // Creating New Map
        MapBuilder mapBuilder = new MapBuilder(gameModel);
        mapBuilder.readMapFile(null);
        // Startup phase
        StartupPhase startupPhase = new StartupPhase(gameModel);
        startupPhase.initNewGame(playerCount);
        return gameModel;
    }


    /**
     *
     * */
    public void saveGame(GameModel gameModel) {
        // Getting Current DataTime as String.
        StringBuffer stringBuffer = new StringBuffer();
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ssZ");
        simpleDateFormat.format(now, stringBuffer, new FieldPosition(0));
        try {
            String filePath = GameConstants.USER_SAVED_GAMES_PATH + stringBuffer+".ser";
            // write object to file
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(gameModel);
            oos.close();
        } catch (IOException e) {
            DialogHandler.showErrorMessage(GameConstants.SAVE_GAME_ERROR);
        }
    }

    /**
     *
     * */
    public GameModel loadGame(String fileName){
        GameModel gameModel = null;
        try {
            // read object from file
            FileInputStream fis = new FileInputStream(GameConstants.USER_SAVED_GAMES_PATH+fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            gameModel = (GameModel) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            DialogHandler.showErrorMessage(GameConstants.LOAD_GAME_ERROR);
        }
        return gameModel;
    }

    /**
     * Getting the attacker adjacent countries according to the parameters below
     * @param adjacentCountries array list of adjacent countries
     * @param playerCountries array list of players countries
     * @return array list of country model which are adjacent
     */
    public ArrayList<CountryModel> getAttackerAdjacentCounties(ArrayList<CountryModel> adjacentCountries, ArrayList<CountryModel> playerCountries) {
        ArrayList<CountryModel> adjacentList = new ArrayList<>();
        for(CountryModel country: adjacentCountries) {
            if(!playerCountries.contains(country)){
                adjacentList.add(country);
            }
        }
        return adjacentList;
    }

    /**
     * This method switchs the control of the player to next player.
     * @param gameModel it take the param as gameModel to change few things int he model.
     * */
    public void switchPlayerControl(GameModel gameModel){
        if(gameModel.getNumberOfPlayers() == gameModel.getCurrentPlayerIndex()+1){
            gameModel.setCurrentPlayerIndex(0);
            gameModel.getPlayers().get(gameModel.getCurrentPlayerIndex()).setArmyInHand(3);
        }
        else{
            gameModel.setCurrentPlayerIndex(gameModel.getCurrentPlayerIndex()+1);
            gameModel.getPlayers().get(gameModel.getCurrentPlayerIndex()).setArmyInHand(3);
        }
    }

    /**
     * This methods gets all the filename in resources path
     * @param path resource folder path.
     * @return this method returns a list of strings with file name  in the path.
     */
    public ArrayList<String> getResourceFiles(String path) throws IOException {
        ArrayList<String> filenames = new ArrayList<>();
        File directory = new File(path);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        if (fList != null) {
            for (File file : fList){
                if (file.isFile() && !file.isHidden()){
                    filenames.add(file.getName());
                }
            }
        }
        return filenames;
    }
}
