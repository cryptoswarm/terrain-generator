package world.generator.biome;

import world.Tile;
import world.TileColor;
import world.World;

import java.util.HashSet;

import static world.TileColor.ROCKBEACH;

public class RockBeach extends Biome {

    private  final TileColor color = ROCKBEACH;
    private final  String type = "beach";
    private final int maxT = 10;
    private final int minT = -5;
    private final int maxP= 500;
    private final int minP = 0;
    private final Localization localisation;
    public RockBeach(Localization localization) {
        this.localisation = localization;
    }

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
        if(!validLocalization(localisation,minT,maxT,minP,maxP)) return;
        HashSet<Tile> onIslandtiles = world.getOnIslandTiles();
        for (Tile tile: onIslandtiles) {

            for (Tile neighbor : world.getNeighbor(tile)) {

                if ( neighbor.isInOcean() || neighbor.isInLagoon() ) {

                    tile.setItem(new RockBeach(localisation));
                    tile.setBackgroundColor(color);
                    tile.setHumidityLevel(255);
                    tile.setInOcean(false);
                    tile.setOnIsland(false); //add
                    break;
                }
            }

        }
    }
}
