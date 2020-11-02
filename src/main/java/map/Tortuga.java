package map;

import geometrie.Coordinate;
import geometrie.Ellipse;

import java.util.HashMap;

public class Tortuga implements Island {

    private Ellipse ellipse;
    private HashMap<Coordinate, Tile> tortuga = new HashMap<Coordinate, Tile>();


    public Tortuga(World world){
        this.ellipse = new Ellipse(world.getWidth(), world.getHeight());
        HashMap<Coordinate, Tile> tiles = world.getTiles();
        for (java.util.Map.Entry<Coordinate, Tile> entry : tiles.entrySet()) {
            Tile tile = entry.getValue();
            Coordinate tileCenter = entry.getKey();
            if (ellipse.isOutEllipse(tile)){
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
}
