package World.Generator.Biome;

import World.TileColor;
import World.World;
import World.Tile;
import java.util.HashSet;

public class Vegetation extends Biome {

    TileColor color = TileColor.LIGHTGREEN;
    final private String type = "vegetation";


    public Vegetation(){}

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
        for (Tile tile: tiles) {
            if(tile.getBiome() == null){
                tile.setBiome(new Vegetation());
                tile.setBackgroundColor(color);
            }
        }
    }
}
