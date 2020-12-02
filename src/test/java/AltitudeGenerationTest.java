import geometry.Circle;
import geometry.Coordinate;
import geometry.Line;
import org.junit.Before;
import org.junit.Test;
import randomStrategy.RandomContexte;
import world.Tile;
import world.generator.island.Atoll;
import world.generator.island.Island;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AltitudeGenerationTest {


    Tile tile1;
    Tile tile2;
    Coordinate coordinate1;
    Coordinate coordinate2;
    Coordinate coordinate3;
    Coordinate coordinate4;
    Coordinate coordinate5;
    Coordinate coordinate6;


    Line line1;
    Line line2;
    Line line3;
    Line line4;
    Line line5;
    Line line6;
    Line line7;

    Island island;
    List<Tile> tileList;
    Circle circle;
    RandomContexte randomContexte;
    Coordinate circleCenter;


    @Before
    public void setUp(){

        tile1 = new Tile(new Coordinate(2, 4,-1));
        tile2 = new Tile(new Coordinate(4, 3,-1));

        coordinate1 = new Coordinate(1, 3, -1);
        coordinate2 = new Coordinate(3, 1, -1);
        coordinate3 = new Coordinate(3, 4, -1);
        coordinate4 = new Coordinate(2, 5, -1);
        coordinate5 = new Coordinate(5, 3, -1);
        coordinate6 = new Coordinate(4, 6, -1);

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

        tileList= new ArrayList<>();
        tileList.add(tile1);
        tileList.add(tile2);

        circleCenter = new Coordinate(3, 9, 0);

        randomContexte = new RandomContexte(10);

        circle = new Circle(20, randomContexte, circleCenter);
        island = new Atoll(tileList, circle, 50);
    }

    @Test
    public void setAltWithoutRepetitionTest(){

        assertEquals(5.09902, tile1.getCenter().distance(circleCenter), 0.001);
        assertEquals(6.082763, tile2.getCenter().distance(circleCenter), 0.001);
    }
    @Test
    public void altEcartTileEachCornerTest(){

        float diffrenceAltEachtile = 8;
        int nbCornersTile1 = tile1.getCorner().size();
        int nbCornersTile2 = tile2.getCorner().size();

        assertEquals(2, diffrenceAltEachtile / nbCornersTile1, 0);
        assertEquals(2, diffrenceAltEachtile / nbCornersTile2, 0);
    }

    @Test
    public void distanceEachCornerFromCenterOfShapeTest1(){


        assertEquals(6.324555, coordinate1.distance(circleCenter), 0.001);
        assertEquals(8, coordinate2.distance(circleCenter), 0.001);
        assertEquals(5, coordinate3.distance(circleCenter), 0.001);
        assertEquals(4.123106, coordinate4.distance(circleCenter), 0.001);

    }

    @Test
    public void validerAltitudeCornersTile1(){

        double currentAlt = 30;
        float diffrenceAltEachtile = 8;


        island.applyAltitudeToTileCorners(tile1, currentAlt, circleCenter, diffrenceAltEachtile);

        assertEquals( 28, coordinate1.getZ(), 0);
        assertEquals( 28, coordinate2.getZ(), 0);
        assertEquals( 32, coordinate3.getZ(), 0);
        assertEquals( 32, coordinate4.getZ(), 0);

    }



    @Test
    public void distanceEachCornerFromCenterOfShapeTest2(){

        assertEquals(8, coordinate2.distance(circleCenter), 0.001);
        assertEquals(5, coordinate3.distance(circleCenter), 0.001);
        assertEquals(6.324555, coordinate5.distance(circleCenter), 0.001);
        assertEquals(3.162278, coordinate6.distance(circleCenter), 0.001);

    }

    @Test
    public void validerAltitudeCornersTile2(){

        double currentAlt = 30;
        float diffrenceAltEachtile = 8;


        island.applyAltitudeToTileCorners(tile2, currentAlt, circleCenter, diffrenceAltEachtile);

        assertEquals( 28, coordinate2.getZ(), 0);
        assertEquals( 32, coordinate3.getZ(), 0);
        assertEquals( 28, coordinate5.getZ(), 0);
        assertEquals( 32, coordinate6.getZ(), 0);

    }


    @Test
    public void validerAltitudeCornersTileInOneIsland(){ //alt should not change if it is already set

        double currentAlt = 30;
        float diffrenceAltEachtile = 8;

        island.applyAltitudeToTileCorners(tile1, currentAlt, circleCenter, diffrenceAltEachtile);


        assertEquals( 28, coordinate1.getZ(), 0);
        assertEquals( 28, coordinate2.getZ(), 0);
        assertEquals( 32, coordinate3.getZ(), 0);
        assertEquals( 32, coordinate4.getZ(), 0);

        currentAlt = 20;
        island.applyAltitudeToTileCorners(tile2, currentAlt, circleCenter, diffrenceAltEachtile);

        assertEquals( 28, coordinate2.getZ(), 0);
        assertEquals( 32, coordinate3.getZ(), 0);
        assertEquals( 18, coordinate5.getZ(), 0);
        assertEquals( 22, coordinate6.getZ(), 0);

    }

}
