package map;

import geometrie.Dot;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Plage {

    //private List<Tuile> ocean;
    private Map<Dot, Tile>  plage;
    Color color;
    //private int oceanTuileId;

    public Plage(){
        // this.ocean = new ArrayList<>();
        this.plage = new HashMap<>();
    }
    /*
        public void constructOcean(Tuile tuile){
            ocean.add(tuile);
        }
    */
    public void constructPlage( Tile tuile){
        plage.put( tuile.getTilePseudoCenter(), tuile);
    }
/*
    public List<Tuile> getOcean2() {
        return ocean;
    }
*/

    public Map<Dot, Tile> getPlage() {
        return plage;
    }

    public Tile getPlageTuildId(Dot dot) {
        return plage.get(dot);
    }
    /*
        public int getOceanTuileId() {
            return oceanTuileId;
        }

     */
    public int plageTuildNbr(){
        return plage.size();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
