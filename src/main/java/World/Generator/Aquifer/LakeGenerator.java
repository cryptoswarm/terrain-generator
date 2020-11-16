package World.Generator.Aquifer;

import RandomStrategy.RandomContexte;
import World.*;
import World.Generator.Generator;
import World.Generator.WorldProcessor;

public class LakeGenerator implements Generator {
    final private int nbsWaterSrc;
    final private RandomContexte random;

    public LakeGenerator(int nbsWaterSrc, RandomContexte r) {
        this.random = r;
        this.nbsWaterSrc = nbsWaterSrc;
    }

    @Override
    public void generate(World w) {
        int nbsNape  = random.getRandomInt(nbsWaterSrc+1);
        WorldProcessor wp;
        for (int i = nbsNape ;i >= 0; i--) {
            wp = new Nape(w.findRandomVegetationTile());
            wp.apply(w);
        }
        for (int i = nbsWaterSrc-nbsNape; i >= 0; i--) {
            wp = new Lake(w.findRandomVegetationTile());
            wp.apply(w);
        }
    }
}
