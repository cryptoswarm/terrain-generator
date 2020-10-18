package map;

import geometrie.Dot;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Vegetation {

    //private List<Tuile> ocean;
    private Map<Dot, Tile>  vegetation;
    Color color;
    //private int oceanTuileId;

    public Vegetation(){
        // this.ocean = new ArrayList<>();
        this.vegetation = new HashMap<>();
    }
    /*
        public void constructOcean(Tuile tuile){
            ocean.add(tuile);
        }
    */
    public void constructVegetation(Tile tuile){
        vegetation.put( tuile.getTilePseudoCenter(), tuile);
    }
/*
    public List<Tuile> getOcean2() {
        return ocean;
    }
*/

    public Map<Dot, Tile> getVegetation() {
        return vegetation;
    }

    public Tile getVegeTuildId(Dot dot) {
        return vegetation.get(dot);
    }
    /*
        public int getOceanTuileId() {
            return oceanTuileId;
        }

     */
    public int vegeTuildNbr(){
        return vegetation.size();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
