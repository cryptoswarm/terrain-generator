package world.generator.biome;

import world.Tile;
import world.TileColor;
import world.World;

import java.util.HashSet;

public class Toundra extends Biome {

    private final TileColor color = TileColor.TOUNDRA;
    private final  String type = "toundra";
    private final int maxT = -5;
    private final int minT = -15;
    private final int maxP = 100;
    private final int minP = 0;
    private final Localization localisation;
    public Toundra(Localization localization) {
        this.localisation = localization;
    }

    @Override
    public void apply(World world) {
        if(!validLocalization(localisation,minT,maxT,minP,maxP)) return;

        HashSet<Tile> onIslandtiles = world.getOnIslandTiles();
        for (Tile tile: onIslandtiles) {

            tile.setItem(new Toundra(localisation));
            tile.setBackgroundColor(color);
            tile.setHumidityLevel(0);
            tile.setInOcean(false);

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
