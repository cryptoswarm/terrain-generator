package World.Generator.Island;

import Geometry.Coordinate;
import Geometry.Ellipse;
import RandomStrategy.RandomContexte;
import World.Tile;
import World.World;
import java.util.*;

public class EllipticIsland extends IslandShape {
    HashMap<Coordinate, Tile> tiles;
    int height;
    int width;

    public EllipticIsland(HashMap<Coordinate, Tile> tiles, int height, int width) {
        this.tiles = tiles;
        this.height = height;
        this.width = width;
    }

    @Override
    public boolean inArea(Tile tile, Tile islandCenter, int diameter, int angle) {
        double majorRadius = (double)diameter;
        double minorRadius = (double)diameter / 2;
        float x = tile.getCenter().getX();
        float y = tile.getCenter().getY();
        float h = islandCenter.getCenter().getX();
        float k = islandCenter.getCenter().getY();

        return (Math.pow(((x-h)*Math.cos(angle) + (y-k)*Math.sin(angle)), 2) / Math.pow(diameter, 2)) +
                (Math.pow(((x-h)*Math.sin(angle) - (y-k)*Math.cos(angle)), 2) / Math.pow(minorRadius, 2)) <=1;
    }

    @Override
    public boolean createIsland(World world, RandomContexte random, int maxAltitude, Coordinate border){
        boolean created = false;
        int angle = random.getRandomInt(359) + 1;

        List<Coordinate> coordinates = new ArrayList<>(tiles.keySet());
        Ellipse ellipse = null;
        Boolean validIslandFound = false;

        while (!coordinates.isEmpty()) {
            Coordinate c = coordinates.get(random.getRandomInt(coordinates.size()-1));
            ellipse = new Ellipse((int)border.getX(), random, angle, c);
            Tile t = tiles.get(c);
            if (validIsland(tiles, t, ellipse,height,width)){
                validIslandFound = true;
                break;
            }
            coordinates.remove(c);
        }


        if(validIslandFound) {
            Island island = new Tortuga(tiles, ellipse, random, maxAltitude);
            island.apply(world);
            created = true;
        }
        return created;
    }

}
