package world.generator.aquifer;

import geometry.Coordinate;
import world.generator.Generator;
import world.generator.WorldProcessor;
import world.World;
import world.soilType;

public class RiverGenerator implements Generator {
        final private int nbsRiverSrc;
        final private soilType soil;

        public RiverGenerator(int nbsRiverSrc, soilType soil) {
            this.nbsRiverSrc = nbsRiverSrc;
            this.soil = soil;
        }

        @Override
        public void generate(World w) {
            for (int i = nbsRiverSrc; i > 0; i--) {
                Coordinate coordinate = w.findRandomCoordinate();
                WorldProcessor river = new River(coordinate, soil);
                river.apply(w);
            }
        }

    }
