package World.Generator.Aquifer;

import Geometry.Coordinate;
import World.*;
import World.Generator.Biome.Vegetation;
import World.Generator.WorldProcessor;

import java.util.HashMap;

public abstract class Aquifer implements WorldProcessor {
    abstract public HashMap<Coordinate, Tile> getTiles();
    public void applyHumidityEffect(World world, HashMap<Coordinate, Tile> waterSource){
        soilType soil = world.getSoil();
        for(Tile i: world.getTiles().values()) {
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
    private float getDistanceFromWaterSource(Tile tile, HashMap<Coordinate, Tile> waterSource) {
        float distance = (float)10000;
        for(Tile i: waterSource.values()){
            float tmp = tile.getCenter().distance(i.getCenter());
            double altitudeDifference =  i.getAltitude() - tile.getAltitude();
            tmp = tmp - (float) altitudeDifference;
            if( tmp < distance) distance = tmp;
        }
        return distance;
    }
}
