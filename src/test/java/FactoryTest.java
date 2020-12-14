import org.junit.Before;
import org.junit.Test;
import userInterface.UserArgs;
import world.generator.island.CircularIsland;
import world.generator.island.EllipticIsland;
import world.mode.Altitude;
import world.mode.Humidity;
import world.mode.Normal;

import static org.junit.Assert.assertTrue;

public class FactoryTest {

    UserArgs userArgs;
    String[] args;

    @Before
    public void setUp(){
        args = new String[]{"-i", "samples/sample-1000-1000-4096.mesh", "-o", "result38.mesh",
                "--shape", "tortuga", "--archipelago", "2", "--water", "5"};
        userArgs = new UserArgs(args);
    }

    @Test
    public void shapeTest1(){

        assertTrue(   userArgs.getIslandShape() instanceof EllipticIsland   );
    }

    @Test
    public void shapeTest2(){
        args = new String[]{"-i", "samples/sample-1000-1000-4096.mesh", "-o", "result38.mesh",
                "--shape", "atoll",};
        userArgs = new UserArgs(args);

        assertTrue(   userArgs.getIslandShape() instanceof CircularIsland);
    }

    @Test
    public void modeTest1(){
        args = new String[]{"-i", "samples/sample-1000-1000-4096.mesh", "-o", "result38.mesh",
                "--shape", "atoll", "--heatmap", "humidity"};
        userArgs = new UserArgs(args);

        assertTrue( userArgs.getHeatmapMode() instanceof Humidity);
    }

    @Test
    public void modeTest2(){
        args = new String[]{"-i", "samples/sample-1000-1000-4096.mesh", "-o", "result38.mesh",
                "--shape", "atoll", "--heatmap", "altitude"};
        userArgs = new UserArgs(args);

        assertTrue( userArgs.getHeatmapMode() instanceof Altitude);
    }

    @Test
    public void modeTest3(){
        args = new String[]{"-i", "samples/sample-1000-1000-4096.mesh", "-o", "result38.mesh",
                "--shape", "atoll"};
        userArgs = new UserArgs(args);

        assertTrue( userArgs.getHeatmapMode() instanceof Normal);
    }


}
