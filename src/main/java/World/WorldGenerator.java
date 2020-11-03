package World;

import Geometry.Coordinate;

import java.util.Random;

public class WorldGenerator {
    private static World world;
    private static Mode mode;
    private static Random random;
    private static int nbsWaterSource;
    private static soilType soil;
    private static String islandType;
    private static int width;
    private static int height;



    public static void generateWorld() {
        random = new Random();

        //island.defineAltitude();
        Generator i = new IslandGenerator(world.getTiles(), islandType, width, height);
        i.generate();
        Generator bg = new BiomeGenerator(world.getTiles());
        bg.generate();
        Generator ws = new LakeGenerator(world.getTiles(), nbsWaterSource, soil);
        ws.generate();
    }
    public static void newWorld(){
        world = new World();
    }
    public static void setHeight(int h){
        height = h;
    }
    public static void setWidth(int w){
        width = w;
    }
    public static void setSoil(String s){
        soil = soilType.getSoilType(s);
    }
    public static void setNbsWaterSource(int i) {nbsWaterSource = i;}
    public static void setShape(String s){ islandType = s;}
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

