package World.Generator.Island;



import Geometry.Coordinate;
import Geometry.Ellipse;
import RandomStrategy.RandomContexte;
import World.Tile;

import java.util.*;

public class EllipticIsland implements IslandShape {
    HashMap<Coordinate, Tile> tiles;

    public EllipticIsland(HashMap<Coordinate, Tile> tiles) {
        this.tiles = tiles;
    }
    

    @Override
    public boolean inArea(Tile tile, Tile islandCenter, int diameter, int angle) {
        double minorRadius = (double)diameter / 2;
        float x = tile.getCenter().getX();
        float y = tile.getCenter().getY();
        float h = islandCenter.getCenter().getX();
        float k = islandCenter.getCenter().getY();

        return (Math.pow(((x-h)*Math.cos(angle) + (y-k)*Math.sin(angle)), 2) / Math.pow(diameter, 2)) +
                (Math.pow(((x-h)*Math.sin(angle) - (y-k)*Math.cos(angle)), 2) / Math.pow(minorRadius, 2)) <=1;
    }

    @Override
    public boolean createIsland(RandomContexte random, int maxAltitude, Coordinate coordinate){
        boolean created = false;
        int angle = random.getRandomInt(359) + 1;
        Tile tile = findRandomTile(angle, coordinate);

        if(tile != null) {
            Ellipse ellipse = new Ellipse((int)coordinate.getX(), random, angle, tile.getCenter());
            Island island = new Tortuga(tiles, ellipse, random, maxAltitude);
            created = true;
        }

        return created;
    }

    @Override
    public Tile findRandomTile( int angle, Coordinate coordinate){
        Random random = new Random();
        Tile randtile = null;
        HashMap<Coordinate, Tile> temp = new LinkedHashMap<>(tiles);

        Coordinate randomCoordinate;
        List<Coordinate> coordinates = new ArrayList<>(temp.keySet());

        while (!temp.isEmpty()) {
            randomCoordinate = coordinates.get(random.nextInt(coordinates.size()));

            if (checkTilePosition(temp.get(randomCoordinate), coordinate) &&
                    checkBorders(temp.get(randomCoordinate), (int)coordinate.getX(), angle)) {

                randtile = temp.get(randomCoordinate);
                break;
            }
            temp.remove(randomCoordinate);
        }
        return randtile;
    }

    @Override
    public boolean checkTilePosition(Tile tile, Coordinate coordinate){
        boolean ans = false;
        if(tile != null) {
            ans = tile.getCenter().getX() > coordinate.getX() && tile.getCenter().getX() < coordinate.getY()
                    && tile.getCenter().getY() > coordinate.getX() && tile.getCenter().getY() < coordinate.getY();
        }
        return ans;
    }

    public boolean checkBorders(Tile tileCenter, int diametre, int angle){
        boolean isInside = true;
        for (Tile tile : tiles.values()) {
            if(inArea(tile, tileCenter, diametre, angle)){
                if(tile.getAltitude() == -1) {
                    isInside = false;
                    break;
                }
            }
        }
        return isInside;
    }

}
