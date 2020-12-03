package world.generator.island;

import geometry.Coordinate;
import geometry.Ellipse;
import randomStrategy.RandomContexte;
import world.Tile;
import world.World;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class EllipticIsland extends IslandShape {

    private Ellipse ellipse;
    int height;
    int width;

    public EllipticIsland( int height, int width) {

        this.height = height;
        this.width = width;
    }

    @Override
    public boolean createIsland(World world, RandomContexte random, int maxAltitude, int diameter){
        boolean created = false;

        HashSet<Tile> islandTiles = findValidIsland(world, random, diameter );

        if(!islandTiles.isEmpty()) {
            Island island = new Tortuga( islandTiles, ellipse, random, maxAltitude);
            island.apply(world);
            created = true;
        }
        return created;
    }

    private HashSet<Tile> findValidIsland(World world, RandomContexte random, int diameter){

        HashSet<Tile> validIslandTiles = new LinkedHashSet<>();

        int angle = random.getRandomInt(359) + 1;
        List<Coordinate> coordinates = world.getAllCordinates();
        boolean isValide = false;

        while ( !coordinates.isEmpty() && !isValide ) {

            Coordinate c = coordinates.get(random.getRandomInt(coordinates.size()-1));
            Ellipse e = new Ellipse(diameter, random, angle, c);
            HashSet<Tile> islandTiles = world.getIslandTiles( e );

            if ( validIsland( islandTiles, height, width, world) ) {
                isValide = true;
                validIslandTiles = islandTiles;
                this.ellipse = e;
            }else {
                coordinates.remove(c);
            }
        }
        return validIslandTiles;
    }




}
