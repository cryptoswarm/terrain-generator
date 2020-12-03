package world.generator.aquifer;

import islandSet.Isle;
import randomStrategy.RandomContexte;
import world.Tile;
import world.World;
import world.generator.Generator;
import world.generator.IslandProcessor;
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
                Tile tile = isle.findRandomTile(random);
                IslandProcessor river = new River(random);
                river.setSoil(soil);
                river.setAquiferCenter(tile);
                river.apply(isle);
            }
        }
    }
}
