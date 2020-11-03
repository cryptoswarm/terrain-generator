package World;

import Geometry.Coordinate;

import java.util.HashMap;

public interface Generator {
    void generate(HashMap<Coordinate, Tile> tiles, Island island);
}
