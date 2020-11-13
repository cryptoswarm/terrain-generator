package World.Generator.Biome;

import World.World;
import World.Generator.Generator;
import World.Tile;
import java.util.HashSet;

public class BiomeGenerator implements Generator {

    public BiomeGenerator() {}

    public void generate(World w) {
        Ocean ocean = new Ocean();
        ocean.apply(w);
        Lagoon lagoon = new Lagoon();
        lagoon.apply(w);
        Plage plage = new Plage();
        plage.apply(w);
        Vegetation vegetation = new Vegetation();
        vegetation.apply(w);
    }

}
