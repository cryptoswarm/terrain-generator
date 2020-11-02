package map;

import geometrie.Coordinate;

import java.util.HashMap;

public interface Biome {
    public void addToBiome(Tile tile);
    public HashMap<Coordinate, Tile> getTiles();
}
