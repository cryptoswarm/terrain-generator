package translator;

import geometrie.Dot;
import map.Tile;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Ocean {
    //private List<Tuile> ocean;
    private Map<Dot, Tile> ocean;
    Color color;
    //private int oceanTuileId;

    public Ocean(){
        // this.ocean = new ArrayList<>();
        this.ocean = new HashMap<>();
    }
    /*
        public void constructOcean(Tuile tuile){
            ocean.add(tuile);
        }
    */
    public void constructOcean(Dot dot, Tile tuile){
        ocean.put( dot, tuile);
    }
/*
    public List<Tuile> getOcean2() {
        return ocean;
    }
*/

    public Map<Dot, Tile> getOcean2() {
        return ocean;
    }

    public Tile getOceanTuildId(Dot dot) {
        return ocean.get(dot);
    }
    /*
        public int getOceanTuileId() {
            return oceanTuileId;
        }

     */
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
            Dot center = entry.getKey();
            Tile b = entry.getValue();
            //if(b.getNeighborPseudoCenters().contains(tile.getTilePseudoCenter())){
            //    isPresent = true;
             //   break;
           // }
            for(Dot val : b.getNeighborPseudoCenters())
            {
                //System.out.println(val.getCoordonnee().toString());
                if(val.equals(tile.getTilePseudoCenter())){
                    isPresent = true;
                    System.out.println("****************************************** dot are equals ******************************************");
                    break;
                }

            }

            //if(b.getNeighborPseudoCenters().contains(tile.getTilePseudoCenter())){
            //   isPresent = true;
            if(isPresent) {
                break;
            }
        }
        return isPresent;
    }
}
