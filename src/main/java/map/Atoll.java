package map;

import geometrie.Circle;
import geometrie.Dot;

import java.util.HashMap;

public class Atoll implements Island {
    private HashMap<Dot, Tile> atoll = new HashMap<Dot, Tile>();
    private Circle circle;


    public Atoll(World world){
        this.circle = new Circle(world.getWidth(), world.getHeight());

        HashMap<Dot, Tile> tiles = world.getTiles();
        for (java.util.Map.Entry<Dot, Tile> entry : tiles.entrySet()) {
            Tile tile = entry.getValue();
            Dot tileCenter = entry.getKey();
            if (tileCenter.distance(circle.getCenter()) <= circle.getBigRadius()){
                atoll.put(tileCenter,tile);
            }
        }
    }


    @Override
    public boolean isOnIsland(Tile tile) {
        if(atoll.get(tile.getTileCenter()) == null) return false;
        return true;
    }

    public boolean isInLagon(Tile tile){
        return tile.getTileCenter().distance(circle.getCenter()) <= circle.getSmallRadius();
    }

    public HashMap<Dot, Tile> getTiles(){
        return atoll;
    }




}
