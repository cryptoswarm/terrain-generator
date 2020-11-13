package World.Generator.Island;


import Geometry.Coordinate;
import Geometry.Ellipse;
import RandomStrategy.RandomContexte;
import World.Tile;
import World.World;

public class Tortuga extends Island {
    Ellipse ellipse;
    int spacePercentage;
    Coordinate position;
    RandomContexte random;



    public Tortuga(RandomContexte r, int h, int w){
        this.spacePercentage = 90;
        this.random = r;
        this.position = new Coordinate(w/2, h/2,0);
        this.ellipse = new Ellipse((int)((float)w/1.5), (int)((float)h/3), position, random.getRandomInt(120));
    }

    public void apply(World w) {
        for(Tile t: w.getTiles()) t.setAltitude(getAltitudeProfile(t));
    }

    private boolean contains(Tile tile) {
        return ellipse.isInArea(tile.getCenter());
    }

    private int getAltitudeProfile(Tile tile) {
        if(!contains(tile)) return 0;
        Ellipse altitudeProfile = null;
        int i = 0;
        do{
            i++;
            if(!(ellipse.getMajorAxis() - i <= 0) || !(ellipse.getMajorAxis() - i <= 0)) {
                altitudeProfile = new Ellipse((int)(ellipse.getMajorAxis()-i), (int)(ellipse.getMinorAxis()-i), position, ellipse.getAngle());
            } else {
                break;
            }
        } while(altitudeProfile.isInArea(tile.getCenter()) || i < 100);
        return i;
    }
}
