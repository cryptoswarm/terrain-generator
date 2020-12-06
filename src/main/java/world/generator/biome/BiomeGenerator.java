package world.generator.biome;

import world.World;
import world.generator.Generator;
import world.generator.WorldProcessor;

public class BiomeGenerator implements Generator {

        public BiomeGenerator() {}

        public void generate(World w) {
        WorldProcessor wp;
        wp = new Ocean();
        wp.apply(w);
        wp = new Lagoon();
        wp.apply(w);
        //magic
        wp = new Vegetation();
        wp.apply(w);
        //
        wp = new Plage();
        wp.apply(w);

    }


}
