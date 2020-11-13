package World.Generator.Aquifer;

import World.Generator.Generator;
import World.World;

public class RiverGenerator implements Generator {
        final private int nbsRiverSrc;

        public RiverGenerator(int nbsRiverSrc) {
            this.nbsRiverSrc = nbsRiverSrc;
        }

        @Override
        public void generate(World w) {
            for (int i = nbsRiverSrc; i > 0; i--) {
                Aquifer river = new River(w.findRandomVegetationTile());
                river.apply(w);
            }
        }

    }
