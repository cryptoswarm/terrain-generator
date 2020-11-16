package World.Generator.Biome;

import World.TileColor;
import World.World;
import World.Tile;

import java.util.HashSet;

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
        return null;
    }

    @Override
    public void apply(World world) {
        HashSet<Tile> tiles = world.getTiles();
        for (Tile tile: tiles) {
            for (Tile neighbor : tile.getNeighbors()) {
                if (tile.getAltitude() !=0 && neighbor.getAltitude() == 0) {
                    tile.setBiome(new Plage());
                    tile.setBackgroundColor(color);
                    break;
                }
            }
        }
    }
}
