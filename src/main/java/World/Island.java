package World;

import Geometry.Coordinate;
import java.util.HashMap;

public interface Island {
    HashMap<Coordinate, Tile> getTiles();
    boolean isOnIsland(Tile tile);
     void defineAltitude();
}
