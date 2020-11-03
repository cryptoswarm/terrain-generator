package World;

import Geometry.Coordinate;

import java.util.HashMap;

public class BiomeGenerator implements Generator {
    public void generate(HashMap<Coordinate, Tile> tiles, Island island) {
        //Ocean
        for (Tile tile : tiles.values()) {
            if(!island.isOnIsland(tile)) tile.setBiome(new Ocean());
        }

        //lagon
        if(island instanceof Atoll) {
            for (Tile tile : island.getTiles().values()) {
                if (((Atoll)island).isInLagon(tile)) tile.setBiome(new Lagoon());
            }
        }

        //plage
        for (Tile tile : island.getTiles().values()) {
            if(tile.getBiome() == null) {
                for (Tile neighbor : tile.getNeighbors().values()) {
                    if (neighbor.getBiome() != null && neighbor.getBiome() instanceof Ocean || neighbor.getBiome() instanceof Lagoon) {
                        tile.setBiome(new Plage());
                        break;
                    }
                }
            }
        }

        //vegetation
        for (Tile tile : island.getTiles().values()) {
            if(tile.getBiome() == null) tile.setBiome(new Vegetation());
        }
    }

}
