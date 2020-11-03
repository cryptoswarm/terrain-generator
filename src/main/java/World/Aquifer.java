package World;

import Geometry.Coordinate;
import java.util.HashMap;

public interface Aquifer {
    HashMap<Coordinate, Tile> getTiles();
}
