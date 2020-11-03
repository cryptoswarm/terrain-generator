package map;

import UserInterface.UserArgs;

public class WorldGenerator {
    public static World generateWorld(UserArgs parsedArgs, World world) {
        int nbWaterSources = Integer.parseInt(parsedArgs.getNbWaterSources());
        Island island = null;

        if (parsedArgs.getShapeSpecification().equals("atoll")) {
            island = new Atoll(world);
        } else if (parsedArgs.getShapeSpecification().equals("tortuga")) {
            island = new Tortuga(world);
        }

        island.defineAltitude();
        world.createBiome(island);
        world.createLake(nbWaterSources);
        world.createNape(nbWaterSources);

        return world;
    }
}

