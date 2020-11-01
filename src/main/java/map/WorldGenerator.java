package map;

import UserInterface.UserArgs;

public class WorldGenerator {
    public static World generateWorld(UserArgs parsedArgs, World world) {
            Island island = null;
            if (parsedArgs.getShapeSpecification().equals("atoll")) {

                island = new Atoll(world);

                if (parsedArgs.getNbWaterSources() != null) {
                    int nbWaterSources = Integer.parseInt(parsedArgs.getNbWaterSources());

                    //world.createNape(atoll.getVegetation(), nbWaterSources, parsedArgs.getSoilType());
                }
                if (parsedArgs.getSoilType() != null) {

                }

            } else if (parsedArgs.getShapeSpecification().equals("tortuga")) {
                island = new Tortuga(world);
                /*
                if (parsedArgs.getNbWaterSources() != null) {
                    int nbWaterSources = Integer.parseInt(parsedArgs.getNbWaterSources());
                    world.createLake(tortuga.getVegetation(), nbWaterSources, parsedArgs.getSoilType());
                }*/
            }

        world.createBiome(island);
        return world;
    }


}

