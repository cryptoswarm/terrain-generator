package map;

import geometrie.Dot;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Ocean {

    private Map<Dot, Tile> ocean;
    Color color;

    public Ocean(){
        this.ocean = new HashMap<>();
    }

    public void constructOcean(Dot dot, Tile tuile){
        tuile.setBackgroundColor(TileColor.OCEANBLUE);
        ocean.put( dot, tuile);
    }


    public Map<Dot, Tile> getOcean2() {
        return ocean;
    }

    public Tile getOceanTuildId(Dot dot) {
        return ocean.get(dot);
    }

    public int oceanTuildNbr(){
        return ocean.size();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean isNeighbor( Tile tile){
        boolean isPresent = false;
        for(Map.Entry<Dot, Tile> entry:ocean.entrySet() ) {
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
