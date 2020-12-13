import org.junit.Before;
import org.junit.Test;
import userInterface.UserArgs;
import world.generator.island.EllipticIsland;

import static org.junit.Assert.*;

public class UserArgsTest {

    UserArgs userArgs;
    String[] args;

    @Before
    public void setUp(){
        args = new String[]{"-i", "samples/sample-1000-1000-4096.mesh", "-o", "result38.mesh", "--shape", "tortuga",
                "--archipelago", "2", "--water", "5", "--altitude", "200", "--soil", "wet",
                "--rivers", "5", "--production", "--pois", "cities:3", "--pois", "ports:6", "--pois", "villages:1"};
        userArgs = new UserArgs(args);
    }


    @Test
    public void fileNameTest1(){

        assertEquals("samples/sample-1000-1000-4096.mesh", userArgs.getInputFile());
    }
    @Test
    public void fileNameTest2(){
        assertNotEquals(" samples/sample-1000-1000-4096.mesh", userArgs.getInputFile());
    }

    @Test
    public void outPutFileNameTest(){
        assertEquals("result38.mesh", userArgs.getOutputFile());
    }
/*
    @Test
    public void getShapeNameTest(){
        assertEquals("tortuga", userArgs.getShape());
    }

 */
    @Test
    public void getShapeNameTest(){
        assertTrue( userArgs.getIslandShape() instanceof EllipticIsland );
    }


    @Test
    public void getWaterSourcesNbTest(){
        assertEquals(5, userArgs.getNbWaterSources());
    }

    @Test
    public void getSoilTypeTest(){
        assertEquals("wet", userArgs.getSoilType());
    }

    @Test
    public void getNbIslandsTest(){
        assertEquals(2, userArgs.getNbsIsland());
    }

    @Test
    public void getNbRiversTest(){
        assertEquals(5, userArgs.getRivers());
    }

    @Test
    public void isProductionTest(){
        assertTrue( userArgs.isProductionActivated());
    }

    @Test
    public void getAltitudeTest(){
        assertEquals(200,  userArgs.getMaxAltitude());
    }

    @Test
    public void getPointsInterets(){
        int[] expected = userArgs.getPois();
        int[] points = new int[] {6, 1, 3};  //ports first, village then cities according to enum definition of POIS.
        assertEquals(points[0], expected[0] );
        assertEquals(points[1], expected[1] );
        assertEquals(points[2], expected[2] );
    }

}
