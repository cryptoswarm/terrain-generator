import geometry.Coordinate;
import geometry.Line;
import org.junit.Test;
import world.Tile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LineTest {


    @Test
    public void addLinesTest(){

        Tile tile1 = new Tile(new Coordinate(3,3,0));

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

        List<Line> lineList = new ArrayList<>();
        lineList.add(line1);
        lineList.add(line2);
        lineList.add(line3);
        lineList.add(line4);
        lineList.add(line5);
        lineList.add(line6);

        assertEquals(lineList.size(), tile1.getBorder().size());
        assertTrue(lineList.containsAll(tile1.getBorder()));
    }

    @Test
    public void getBorderTest1(){

        Tile tile1 = new Tile(new Coordinate(3,3,0));

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

        List<Line> lineList = new ArrayList<>();
        lineList.add(line1);
        lineList.add(line2);
        lineList.add(line3);
        lineList.add(line4);
        lineList.add(line5);
        lineList.add(line6);


        assertTrue(lineList.containsAll(tile1.getBorder()));
    }

    @Test
    public void getBorderTest2(){  //square shape

        Tile tile1 = new Tile(new Coordinate(1,1,0));

        Line line1 = new Line(new Coordinate(0, 0, 0), new Coordinate(2, 0, 0));
        Line line2 = new Line(new Coordinate(2, 0, 0), new Coordinate(2, 2, 0));
        Line line3 = new Line(new Coordinate(2, 2, 0), new Coordinate(0, 2, 0));
        Line line4 = new Line(new Coordinate(0, 2, 0), new Coordinate(0, 0, 0));


        tile1.addBorder(line1);
        tile1.addBorder(line2);
        tile1.addBorder(line3);
        tile1.addBorder(line4);


        List<Line> lineList = new ArrayList<>();
        lineList.add(line1);
        lineList.add(line2);
        lineList.add(line3);
        lineList.add(line4);

        assertTrue(lineList.containsAll(tile1.getBorder()));
    }


    @Test
    public void testEquals(){
        Coordinate c1 = new Coordinate(1,1,0);
        Coordinate c2 = new Coordinate(2,2,0);
        Line line1 = new Line(c1,c2);
        Line line2 = new Line(c2,c1);
        assertEquals(line1,line2);
    }

    @Test
    public void testNotEquals(){
        Coordinate c1 = new Coordinate(1,1,0);
        Coordinate c2 = new Coordinate(2,2,0);
        Coordinate c3 = new Coordinate(3,3,0);
        Line line1 = new Line(c1,c2);
        Line line2 = new Line(c1,c3);
        assertNotEquals(line1,line2);
    }

    @Test
    public void testIncreaseFlow(){
        /*
        int expected = 2;
        Coordinate c1 = new Coordinate(1,1,0);
        Coordinate c2 = new Coordinate(2,2,0);
        Line line1 = new Line(c1,c2);

        line1.increaseFlow();
        assertEquals(line1.getFlow(), expected);

         */
    }
}