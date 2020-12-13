package world.generator.biome;

import world.Tile;
import world.TileColor;
import world.World;

import java.util.Set;

public class Savane extends Biome {
    private final TileColor color = TileColor.SAVANE;
    private final  String type = "savane";
    private final int maxT = 30;
    private final int minT = 15;
    private final int maxP = 100;
    private final int minP = 20;
    private final Localization localisation;
    public Savane(Localization localization) {
        this.localisation = localization;
    }

    @Override
    public void apply(World world) {
        if(!validLocalization(localisation,minT,maxT,minP,maxP)) return;

        Set<Tile> onIslandtiles = world.getOnIslandTiles();
        for (Tile tile: onIslandtiles) {

            tile.setItem(new Savane(localisation));
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
