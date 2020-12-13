import geometry.Coordinate;
import geometry.Line;
import islandSet.Isle;
import org.junit.Before;
import org.junit.Test;
import randomStrategy.RandomContexte;
import world.Tile;
import world.TileColor;
import world.World;
import world.borders.Border;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

public class WorldTest {

    World world;

    Isle isle;
    HashMap<Coordinate, Tile> islandTiles;

    Tile tile1;
    Tile tile2;
    Tile tile3;
    Tile tile4;

    Border border1;
    Border border2;
    Border border3;
    Border border4;

    Coordinate center1;
    Coordinate center2;
    Coordinate center3;
    Coordinate center4;

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

    RandomContexte randomContexte;



    @Before
    public void setUp(){


        islandTiles = new HashMap<>();

        center1 = new Coordinate(2, 1.5f, 0);
        center2 = new Coordinate(5, 2, 0);
        center3 = new Coordinate(5, 5, 0);
        center4 = new Coordinate(4, 4, 0);

        tile1 = new Tile(center1);
        border1 = new Border();
        tile2 = new Tile(center2);
        border2 = new Border();
        tile3 = new Tile(center3);
        border3 = new Border();
        tile4 = new Tile(center4);
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

        islandTiles.put(center1, tile1);
        islandTiles.put(center2, tile2);
        islandTiles.put(center3, tile3);
        islandTiles.put(center4, tile4);

        randomContexte = new RandomContexte(10);

        isle = new Isle( new HashSet<>(islandTiles.values()));
        world = new World(randomContexte);
        world.addTile(tile1);
        world.addTile(tile2);
        world.addTile(tile3);
        world.addTile(tile4);
        world.addArchipelago(isle);

    }
    @Test
    public void getATileTest(){
        assertEquals(tile1, islandTiles.get(center1));
        assertEquals(tile2, islandTiles.get(center2));
        assertEquals(tile3, islandTiles.get(center3));
        assertNotEquals(tile4, islandTiles.get(center3));
    }

    @Test
    public void getLineColorTest1(){

        line3.setColor(TileColor.DARKGREEN);
        assertEquals(TileColor.DARKGREEN.toString(), world.getLineColor(line3));
    }

    @Test
    public void getLineColorTest2(){

        line5.setColor(TileColor.WATERBLUE);
        assertEquals(TileColor.WATERBLUE.toString(), world.getLineColor(line5));
    }
    @Test
    public void getLineColorTest3(){

        line3.setColor(TileColor.WATERBLUE);
        assertEquals(TileColor.WATERBLUE.toString(), world.getLineColor(line3));

    }
    @Test
    public void getLineColorTest4(){

        line3.setColor(null);
        assertEquals("0:0:0:0", world.getLineColor(line3));

    }

    @Test
    public void getRoads1(){

        world.setRoads(tile1, tile2);
        ArrayList<Line> lines = world.getRoads();
        Line line12 = new Line(tile1.getCenter(), tile2.getCenter());
        assertTrue(lines.contains(line12));

    }

    @Test
    public void getRoads2(){

        world.setRoads(tile2, tile3);
        world.setRoads(tile3, tile4);
        ArrayList<Line> lines = world.getRoads();

        Line line23 = new Line(tile2.getCenter(), tile3.getCenter());
        Line line34 = new Line(tile3.getCenter(), tile4.getCenter());

        assertEquals(2, world.getRoads().size());
        assertTrue(lines.contains(line23));
        assertTrue(lines.contains(line34));

    }

    @Test
    public void getNeighbor1(){

        assertEquals(3, world.getNeighbor(coordinate3).size());
        assertTrue( world.getNeighbor(coordinate3).contains(tile1));

    }

    @Test
    public void getNeighbor2(){

        assertEquals(3, world.getNeighbor(coordinate5).size());
        assertTrue( world.getNeighbor(coordinate5).contains(tile3));

    }

    @Test
    public void getNeighbor3(){

        assertEquals(3, world.getNeighbor(coordinate5).size());
        assertFalse( world.getNeighbor(coordinate5).contains(tile1));

    }

    @Test
    public void getNeighbor4(){

        assertEquals(4, world.getNeighbor(line5).size());
        assertTrue( world.getNeighbor(line5).contains(tile2));

    }

    @Test
    public void getNeighbor5(){

        assertEquals(3, world.getNeighbor(line6).size());
        assertTrue( world.getNeighbor(line6).contains(tile4));

    }

    @Test
    public void getNeighbor6(){

        assertEquals(3, world.getNeighbor(line6).size());
        assertFalse( world.getNeighbor(line6).contains(tile1));
    }

    @Test
    public void getNeighbor7(){

        assertEquals(3, world.getNeighbor(tile1).size());
        assertTrue( world.getNeighbor(tile1).contains(tile2));
        assertTrue( world.getNeighbor(tile1).contains(tile4));
        assertTrue( world.getNeighbor(tile1).contains(tile1));
    }
















}
