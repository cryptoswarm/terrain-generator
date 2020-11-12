package World.Island;

import Geometry.Circle;
import Geometry.Coordinate;
import Geometry.Shape;
import RandomStrategy.RandomContexte;
import World.Tile;
import World.World;

public class Atoll extends Island {
    Shape lagoonShape;
    Shape islandShape;
    RandomContexte random;

    public Atoll(RandomContexte r, int height, int width){
        this.random = r;
        int spaceCoverage = 90;
        int min = Math.min(width,height);
        double smallRadius = (min/2) * (((float) spaceCoverage - 50)/100.0);
        double bigRadius = (min/2) * (((float) spaceCoverage)/100.0);
        Coordinate center = new Coordinate(width/2, height/2,0);
        this.lagoonShape = new Circle(center, smallRadius);
        this.islandShape = new Circle(center, bigRadius);
    }

    public void apply(World w) {
        for(Tile t: w.getTiles()){
            if(this.isInLagoon(t)) t.setInLagoon(true);
            t.setAltitude(this.getAltitudeProfile(t));
        }
    }


    private boolean contains(Tile tile) {
        return islandShape.isInArea(tile.getCenter());
    }

    private boolean isInLagoon(Tile tile) {
        return lagoonShape.isInArea(tile.getCenter());
    }

    private int getAltitudeProfile(Tile tile) {
        if(isInLagoon(tile) || !contains(tile)) return 0;
        return Math.min(islandShape.getDistanceFrom(tile.getCenter()),
                lagoonShape.getDistanceFrom(tile.getCenter()));
    }


}
