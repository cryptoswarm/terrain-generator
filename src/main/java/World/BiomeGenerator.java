package World;

import Geometry.Coordinate;

import java.util.HashMap;

public class BiomeGenerator implements Generator {
    private HashMap<Coordinate, Tile> tiles;
    public BiomeGenerator(HashMap<Coordinate, Tile> tiles) {
        this.tiles = tiles;
    }

    public void generate() {
        for (Tile tile : tiles.values()) {
            if(!tile.isOnIsland()) {
                tile.setBiome(new Ocean());
                tile.setBackgroundColor(tile.getBiome().getColor());
            } else {
                if (tile.isInLagoon()) {
                    tile.setBiome(new Lagoon());
                    tile.setBackgroundColor(tile.getBiome().getColor());
                } else {
                    for (Tile neighbor : tile.getNeighbors().values()) {
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
