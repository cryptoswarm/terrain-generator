
package World;

import Geometry.Coordinate;
import java.util.HashMap;

import static World.TileColor.WATERBLUE;

public class Lake implements Aquifer {
    private HashMap<Coordinate, Tile> lake = new HashMap<>();
    final private TileColor color = WATERBLUE;

    public Lake(Tile tile) {
        lake.put(tile.getCenter(), tile);
        for(Tile i : tile.getNeighbors().values()) {
            if(i.getBiome() instanceof Vegetation) {
                lake.put(i.getCenter(), i);
            }

        }
        for(Tile i: lake.values()) {
            i.setBackgroundColor(color);
            i.setHumidityLevel(5);
            //vegetation.remove(i.getCenter());
        }
    }

    @Override
    public HashMap<Coordinate, Tile> getTiles() {
        return lake;
    }
}
