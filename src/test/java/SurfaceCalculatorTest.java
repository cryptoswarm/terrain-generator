import geometry.Coordinate;
import geometry.Line;
import org.junit.Before;
import org.junit.Test;
import randomStrategy.RandomContexte;
import world.Tile;
import world.World;
import world.generator.calculator.TileAttributesCalculator;
import world.generator.island.IslandGenerator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SurfaceCalculatorTest {

    RandomContexte random;
    World world;
    IslandGenerator islandGenerator;
    Tile tile;
    List<Coordinate> coordinateList;

    TileAttributesCalculator calculator;

    @Before
    public void setUp(){

        random = new RandomContexte( 10);
        world= new World(random);


        String circle = "Circle";
        islandGenerator = new IslandGenerator(circle, 1000, 1000, 200, random, 3);

        Coordinate coordinate1 = new Coordinate(344.75677f, 455.95175f, 0);
        Coordinate coordinate2 = new Coordinate( 362.25214f, 461.47955f, 0);
        Coordinate coordinate3 = new Coordinate( 344.92426f, 418.5051f, 0);
        Coordinate coordinate4 = new Coordinate( 376.90897f, 440.35535f, 0);
        Coordinate coordinate5 = new Coordinate( 362.83347f, 415.10773f, 0);
        Coordinate coordinate6 = new Coordinate( 368.75397f, 458.82437f, 0);
        Coordinate coordinate7 = new Coordinate( 337.9639f, 426.35175f, 0);



        coordinateList= new ArrayList<>();
        coordinateList.add(coordinate1);
        coordinateList.add(coordinate2);
        coordinateList.add(coordinate3);
        coordinateList.add(coordinate4);
        coordinateList.add(coordinate5);
        coordinateList.add(coordinate6);
        coordinateList.add(coordinate7);

        tile = new Tile(new Coordinate(0,0,0));

        tile.addCorners(coordinate1);
        tile.addCorners(coordinate2);
        tile.addCorners(coordinate3);
        tile.addCorners(coordinate4);
        tile.addCorners(coordinate5);
        tile.addCorners(coordinate6);
        tile.addCorners(coordinate7);

        Line line1 = new Line(coordinate1, coordinate2);
        Line line2 = new Line(coordinate2, coordinate3);
        Line line3 = new Line(coordinate3, coordinate4);
        Line line4 = new Line(coordinate4, coordinate5);
        Line line5 = new Line(coordinate5, coordinate6);
        Line line6 = new Line(coordinate6, coordinate7);

        world.addTile(tile);
        tile.addBorder(line1);
        tile.addBorder(line2);
        tile.addBorder(line3);
        tile.addBorder(line4);
        tile.addBorder(line5);
        tile.addBorder(line6);

        calculator = new TileAttributesCalculator();
    }

    @Test
    public void checkSurface1(){ ////unknown shape


        assertEquals(72.3671875,  calculator.findTileSurface(tile), 0.001 );
    }


    @Test
    public void checkSurface2(){ //unknown shape

        coordinateList.clear();

        Tile tile1 = new Tile(new Coordinate(6,6,0));

        Coordinate x1 = new Coordinate(4, 10, 0);
        Coordinate x2 = new Coordinate(9, 7, 0);
        Coordinate x3 = new Coordinate(11, 2, 0);
        Coordinate x4 = new Coordinate(2, 2, 0);


        coordinateList.add(x1);
        coordinateList.add(x2);
        coordinateList.add(x3);
        coordinateList.add(x4);




        Line line1 = new Line(new Coordinate(4, 10, 0), new Coordinate(9, 7, 0));
        Line line2 = new Line(new Coordinate(9, 7, 0), new Coordinate(11, 2, 0));
        Line line3 = new Line(new Coordinate(11, 2, 0), new Coordinate(2, 2, 0));
        Line line4 = new Line(new Coordinate(2, 2, 0), new Coordinate(4, 10, 0));

        tile1.addBorder(line1);
        tile1.addBorder(line2);
        tile1.addBorder(line3);
        tile1.addBorder(line4);

        tile1.addCorners(x1);
        tile1.addCorners(x2);
        tile1.addCorners(x3);
        tile1.addCorners(x4);


        assertEquals(45.5, calculator.findTileSurface(tile1), 0.0001);

    }


    @Test
    public void checkSurface3(){ //unknown shape

        coordinateList.clear();

        Tile tile1 = new Tile(new Coordinate(3,3,0));

        Coordinate x1 = new Coordinate(2.66f, 4.71f, 0);
        Coordinate x2 = new Coordinate(5, 3.5f, 0);
        Coordinate x3 = new Coordinate(3.63f, 2.52f, 0);
        Coordinate x4 = new Coordinate(4, 1.6f, 0);
        Coordinate x5 = new Coordinate(1.9f, 1, 0);
        Coordinate x6 = new Coordinate(0.72f, 2.28f, 0);
        Coordinate x7 = new Coordinate(2.66f, 4.71f, 0);

        coordinateList.add(x1);
        coordinateList.add(x2);
        coordinateList.add(x3);
        coordinateList.add(x4);
        coordinateList.add(x5);
        coordinateList.add(x6);
        coordinateList.add(x7);




        Line line1 = new Line(x1, x2);
        Line line2 = new Line(x2, x3);
        Line line3 = new Line(x3, x4);
        Line line4 = new Line(x4, x5);
        Line line5 = new Line(x5, x6);
        Line line6 = new Line(x6, x7);

        tile1.addBorder(line1);
        tile1.addBorder(line2);
        tile1.addBorder(line3);
        tile1.addBorder(line4);
        tile1.addBorder(line5);
        tile1.addBorder(line6);

        tile1.addCorners(x1);
        tile1.addCorners(x2);
        tile1.addCorners(x3);
        tile1.addCorners(x4);
        tile1.addCorners(x5);
        tile1.addCorners(x6);


        assertEquals(39.44199, calculator.multiplyXByYNextCoordinate( coordinateList ), 0.001 );
        assertEquals(56.09299, calculator.multiplyYByXNextCoordinate(coordinateList), 0.1 );
        assertEquals(8.3255, calculator.findTileSurface(tile1), 0.1 );



    }


    @Test
    public void checkSurface4(){  //triangle

        coordinateList.clear();

        Tile tile1 = new Tile(new Coordinate(3,4,0));


        Coordinate x1 = new Coordinate(2f, 1f, 0);
        Coordinate x2 = new Coordinate(8, 9f, 0);
        Coordinate x3 = new Coordinate(1f, 8f, 0);

        coordinateList.add(x1);
        coordinateList.add(x2);
        coordinateList.add(x3);

        coordinateList.add(x1); //we add this coordinate for the sake of test


        Line line1 = new Line(x1, x2);
        Line line2 = new Line(x2, x3);
        Line line3 = new Line(x3, x1);


        tile1.addBorder(line1);
        tile1.addBorder(line2);
        tile1.addBorder(line3);

        tile1.addCorners(x1);
        tile1.addCorners(x2);
        tile1.addCorners(x3);



        assertEquals(83, calculator.multiplyXByYNextCoordinate( coordinateList ), 0.001 );
        assertEquals(33, calculator.multiplyYByXNextCoordinate(coordinateList), 0.1 );
        assertEquals(25, calculator.findTileSurface(tile1), 0.1 );

    }

    @Test
    public void checkSurface5(){  //square

        coordinateList.clear();

        Tile tile1 = new Tile(new Coordinate(17,15,0));

        Coordinate x1 = new Coordinate(7f, 4f, 0);
        Coordinate x2 = new Coordinate(29, 4f, 0);
        Coordinate y1 = new Coordinate(29f, 26f, 0);
        Coordinate y2 = new Coordinate(7f, 26f, 0);

        coordinateList.add(x1);
        coordinateList.add(x2);
        coordinateList.add(y1);
        coordinateList.add(y2);


        Line line1 = new Line(new Coordinate(7f, 4f, 0), new Coordinate(29, 4f, 0));
        Line line2 = new Line(new Coordinate(29, 4f, 0), new Coordinate(29f, 26f, 0));
        Line line3 = new Line(new Coordinate(29f, 26f, 0), new Coordinate(7f, 26f, 0));
        Line line4 = new Line(new Coordinate(7f, 26f, 0), new Coordinate(7f, 4f, 0));


        tile1.addBorder(line1);
        tile1.addBorder(line2);
        tile1.addBorder(line3);
        tile1.addBorder(line4);

        tile1.addCorners(x1);
        tile1.addCorners(x2);
        tile1.addCorners(y1);
        tile1.addCorners(y2);


        assertEquals(484, calculator.findTileSurface(tile1), 0.1 );

    }

}
