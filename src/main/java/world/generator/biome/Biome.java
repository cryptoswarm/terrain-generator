package world.generator.biome;

import world.TileColor;

/*
public abstract class Biome  implements WorldProcessor, IslandProcessor {

    abstract public TileColor getColor();
    abstract public String getType();

}

 */
public interface Biome   {

    TileColor getColor();
    String getType();
}