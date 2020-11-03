package World;

import Geometry.Coordinate;
import Geometry.Ellipse;

import java.util.HashMap;
import java.util.Random;

public class Tortuga implements Island {
    private Ellipse ellipse;
    public Tortuga(HashMap<Coordinate, Tile> tiles, Random random, int width, int height){
        this.ellipse = new Ellipse(width, height, random);
        for (Tile tile : tiles.values()) {
            if (ellipse.isInEllipse(tile.getCenter())) {
                tile.setOnIsland(true);
            }
        }
    }


    @Override
    public void defineAltitude() {

    }
}
