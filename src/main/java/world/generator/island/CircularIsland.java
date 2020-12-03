package world.generator.island;

import geometry.Circle;
import geometry.Coordinate;
import randomStrategy.RandomContexte;
import world.Tile;
import world.World;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class CircularIsland extends IslandShape {
    private Circle circle;
    int height;
    int width;

    public CircularIsland( int height, int width) {

        this.height = height;
        this.width = width;
    }

    @Override
    public boolean createIsland(World world, RandomContexte random, int maxAltitude, int diameter) {
        boolean created = false;

        HashSet<Tile> islandTiles = findValidIsland(world, random, diameter);

        if(!islandTiles.isEmpty()){
            Island island = new Atoll( islandTiles, circle, maxAltitude);
            island.apply(world);
            created = true;
        }
        return created;
    }

    private HashSet<Tile> findValidIsland(World world, RandomContexte random, int diameter){


        HashSet<Tile> validIslandTiles = new LinkedHashSet<>();

        List<Coordinate> coordinates = world.getAllCordinates(); //new ArrayList<>(tiles.keySet());
        boolean isValide = false;

        while (!coordinates.isEmpty()  && !isValide ) {

            Coordinate coordinate = coordinates.get(random.getRandomInt(coordinates.size()-1));
            Circle cir = new Circle(diameter, random, coordinate);
            HashSet<Tile> islandTiles = world.getIslandTiles( cir);

            if (validIsland( islandTiles,height,width, world)){
                isValide = true;
                validIslandTiles = islandTiles;
                this.circle = cir;
            }else{
                coordinates.remove(coordinate);
            }
        }
        return validIslandTiles;
    }
}
