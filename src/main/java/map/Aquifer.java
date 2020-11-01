package map;

import geometrie.Dot;

import java.util.HashMap;

public class Aquifer implements Biome{

    private Tile aquiferTileCenter;
    private HashMap<Dot, Tile> neighbors;
    private Vegetation vegetation;
    //private HashSet<Dot> neighbors2;

    public Aquifer(Tile tile, HashMap<Dot, Tile> neighbors) {

        this.aquiferTileCenter = tile;
        this.neighbors = neighbors;
    }

    public void setColor(){
        this.aquiferTileCenter.setBackgroundColor(TileColor.DARKGREEN);
    }

    public void propager(){
        //this.neighbors = this.vegetation.findNeighbors(aquifereTileCenter);
        //aquifereTileCenter.getNeighborPseudoCenters();
        for(Dot dot : aquiferTileCenter.getNeighborPseudoCenters() ){

            if( vegetation.findCorrespendingTile(dot) != null ){
                vegetation.findCorrespendingTile(dot).setBackgroundColor(TileColor.GREEN);
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

    public Tile getAquiferTileCenter() {
        return aquiferTileCenter;
    }

    public void setColorAquifer(TileColor color) {

        this.aquiferTileCenter.setBackgroundColor(color);
        for(HashMap.Entry<Dot, Tile> entry:neighbors.entrySet() ) {
            Tile b = entry.getValue();
            b.setBackgroundColor(color);
        }
    }

    public HashMap<Dot, Tile> getNeighbors() {
        return neighbors;
    }


    @Override
    public HashMap<Dot, Tile> getTiles() {
        return null;
    }

    @Override
    public void addToBiome(Tile tile) {

    }
}
