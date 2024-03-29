package world.generator.biome;


import world.Tile;
import world.TileColor;
import world.World;

import java.util.Set;

public class Ocean extends Biome {

    public static final int HUMIDITY_LEVEL = -1;
    public static final int ALTITUDE = 0;
    private final  TileColor color = TileColor.OCEANBLUE;
    private final  String type = "ocean";

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

        Set<Tile> oceanTiles = world.getOceanTiles();
        for (Tile tile :oceanTiles) {

                tile.setItem(new Ocean());
                tile.setBackgroundColor(color);
                tile.setHumidityLevel(HUMIDITY_LEVEL);
                tile.setAltitude(ALTITUDE);
                tile.setOnIsland(false);
                tile.setInLagoon(false);
        }
    }
}
