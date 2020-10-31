package map;

import UserInterface.UserArgs;

public class WorldGenerator {
    public static World generateWorld(UserArgs parsedArgs, World world) {
        if (parsedArgs.getShapeSpecification() != null) {
            if (parsedArgs.getShapeSpecification().equals("atoll")) {

                Atoll atoll = world.createAtoll();

                if (parsedArgs.getNbWaterSources() != null) {
                    int nbWaterSources = Integer.parseInt(parsedArgs.getNbWaterSources());

                    //carte.createLake(atoll.getVegetation(), nbWaterSources, parsedArgs.getSoilType());
                    world.createNape(atoll.getVegetation(), nbWaterSources, parsedArgs.getSoilType());
                }
                if (parsedArgs.getSoilType() != null) {

                }

            } else if (parsedArgs.getShapeSpecification().equals("tortuga")) {
                Tortuga tortuga = world.createATortuga();
                if (parsedArgs.getNbWaterSources() != null) {
                    int nbWaterSources = Integer.parseInt(parsedArgs.getNbWaterSources());
                    world.createLake(tortuga.getVegetation(), nbWaterSources, parsedArgs.getSoilType());
                }
            }
        }
        return world;
    }
}

