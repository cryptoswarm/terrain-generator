package map;

import geometrie.Dot;

import java.util.HashMap;

public interface Biome {
    public void addToBiome(Tile tile);
    public HashMap<Dot, Tile> getTiles();
}
