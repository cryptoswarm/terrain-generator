package World.Generator.Island;


import Geometry.Coordinate;
import Geometry.Ellipse;
import RandomStrategy.RandomContexte;
import World.Tile;
import World.World;

public class Tortuga extends Island {
    final private Ellipse ellipse;
    final private int spacePercentage;
    final private Coordinate position;
    final private RandomContexte random;
    final private int altitude;


    public Tortuga(RandomContexte r, int h, int w, int altitude){
        this.spacePercentage = 90;
        this.altitude = altitude;
        this.random = r;
        this.position = new Coordinate(w/2, h/2,0);
        this.ellipse = new Ellipse((int)((float)w/1.5), (int)((float)h/3), position, random.getRandomInt(120));
    }

    public void apply(World w) {
        for(Tile t: w.getTiles().values()) {
            t.setAltitude(getAltitudeProfile(altitude, t));
        }
    }

    private boolean contains(Tile tile) {
        return ellipse.isInArea(tile.getCenter());
    }

    private int getAltitudeProfile(int maxAltitude, Tile tile) {
        if(!contains(tile)) return 0;
        Ellipse altitudeProfile = null;
        int i = (int) ellipse.getMajorAxis()/3;
        do{
            i++;
            altitudeProfile = new Ellipse((int)(i), (int)(i - ellipse.getMajorAxis()/3), position, ellipse.getAngle());
        } while(!altitudeProfile.isInArea(tile.getCenter()));

        if(maxAltitude - i <= 0){
            return 1;
        }

        if(altitudeProfile.getMajorAxis() + ellipse.getMajorAxis()/3 + 10 > ellipse.getMajorAxis()){
            if(maxAltitude - i > 50){
                System.out.println("BAD ALTITUDE");
            }
        }

        return maxAltitude - i;
    }
}
