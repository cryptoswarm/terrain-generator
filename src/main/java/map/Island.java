package map;

import geometrie.Coordinate;
import java.util.HashMap;

public interface Island {
    public HashMap<Coordinate, Tile> getTiles();
    public boolean isOnIsland(Tile tile);
}
