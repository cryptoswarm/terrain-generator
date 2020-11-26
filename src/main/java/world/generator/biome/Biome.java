package world.generator.biome;

import world.generator.WorldProcessor;
import world.TileColor;

public abstract class Biome  implements WorldProcessor {
    abstract public TileColor getColor();
    abstract public String getType();
}
