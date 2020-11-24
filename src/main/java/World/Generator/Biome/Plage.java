package World.Generator.Biome;

import Geometry.Coordinate;
import World.TileColor;
import World.World;
import World.Tile;

import java.util.HashMap;

import static World.TileColor.SAND;

public class Plage extends Biome {

    final private TileColor color = SAND;
    final private String type = "plage";

    public Plage(){}

    @Override
    public TileColor getColor() {
        return color;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void apply(World world) {
        HashMap<Coordinate, Tile> tiles = world.getTiles();
        for (Tile tile: tiles.values()) {
            if ( tile.isOnIsland()  ) {
                for (Tile neighbor : world.getNeighbor(tile)) {

                    if (neighbor.isInOcean() || neighbor.isInLagoon() ) {
                        tile.setBiome(new Plage());
                        tile.setBackgroundColor(color);
                        tile.setHumidityLevel(255);
                        tile.setInOcean(false);
                        break;
                    }
                }
            }
        }
    }
}
