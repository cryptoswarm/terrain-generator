import geometry.Coordinate;
import geometry.Line;
import org.junit.Before;
import org.junit.Test;
import randomStrategy.RandomContexte;
import world.Tile;
import world.World;
import world.generator.island.IslandGenerator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SurfaceCalculatorTest {

    RandomContexte random;
    World world;
    IslandGenerator islandGenerator;
    Tile tile;
    List<Coordinate> coordinateList;

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

        Line line1 = new Line(coordinate1, coordinate2);
        Line line2 = new Line(coordinate2, coordinate3);
        Line line3 = new Line(coordinate3, coordinate4);
        Line line4 = new Line(coordinate4, coordinate5);
        Line line5 = new Line(coordinate5, coordinate6);
        Line line6 = new Line(coordinate6, coordinate7);

        world.addTile(0,0,0);
        tile.addBorder(line1);
        tile.addBorder(line2);
        tile.addBorder(line3);
        tile.addBorder(line4);
        tile.addBorder(line5);
        tile.addBorder(line6);
    }

    @Test
    public void checkSurface1(){


        assertEquals(72.3671875,  islandGenerator.findTileSurface(tile), 0.001 );
    }


    @Test
    public void checkSurface2(){

        coordinateList.clear();

        Tile tile1 = new Tile(new Coordinate(6,6,0));

        coordinateList.add(new Coordinate(4, 10, 0));
        coordinateList.add(new Coordinate(9, 7, 0));
        coordinateList.add(new Coordinate(11, 2, 0));
        coordinateList.add(new Coordinate(2, 2, 0));




        Line line1 = new Line(new Coordinate(4, 10, 0), new Coordinate(9, 7, 0));
        Line line2 = new Line(new Coordinate(9, 7, 0), new Coordinate(11, 2, 0));
        Line line3 = new Line(new Coordinate(11, 2, 0), new Coordinate(2, 2, 0));
        Line line4 = new Line(new Coordinate(2, 2, 0), new Coordinate(4, 10, 0));

        tile1.addBorder(line1);
        tile1.addBorder(line2);
        tile1.addBorder(line3);
        tile1.addBorder(line4);

        assertEquals(45.5, islandGenerator.findTileSurface(tile1), 0.0001);

    }


    @Test
    public void checkSurface3(){

        coordinateList.clear();

        Tile tile1 = new Tile(new Coordinate(3,3,0));

        coordinateList.add(new Coordinate(2.66f, 4.71f, 0));
        coordinateList.add(new Coordinate(5, 3.5f, 0));
        coordinateList.add(new Coordinate(3.63f, 2.52f, 0));
        coordinateList.add(new Coordinate(4, 1.6f, 0));
        coordinateList.add(new Coordinate(1.9f, 1, 0));
        coordinateList.add(new Coordinate(0.72f, 2.28f, 0));
        coordinateList.add(new Coordinate(2.66f, 4.71f, 0));




        Line line1 = new Line(new Coordinate(2.66f, 4.71f, 0), new Coordinate(5, 3.5f, 0));
        Line line2 = new Line(new Coordinate(5, 3.5f, 0), new Coordinate(3.63f, 2.52f, 0));
        Line line3 = new Line(new Coordinate(3.63f, 2.52f, 0), new Coordinate(4, 1.6f, 0));
        Line line4 = new Line(new Coordinate(4, 1.6f, 0), new Coordinate(1.9f, 1, 0));
        Line line5 = new Line(new Coordinate(1.9f, 1, 0), new Coordinate(0.72f, 2.28f, 0));
        Line line6 = new Line(new Coordinate(0.72f, 2.28f, 0), new Coordinate(2.66f, 4.71f, 0));

        tile1.addBorder(line1);
        tile1.addBorder(line2);
        tile1.addBorder(line3);
        tile1.addBorder(line4);
        tile1.addBorder(line5);
        tile1.addBorder(line6);


        assertEquals(39.44199, islandGenerator.multiplyXByYNextCoordinate( coordinateList ), 0.001 );
        assertEquals(56.09299, islandGenerator.multiplyYByXNextCoordinate(coordinateList), 0.1 );
        assertEquals(8.3255, islandGenerator.substractAndDivide(39.44199,56.09299 ), 0.1 );
        assertEquals(8.3255, islandGenerator.findTileSurface(tile1), 0.1 );



    }

}
