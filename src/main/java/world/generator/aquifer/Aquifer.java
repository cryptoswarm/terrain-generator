package world.generator.aquifer;

import islandSet.Isle;
import world.Tile;
import world.generator.IslandProcessor;
import world.soilType;

import java.util.HashSet;

public abstract class Aquifer implements IslandProcessor{ //WorldProcessor{


    //public void applyHumidityEffect( World world, HashSet<Tile> waterSource, soilType soil){
    public void applyHumidityEffect(Isle isle, HashSet<Tile> waterSource, soilType soil){

        for(Tile tile: isle.getIslandTiles() ) {

            if(tile.getBiome().getType().equals("vegetation")){
                float distance = getDistanceFromWaterSource(tile, waterSource);
                if( distance < soil.getAffectedDistance()) {
                    if (tile.getHumidityLevel() == 0 || tile.getHumidityLevel() > Math.round(distance)) {
                        tile.setHumidityLevel(Math.round(distance));
                    }
                }
                if(waterSource.contains(tile)) tile.setHumidityLevel(5);
            }
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
