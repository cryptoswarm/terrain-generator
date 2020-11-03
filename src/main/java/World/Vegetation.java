package World;

import Geometry.Coordinate;
import java.util.*;

public class Vegetation implements Biome {

    TileColor color = TileColor.LIGHTGREEN;


    public Vegetation(){

    }

    @Override
    public TileColor getColor() {
        return color;
    }
}
