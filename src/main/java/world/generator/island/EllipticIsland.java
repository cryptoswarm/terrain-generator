package world.generator.island;

import geometry.Coordinate;
import geometry.Ellipse;
import islandSet.Isle;
import randomStrategy.RandomContexte;
import world.Tile;
import world.World;

import java.util.List;
import java.util.Set;

public class EllipticIsland extends IslandShape {

    private Ellipse ellipse;
    private Isle isle;
    int height;
    int width;

    public EllipticIsland( int height, int width) {

        this.height = height;
        this.width = width;
    }

    @Override
    public boolean createIsland(World world, RandomContexte random, int maxAltitude, int diameter){
        boolean created = false;

        findValidIsland(world, random, diameter );
        if(isle.isValide()){

            Island island = new Tortuga( isle, ellipse, random, maxAltitude);
            island.apply(world);
            created = true;
        }
        return created;
    }


    private void findValidIsland(World world, RandomContexte random, int diameter){

        int angle = random.getRandomInt(359) + 1;
        List<Coordinate> coordinates = world.getAllCordinates();
        boolean isValide = false;

        while ( !coordinates.isEmpty() && !isValide ) {

            Coordinate c = coordinates.get(random.getRandomInt(coordinates.size()-1));
            Ellipse e = new Ellipse(diameter, random, angle, c);
            Set<Tile> islandTiles = world.getTilesInShape( e );

            if ( validIsland( islandTiles, height, width, world) ) {
                isValide = true;
                isle = new Isle(islandTiles);
                this.ellipse = e;
            }else {
                coordinates.remove(c);
            }
        }
    }




}
