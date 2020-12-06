package world.generator.biome;

import geometry.Coordinate;
import world.Tile;
import world.TileColor;
import world.World;
import world.generator.WorldProcessor;

import java.util.HashMap;

public class Desert implements Biome, WorldProcessor {
    private final  TileColor color = TileColor.DESERT;
    private final  String type = "desert";
    private final int maxTemperature = 30;
    private final int minTemperature = -5;
    private final int maxPrecipitation = 50;
    private final int minPrecipitation = 0;

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

    @Override
    public TileColor getColor() {
        return null;
    }

    @Override
    public String getType() {
        return null;
    }
}
