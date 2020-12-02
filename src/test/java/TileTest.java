import geometry.Coordinate;
import org.junit.Test;
import world.Tile;

import static org.junit.Assert.*;

public class TileTest {



    Tile tile1;
    Tile tile2;


    @Test
    public void setUp(){
        tile1 = new Tile(new Coordinate(1, 1, 0));
        tile2 = new Tile(new Coordinate(5, 5, 0));
    }

    @Test
    public void tileEqualityTest1(){
        tile1 = new Tile(new Coordinate(1, 1, 0));
        tile2 = new Tile(new Coordinate(1, 1, 0));

        assertEquals(tile1, tile2);
    }

    @Test
    public void tileEqualityTest2(){
        tile1 = new Tile(new Coordinate(1, 1, 6));
        tile2 = new Tile(new Coordinate(1, 1, 0));

        assertEquals(tile1, tile2);
    }
}
