package World.Generator.Biome;

import World.Generator.WorldProcessor;
import World.TileColor;

public abstract class Biome  implements WorldProcessor {
    abstract public TileColor getColor();
    abstract public String getType();
}
