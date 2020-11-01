package map;

import geometrie.Dot;
import java.util.HashMap;
import static map.TileColor.SAND;

public class Plage implements Biome{


    private HashMap<Dot, Tile>  plage;
    private TileColor color = SAND;


    public Plage(){
        this.plage = new HashMap<>();
    }

    public TileColor getColor() {
        return color;
    }

    @Override
    public void addToBiome(Tile tile) {
        plage.put(tile.getTileCenter(), tile);
        tile.setBackgroundColor(color);
    }

    @Override
    public HashMap<Dot, Tile> getTiles() {
        return plage;
    }
}
