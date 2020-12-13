package world.generator.island;

import geometry.Circle;
import geometry.Coordinate;
import islandSet.Isle;
import randomStrategy.RandomContexte;
import world.Tile;
import world.World;

import java.util.List;
import java.util.Set;

public class CircularIsland extends IslandShape {
    private Circle circle;
    private Isle isle;
    int height;
    int width;

    public CircularIsland( int height, int width) {

        this.height = height;
        this.width = width;
    }

    @Override
    public boolean createIsland(World world, RandomContexte random, int maxAltitude, int diameter) {
        boolean created = false;

        findValidIsland(world, random, diameter);

        if(isle.isValide()){

            Island island = new Atoll( isle, circle, maxAltitude);
            island.apply(world);
            created = true;
        }
        return created;
    }


    private void findValidIsland(World world, RandomContexte random, int diameter){


        List<Coordinate> coordinates = world.getAllCordinates();
        boolean isValide = false;

        while (!coordinates.isEmpty()  && !isValide ) {

            Coordinate coordinate = coordinates.get(random.getRandomInt(coordinates.size()-1));
            Circle cir = new Circle(diameter, random, coordinate);
            Set<Tile> islandTiles = world.getTilesInShape( cir);

            if (validIsland( islandTiles,height,width, world)){
                isValide = true;

                this.isle = new Isle(islandTiles);
                this.circle = cir;
            }else{
                coordinates.remove(coordinate);
            }
        }
    }
}
