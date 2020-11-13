package World.Generator.Biome;

import World.TileColor;
import World.World;
import  World.Tile;

import java.util.HashSet;

public class Lagoon implements Biome {
    final private TileColor color = TileColor.WATERBLUE;
    final private String type = "lagoon";
    public Lagoon() {}

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
        HashSet<Tile> tiles = world.getTiles();
        for (Tile tile : tiles) {
            if(tile.isInLagoon()){
                tile.setBiome(new Lagoon());
                tile.setBackgroundColor(color);
            }
        }
    }
}
