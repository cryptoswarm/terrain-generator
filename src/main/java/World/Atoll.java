package World;

import Geometry.Circle;
import Geometry.Coordinate;

import java.util.HashMap;
import java.util.Random;

public class Atoll implements Island {
    private Circle circle;


    public Atoll(HashMap<Coordinate, Tile> tiles, Random random, int width, int height){
        this.circle = new Circle(width, height, random);
        for (Tile tile : tiles.values()) {
            Coordinate c = tile.getCenter();
            if (c.distance(circle.getCenter()) <= circle.getBigRadius()) tile.setOnIsland(true);
            if (c.distance(circle.getCenter()) <= circle.getSmallRadius()) tile.setInLagoon(true);
        }
    }

    @Override
    public void defineAltitude() {

    }
    /*
    @Override
    public void defineAltitude(){
        for(Tile tile: atoll.values()){
            int distanceTileFromCenter = Math.round(tile.getCenter().distance(circle.getCenter()));
            if( Math.abs(distanceTileFromCenter - circle.getBigRadius()) < distanceTileFromCenter - circle.getSmallRadius()){
                tile.setAltitude(Math.abs(distanceTileFromCenter - circle.getBigRadius()));
            } else {
                tile.setAltitude(distanceTileFromCenter - circle.getSmallRadius());
            }

        }
    }
*/

}
