package map;

import geometrie.Circle;
import geometrie.Coordinate;

import java.util.HashMap;

public class Atoll implements Island {
    private HashMap<Coordinate, Tile> atoll = new HashMap<Coordinate, Tile>();
    private Circle circle;


    public Atoll(World world){
        this.circle = new Circle(world.getWidth(), world.getHeight());

        HashMap<Coordinate, Tile> tiles = world.getTiles();
        for (java.util.Map.Entry<Coordinate, Tile> entry : tiles.entrySet()) {
            Tile tile = entry.getValue();
            Coordinate tileCenter = entry.getKey();
            if (tileCenter.distance(circle.getCenter()) <= circle.getBigRadius()){
                atoll.put(tileCenter,tile);
            }
        }
    }


    @Override
    public boolean isOnIsland(Tile tile) {
        if(atoll.get(tile.getCenter()) == null) return false;
        return true;
    }

    public boolean isInLagon(Tile tile){
        return tile.getCenter().distance(circle.getCenter()) <= circle.getSmallRadius();
    }

    public HashMap<Coordinate, Tile> getTiles(){
        return atoll;
    }




}
