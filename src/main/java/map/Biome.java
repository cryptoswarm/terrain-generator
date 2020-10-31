package map;

import geometrie.Dot;

import java.util.HashMap;

public interface Biome {
    public void constructBiome(Dot dot, Tile tuile);
    public HashMap<Dot, Tile> getTiles();
}
