package World.Generator.Island;

import Geometry.Coordinate;
import Geometry.Ellipse;
import RandomStrategy.RandomContexte;
import World.World;

import java.util.List;

public class EllipticIsland extends IslandShape {

    int height;
    int width;

    public EllipticIsland( int height, int width) {

        this.height = height;
        this.width = width;
    }

    @Override
    public boolean createIsland(World world, RandomContexte random, int maxAltitude, Coordinate border){
        boolean created = false;
        Ellipse ellipse = findValidEllipse(world,random,(int)border.getX());

        if(ellipse != null) {
            Island island = new Tortuga( ellipse, random, maxAltitude);
            island.apply(world);
            created = true;
        }
        return created;
    }

    private Ellipse findValidEllipse(World world, RandomContexte random, int diameter){

        Ellipse ellipse = null;
        int angle = random.getRandomInt(359) + 1;
        List<Coordinate> coordinates = world.getAllCordinates();
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
