package map;

import geometrie.Dot;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Lagon {
    private Map<Dot, Tile> lagon;
    private Color color;
    public Lagon() {
        //this.lagon = new ArrayList<>();
        this.lagon = new HashMap<>();
    }

    public void constructLagon(Dot dot, Tile tuile){
        //lagon.add(tuile);
        lagon.put(dot, tuile);
    }
/*
    public List<Tuile> getLagon() {
        return lagon;
    }

 */

    public Map<Dot, Tile> getLagon() {
        return lagon;
    }

    public int lagonTuildNbr(){
        return lagon.size();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean isNeighbor( Tile tile){
        boolean isPresent = false;
        for(Map.Entry<Dot, Tile> entry:lagon.entrySet() ) {
            Dot center = entry.getKey();
            Tile b = entry.getValue();
            System.out.println("tile looking for = "+tile.getTilePseudoCenter().getCoordonnee().toString() +" and lagon neighbor = ");
            for(Dot val : b.getNeighborPseudoCenters())
            {
                //System.out.println(val.getCoordonnee().toString());
                if(val.equals(tile.getTilePseudoCenter())){
                    isPresent = true;
                    break;
                }

            }

            //if(b.getNeighborPseudoCenters().contains(tile.getTilePseudoCenter())){
             //   isPresent = true;
            if(isPresent) {
                break;
            }
            //}
        }
        return isPresent;
    }

}
