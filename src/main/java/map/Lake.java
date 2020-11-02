
package map;

import geometrie.Coordinate;
import java.util.HashMap;

import static map.TileColor.WATERBLUE;

public class Lake implements Aquifer{
    private HashMap<Coordinate, Tile> lake = new HashMap<Coordinate, Tile>();
    TileColor color = WATERBLUE;

    public Lake(Tile tile, HashMap<Coordinate, Tile> vegetation) {
        lake.put(tile.getCenter(), tile);
        for(Tile i : tile.getNeighbors().values()) {
            if(vegetation.get(i.getCenter()) != null) lake.put(i.getCenter(), i);
        }
        for(Tile i: lake.values()) i.setBackgroundColor(color);
    }

    @Override
    public HashMap<Coordinate, Tile> getTiles() {
        return lake;
    }
}
