package world.generator.biome;

import geometry.Coordinate;
import world.Tile;
import world.TileColor;
import world.World;
import world.generator.WorldProcessor;

import java.util.HashMap;

public class Taiga implements Biome, WorldProcessor {
    private final TileColor color = TileColor.TAIGA;
    private final String type = "taiga";
    private final int maxTemperature = 5;
    private final int minTemperature = -5;
    private final int maxPrecipitation = 200;
    private final int minPrecipitation = 50;
    private final Localization localisation;

    public Taiga(Localization localization) {
        this.localisation = localization;
    }

    @Override
    public void apply(World world) {
        //speed up the program, valid until we add temperature to the mix
        if (!validLocalization()) return;

        HashMap<Coordinate, Tile> tiles = world.getTiles();
        for (Tile tile : tiles.values()) {
            if (tile.isOnIsland() && validLocalization()) {
                tile.setBiome(new Taiga(localisation));
                tile.setBackgroundColor(color);
                tile.setHumidityLevel(0);
                tile.setInOcean(false);
            }
        }
    }

    private Boolean validLocalization() {
        int t = localisation.getTemperatureAverage();
        int p = localisation.getPrecipitationAverage();
        if (maxTemperature < 0) {
            return p <= maxPrecipitation && p >= minPrecipitation &&
                    t >= maxTemperature && t <= minTemperature;
        }
        return p <= maxPrecipitation && p >= minPrecipitation &&
                t <= maxTemperature && t >= minTemperature;
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

