package World.Generator.Biome;

import World.TileColor;
import World.World;

public  interface Biome {
    TileColor getColor();
    String getType();
    void apply(World world);
}
