package World;

import Geometry.Coordinate;

import java.util.HashMap;

public  interface Biome {
    void addToBiome(Tile tile);
    HashMap<Coordinate, Tile> getTiles();
}
