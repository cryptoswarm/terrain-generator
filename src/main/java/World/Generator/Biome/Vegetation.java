package World.Generator.Biome;

import Geometry.Coordinate;
import World.TileColor;
import World.World;
import World.Tile;
import java.util.HashMap;

public class Vegetation extends Biome {
    final private TileColor color = TileColor.LIGHTGREEN;
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
        HashMap<Coordinate, Tile> tiles = world.getTiles();
        for (Tile tile: tiles.values()) {

            if( tile.isOnIsland() ) {
                tile.setBiome(new Vegetation());
                tile.setBackgroundColor(color);
                tile.setHumidityLevel(0);
                tile.setInOcean(false);
            }
        }
    }
}
