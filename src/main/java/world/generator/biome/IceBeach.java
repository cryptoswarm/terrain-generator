package world.generator.biome;

import world.Tile;
import world.TileColor;
import world.World;

import java.util.Set;

import static world.TileColor.ICEBEACH;

public class IceBeach extends Biome {

    private final TileColor color = ICEBEACH;
    private final  String type = "beach";
    private final int maxT = -5;
    private final int minT = -30;
    private final int maxP= 500;
    private final int minP = 0;
    private final Localization localisation;
    public IceBeach(Localization localization) {
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
        Set<Tile> tiles = world.getOnIslandTiles();
        for (Tile tile: tiles) {

            for (Tile neighbor : world.getNeighbor(tile)) {

                if ( neighbor.isInOcean() || neighbor.isInLagoon() ) {

                    tile.setItem(new IceBeach(localisation));
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
