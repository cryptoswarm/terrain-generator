package World.Generator.Island;

import Geometry.Circle;
import World.Tile;
import World.World;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Atoll extends Island {
    final private Circle circle;
    //final private HashMap<Coordinate, Tile> tiles;
    final private int maxAltitude;

    public Atoll( Circle circle, int maxAltitude){
        this.circle = circle;
        //this.tiles = tiles;
        this.maxAltitude = maxAltitude;
    }

    @Override
    public void apply(World world) {
        setBorders(world);
        defineAltitude(world, maxAltitude);
    }

    @Override
    public void defineAltitude(World world, int maxAltitude){

        TreeMap<Double, List<Tile>> treeMap = new TreeMap<>();
        List<Tile> islandTiles = world.getIslandTiles( circle);

        for(Tile tile:islandTiles){
            double distance;

            distance = tile.getCenter().distance(circle.getCenter());
            if (treeMap.containsKey(distance)) {
                treeMap.get(distance).add(tile);
            } else {
                List<Tile> tiles = new ArrayList<>();
                tiles.add(tile);
                treeMap.put(distance, tiles);
            }
        }
        applyProfilAltimetrique(treeMap, maxAltitude);
    }

    public void applyProfilAltimetrique(TreeMap<Double, List<Tile>> temp, int maxAlt){
       int milieu = temp.size()/2;
       float tileAlt = (float)maxAlt/milieu;
       float alt = tileAlt;
       int i = 0;

       for(List<Tile> tileList: temp.values()) {
           if(i < milieu) {
               for (Tile tile : tileList) tile.setAltitude(alt);
               alt = alt + tileAlt;
               ++i;
           } else if (i >= milieu) {
               for (Tile tile : tileList) tile.setAltitude(alt);
               alt = alt - tileAlt;
           }
       }
    }

    @Override
    public void setBorders(World world){
        world.setCircularIslandBorders(circle);
    }

}