package World.Island;


import Geometry.Coordinate;
import RandomStrategy.RandomContexte;
import World.Tile;

public interface IslandStrategy {
    void setPosition(int width, int height, Coordinate c, RandomContexte random);
    boolean contains(Tile tile);
    boolean isInLagoon(Tile tile);
    int getAltitudeProfile(Tile tile);
}
