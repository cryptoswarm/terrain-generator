package map;

import UserInterface.UserArgs;
import geometrie.Dot;

import java.awt.desktop.ScreenSleepEvent;

import static map.World.BiomeType.*;

public class WorldGenerator {
    public static World generateWorld(UserArgs parsedArgs, World world) {

            if (parsedArgs.getShapeSpecification().equals("atoll")) {

                IslandType atoll = new Atoll(world);
                createBiome(atoll, world);

                if (parsedArgs.getNbWaterSources() != null) {
                    int nbWaterSources = Integer.parseInt(parsedArgs.getNbWaterSources());

                    //carte.createLake(atoll.getVegetation(), nbWaterSources, parsedArgs.getSoilType());
                    //world.createNape(atoll.getVegetation(), nbWaterSources, parsedArgs.getSoilType());
                }
                if (parsedArgs.getSoilType() != null) {

                }

            } else if (parsedArgs.getShapeSpecification().equals("tortuga")) {
                /*Tortuga tortuga = world.addBiomeTortuga();
                if (parsedArgs.getNbWaterSources() != null) {
                    int nbWaterSources = Integer.parseInt(parsedArgs.getNbWaterSources());
                    world.createLake(tortuga.getVegetation(), nbWaterSources, parsedArgs.getSoilType());
                }*/
            }
        return world;
    }

    private static void createBiome(IslandType island, World world) {
        //Ocean
        world.biomes.put(OCEAN, new Ocean());
        for (java.util.Map.Entry<Dot, Tile> entry : world.tiles.entrySet()) {
            if(island.getTiles().get(entry.getKey()) == null){
                world.biomes.get(OCEAN).constructBiome(entry.getKey(), entry.getValue());
            }
        }
        //lagon
        if(island instanceof Atoll) {
            world.biomes.put(LAGOON, new Lagoon());
            for (java.util.Map.Entry<Dot, Tile> entry : island.getTiles().entrySet()) {
                if (((Atoll) island).isInLagon(entry.getKey())) {
                    world.biomes.get(LAGOON).constructBiome(entry.getKey(), entry.getValue());
                }
            }
        }
        //plage
        world.biomes.put(PLAGE, new Plage());
        for (java.util.Map.Entry<Dot, Tile> entry : island.getTiles().entrySet()) {
            for (Dot tileCenter : entry.getValue().neighbors) {
                if(world.biomes.get(OCEAN).getTiles().get(tileCenter) != null || world.biomes.get(LAGOON).getTiles().get(tileCenter) != null){
                    world.biomes.get(PLAGE).constructBiome(entry.getKey(), entry.getValue());
                    break;
                }
            }
        }
        //vegetation

    }
}

