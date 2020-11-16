package World.Generator.Island;

import Geometry.Circle;
import Geometry.Coordinate;
import Geometry.Shape;
import RandomStrategy.RandomContexte;
import World.Tile;
import World.World;

public class Atoll extends Island {
    final private Shape lagoonShape;
    final private Shape islandShape;
    final private RandomContexte random;
    final private double smallRadius;
    final private double bigRadius;
    final private Coordinate center;

    public Atoll(RandomContexte r, int height, int width){
        this.random = r;
        int spaceCoverage = 90;
        int min = Math.min(width,height);
        this.smallRadius = (min/2) * (((float) spaceCoverage - 50)/100.0);
        this.bigRadius = (min/2) * (((float) spaceCoverage)/100.0);
        this.center = new Coordinate(width/2, height/2,0);
        this.lagoonShape = new Circle(center, smallRadius);
        this.islandShape = new Circle(center, bigRadius);
    }

    public void apply(World w) {
        for(Tile t: w.getTiles()){
            if(this.isInLagoon(t)) t.setInLagoon(true);
            t.setAltitude(this.getAltitudeProfile(w.getMaxAltitude(), t));
        }
    }


    private boolean contains(Tile tile) {
        return islandShape.isInArea(tile.getCenter());
    }

    private boolean isInLagoon(Tile tile) {
        return lagoonShape.isInArea(tile.getCenter());
    }

    private double getAltitudeProfile(int maxAltitude, Tile tile) {
        if(isInLagoon(tile) || !contains(tile)) return 0;
        double mediumRadius = bigRadius - (bigRadius-smallRadius)/2;
        Circle maxHeightCircle = new Circle(center, mediumRadius);
        double altitude = maxAltitude - maxHeightCircle.getDistanceFrom(tile.getCenter());
        if (altitude <= 1) return 1;
        return altitude;
    }


}
