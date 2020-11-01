package map;

import geometrie.Dot;
import java.util.HashMap;

public interface Island {
    public HashMap<Dot, Tile> getTiles();
    public boolean isOnIsland(Tile tile);
}
