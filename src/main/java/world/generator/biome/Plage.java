package world.generator.biome;

import geometry.Coordinate;
import world.TileColor;
import world.World;
import world.Tile;

import java.util.HashMap;

import static world.TileColor.SAND;

public class Plage extends Biome {

    private  final TileColor color = SAND;
    private final  String type = "plage";

    public Plage(){}

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

                        tile.setBiome(new Plage());
                        tile.setBackgroundColor(color);
                        tile.setHumidityLevel(255);
                        tile.setInOcean(false);
                        tile.setInPlage(true);
                        tile.setOnIsland(false); //add
                        break;
                    }
                }
            }
        }
    }
}
