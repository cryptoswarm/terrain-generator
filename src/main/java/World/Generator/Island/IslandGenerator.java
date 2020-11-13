package World.Generator.Island;

import RandomStrategy.RandomContexte;
import World.World;
import World.Generator.Generator;


public class IslandGenerator implements Generator {
    final private String islandType;
    final private int width;
    final private int height;
    public IslandGenerator(String islandType, int width, int height) {
        this.islandType = islandType;
        this.width = width;
        this.height = height;
    }

    @Override
    public void generate(World w) {
        RandomContexte random = w.getRandom();
        Island island = null;
        if (islandType.equals("atoll")) {
            island = new Atoll(random, height,width);
        } else if (islandType.equals("tortuga")) {
            island = new Tortuga(random, height, width);
        }
        island.apply(w);
        try {
            island.validate(w);
        } catch (Exception e) {
            System.out.println("Invalid altitude for the island");
        }
    }
}
