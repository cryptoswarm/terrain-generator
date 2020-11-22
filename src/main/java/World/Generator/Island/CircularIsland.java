package World.Generator.Island;

import Geometry.Circle;
import Geometry.Coordinate;
import RandomStrategy.RandomContexte;
import World.Tile;
import World.World;

import java.util.*;

public class CircularIsland extends IslandShape {
    HashMap<Coordinate, Tile> tiles;
    int height;
    int width;

    public CircularIsland(HashMap<Coordinate, Tile> tiles, int height, int width) {
        this.tiles = tiles;
        this.height = height;
        this.width = width;
    }

    @Override
    public boolean inArea(Tile tile, Tile tileCenter, int diameter, int angle ){
        double radius = (double)diameter/2;

        return Math.pow((tile.getCenter().getX() - tileCenter.getCenter().getX()), 2) +
                Math.pow((tile.getCenter().getY() - tileCenter.getCenter().getY()), 2) <= Math.pow(radius, 2);
    }

    public boolean createIsland(World world, RandomContexte random, int maxAltitude, Coordinate coordinate) {
        Island island;
        boolean created = true;
        Circle circle;
        int angle = 360;



        Tile tile = findRandomTile(tiles, angle, coordinate, height,width);
        if (tile != null) {
            circle = new Circle((int)coordinate.getX(), random, tile.getCenter());
            island = new Atoll(tiles, circle, maxAltitude);
            island.apply(world);
        } else {
            created = false;
        }

        return created;
    }
}
