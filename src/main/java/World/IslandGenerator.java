package World;

import Geometry.Coordinate;
import RandomStrategy.RandomContexte;
import World.Island.Atoll;
import World.Island.IslandStrategy;
import World.Island.Tortuga;
import java.util.HashSet;

import static World.WorldGenerator.getRandom;

public class IslandGenerator implements Generator {
    private String islandType;
    private int width;
    private int height;
    public IslandGenerator(String islandType, int width, int height) {
        this.islandType = islandType;
        this.width = width;
        this.height = height;
    }

    @Override
    public void generate(World w) {
        IslandStrategy island = null;
        if (islandType.equals("atoll")) {
            island = new Atoll(100);
        } else if (islandType.equals("tortuga")) {
            island = new Tortuga(100);
        }
        island.setPosition(width, height, new Coordinate(width/2, height/2,0), new RandomContexte(0));
        for(Tile t: w.getTiles()){
                if(island.contains(t)){
                    t.setAltitude(island.getAltitudeProfile(t));
                    if(island.isInLagoon(t)){
                        t.setInLagoon(true);
                        t.setAltitude(0);
                    }
                }else{
                    t.setAltitude(0);
                }



        }
    }
}
