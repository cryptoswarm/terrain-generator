
package map;

import geometrie.Dot;

import java.util.HashMap;
import java.util.Map;

public class Lake {

    private Aquifere aquifere;
    private Map<Dot, Tile> lake;
    private Tile lakeCenter;
    private Map<Dot, Tile> lakeNeighbors;

    public Lake(Aquifere aquifere) {
        this.aquifere = aquifere;
        this.lake = aquifere.getNeighbors();
        this.lakeCenter = aquifere.getAquifereTileCenter();
        this.lakeNeighbors = new HashMap<>();
    }

    public void setColor(){
        this.lakeCenter.setBackgroundColor(TileColor.WATERBLUE);
        for(Map.Entry<Dot, Tile> entry:lake.entrySet() ) {
            Tile b = entry.getValue();
            b.setBackgroundColor(TileColor.WATERBLUE);
        }
    }

    public void findLakeNeighbors(Vegetation vegetation){

        for( Map.Entry<Dot, Tile> entry2:lake.entrySet()) {  //enlever les tuiles constituants le biom (lac) de ceux
                                                                    // constituant le biom (vegetation)
            Dot lakeCenter = entry2.getKey();
            vegetation.getTuileVege().remove(lakeCenter);
        }

        for(Map.Entry<Dot, Tile> entry:vegetation.getTuileVege().entrySet() ) {
            Dot vegeCenter = entry.getKey();
            Tile vegeTile = entry.getValue();
            for( Map.Entry<Dot, Tile> entry2:lake.entrySet()) {

                Tile lakeTile = entry2.getValue();

                for (Dot dotLake : lakeTile.getNeighborPseudoCenters()) {
                    if ( dotLake.equals(vegeTile.getTileCenter()) ) {
                            this.lakeNeighbors.put(vegeCenter, vegeTile);
                    }
                }
            }
        }
    }

    public void setColorNeighbors( ){

        for(Map.Entry<Dot, Tile> entry:lakeNeighbors.entrySet() ) {
            Tile b = entry.getValue();
            b.setBackgroundColor(TileColor.DARKGREEN);
        }
    }



}
