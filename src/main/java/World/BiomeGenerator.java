package World;

import Geometry.Coordinate;

import java.util.HashMap;
import java.util.HashSet;

public class BiomeGenerator implements Generator {

    public BiomeGenerator() {}

    public void generate(World w) {
        HashSet<Tile> tiles = w.getTiles();
        for (Tile tile : tiles) {
            if(!tile.isOnIsland()) {
                tile.setBiome(new Ocean());
                tile.setBackgroundColor(tile.getBiome().getColor());
            } else {
                if (tile.isInLagoon()) {
                    tile.setBiome(new Lagoon());
                    tile.setBackgroundColor(tile.getBiome().getColor());
                } else {
                    for (Tile neighbor : tile.getNeighbors()) {
                        if (neighbor.getBiome() instanceof Ocean || neighbor.getBiome() instanceof Lagoon) {
                            tile.setBiome(new Plage());
                            tile.setBackgroundColor(tile.getBiome().getColor());
                            break;
                        }
                    }

                    if(tile.getBiome() == null) {
                        tile.setBiome(new Vegetation());
                        tile.setBackgroundColor(tile.getBiome().getColor());
                    }
                }

            }
        }
    }

}
