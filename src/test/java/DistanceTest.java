import geometry.Coordinate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DistanceTest {


    @Test
    public void distanceTest(){
        Coordinate coordinate1 = new Coordinate(3, 2, 0);
        Coordinate coordinate2 = new Coordinate(9, 7, 0);

        assertEquals(7.8102, coordinate1.distance(coordinate2), 0.0005);
        assertEquals(7.8102, coordinate2.distance(coordinate1), 0.0005);
        assertEquals(coordinate1.distance(coordinate2), coordinate2.distance(coordinate1), 0);

    }
}
