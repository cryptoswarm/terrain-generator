package World.Generator.Aquifer;

import World.*;
import World.Generator.Biome.Vegetation;
import World.Generator.WorldProcessor;

import java.util.HashSet;

public abstract class Aquifer implements WorldProcessor {
    abstract public HashSet<Tile> getTiles();
    public void applyHumidityEffect(World world, HashSet<Tile> waterSource){
        soilType soil = world.getSoil();
        for(Tile i: world.getTiles()) {
            if(i.getBiome() instanceof Vegetation){
                float distance = getDistanceFromWaterSource(i, waterSource);
                if( distance < soil.getAffectedDistance()) {
                    if (i.getHumidityLevel() == 0 || i.getHumidityLevel() > Math.round(distance)) {
                        i.setHumidityLevel(Math.round(distance));
                    }
                }
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
