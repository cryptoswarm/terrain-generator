package world.generator.aquifer;

import world.World;
import world.Tile;
import world.soilType;
import world.generator.WorldProcessor;
import java.util.HashSet;

public abstract class Aquifer implements WorldProcessor {

    public void applyHumidityEffect(World world, HashSet<Tile> waterSource, soilType soil){

        for(Tile i: world.getTiles().values()) {

            if(i.getBiome().getType().equals("vegetation")){
                float distance = getDistanceFromWaterSource(i, waterSource);
                if( distance < soil.getAffectedDistance()) {
                    if (i.getHumidityLevel() == 0 || i.getHumidityLevel() > Math.round(distance)) {
                        i.setHumidityLevel(Math.round(distance));
                    }
                }
                if(waterSource.contains(i)) i.setHumidityLevel(5);
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
