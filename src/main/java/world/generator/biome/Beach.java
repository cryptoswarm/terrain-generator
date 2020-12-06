package world.generator.biome;

import geometry.Coordinate;
import world.Tile;
import world.TileColor;
import world.World;
import world.generator.WorldProcessor;

import java.util.HashMap;

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
        HashMap<Coordinate, Tile> tiles = world.getTiles();
        for (Tile tile: tiles.values()) {
            if ( tile.isOnIsland()  ) {
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
}
