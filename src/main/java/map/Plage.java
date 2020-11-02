package map;

import geometrie.Coordinate;
import java.util.HashMap;
import static map.TileColor.SAND;

public class Plage implements Biome{


    private HashMap<Coordinate, Tile>  plage;
    private TileColor color = SAND;


    public Plage(){
        this.plage = new HashMap<>();
    }

    public TileColor getColor() {
        return color;
    }

    @Override
    public void addToBiome(Tile tile) {
        plage.put(tile.getCenter(), tile);
        tile.setBackgroundColor(color);
    }

    @Override
    public HashMap<Coordinate, Tile> getTiles() {
        return plage;
    }
}
