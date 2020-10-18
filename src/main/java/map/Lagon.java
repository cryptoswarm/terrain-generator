package map;

import geometrie.Dot;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Lagon {

    private Map<Dot, Tile> lagon;
    private Color color;

    public Lagon() {
        this.lagon = new HashMap<>();
    }

    public void constructLagon(Dot dot, Tile tuile){
        lagon.put(dot, tuile);
    }


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
            //Dot center = entry.getKey();
            Tile b = entry.getValue();
            for(Dot val : b.getNeighborPseudoCenters())
            {
                if(val.equals(tile.getTilePseudoCenter())){
                    isPresent = true;
                    break;
                }
            }
            if(isPresent) {
                break;
            }
        }
        return isPresent;
    }

}
