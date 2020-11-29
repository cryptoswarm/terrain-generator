package world.generator.aquifer;

import randomStrategy.RandomContexte;
import world.*;
import world.generator.Generator;
import world.generator.WorldProcessor;

public class LakeGenerator implements Generator {


    final private int nbsWaterSrc;
    final private RandomContexte random;
    final private soilType soil;

    public LakeGenerator(int nbsWaterSrc, RandomContexte r, soilType soil) {
        this.random = r;
        this.nbsWaterSrc = nbsWaterSrc;
        this.soil = soil;
    }

    @Override
    public void generate(World w) {
        int nbsNape  = random.getRandomInt(nbsWaterSrc+1);
        WorldProcessor wp;
        for (int i = nbsNape ;i >= 0; i--) {
            wp = new Nape(w.findRandomVegetationTile(), soil);
            wp.apply(w);
        }
        for (int i = nbsWaterSrc-nbsNape; i >= 0; i--) {
            wp = new Lake(w.findRandomVegetationTile(), soil);
            wp.apply(w);
        }
    }
}
