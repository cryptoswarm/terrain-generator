package World;

import geometrie.Coordinate;

import java.util.HashMap;

public class Lagoon implements Biome {

    private HashMap<Coordinate, Tile> lagoon;
    final private TileColor color = TileColor.WATERBLUE;

    public Lagoon() {
        this.lagoon = new HashMap<>();
    }


    @Override
    public HashMap<Coordinate, Tile> getTiles() {
        return lagoon;
    }

    @Override
    public void addToBiome(Tile tile) {
        lagoon.put(tile.getCenter(), tile);
        tile.setBackgroundColor(color);
    }
}
