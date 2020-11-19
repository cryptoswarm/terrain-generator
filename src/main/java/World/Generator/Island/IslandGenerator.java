package World.Generator.Island;

import RandomStrategy.RandomContexte;
import World.World;
import World.Generator.Generator;


public class IslandGenerator implements Generator {
    final private String islandType;
    final private int width;
    final private int height;
    final private int altitude;
    final private RandomContexte random;
    public IslandGenerator(String islandType, int width, int height, int altitude, RandomContexte random) {
        this.islandType = islandType;
        this.width = width;
        this.height = height;
        this.altitude = altitude;
        this.random = random;

    }

    @Override
    public void generate(World w) {
        Island island;
        if (islandType.equals("atoll")) {
            island = new Atoll(random, height,width,altitude);
        } else if (islandType.equals("tortuga")) {
            island = new Tortuga(random, height, width, altitude);
        } else {
            island = new Atoll(random, height,width,altitude);
        }
        island.apply(w);
        try {
            island.validate(w);
        } catch (Exception e) {
            System.out.println("Invalid altitude for the island");
        }
    }
}
