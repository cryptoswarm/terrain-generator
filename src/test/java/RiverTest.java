import geometry.Circle;
import geometry.Coordinate;
import geometry.Line;
import islandSet.Isle;
import org.junit.Before;
import org.junit.Test;
import randomStrategy.RandomContexte;
import world.Tile;
import world.borders.Border;
import world.generator.aquifer.River;
import world.generator.biome.Beach;
import world.generator.biome.Ocean;
import world.generator.biome.Vegetation;
import world.generator.island.Atoll;
import world.generator.island.Island;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RiverTest {


    Isle isle;

    Tile tile1;
    Tile tile2;
    Tile tile3;
    Tile tile4;
    Tile tile5;



    Coordinate center1;
    Coordinate center2;
    Coordinate center3;
    Coordinate center4;
    Coordinate center5;



    Border tile1border;
    Border tile2border;
    Border tile3border;
    Border tile4border;
    Border tile5border;


    Coordinate coordinate1;
    Coordinate coordinate2;
    Coordinate coordinate3;
    Coordinate coordinate4;
    Coordinate coordinate11;
    Coordinate coordinate12;
    Coordinate coordinate13;
    Coordinate coordinate14;
    Coordinate coordinate15;
    Coordinate coordinate16;
    Coordinate coordinate17;
    Coordinate coordinate18;
    Coordinate coordinate55;




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
    Line line20;
    Line line111;



    Island island;
    HashSet<Tile> tileList;
    Circle circle;
    RandomContexte randomContexte;
    Coordinate circleCenter;
    List<Coordinate> uniqeCoordinates = new ArrayList<>();


    @Before
    public void setUp(){

        center1 = new Coordinate(2, 3,-1);
        center2 = new Coordinate(4, 5,-1);
        center3 = new Coordinate(6, 7,-1);
        center4 = new Coordinate(10, 9,-1);
        center5 = new Coordinate(2, 2,-1);

        tile1 = new Tile(center1);
        tile2 = new Tile(center2);
        tile3 = new Tile(center3);
        tile4 = new Tile(center4);
        tile5 = new Tile(center5);

        tile1border = new Border();
        tile2border = new Border();
        tile3border = new Border();
        tile4border = new Border();
        tile5border = new Border();


        coordinate1 = new Coordinate(1, 3, -1);
        coordinate2 = new Coordinate(3, 1, -1);
        coordinate3 = new Coordinate(3, 4, -1);
        coordinate4 = new Coordinate(2, 5, -1);

        coordinate11 = new Coordinate(5, 4, -1);
        coordinate12 = new Coordinate(5, 6, -1);

        coordinate13 = new Coordinate(8, 6, -1);
        coordinate14 = new Coordinate(8, 8, -1);
        coordinate15 = new Coordinate(5, 8, -1);
        coordinate16 = new Coordinate(4, 7, -1);

        coordinate17 = new Coordinate(11, 8, -1);
        coordinate18 = new Coordinate(11, 10, -1);

        coordinate55 = new Coordinate(1,1,-1);




        line1 = new Line(coordinate1, coordinate2);
        line2 = new Line(coordinate2, coordinate3);
        line3 = new Line(coordinate3, coordinate4);
        line4 = new Line(coordinate4, coordinate1);

        line5 = new Line(coordinate3, coordinate11);
        line6 = new Line(coordinate11, coordinate12);
        line7 = new Line(coordinate12, coordinate4);

        line8 = new Line(coordinate12, coordinate13);
        line9 = new Line(coordinate13, coordinate14);
        line10 = new Line(coordinate14, coordinate15);
        line11 = new Line(coordinate15, coordinate16);
        line12 = new Line(coordinate16, coordinate12);

        line13 = new Line(coordinate14, coordinate17);
        line14 = new Line(coordinate17, coordinate18);
        line15 = new Line(coordinate18, coordinate14);

        line20= new Line(coordinate2, coordinate55);
        line111 = new Line(coordinate1, coordinate55);




        tile1border.addTileLines(line1);
        tile1border.addTileLines(line2);
        tile1border.addTileLines(line3);
        tile1border.addTileLines(line4);
        tile1.setBorder(tile1border);

        tile2border.addTileLines(line5);
        tile2border.addTileLines(line6);
        tile2border.addTileLines(line7);
        tile2border.addTileLines(line3);
        tile2.setBorder(tile2border);


        tile3border.addTileLines(line8);
        tile3border.addTileLines(line9);
        tile3border.addTileLines(line10);
        tile3border.addTileLines(line11);
        tile3border.addTileLines(line12);
        tile3.setBorder(tile3border);


        tile4border.addTileLines(line13);
        tile4border.addTileLines(line14);
        tile4border.addTileLines(line15);
        tile4.setBorder(tile4border);

        tile5border.addTileLines(line1);
        tile5border.addTileLines(line20);
        tile5border.addTileLines(line111);
        tile5.setBorder(tile5border);






        tileList = new LinkedHashSet<>();
        tileList.add(tile1);
        tileList.add(tile2);
        tileList.add(tile3);
        tileList.add(tile4);

        isle = new Isle(tileList);



        coordinate18.setZ(18);
        coordinate17.setZ(17);
        coordinate16.setZ(16);
        coordinate15.setZ(15);
        coordinate14.setZ(14);
        coordinate13.setZ(13);
        coordinate12.setZ(12);
        coordinate11.setZ(11);
        coordinate4.setZ(4);
        coordinate3.setZ(3);
        coordinate2.setZ(2);
        coordinate1.setZ(1);


        tile1.setItem(new Vegetation());
        tile2.setItem(new Vegetation());
        tile3.setItem(new Vegetation());
        tile4.setItem(new Vegetation());
        tile5.setItem(new Ocean() );



        circleCenter = new Coordinate(3, 9, 0);
        randomContexte = new RandomContexte(10);

        circle = new Circle(20, randomContexte, circleCenter);
        island = new Atoll(isle, circle, 50);
    }

    @Test
    public void riverPathTest1(){

        HashSet<Line> riverElement = new HashSet<>();
        River river = new River(randomContexte);
        river.findRiverPath(isle, coordinate18, riverElement);

        HashSet<Line> expected = new HashSet<>();
        expected.add(line15);
        expected.add(line9);
        expected.add(line8);
        expected.add(line7);
        expected.add(line4);

        assertEquals(expected, riverElement);

    }

    @Test
    public void riverPathTest2(){

        Tile tile6 = new Tile(new Coordinate(6, 5, -1));
        Line line1113 = new Line(coordinate11, coordinate13);
        Border tile6border = new Border();

        tile6border.addTileLines(line1113);
        tile6border.addTileLines(line8);
        tile6border.addTileLines(line6);
        tile6.setBorder(tile6border);

        tile6.setItem(new Beach() );

        tileList.add(tile6);

        HashSet<Line> riverElement = new HashSet<>();
        River river = new River(randomContexte);
        river.findRiverPath(isle, coordinate17, riverElement);

        HashSet<Line> expected = new HashSet<>();
        expected.add(line13);
        expected.add(line9);

        assertEquals(expected, riverElement);

    }


}
