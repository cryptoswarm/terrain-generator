package World;

import geometrie.Coordinate;
import java.util.HashMap;
import static World.TileColor.DARKGREEN;

public class Nape implements Aquifer{
    private HashMap<Coordinate, Tile> nape = new HashMap<>();
    TileColor color = DARKGREEN;

    public Nape(Tile tile, HashMap<Coordinate, Tile> vegetation) {
        nape.put(tile.getCenter(), tile);
        for(Tile i : tile.getNeighbors().values()) {
            if(vegetation.get(i.getCenter()) != null) {
                nape.put(i.getCenter(), i);
            }
        }
        for(Tile i: nape.values()) {
            i.setBackgroundColor(color);
            i.setHumidityLevel(5);
            vegetation.remove(i.getCenter());
        }
    }

    @Override
    public HashMap<Coordinate, Tile> getTiles() {
        return nape;
    }
}
