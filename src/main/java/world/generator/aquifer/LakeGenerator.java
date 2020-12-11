package world.generator.aquifer;

import islandSet.Isle;
import randomStrategy.RandomContexte;
import world.Tile;
import world.World;
import world.generator.Generator;
import world.generator.IslandProcessor;
import world.soilType;

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

        for(Isle isle:w.getIsleList()){

            int nbsNape  = random.getRandomInt(nbsWaterSrc);

            IslandProcessor islandProcessor;

            for (int i = nbsNape ;i > 0; i--) {

                Tile centerNape = null;
                try {
                    centerNape = isle.findRandomVegetationTile(random);
                }catch (NullPointerException e){
                    System.out.println("Pas possible de trouver une tuile du biome vegetation");
                    System.exit(0);
                }

                islandProcessor = new Nape();
                islandProcessor.setAquiferCenter(centerNape);
                islandProcessor.setSoil(soil);
                islandProcessor.apply(isle);
            }

            for (int i = nbsWaterSrc-nbsNape; i > 0; i--) {

                Tile centerLake = null;
                try {
                    centerLake = isle.findRandomVegetationTile(random);
                }catch (NullPointerException e){
                    System.out.println("Pas possible de trouver une tuile du biome vegetation");
                    System.exit(0);
                }

                islandProcessor = new Lake();
                islandProcessor.setAquiferCenter(centerLake);
                islandProcessor.setSoil(soil);

                islandProcessor.apply(isle);
            }
        }
    }
}
