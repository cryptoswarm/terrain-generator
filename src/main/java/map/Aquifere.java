package map;

import geometrie.Dot;

import java.util.Map;

public class Aquifere implements Biome{

    private Tile aquifereTileCenter;
    private Map<Dot, Tile> neighbors;
    private Vegetation vegetation;
    //private HashSet<Dot> neighbors2;

    public Aquifere(Tile tile, Map<Dot, Tile> neighbors) {

        this.aquifereTileCenter = tile;
        this.neighbors = neighbors;
    }

    public void setColor(){
        this.aquifereTileCenter.setBackgroundColor(TileColor.DARKGREEN);
    }

    public void propager(){
        //this.neighbors = this.vegetation.findNeighbors(aquifereTileCenter);
        //aquifereTileCenter.getNeighborPseudoCenters();
        for(Dot dot : aquifereTileCenter.getNeighborPseudoCenters() ){

            if( vegetation.findCorrespendingTile(dot) != null ){
                vegetation.findCorrespendingTile(dot).setBackgroundColor(TileColor.LESSDARKERGREEN);
            }
        }
        /*
        neighbors2 = aquifereTileCenter.getNeighborPseudoCenters();
        for( Dot dot : neighbors2 ){
            vegetation.findCorrespendingTile(dot).setBackgroundColor( TileColor.MIDGREEN );
        }

         */
        /*
        for( Map.Entry<Dot, Tile> entry:neighbors.entrySet() ) {
            //Dot center = entry.getKey();
            Tile b = entry.getValue();
            b.setBackgroundColor(TileColor.MIDGREEN);

            //if(  b.getTilePseudoCenter().equals(tile.getTilePseudoCenter()) ){
            //    neighbors = b.getNeighborPseudoCenters();
            //    break;
            //}
        }

         */


    }
/*
    public  Map<Dot, Tile> findAquifereNeighbors(Tile aquifereTileCenter){
        Map<Dot, Tile> neighbor = new HashMap<>();
        Tile tile;
        for(Dot dot : aquifereTileCenter.getNeighborPseudoCenters() ){
            if(  ( tile = vegetation.findCorrespendingTile(dot))  != null ){
                neighbor.put(dot, tile );
            }
        }
        return neighbor;
    }

 */

    public Tile getAquifereTileCenter() {
        return aquifereTileCenter;
    }

    public void setColorAquifere(TileColor color) {

        this.aquifereTileCenter.setBackgroundColor(color);
        for(Map.Entry<Dot, Tile> entry:neighbors.entrySet() ) {
            Tile b = entry.getValue();
            b.setBackgroundColor(color);
        }
    }

    public Map<Dot, Tile> getNeighbors() {
        return neighbors;
    }
}
