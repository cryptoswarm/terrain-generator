package world.generator.biome;

import world.Tile;
import world.TileColor;
import world.World;

import java.util.HashSet;

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
        HashSet<Tile> onIslandTiles;
        if(!validLocalization(localisation,minT,maxT,minP,maxP)) return;

        //HashMap<Coordinate, Tile> tiles = world.getTiles();
        onIslandTiles = world.getOnIslandTiles();
        for (Tile tile: onIslandTiles) {
            //if( tile.isOnIsland()) {
                tile.setItem(new Desert(localisation));
                tile.setBackgroundColor(color);
                tile.setHumidityLevel(0);
                tile.setInOcean(false);
           // }
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
