package World;

import World.Island.Atoll;
import World.Island.IslandStrategy;
import World.Island.Tortuga;
import java.util.HashSet;

import static World.WorldGenerator.getRandom;

public class IslandGenerator implements Generator {
    private String islandType;
    private int width;
    private int height;
    public IslandGenerator(String islandType, int width, int height) {
        this.islandType = islandType;
        this.width = width;
        this.height = height;
    }

    @Override
    public void generate(World w) {
        IslandStrategy island;
        HashSet<Tile> tiles = w.getTiles();
        if (islandType.equals("atoll")) {
            island = new Atoll(100);
        } else if (islandType.equals("tortuga")) {
            island = new Tortuga(100);
        }
    }
}
