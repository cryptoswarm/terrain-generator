package world.generator.aquifer;

import islandSet.Isle;
import world.Tile;
import world.generator.IslandProcessor;
import world.soilType;

import java.util.HashSet;
import java.util.List;

public abstract class Aquifer implements IslandProcessor{


    public void applyHumidityEffect(Isle isle, HashSet<Tile> waterSource, soilType soil){

        List<Tile> vegetationBiom = isle.getVegetationTiles();
        for(Tile tile: vegetationBiom ) {

                float distance = getDistanceFromWaterSource(tile, waterSource);
                if( distance < soil.getAffectedDistance()) {
                    if (tile.getHumidityLevel() == 0 || tile.getHumidityLevel() > Math.round(distance)) {
                        tile.setHumidityLevel(Math.round(distance));
                    }
                }
                if(waterSource.contains(tile)) tile.setHumidityLevel(5);
        }
    }

    private float getDistanceFromWaterSource(Tile tile, HashSet<Tile> waterSource) {
        float distance = (float)10000;
        for(Tile i: waterSource){
            float tmp = tile.getCenter().distance(i.getCenter());
            double altitudeDifference =  i.getAltitude() - tile.getAltitude();
            tmp = tmp - (float) altitudeDifference;
            if( tmp < distance) distance = tmp;
        }
        return distance;
    }

}
