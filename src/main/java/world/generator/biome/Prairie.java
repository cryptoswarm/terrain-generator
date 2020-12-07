package world.generator.biome;

import geometry.Coordinate;
import world.Tile;
import world.TileColor;
import world.World;

import java.util.HashMap;

public class Prairie extends Biome {

    private final TileColor color = TileColor.PRAIRIE;
    private final  String type = "prairie";
    private final int maxT = 20;
    private final int minT = -5;
    private final int maxP = 100;
    private final int minP = 20;
    private final Localization localisation;
    public Prairie(Localization localization) {
        this.localisation = localization;
    }

    @Override
    public void apply(World world) {
        if(!validLocalization(localisation,minT,maxT,minP,maxP)) return;

        HashMap<Coordinate, Tile> tiles = world.getTiles();
        for (Tile tile: tiles.values()) {
            if( tile.isOnIsland()) {
                tile.setItem(new Prairie(localisation));
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
