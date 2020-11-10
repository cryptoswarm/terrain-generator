package World;

import Geometry.Coordinate;

import java.util.*;

import static World.WorldGenerator.getRandom;

public class World {
    private int width;
    private int height;
    private String shape;
    private HashSet<Tile> tiles;



    public World() {
        this.tiles = new HashSet<>();
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setShape(String shape) {
        this.shape = shape;
    }
    public String getShape() {
        return shape;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public HashSet<Tile> getTiles() {
        return tiles;
    }

    public void addTile(Tile tile) {
        tiles.add(tile);
    }








}
