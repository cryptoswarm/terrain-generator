package world.generator.island;

import geometry.Coordinate;
import geometry.Ellipse;
import randomStrategy.RandomContexte;
import world.Tile;
import world.World;

import java.util.ArrayList;
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
    public boolean createIsland(World world, RandomContexte random, int maxAltitude, Coordinate border){
        boolean created = false;

        List<Tile> islandTiles = findValidIsland(world, random, (int)border.getX() );

        if(!islandTiles.isEmpty()) {
            Island island = new Tortuga( islandTiles, ellipse, random, maxAltitude);
            island.apply(world);
            created = true;
        }
        return created;
    }

    private List<Tile> findValidIsland(World world, RandomContexte random, int diameter){

        List<Tile> validIslandTiles = new ArrayList<Tile>();

        int angle = random.getRandomInt(359) + 1;
        List<Coordinate> coordinates = world.getAllCordinates();
        boolean isValide = false;

        while ( !coordinates.isEmpty() && !isValide ) {

            Coordinate c = coordinates.get(random.getRandomInt(coordinates.size()-1));
            Ellipse e = new Ellipse(diameter, random, angle, c);
            List<Tile> islandTiles = world.getIslandTiles( e );

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
