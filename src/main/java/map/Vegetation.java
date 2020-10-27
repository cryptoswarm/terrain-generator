package map;

import geometrie.Dot;

import java.awt.*;
import java.util.*;

public class Vegetation {

    private Map<Dot, Tile>  vegetation;
    Color color;


    public Vegetation(){

        this.vegetation = new HashMap<>();
    }

    public void constructVegetation(Tile tuile){
        vegetation.put( tuile.getTilePseudoCenter(), tuile);
    }


    public Map<Dot, Tile> getTuileVege() {
        return vegetation;
    }

    public Tile getVegeTuildId(Dot dot) {
        return vegetation.get(dot);
    }

    public int getNbTuilesVegetales(){
        return vegetation.size();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }


    public Tile findRandomVegtalTile(){
        Random random = new Random();
        ArrayList<Dot>  dots    = new ArrayList<>(vegetation.keySet());
        Dot      randomDot = dots.get( random.nextInt(dots.size()) );
        return vegetation.get(randomDot);

    }



    public HashSet<Dot> findNeighbors( Tile tile){
        HashSet<Dot> neighbors = new HashSet<>();
        for(Map.Entry<Dot, Tile> entry:vegetation.entrySet() ) {
            //Dot center = entry.getKey();
            Tile b = entry.getValue();

            if(  b.getTilePseudoCenter().equals(tile.getTilePseudoCenter()) ){
                neighbors = b.getNeighborPseudoCenters();
                break;
            }
        }
        return neighbors;
    }
}
