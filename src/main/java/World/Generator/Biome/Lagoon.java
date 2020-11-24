package World.Generator.Biome;

import Geometry.Coordinate;
import World.TileColor;
import World.World;
import  World.Tile;

import java.util.HashMap;

public class Lagoon extends Biome {
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
        HashMap<Coordinate, Tile> tiles = world.getTiles();
        for (Tile tile : tiles.values()) {
            if(tile.isInLagoon()){
                tile.setBiome(new Lagoon());
                tile.setBackgroundColor(color);
                tile.setHumidityLevel(-1);
                tile.setInOcean(false);
            }
        }
    }
}
