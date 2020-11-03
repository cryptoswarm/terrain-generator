package World;

import Geometry.Coordinate;

import java.util.HashMap;

import static World.WorldGenerator.getRandom;

public class IslandGenerator implements Generator {
    private HashMap<Coordinate, Tile> tiles;
    private String islandType;
    private int width;
    private int height;
    public IslandGenerator(HashMap<Coordinate, Tile> tiles, String islandType, int width, int height) {
        this.tiles = tiles;
        this.islandType = islandType;
        this.width = width;
        this.height = height;
    }

    @Override
    public void generate() {
        Island island;
        if (islandType.equals("atoll")) {
            island = new Atoll(tiles, getRandom(), width, height);
        } else if (islandType.equals("tortuga")) {
            island = new Tortuga(tiles, getRandom(), width, height);
        }
    }
}
