package map;

import geometrie.Coordinate;
import java.util.HashMap;
import static map.TileColor.DARKGREEN;

public class Nape implements Aquifer{
    private HashMap<Coordinate, Tile> nape = new HashMap<Coordinate, Tile>();
    TileColor color = DARKGREEN;

    public Nape(Tile tile, HashMap<Coordinate, Tile> vegetation) {
        nape.put(tile.getCenter(), tile);
        for(Tile i : tile.getNeighbors().values()) {
            if(vegetation.get(i.getCenter()) != null) {
                nape.put(i.getCenter(), i);
                i.setHumidityLevel(1000);
            }
        }
        for(Tile i: nape.values()) i.setBackgroundColor(color);
    }

    @Override
    public HashMap<Coordinate, Tile> getTiles() {
        return nape;
    }
}
