package world.generator.aquifer;

import geometry.Coordinate;
import islandSet.Isle;
import randomStrategy.RandomContexte;
import world.World;
import world.generator.Generator;
import world.generator.WorldProcessor;
import world.soilType;

public class RiverGenerator implements Generator {


    final private int nbsRiverSrc;
    final private soilType soil;
    private  final  RandomContexte random;

    public RiverGenerator(int nbsRiverSrc, soilType soil, RandomContexte r) {
        this.nbsRiverSrc = nbsRiverSrc;
        this.soil = soil;
        this.random = r;
    }

    @Override
    public void generate(World w) {

        for(Isle isle:w.getIsleList() ) {

            for (int i = nbsRiverSrc; i > 0; i--) {
                Coordinate coordinate = isle.findRandomCoordinate(random);
                WorldProcessor river = new River(coordinate, soil);
                river.apply(w);
            }
        }
    }

}
