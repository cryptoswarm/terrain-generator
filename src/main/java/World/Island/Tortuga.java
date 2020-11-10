package World.Island;


import Geometry.Coordinate;
import Geometry.Ellipse;
import RandomStrategy.RandomContexte;
import World.Tile;

public class Tortuga implements IslandStrategy {

    Ellipse island;
    int spacePercentage;
    Coordinate position;
    RandomContexte random;



    public Tortuga(int spacePercentage){

        this.spacePercentage = spacePercentage;
    }

    @Override
    public void setPosition(int width, int height, Coordinate c, RandomContexte random) {

        this.position = c;
        island = new Ellipse((int)((float)width/1.5), (int)((float)height/3), c, random.getRandomInt(120));
        this.random = random;
    }

    @Override
    public boolean contains(Tile tile) {
        return island.isInArea(tile.getCenter());
    }

    @Override
    public boolean isInLagoon(Tile tile) {
        return false;
    }

    @Override
    public int getAltitudeProfile(Tile tile) {

        int altitude = 0;

        if(island.isInArea(tile.getCenter())){
            Ellipse altitudeProfile = null;
            int i = 0;
            do{
                i++;
                if(!(island.getMajorAxis() - i <= 0) || !(island.getMajorAxis() - i <= 0)){

                    altitudeProfile = new Ellipse((int)(island.getMajorAxis()-i), (int)(island.getMinorAxis()-i), position, island.getAngle());
                }else{
                    break;
                }

            }while(altitudeProfile.isInArea(tile.getCenter()) || i < 100);
            altitude = i;
        }

        return altitude;
    }
}
