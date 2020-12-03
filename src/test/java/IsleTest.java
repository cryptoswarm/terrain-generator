import geometry.Coordinate;
import geometry.Line;
import islandSet.Isle;
import org.junit.Before;
import org.junit.Test;
import world.Tile;
import world.borders.Border;

import java.util.HashSet;
import java.util.LinkedHashSet;

import static org.junit.Assert.*;

public class IsleTest {

    Isle isle;
    HashSet<Tile> islandTiles;

    Tile tile1;
    Tile tile2;
    Tile tile3;
    Tile tile4;

    Border border1;
    Border border2;
    Border border3;
    Border border4;

    Coordinate coordinate1;
    Coordinate coordinate2;
    Coordinate coordinate3;
    Coordinate coordinate4;
    Coordinate coordinate5;
    Coordinate coordinate6;
    Coordinate coordinate7;
    Coordinate coordinate8;
    Coordinate coordinate9;
    Coordinate coordinate10;
    Coordinate coordinate11;
    Coordinate coordinate12;
    Coordinate coordinate13;
    Coordinate coordinate14;

    Line line1;
    Line line2;
    Line line3;
    Line line4;
    Line line5;
    Line line6;
    Line line7;
    Line line8;
    Line line9;
    Line line10;
    Line line11;
    Line line12;
    Line line13;
    Line line14;
    Line line15;
    Line line16;
    Line line17;



    @Before
    public void setUp(){

        islandTiles = new LinkedHashSet<>();

        tile1 = new Tile(new Coordinate(2, 1.5f, 0));
        border1 = new Border();
        tile2 = new Tile(new Coordinate(5, 2, 0));
        border2 = new Border();
        tile3 = new Tile(new Coordinate(5, 5, 0));
        border3 = new Border();
        tile4 = new Tile(new Coordinate(4, 4, 0));
        border4 = new Border();

        coordinate1 = new Coordinate(1, 1, 0);
        coordinate2 = new Coordinate(3, 1, 0);
        coordinate3 = new Coordinate(3, 2, 0);
        coordinate4 = new Coordinate(2, 2, 0);

        coordinate5 = new Coordinate(4, 3, 0);
        coordinate6 = new Coordinate(6, 3, 0);
        coordinate7 = new Coordinate(7, 2, 0);
        coordinate8 = new Coordinate(6, 1, 0);
        coordinate9 = new Coordinate(4, 1, 0);

        coordinate10 = new Coordinate(2, 5, 0);
        coordinate11 = new Coordinate(3, 5, 0);
        coordinate12 = new Coordinate(4, 7, 0);
        coordinate13 = new Coordinate(6, 7, 0);
        coordinate14 = new Coordinate(7, 5, 0);


        line1 = new Line(coordinate1, coordinate4);
        line2 = new Line(coordinate4, coordinate3);
        line3 = new Line(coordinate3, coordinate2);
        line4 = new Line(coordinate2, coordinate1);


        line5 = new Line(coordinate3, coordinate5);
        line6 = new Line(coordinate5, coordinate6);
        line7 = new Line(coordinate6, coordinate7);
        line8 = new Line(coordinate7, coordinate8);
        line9 = new Line(coordinate8, coordinate9);
        line10 = new Line(coordinate9, coordinate3);


        line11 = new Line(coordinate11, coordinate12);
        line12 = new Line(coordinate12, coordinate13);
        line13 = new Line(coordinate13, coordinate14);
        line14 = new Line(coordinate14, coordinate6);
        line15 = new Line(coordinate5, coordinate11);

        line16 = new Line(coordinate11, coordinate10);
        line17 = new Line(coordinate10, coordinate2);




        border1.addTileLines(line1);
        border1.addTileLines(line2);
        border1.addTileLines(line3);
        border1.addTileLines(line4);
        tile1.setBorder(border1);

        border2.addTileLines(line5);
        border2.addTileLines(line6);
        border2.addTileLines(line7);
        border2.addTileLines(line8);
        border2.addTileLines(line9);
        border2.addTileLines(line10);
        tile2.setBorder(border2);

        border3.addTileLines(line6);
        border3.addTileLines(line14);
        border3.addTileLines(line13);
        border3.addTileLines(line12);
        border3.addTileLines(line11);
        border3.addTileLines(line15);
        tile3.setBorder(border3);

        border4.addTileLines(line5);
        border4.addTileLines(line15);
        border4.addTileLines(line16);
        border4.addTileLines(line17);
        border4.addTileLines(line3);
        tile4.setBorder(border4);

        islandTiles.add(tile1);
        islandTiles.add(tile2);
        islandTiles.add(tile3);
        islandTiles.add(tile4);

        isle = new Isle(islandTiles);
    }

    @Test
    public void cornersSize1(){
        assertEquals(4, tile1.getCorner().size());
    }
    @Test
    public void cornersSize2(){
        assertEquals(6, tile2.getCorner().size());
    }
    @Test
    public void cornersSize3(){
        assertEquals(6, tile3.getCorner().size());
    }
    @Test
    public void cornersSize4(){
        assertEquals(5, tile4.getCorner().size());
    }


    @Test
    public void borderssize1(){
        assertEquals(4, tile1.getBorder().size());
    }
    @Test
    public void borderssize2(){
        assertEquals(6, tile2.getBorder().size());
    }
    @Test
    public void borderssize3(){
        assertEquals(6, tile3.getBorder().size());
    }
    @Test
    public void borderssize4(){
        assertEquals(5, tile4.getBorder().size());
    }

    @Test
    public void getLinesTest1(){
        assertEquals(4, isle.getLine(coordinate3).size());
    }

    @Test
    public void getLinesTest2(){
        assertEquals(3, isle.getLine(coordinate5).size());
    }

    @Test
    public void getLinesTest3(){
        assertEquals(3, isle.getLine(coordinate6).size());
    }

    @Test
    public void getLinesTest4(){
        assertEquals(2, isle.getLine(coordinate14).size());
    }


    @Test
    public void verifyLinesTest1(){
        assertEquals(2, isle.getLine(coordinate14).size());
        HashSet<Line> lines = isle.getLine(coordinate14);
        assertTrue(lines.contains(line13));
        assertTrue(lines.contains(line14));
        assertFalse(lines.contains(line17));

    }

    @Test
    public void verifyLinesTest2(){

        assertEquals(4, isle.getLine(coordinate3).size());
        HashSet<Line> lines = isle.getLine(coordinate3);

        assertTrue(lines.contains(line2));
        assertTrue(lines.contains(line3));
        assertTrue(lines.contains(line10));
        assertTrue(lines.contains(line5));
        assertFalse(lines.contains(line4));
        assertFalse(lines.contains(line9));



    }




}
