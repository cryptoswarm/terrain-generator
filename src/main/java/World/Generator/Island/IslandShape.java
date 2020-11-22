package World.Generator.Island;

import Geometry.Circle;
import Geometry.Coordinate;
import Geometry.Ellipse;
import Geometry.Line;
import RandomStrategy.RandomContexte;
import World.Tile;
import World.World;
import java.util.*;

public abstract class IslandShape {
    abstract boolean createIsland(World world, RandomContexte random, int maxAltitude, Coordinate border);
    abstract boolean inArea(Tile tile, Tile tileCenter, int diameter, int angle );

    public Tile findRandomTile(HashMap<Coordinate, Tile> tiles, int angle, Coordinate border, int h, int w){
        Random random = new Random();
        List<Coordinate> coordinates = new ArrayList<>(tiles.keySet());

        int diameter = (int)border.getX();
        Coordinate c = null;
        while (!coordinates.isEmpty()) {
            c = coordinates.get(random.nextInt(coordinates.size()));
            Tile t = tiles.get(c);
            //if (validIsland(tiles, t, diameter, angle,h,w)) break;
            coordinates.remove(c);
        }
        return tiles.get(c);
    }


    public boolean validIsland(HashMap<Coordinate, Tile> tiles, Tile tileCenter, Ellipse e, int h, int w) {
        for (Tile tile : tiles.values()) {
            if(e.isInEllipse(tileCenter.getCenter())) {
                //is ocean
                if(tile.getAltitude() != -1) return false;
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
