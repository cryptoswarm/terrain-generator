package world.generator.biome;

import geometry.Coordinate;
import world.Tile;
import world.TileColor;
import world.World;
import world.generator.WorldProcessor;

import java.util.HashMap;

public class Vegetation extends Biome implements WorldProcessor {
    private final  TileColor color = TileColor.LIGHTGREEN;
    private final  String type = "vegetation";


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

                tile.setItem(new Vegetation());
                tile.setBackgroundColor(color);
                tile.setHumidityLevel(0);
                tile.setInOcean(false);

            }
        }
    }


}
