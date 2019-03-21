package main.helpers;

import main.models.GameModel;
import main.utills.GameConstants;
import main.utills.GameException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;

public class MapBuilder {
    GameModel gameModel;

    FileReader mapFileReader;
    private ArrayList<String> mapDataList = new ArrayList<>();

    public MapBuilder(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    /**
     * Processing of World.map file starts here
     *
     * @param mapFilePath This parameter will contain the path of map file
     * @throws GameException
     *
     */
    public void readMapFile(String mapFilePath) throws GameException {
        if (mapFilePath == null) {
            mapFilePath = GameConstants.MAP_FILE_PATH;
        }
        try {
            mapFileReader = new FileReader(mapFilePath);
            BufferedReader mapDataReader = new BufferedReader(mapFileReader);
            String mapLineData;
            while ((mapLineData = mapDataReader.readLine()) != null) {
                if (!mapLineData.equals("")) {
                    mapDataList.add(mapLineData);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadContinentMapData(mapDataList.indexOf("[Continents]"), mapDataList.indexOf("[Territories]"));
    }

    private void loadContinentMapData(int startIndex, int endIndex) {
        for (int index = startIndex + 1; index < endIndex; index++) {
            String countryMapLine = mapDataList.get(index);
        }
    }
}
