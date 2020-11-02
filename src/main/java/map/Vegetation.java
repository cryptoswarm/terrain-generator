package map;

import geometrie.Coordinate;
import java.util.*;

public class Vegetation implements Biome {

    private HashMap<Coordinate, Tile>  vegetation;
    TileColor color = TileColor.LIGHTGREEN;


    public Vegetation(){
        this.vegetation = new HashMap<>();
    }

    @Override
    public void addToBiome(Tile tile) {
        vegetation.put(tile.getCenter(), tile);
        tile.setBackgroundColor(color);
        tile.setHumidityLevel(10);
    }

    @Override
    public HashMap<Coordinate, Tile> getTiles() {
        return vegetation;
    }
}
