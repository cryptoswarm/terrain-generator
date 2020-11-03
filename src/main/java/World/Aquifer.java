package World;

import geometrie.Coordinate;
import java.util.HashMap;

public interface Aquifer {
    HashMap<Coordinate, Tile> getTiles();
}
