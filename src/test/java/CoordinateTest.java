import geometry.Coordinate;
import geometry.Line;
import org.junit.Before;
import org.junit.Test;
import world.Tile;

import static org.junit.Assert.*;

public class CoordinateTest {

    Tile tile1;
    Tile tile2;
    Coordinate coordinate1;
    Coordinate coordinate2;
    Coordinate coordinate3;
    Coordinate coordinate4;
    Coordinate coordinate5;
    Coordinate coordinate6;
    Coordinate coordinate10;

    Line line1;
    Line line2;
    Line line3;
    Line line4;
    Line line5;
    Line line6;
    Line line7;


    @Before
    public void setUp(){
        tile1 = new Tile(new Coordinate(0, 0,0));
        tile2 = new Tile(new Coordinate(6, 1999,0));

        coordinate1 = new Coordinate(1, 3, 0);
        coordinate2 = new Coordinate(3, 1, 0);
        coordinate3 = new Coordinate(3, 4, 0);
        coordinate4 = new Coordinate(2, 5, 0);
        coordinate5 = new Coordinate(5, 3, 0);
        coordinate6 = new Coordinate(4, 6, 0);

        tile1.addCorners(coordinate1);
        tile1.addCorners(coordinate2);
        tile1.addCorners(coordinate3);
        tile1.addCorners(coordinate4);

        tile2.addCorners(coordinate2);
        tile2.addCorners(coordinate5);
        tile2.addCorners(coordinate6);
        tile2.addCorners(coordinate3);


        line1 = new Line(coordinate1, coordinate2);
        line2 = new Line(coordinate2, coordinate3);
        line3 = new Line(coordinate3, coordinate4);
        line4 = new Line(coordinate4, coordinate1);

        line5 = new Line(coordinate2, coordinate5);
        line6 = new Line(coordinate5, coordinate6);
        line7 = new Line(coordinate6, coordinate3);


        tile1.addBorder(line1);
        tile1.addBorder(line2);
        tile1.addBorder(line3);
        tile1.addBorder(line4);

        tile2.addBorder(line5);
        tile2.addBorder(line6);
        tile2.addBorder(line7);
        tile2.addBorder(line2);


    }

    @Test
    public void  cornersSizeTest() {

        assertEquals(4, tile1.getCorner().size());
        assertEquals(4, tile2.getCorner().size());

    }


    @Test
    public void testEquals() {
        Coordinate c1 = new Coordinate(5,5,5);
        Coordinate c2 = new Coordinate(5,5,0);
        assertEquals(c1,c2);
        c1 = new Coordinate(5,5,50);
        assertEquals(c1,c2);

    }

    @Test
    public void testDistanceSameCoordinate() {
        double expected = 0;
        Coordinate c1 = new Coordinate(5,5,5);
        Coordinate c2 = new Coordinate(5,5,0);
        assertEquals(expected, c1.distance(c2), 0.0);
    }

    @Test
    public void testDistanceDifferentCoordinate() {
        double expected = 5;
        Coordinate c1 = new Coordinate(0,5,5);
        Coordinate c2 = new Coordinate(5,5,0);

        assertEquals(expected, c1.distance(c2), 0.0);
    }

    @Test
    public void coordinateEqualityTest(){
        Coordinate coordinate1 = new Coordinate(1,1,0);
        Coordinate coordinate2 = new Coordinate(1,1,0);

        assertEquals(coordinate1, coordinate2);

        Coordinate coordinate3 = new Coordinate(3,1,3);
        Coordinate coordinate4 = new Coordinate(3,1,0);

        assertEquals(coordinate3, coordinate4);
    }

    @Test
    public void coordinateHeighTest1(){
        coordinate10 = new Coordinate(3,6,7);
        assertNotEquals(-1, coordinate10.getZ());
    }

    @Test
    public void  coordinateHeighTest2() {

        for (Coordinate coordinate : tile1.getCorner()) {
            assertEquals(0, coordinate.getZ(), 0);
        }
    }

    @Test
    public void  coordinateHeighTest3() {
        for (Coordinate coordinate : tile1.getCorner()) {
            if (coordinate.getZ() == 0) {
                coordinate.setZ(5);
            }
        }

        for (Coordinate coordinate : tile1.getCorner()) {
            assertEquals(5, coordinate.getZ(), 0);
        }
    }

    @Test
    public void  coordinateHeighTest4() {

        for(Coordinate coordinate:tile2.getCorner()){
            if(coordinate.getZ() == 0) {
                coordinate.setZ(8);
            }
        }

        tile1.getCorner().retainAll(tile2.getCorner());

        assertEquals(8, coordinate2.getZ(), 0);
    }


    @Test
    public void  coordinateHeighTest5() {  //Une fois que l'altitude d'une coordonnée est appliqué,
                                            // l'altitude de cette coordonnée devrait rester la meme pour une autre tuile
                                            //partagant cette coordonnée

        for (Coordinate coordinate : tile1.getCorner()) {
            if (coordinate.getZ() == 0) {
                coordinate.setZ(15);
            }
        }

        for (Coordinate coordinate : tile1.getCorner()) {
            assertEquals(15, coordinate.getZ(), 0);
        }

        for(Coordinate coordinate:tile2.getCorner()){
            if(coordinate.getZ() == 0) {
                coordinate.setZ(16);
            }
        }

        tile1.getCorner().retainAll(tile2.getCorner());

        assertEquals(15, coordinate2.getZ(), 0);

        for(Coordinate coordinate:tile2.getCorner()){
            if(coordinate.getZ() == 0) {
                coordinate.setZ(20);
            }
        }

        for(Coordinate coordinate:tile2.getCorner()){
            assertNotEquals(20, coordinate.getZ(), 0);
        }
    }



    @Test
    public void  commonCoordinateTest() {

        int common = 0;
        for(Coordinate coordinate:tile1.getCorner()){
            for(Coordinate coordinatex:tile2.getCorner()){
                if(coordinate.equals(coordinatex)){
                    ++common;
                }
            }
        }
        assertEquals(2, common);
    }

    @Test
    public void common(){
        Tile tile4 = new Tile(new Coordinate(0, 0,0));
        Tile tile5 = new Tile(new Coordinate(6, 1999,0));

        Coordinate coordinate1 = new Coordinate(1, 3, 0);
        Coordinate coordinate2 = new Coordinate(3, 1, 0);
        Coordinate coordinate3 = new Coordinate(3, 4, 0);
        Coordinate coordinate4 = new Coordinate(2, 5, 0);
        Coordinate coordinate5 = new Coordinate(5, 3, 0);
        Coordinate coordinate6 = new Coordinate(4, 6, 0);

        tile4.addCorners(coordinate1);
        tile5.addCorners(coordinate1);

        assertTrue( tile4.getCorner().contains(coordinate1));
        assertTrue( tile5.getCorner().contains(coordinate1));
        Coordinate coordinate8 = new Coordinate(1, 3, 1000);
        assertTrue( tile4.getCorner().contains(coordinate8));
    }

}
