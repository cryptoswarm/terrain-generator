package world.generator.biome;

import world.World;
import world.generator.Generator;
import world.generator.WorldProcessor;

public class BiomeGenerator implements Generator {
    private Localization localization;
    public BiomeGenerator(Localization localization) {
        this.localization = localization;
    }

    public void generate(World w) {
        WorldProcessor wp;
        wp = new Ocean();
        wp.apply(w);
        wp = new Lagoon();
        wp.apply(w);
        //magic
        wp = new Vegetation();
        wp.apply(w);
        wp = new Desert(localization);
        wp.apply(w);
        wp = new Toundra(localization);
        wp.apply(w);
        wp = new Taiga(localization);
        wp.apply(w);
        wp = new Savane(localization);
        wp.apply(w);
        wp = new Prairie(localization);
        wp.apply(w);
        //
        wp = new Plage();
        wp.apply(w);

    }


}
