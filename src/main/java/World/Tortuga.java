package World;

import Geometry.Coordinate;
import Geometry.Ellipse;

import java.util.HashMap;
import java.util.Random;

public class Tortuga implements Island {

    private Ellipse ellipse;
    private HashMap<Coordinate, Tile> tortuga = new HashMap<Coordinate, Tile>();


    public Tortuga(World world, Random random){
        this.ellipse = new Ellipse(world.getWidth(), world.getHeight(), random);
        HashMap<Coordinate, Tile> tiles = world.getTiles();
        for (Tile tile : tiles.values()) {
            Coordinate tileCenter = tile.getCenter();
            if (ellipse.isInEllipse(tileCenter)) {
                tortuga.put(tileCenter,tile);
            }
        }
    }

    @Override
    public HashMap<Coordinate, Tile> getTiles() {
        return tortuga;
    }

    @Override
    public boolean isOnIsland(Tile tile) {
        return tortuga.get(tile.getCenter()) != null;
    }

    @Override
    public void defineAltitude() {

    }
}
