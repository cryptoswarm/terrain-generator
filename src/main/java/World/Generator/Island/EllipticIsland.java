package World.Generator.Island;

import Geometry.Coordinate;
import Geometry.Ellipse;
import RandomStrategy.RandomContexte;
import World.Tile;
import World.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    public boolean createIsland(World world, RandomContexte random, int maxAltitude, Coordinate border){
        boolean created = false;
        Ellipse ellipse = findValidEllipse(world,random,(int)border.getX());

        if(ellipse != null) {
            Island island = new Tortuga(tiles, ellipse, random, maxAltitude);
            island.apply(world);
            created = true;
        }
        return created;
    }

    private Ellipse findValidEllipse(World world, RandomContexte random, int diameter){

        Ellipse ellipse = null;
        int angle = random.getRandomInt(359) + 1;
        List<Coordinate> coordinates = new ArrayList<>(tiles.keySet());
        boolean isValide = false;

        while ( !coordinates.isEmpty() && !isValide ) {

            Coordinate c = coordinates.get(random.getRandomInt(coordinates.size()-1));
            Ellipse e = new Ellipse(diameter, random, angle, c);

            if ( validIsland(world, e, height, width) ) {
                isValide = true;
                ellipse = e;
            }else {
                coordinates.remove(c);
            }
        }
        return ellipse;
    }

}
