package World.Generator.Biome;

import World.Generator.WorldProcessor;
import World.World;
import World.Generator.Generator;

public class BiomeGenerator implements Generator {

    public BiomeGenerator() {}

    public void generate(World w) {
        WorldProcessor wp;
        wp = new Ocean();
        wp.apply(w);
        wp = new Lagoon();
        wp.apply(w);
        wp = new Plage();
        wp.apply(w);
        wp = new Vegetation();
        wp.apply(w);
    }

}
