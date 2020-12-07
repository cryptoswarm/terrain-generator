package world.generator.biome;

import world.World;
import world.generator.Generator;
import world.generator.WorldProcessor;

public class BiomeGenerator implements Generator {


    private final Localization localization;
    public BiomeGenerator(Localization localization) {
        this.localization = localization;
    }

    public void generate(World w) {
        WorldProcessor wp;
        //water
        wp = new Ocean();
        wp.apply(w);
        wp = new Lagoon();
        wp.apply(w);

        //main land
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
        //plage
        wp = new RockBeach(localization);
        wp.apply(w);
        wp = new IceBeach(localization);
        wp.apply(w);
        wp = new Beach();
        wp.apply(w);
    }


}
