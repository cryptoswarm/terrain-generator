package World.Generator.Aquifer;

import RandomStrategy.RandomContexte;
import World.*;
import World.Generator.Generator;

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
        for (int i = nbsNape ;i >= 0; i--) {
            Aquifer nape = new Nape(w.findRandomVegetationTile());
            nape.apply(w);
        }
        for (int i = nbsWaterSrc-nbsNape; i >= 0; i--) {
            Aquifer lake = new Lake(w.findRandomVegetationTile());
            lake.apply(w);
        }
    }
}
