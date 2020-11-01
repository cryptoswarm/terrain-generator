package map;

import geometrie.Dot;
import java.util.*;

public class Vegetation implements Biome {

    private HashMap<Dot, Tile>  vegetation;
    TileColor color = TileColor.GREEN;


    public Vegetation(){
        this.vegetation = new HashMap<>();
    }

    @Override
    public void addToBiome(Tile tile) {
        vegetation.put(tile.getTileCenter(), tile);
        tile.setBackgroundColor(color);
    }

    public Map<Dot, Tile> getVegetation() {
        return vegetation;
    }

    public Tile findRandomVegtalTile(){
        Random random = new Random();
        ArrayList<Dot>  dots    = new ArrayList<>(vegetation.keySet());
        Dot      randomDot = dots.get( random.nextInt( dots.size()) );
        return vegetation.get(randomDot);
    }

    public HashMap<Dot, Tile> findAquiferNeighbors(Tile randomTile){
        HashMap<Dot, Tile> neighbor = new HashMap<>();
        Tile tile;
        for(Dot dot : randomTile.getNeighborPseudoCenters() ){
            if(  ( tile = findCorrespendingTile(dot))  != null ){
                neighbor.put(dot, tile );
            }
        }
        neighbor.put(randomTile.getTileCenter(), randomTile);
        return neighbor;
    }



    public Map<Dot, Tile> findNeighbors(Tile tile){

        HashMap<Dot, Tile> aquiferNeighbors = new HashMap<>();

        //for(Map.Entry<Dot, Tile> entry:vegetation.entrySet() ) {
            //Dot center = entry.getKey();
           // Tile b = entry.getValue();

            //if(  b.getTileCenter().equals(tile.getTileCenter() ) ){ //Si le point centre de la tuile porteuse de source d'eau
                                                                                // est dans la liste des voisines
                for( Dot  dot : tile.getNeighborPseudoCenters() ){
                    Tile tile1 = vegetation.get(dot);
                    aquiferNeighbors.put(dot, tile1);
                }
               // break;
            //}
       // }
        return aquiferNeighbors;
    }

    public Tile findCorrespendingTile(Dot dot){
        return vegetation.get(dot);
    }


    @Override
    public HashMap<Dot, Tile> getTiles() {
        return vegetation;
    }
}
