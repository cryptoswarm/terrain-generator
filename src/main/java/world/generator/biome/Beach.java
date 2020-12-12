package world.generator.biome;

import world.Tile;
import world.TileColor;
import world.World;

import java.util.HashSet;

import static world.TileColor.SAND;

public class Beach extends Biome {

    private  final TileColor color = SAND;
    private final  String type = "beach";

    public Beach(){}

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
        HashSet<Tile> onIsland = world.getOnIslandTiles();
        for (Tile tile: onIsland) {
            for (Tile neighbor : world.getNeighbor(tile)) {

                if ( neighbor.isInOcean() || neighbor.isInLagoon() ) {

                    tile.setItem(new Beach());
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
