package World.Generator.Island;



import Geometry.Circle;
import Geometry.Coordinate;
import RandomStrategy.RandomContexte;
import World.Tile;

import java.util.*;

public class CircularIsland implements IslandShape {
    HashMap<Coordinate, Tile> tiles;

    public CircularIsland(HashMap<Coordinate, Tile> tiles) {
        this.tiles = tiles;
    }

    @Override
    public boolean inArea(Tile tile, Tile tileCenter, int diameter, int angle ){
        double radius = (double)diameter/2;

        return Math.pow((tile.getCenter().getX() - tileCenter.getCenter().getX()), 2) +
                Math.pow((tile.getCenter().getY() - tileCenter.getCenter().getY()), 2) <= Math.pow(radius, 2);
    }

    public boolean createIsland(RandomContexte random, int maxAltitude, Coordinate coordinate) {
        Island island;
        boolean created = true;
        Circle circle;
        int angle = 360;

        Tile tile = findRandomTile(angle, coordinate);
        if (tile != null) {
            circle = new Circle((int)coordinate.getX(), random, tile.getCenter());
            island = new Atoll(tiles, circle, maxAltitude);
            island.defineAltitude(maxAltitude);
        } else {
            created = false;
        }

        return created;
    }

    @Override
    public Tile findRandomTile(int angle, Coordinate coordinate){
        float diameter = coordinate.getX();
        Random random = new Random();
        Tile randtile = null;
        HashMap<Coordinate, Tile> temp = new LinkedHashMap<>(tiles);
        Coordinate randomCoordinate;
        List<Coordinate> coordinates = new ArrayList<>(temp.keySet());

        while (!temp.isEmpty()) {
            randomCoordinate = coordinates.get(random.nextInt(coordinates.size()));

            if (checkTilePosition(temp.get(randomCoordinate), coordinate) &&
                    checkBorders(temp.get(randomCoordinate), (int)diameter, angle)) {

                randtile = temp.get(randomCoordinate);
                break;
            }
            temp.remove(randomCoordinate);
        }
        return randtile;
    }

    @Override
    public boolean checkTilePosition(Tile tile, Coordinate coordinate) {
        boolean ans = false;
        if (tile != null) {
            ans = tile.getCenter().getX() > coordinate.getX() && tile.getCenter().getX() < coordinate.getY()
                    && tile.getCenter().getY() > coordinate.getX() && tile.getCenter().getY() < coordinate.getY();
        }
        return ans;
    }

    @Override
    public  boolean checkBorders(Tile tileCenter, int diameter, int angle) {
        boolean isInside = true;

        for (Tile tile : tiles.values()) {
            if(inArea(tile, tileCenter, diameter, angle)) {
                if(tile.getAltitude() == -1){
                    isInside = false;
                    break;
                }
            }
        }
        return isInside;
    }

}
