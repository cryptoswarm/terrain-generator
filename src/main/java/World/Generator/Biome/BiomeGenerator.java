package World.Generator.Biome;

import World.World;
import World.Generator.Generator;
import World.Tile;
import java.util.HashSet;

public class BiomeGenerator implements Generator {

    public BiomeGenerator() {}

    public void generate(World w) {
        HashSet<Tile> tiles = w.getTiles();
        for (Tile tile : tiles) {
            if(tile.getAltitude() == 0) {
                if(tile.isInLagoon()){
                    tile.setBiome(new Lagoon());
                } else {
                    tile.setBiome(new Ocean());
                }
            } else {
                for (Tile neighbor : tile.getNeighbors()) {
                    if (neighbor.getAltitude() == 0) {
                            tile.setBiome(new Plage());
                            break;
                    }
                }
                if(tile.getBiome() == null) tile.setBiome(new Vegetation());
            }
            tile.setBackgroundColor(tile.getBiome().getColor());
        }
    }

}
