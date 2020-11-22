package World.Generator.Island;

import Geometry.Circle;
import Geometry.Coordinate;
import World.Tile;
import World.World;

import java.util.*;

public class Atoll extends Island {
    private Circle circle;
    private HashMap<Coordinate, Tile> tiles;
    private int maxAltitude;

    public Atoll(HashMap<Coordinate, Tile> tiles, Circle circle, int maxAltitude){
        this.circle = circle;
        this.tiles = tiles;
        this.maxAltitude = maxAltitude;
    }

    @Override
    public void apply(World world) {
        setBorders();
        defineAltitude(maxAltitude);
    }

    @Override
    public void defineAltitude(int maxAltitude){
        TreeMap<Double, List<Tile>> treeMap = new TreeMap<>();
        for(Tile tile: tiles.values()){
            double distance;
            if(tile.getAltitude() == 1) {
                distance = tile.getCenter().distance(circle.getCenter());
                if (treeMap.containsKey(distance)) {
                    treeMap.get(distance).add(tile);
                } else {
                    List<Tile> tiles = new ArrayList<>();
                    tiles.add(tile);
                    treeMap.put(distance, tiles);
                }
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
    public void setBorders(){
        for (Tile tile : tiles.values()) {
            if(tile.getAltitude() == -1) {
                Coordinate c = tile.getCenter();
                if ( c.distance(circle.getCenter()) > circle.getSmallRadius() &&
                        c.distance(circle.getCenter()) <= circle.getBigRadius()) {
                    tile.setAltitude(1);
                }
                if (c.distance(circle.getCenter()) <= circle.getSmallRadius()){
                    tile.setInLagoon(true);
                }
            }
        }
    }



}