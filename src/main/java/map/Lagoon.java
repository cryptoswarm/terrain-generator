package map;

import geometrie.Dot;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class Lagoon implements Biome {

    private Map<Dot, Tile> lagon;
    private TileColor color = TileColor.WATERBLUE;

    public Lagoon() {
        this.lagon = new HashMap<>();
    }

    public void constructLagon(Dot dot, Tile tuile){
        lagon.put(dot, tuile);
    }


    public Map<Dot, Tile> getLagon() {
        return lagon;
    }


    public TileColor getColor() {
        return color;
    }

    public boolean isNeighbor( Tile tile){
        boolean isPresent = false;
        for(Map.Entry<Dot, Tile> entry:lagon.entrySet() ) {
            //Dot center = entry.getKey();
            Tile b = entry.getValue();
            for(Dot val : b.getNeighborPseudoCenters())
            {
                if(val.equals(tile.getTileCenter())){
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
/*
    public boolean isNeighbor1( Dot dot){

        boolean isPresent = false;
       if( lagon.containsKey(dot )) {
           isPresent = true;
       }
        return isPresent;
    }
*/

}
