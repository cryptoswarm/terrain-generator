
package World;

import Geometry.Coordinate;
import java.util.HashMap;
import java.util.HashSet;

import static World.TileColor.WATERBLUE;

public class Lake implements Aquifer {
    private HashSet<Tile> lake = new HashSet<>();
    final private TileColor color = WATERBLUE;

    public Lake(Tile tile) {
        lake.add(tile);
        for(Tile i : tile.getNeighbors().values()) {
            if(i.getBiome() instanceof Vegetation) {
                lake.add(i);
            }

        }
        for(Tile i: lake) {
            i.setBackgroundColor(color);
            i.setHumidityLevel(5);
            //vegetation.remove(i.getCenter());
        }
    }

    @Override
    public HashSet<Tile> getTiles() {
        return lake;
    }
}
