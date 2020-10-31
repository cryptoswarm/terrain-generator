package map;

import geometrie.Dot;

import java.util.HashMap;

public class Lagoon implements Biome {

    private HashMap<Dot, Tile> lagoon;
    private TileColor color = TileColor.WATERBLUE;

    public Lagoon() {
        this.lagoon = new HashMap<>();
    }

    public void constructBiome(Dot dot, Tile tuile){
        lagoon.put(dot, tuile);
    }

    @Override
    public HashMap<Dot, Tile> getTiles() {
        return lagoon;
    }


    public HashMap<Dot, Tile> getLagon() {
        return lagoon;
    }


    public TileColor getColor() {
        return color;
    }

    public boolean isNeighbor( Tile tile){
        boolean isPresent = false;
        for(HashMap.Entry<Dot, Tile> entry:lagoon.entrySet() ) {
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
