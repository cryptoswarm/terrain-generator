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

    public boolean createIsland(World world, RandomContexte random, int maxAltitude, Coordinate coordinate) {
        boolean created = false;
        Circle circle = findValidCircle(world, random, (int)coordinate.getX());

        if (circle != null) {
            Island island = new Atoll(tiles, circle, maxAltitude);
            island.apply(world);
            created = true;
        }
        return created;
    }

    private Circle findValidCircle(World world, RandomContexte random, int diameter){
        Circle circle = null;
        List<Coordinate> coordinates = new ArrayList<>(tiles.keySet());
        boolean isValide = false;

        while (!coordinates.isEmpty()  && !isValide ) {
            Coordinate coordinate = coordinates.get(random.getRandomInt(coordinates.size()-1));
            Circle cir= new Circle(diameter, random, coordinate);
            if (validIsland(world, cir,height,width)){
                isValide = true;
                circle = cir;
            }else{
                coordinates.remove(coordinate);
            }
        }
        return circle;
    }
}
