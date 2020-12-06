package world.generator.biome;

import geometry.Coordinate;
import world.Tile;
import world.TileColor;
import world.World;
import world.generator.WorldProcessor;

import java.util.HashMap;

public class Desert extends Biome {
    private final  TileColor color = TileColor.DESERT;
    private final  String type = "desert";
    private final int maxT = 30;
    private final int minT = -5;
    private final int maxP = 50;
    private final int minP = 0;
    private final Localization localisation;
    public Desert(Localization localization) {
        this.localisation = localization;
    }

    @Override
    public void apply(World world) {
        if(!validLocalization(localisation,minT,maxT,minP,maxP)) return;

        HashMap<Coordinate, Tile> tiles = world.getTiles();
        for (Tile tile: tiles.values()) {
            if( tile.isOnIsland()) {
                tile.setItem(new Desert(localisation));
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
