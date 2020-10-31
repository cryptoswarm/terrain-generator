
package map;

import geometrie.Dot;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Lake {

    private Aquifer aquifer;
    private Map<Dot, Tile> lake;
    private Tile lakeCenter;
    private Map<Dot, Tile> lakeNeighbors;

    public Lake(Aquifer aquifer) {
        this.aquifer = aquifer;
        this.lake = aquifer.getNeighbors();
        this.lakeCenter = aquifer.getAquiferTileCenter();
        this.lakeNeighbors = new HashMap<>();
    }

    public void setColor(TileColor tileColor){
        this.lakeCenter.setBackgroundColor(tileColor);
        for(Map.Entry<Dot, Tile> entry:lake.entrySet() ) {
            Tile b = entry.getValue();
            b.setBackgroundColor(tileColor);
        }
    }

    public void findAdjacentLakeNeighbors(Vegetation vegetation) {

        for (Map.Entry<Dot, Tile> entry2 : lake.entrySet()) {  //enlever les tuiles constituants le biom (lac) de ceux
            // constituant le biome (vegetation)
            Dot lakeCenter = entry2.getKey();
            vegetation.getVegetation().remove(lakeCenter);
        }

        for (Map.Entry<Dot, Tile> entry : vegetation.getVegetation().entrySet()) {
            Dot vegeCenter = entry.getKey();
            Tile vegeTile = entry.getValue();
            for (Map.Entry<Dot, Tile> entry2 : lake.entrySet()) {

                Tile lakeTile = entry2.getValue();

                for (Dot dotLake : lakeTile.getNeighborPseudoCenters()) {
                    if (dotLake.equals(vegeTile.getTileCenter())) {
                        this.lakeNeighbors.put(vegeCenter, vegeTile);
                    }
                }
            }
        }
    }

    public void setColorNeighbors(TileColor color){

        for(Map.Entry<Dot, Tile> entry:lakeNeighbors.entrySet() ) {
            Tile b = entry.getValue();
            b.setBackgroundColor(color);
        }
    }

    public Map<Double, Tile> findDistanceFromAquifereCenter(Vegetation vegetation){

        Map<Double, Tile> distanceTuileFromCenter = new TreeMap<>();
        //TreeMap<Double, Tile> map = new TreeMap<Integer,String>()

        for( Map.Entry<Dot, Tile> entry2:lake.entrySet()) {  //enlever les tuiles constituants le biom (lac) de ceux
            // constituant le biom (vegetation)
            Dot lakeCenter = entry2.getKey();
            vegetation.getVegetation().remove(lakeCenter);
        }

        for(Map.Entry<Dot, Tile> entry:vegetation.getVegetation().entrySet() ) {

            Dot vegeCenter = entry.getKey();
            Tile vegeTile = entry.getValue();

            double distance = vegeCenter.distance(lakeCenter.getTileCenter());

            distanceTuileFromCenter.put(distance, vegeTile );
        }
        return distanceTuileFromCenter;
    }



}
