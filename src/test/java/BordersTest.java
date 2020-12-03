import geometry.Circle;
import geometry.Coordinate;
import org.junit.Before;
import org.junit.Test;
import randomStrategy.RandomContexte;
import world.Tile;
import world.World;
import world.generator.island.Atoll;
import world.generator.island.Island;

import java.util.HashSet;
import java.util.LinkedHashSet;

import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;

public class BordersTest {

    RandomContexte randomContexte;
    int diameter;
    Coordinate coordinateCenter;
    Circle circle;
    Island island;
    World world;
    int maxAltitude = 200;
    HashSet<Tile> islandTiles;
    int seed;
    Tile tile1;
    Tile tile2;
    Tile tile3;
    Tile tile4;
    Tile tile5;
    Tile tile6;
    Tile tile7;
    Tile tile8;
    Tile tile9;
    Tile tile10;

    @Before
    public void setUp(){
        seed = 10;

        randomContexte = new RandomContexte(seed);
        world = new World(randomContexte);

        diameter = 10;
        coordinateCenter = new Coordinate(4, 6, 0) ;
        circle = new Circle(diameter, randomContexte, coordinateCenter);

        tile1 = new Tile(new Coordinate(1, 2, 0));
        tile2 = new Tile(new Coordinate(0, 5, 0));
        tile3 = new Tile(new Coordinate(1, 6, 0));
        tile4 = new Tile(new Coordinate(3, 8, 0));
        tile5 = new Tile(new Coordinate(6, 5, 0));
        tile6 = new Tile(new Coordinate(7, 7, 0));
        tile7 = new Tile(new Coordinate(3, 4, 0));
        tile8 = new Tile(new Coordinate(8, 6, 0));
        tile9 = new Tile(new Coordinate(3, 6, 0));
        tile10 = new Tile(new Coordinate(5, 6, 0));

        world.addTile(tile1);
        world.addTile(tile2);
        world.addTile(tile3);
        world.addTile(tile4);
        world.addTile(tile5);
        world.addTile(tile6);
        world.addTile(tile7);
        world.addTile(tile8);
        world.addTile(tile9);
        world.addTile(tile10);

        islandTiles = new LinkedHashSet<>();
        islandTiles.addAll(world.getTiles().values());
        island = new Atoll(islandTiles, circle, maxAltitude);

    }

    @Test
    public void isInOceanTest1(){
        island.setBorders(world);
        assertTrue(tile1.isInOcean());
        assertTrue(tile2.isInOcean());
        assertTrue(tile8.isInOcean());
    }

    @Test
    public void isOnIslandTest1(){

        island.setBorders(world);

        assertTrue(tile3.isOnIsland());
        assertTrue(tile4.isOnIsland());
        assertTrue(tile5.isOnIsland());
        assertTrue(tile6.isOnIsland());
        assertTrue(tile7.isOnIsland());

    }

    @Test
    public void isInLagoonTest1(){

        islandTiles.add(tile9);
        islandTiles.add(tile10);

        island.setBorders(world);

        assertTrue(tile9.isInLagoon());
        assertTrue(tile10.isInLagoon());

    }

    @Test
    public void isInLagoonTest2(){ //change diameter of island to 1000, all tiles will be inside lagoon

        diameter = 1000;
        circle = new Circle(diameter, randomContexte, coordinateCenter);
        island = new Atoll(islandTiles, circle, 100);
        island.setBorders(world);

        for(Tile tile:islandTiles) {
            assertTrue(tile.isInLagoon());
        }
    }

    Tile tile11 = new Tile(new Coordinate(10, 10, 0));
    Tile tile12 = new Tile(new Coordinate(10, 11, 0));
    Tile tile13 = new Tile(new Coordinate(10, 12, 0));
    Tile tile14 = new Tile(new Coordinate(10, 14, 0));

    @Test
    public void isInOceanTest2(){ //change diameter of island to 10 and adding more tiles that will be outside island

        world.addTile(tile11);
        world.addTile(tile12);
        world.addTile(tile13);
        world.addTile(tile14);

        diameter = 10;
        circle = new Circle(diameter, randomContexte, coordinateCenter);
        island = new Atoll(islandTiles, circle, 100);
        island.setBorders(world);

        assertTrue(tile11.isInOcean());
        assertTrue(tile12.isInOcean());
        assertTrue(tile13.isInOcean());
        assertTrue(tile14.isInOcean());

    }

    @Test
    public void isInOceanTest3(){ //change the center of the new island, all tiles will be outside this new island.

        coordinateCenter = new Coordinate(50, 50, 0);
        diameter = 10;
        circle = new Circle(diameter, randomContexte, coordinateCenter);
        island = new Atoll(islandTiles, circle, 100);
        island.setBorders(world);

        for(Tile tile:world.getTiles().values()){
            assertTrue(tile.isInOcean());
        }
    }

    Tile tile15 = new Tile(new Coordinate(50, 49, 0));
    Tile tile16 = new Tile(new Coordinate(50, 51, 0));
    Tile tile17 = new Tile(new Coordinate(49, 50, 0));
    Tile tile18 = new Tile(new Coordinate(49, 51, 0));


    @Test
    public void isInLagoonTest3(){ //same island as above, we add a new tile that is close to its center

        coordinateCenter = new Coordinate(50, 50, 0);
        diameter = 10;
        world.addTile(tile15);
        world.addTile(tile16);
        world.addTile(tile17);
        world.addTile(tile18);
        circle = new Circle(diameter, randomContexte, coordinateCenter);
        islandTiles.addAll(world.getTiles().values());
        island = new Atoll(islandTiles, circle, 100);
        island.setBorders(world);

        assertTrue(tile15.isInLagoon());
    }



}
