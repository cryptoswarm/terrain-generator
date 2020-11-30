import geometry.Coordinate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CoordinateTest {
    @Test
    public void testEquals() {
        Coordinate c1 = new Coordinate(5,5,5);
        Coordinate c2 = new Coordinate(5,5,0);
        assertEquals(c1,c2);
    }

    @Test
    public void testDistanceSameCoordinate() {
        double expected = 0;
        Coordinate c1 = new Coordinate(5,5,5);
        Coordinate c2 = new Coordinate(5,5,0);
        double actual = c1.distance(c2);
        assertTrue(expected == actual);
    }

    @Test
    public void testDistanceDifferentCoordinate() {
        double expected = 5;
        Coordinate c1 = new Coordinate(0,5,5);
        Coordinate c2 = new Coordinate(5,5,0);
        double actual = c1.distance(c2);
        assertTrue(expected == actual);
    }


}
