package world.generator.biome;

import world.Tile;
import world.TileColor;
import world.World;

import java.util.HashSet;

public class Lagoon extends Biome {

    private final TileColor color = TileColor.WATERBLUE;
    private final String type = "lagoon";

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

        HashSet<Tile> lagoonTiles = world.getLagoonTiles();

        for (Tile tile : lagoonTiles) {
            tile.setItem(new Lagoon());
            tile.setBackgroundColor(color);
            tile.setHumidityLevel(-1);
            tile.setInOcean(false);
            tile.setOnIsland(false);
        }
    }
}
