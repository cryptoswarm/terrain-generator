package World.Generator.Island;

import Geometry.*;
import RandomStrategy.RandomContexte;
import World.Tile;
import World.World;

public abstract class IslandShape {
    abstract boolean createIsland(World world, RandomContexte random, int maxAltitude, Coordinate border);

    public boolean validIsland(World world, Shape s, int h, int w) {
        for (Tile tile : world.getTiles().values()) {
            if(s.isInShape(tile.getCenter())) {
                //is ocean
                if(tile.getAltitude() != -1) return false;
                for(Tile n: world.getNeighbor(tile)){
                    if(n.getAltitude() != -1) return false;
                }

                //is not on world border
                for(Line l: tile.getBorder()){
                    if(l.getC1().getY() == 0 || l.getC1().getY() == h) return false;
                    if(l.getC1().getX() == 0 || l.getC1().getX() == w) return false;
                    if(l.getC2().getY() == 0 || l.getC2().getY() == h) return false;
                    if(l.getC2().getX() == 0 || l.getC2().getX() == w) return false;
                }
            }
        }
        return true;
    }


}
