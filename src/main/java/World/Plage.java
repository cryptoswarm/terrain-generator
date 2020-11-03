package World;

import Geometry.Coordinate;
import java.util.HashMap;
import static World.TileColor.SAND;

public class Plage implements Biome {
    private HashMap<Coordinate, Tile>  plage;
    final private TileColor color = SAND;

    public Plage(){
        this.plage = new HashMap<>();
    }

    @Override
    public void addToBiome(Tile tile) {
        plage.put(tile.getCenter(), tile);
        tile.setBackgroundColor(color);
        tile.setHumidityLevel(255);
    }

    @Override
    public HashMap<Coordinate, Tile> getTiles() {
        return plage;
    }
}
