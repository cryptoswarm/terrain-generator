package World.Generator.Biome;


import Geometry.Coordinate;
import World.TileColor;
import World.World;
import World.Tile;

import java.util.HashMap;

public class Ocean extends Biome {
    final private TileColor color = TileColor.OCEANBLUE;
    final private String type = "ocean";

    public Ocean(){}

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
            if(tile.getAltitude() == 0 && !tile.isInLagoon()){
                tile.setBiome(new Ocean());
                tile.setBackgroundColor(color);
                tile.setHumidityLevel(-1);
            }
        }
    }
}
