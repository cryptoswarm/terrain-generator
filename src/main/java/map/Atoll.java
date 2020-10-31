package map;

import geometrie.Circle;
import geometrie.Dot;

import java.util.HashMap;

public class Atoll implements IslandType {
    private HashMap<Dot, Tile> atoll = new HashMap<Dot, Tile>();
    private Lagoon lagon;
    private Plage plage;
    private Vegetation vegetation;
    private Circle circle;


    public Atoll(World world){

        this.circle = new Circle(world.width, world.height);
        this.lagon = new Lagoon();
        this.plage = new Plage();
        this.vegetation = new Vegetation();

        HashMap<Dot, Tile> tiles = world.tiles;
        for (java.util.Map.Entry<Dot, Tile> entry : tiles.entrySet()) {
            Tile tile = entry.getValue();
            if (tile.getTileCenter().distance(circle.getCenter()) <= circle.getsRadius()){
                atoll.put(tile.getTileCenter(),tile);
            }
        }

    }





    private boolean isInAtoll(Dot center){
        return atoll.get(center) != null;
    }
    public boolean isInLagon(Dot center){
        return center.distance(circle.getCenter()) <= circle.getsRadius();
    }

    public boolean isBetweenLagonAndOcean(Dot that){
        return !isInLagon(that) && isInAtoll(that);
    }

    /*public boolean isNeighborLagonOrOcean(Tile tile){
        return ocean.isNeighbor(tile) || lagon.isNeighbor(tile);
    }
     */

    public Vegetation getVegetation(){
        return vegetation;
    }


    public Lagoon getLagon() {
        return lagon;
    }

    public Plage getPlage() {
        return plage;
    }

    public HashMap<Dot, Tile> getTiles(){
        return atoll;
    }
/*
    public boolean isNeighborLagonOrOcean1(Ocean ocean, Dot b){
        return ocean.isNeighbor1(b) || lagon.isNeighbor1(b);
    }

    public boolean isNotNeighborLagonAndOcean1(Ocean ocean, Dot b){
        return !ocean.isNeighbor1(b) && !lagon.isNeighbor1(b);
    }
*/



}
