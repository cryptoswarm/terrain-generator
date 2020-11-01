package map;

import geometrie.Dot;
import geometrie.Ellipse;

import java.util.HashMap;

public class Tortuga implements Island {

    private Ellipse ellipse;
    private HashMap<Dot, Tile> tortuga = new HashMap<Dot, Tile>();


    public Tortuga(World world){
        this.ellipse = new Ellipse(world.getWidth(), world.getHeight());
        HashMap<Dot, Tile> tiles = world.getTiles();
        for (java.util.Map.Entry<Dot, Tile> entry : tiles.entrySet()) {
            Tile tile = entry.getValue();
            Dot tileCenter = entry.getKey();
            if (ellipse.isOutEllipse(tile)){
                tortuga.put(tileCenter,tile);
            }
        }
    }

    @Override
    public HashMap<Dot, Tile> getTiles() {
        return tortuga;
    }

    @Override
    public boolean isOnIsland(Tile tile) {
        return tortuga.get(tile.getTileCenter()) != null;
    }
}
