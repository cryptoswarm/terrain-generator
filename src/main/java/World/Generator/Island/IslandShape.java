package World.Generator.Island;

import Geometry.Coordinate;
import Geometry.Line;
import Geometry.Shape;
import RandomStrategy.RandomContexte;
import World.Tile;
import World.World;

import java.util.ArrayList;
import java.util.List;

public abstract class IslandShape {


    abstract boolean createIsland(World world, RandomContexte random, int maxAltitude, Coordinate border);

    /*
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

     */


    public boolean validIsland(World world, Shape s, int h, int w) {
        boolean valid = true;
        boolean validAlt;
        boolean validLines;
        boolean validNeighbor;

        List<Tile> islandTiles = islandTiles(world, s);

        for (Tile tile : islandTiles) {

            validAlt = (tile.getAltitude()  == -1) ;

            //is not on world border
            validLines = validateLines(tile, h, w);
            // if neighbor does not belong to another island
            validNeighbor = validateNeighborAltitude(world, tile);


            if (!validAlt || !validLines || !validNeighbor) {
                valid = false;
                break;
            }
        }
        
        return valid;
    }


    public boolean validateLines(Tile tile, int h, int w ){
        boolean valid = true;

        for (Line l : tile.getBorder()) {
            if (l.getC1().getY() == 0 || l.getC1().getY() == h) {
                valid = false;
            }
            if (l.getC1().getX() == 0 || l.getC1().getX() == w) {
                valid = false;
            }
            if (l.getC2().getY() == 0 || l.getC2().getY() == h) {
                valid = false;
            }
            if (l.getC2().getX() == 0 || l.getC2().getX() == w) {
                valid = false;
            }
        }
        return valid;
    }

    public boolean validateNeighborAltitude(World world, Tile tile){
        boolean valid = true;
        for (Tile n : world.getNeighbor(tile)) {
            if (n.getAltitude() != -1) {
                valid = false;
                break;
            }
        }
        return valid;
    }
    
    public List<Tile> islandTiles(World world, Shape s){
        
        List<Tile> tileList = new ArrayList<Tile>();
        for (Tile tile : world.getTiles().values() ) {
            if( s.isInShape(tile.getCenter()) ){
                tileList.add(tile);
            }
        }
        return tileList;
    }
    


}
