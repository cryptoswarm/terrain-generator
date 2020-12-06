package world.generator.biome;

import geometry.Coordinate;
import world.Tile;
import world.TileColor;
import world.World;

import java.util.HashMap;

public class Taiga extends Biome {
    private final TileColor color = TileColor.TAIGA;
    private final String type = "taiga";
    private final int maxT = 5;
    private final int minT = -5;
    private final int maxP = 200;
    private final int minP = 50;
    private final Localization localisation;

    public Taiga(Localization localization) {
        this.localisation = localization;
    }

    @Override
    public void apply(World world) {
        if (!validLocalization(localisation,minT,maxT,minP,maxP)) return;

        HashMap<Coordinate, Tile> tiles = world.getTiles();
        for (Tile tile : tiles.values()) {
            if (tile.isOnIsland()) {
                tile.setItem(new Taiga(localisation));
                tile.setBackgroundColor(color);
                tile.setHumidityLevel(0);
                tile.setInOcean(false);
            }
        }
    }

    @Override
    public TileColor getColor() {
        return color;
    }

    @Override
    public String getType() {
        return type;
    }

}

