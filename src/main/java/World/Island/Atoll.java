package World.Island;

import Geometry.Circle;
import Geometry.Coordinate;
import Geometry.Shape;
import RandomStrategy.RandomContexte;
import World.Tile;

public class Atoll implements IslandStrategy {

    int spaceCoverage;
    Shape lagoonShape;
    Shape islandShape;
    Coordinate center;
    RandomContexte random;

    public Atoll(int spaceCoverage){

        this.spaceCoverage = spaceCoverage;
    }

    @Override
    public void setPosition(int width, int height, Coordinate c, RandomContexte random) {

        int min = Math.min(width, height);
        this.center = c;
        this.random = random;

        this.lagoonShape = new Circle(c,  (min/2) * (((float) spaceCoverage - 50)/100.0));
        this.islandShape = new Circle(c, (min/2) * (((float) spaceCoverage)/100.0));
    }

    @Override
    public boolean contains(Tile tile) {

        return islandShape.isInArea(tile.getCenter());
    }

    @Override
    public boolean isInLagoon(Tile tile) {
        return lagoonShape.isInArea(tile.getCenter());
    }

    @Override
    public int getAltitudeProfile(Tile tile) {
        return Math.min(islandShape.getDistanceFrom(tile.getCenter()), lagoonShape.getDistanceFrom(tile.getCenter()));
    }


}
