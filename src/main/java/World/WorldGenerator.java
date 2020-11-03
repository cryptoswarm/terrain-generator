package World;

import geometrie.Coordinate;

import java.util.Random;

public class WorldGenerator {
    private static World world;
    private static Mode mode;
    private static Random random;



    public static World generateWorld() {
        random = new Random();
        Island island = null;

        if (world.getShape().equals("atoll")) {
            island = new Atoll(world, random);
        } else if (world.getShape().equals("tortuga")) {
            island = new Tortuga(world);
        }

        island.defineAltitude();
        world.createBiome(island);
        world.createWaterSource();

        return world;
    }
    public static void newWorld(){
        world = new World();
    }
    public static void setHeight(int height){
        world.setHeight(height);
    }
    public static void setWidth(int width){
        world.setWidth(width);
    }
    public static void setSoil(String s){
        world.setSoil(s);
    }
    public static void setNbsWaterSource(int i) {world.setNbsWaterSrc(i);}
    public static void setShape(String s){ world.setShape(s);}
    public static void addTile(float x, float y){
        world.addTile(new Tile(new Coordinate(x,y,0)));
    }
    public static void addNeighbor(float x, float y, float nx, float ny) {
        Tile tile = world.getTiles().get(new Coordinate(x,y,0));
        Tile tileNeighbor = world.getTiles().get(new Coordinate(nx,ny,0));
        tile.addNeighbor(tileNeighbor);
    }
    public static String getTileColor(float x, float y){
        Tile tile = world.getTiles().get(new Coordinate(x,y,0));
        TileColor color = tile.getBackgroundColor();
        int factor = 0;
        if(mode instanceof Normal || mode instanceof Humidity) factor = tile.getHumidityLevel();
        if(mode instanceof Altitude) factor = (int)tile.getAltitude();

        return mode.getColor(color.getR(), color.getG(), color.getB(), color.getA(), factor);

    }
    public static void setMode(String s){
        if (s == null) {
            mode = new Normal();
        } else if (s.equals("altitude")){
            mode = new Altitude();
        }else if (s.equals("humidity")){
            mode = new Humidity();
        }
    };
    public static Random getRandom() {
        return random;
    }
}

