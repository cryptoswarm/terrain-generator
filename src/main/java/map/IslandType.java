package map;

import geometrie.Dot;
import java.util.HashMap;

public interface IslandType {
    public HashMap<Dot, Tile> getTiles();
}
