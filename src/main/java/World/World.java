package World;

import Geometry.Coordinate;
import RandomStrategy.RandomContexte;
import World.Aquifer.LakeGenerator;
import World.Biome.BiomeGenerator;
import World.Island.IslandGenerator;
import World.Mode.Altitude;
import World.Mode.Humidity;
import World.Mode.Mode;
import World.Mode.Normal;

import java.util.*;

public class World {
    private Mode mode;
    private  RandomContexte random;
    private  int nbsWaterSource;
    private  soilType soil;
    private  String islandType;
    private  int width;
    private  int height;
    private HashSet<Tile> tiles;



    public World() {
        this.tiles = new HashSet<>();
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setShape(String islandType) {
        this.islandType = islandType;
    }
    public String getShape() {
        return islandType;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public HashSet<Tile> getTiles() {
        return tiles;
    }
    public  void setSoil(String s){
        soil = soilType.getSoilType(s);
    }
    public  void setNbsWaterSource(int i) {nbsWaterSource = i;}
    public  void setSeed(int seed){
        random = new RandomContexte(seed);
    }


    public void addTile(float x, float y) {
        tiles.add(new Tile(new Coordinate(x,y,0)));
    }
    public void generateWorld() {
        Handler h = new Handler();
        h.addGenerator(new IslandGenerator(islandType, width, height));
        h.addGenerator(new BiomeGenerator());
        h.addGenerator(new LakeGenerator(nbsWaterSource, soil));
        h.process(this);
    }
    public  void addNeighbor(float x, float y, float nx, float ny) {
        Tile tile = null;
        Tile neighbor = null;
        for (Tile t : tiles) {
            if(t.equals(new Tile(new Coordinate(x,y,0)))) {
                tile = t;
                break;
            }
        }
        for (Tile n : tiles) {
            if(n.equals(new Tile(new Coordinate(nx,ny,0)))) {
                neighbor = n;
                break;
            }
        }
        tile.addNeighbor(neighbor);
    }
    public  String getTileColor(float x, float y){
        Tile tile = null;
        for (Tile t : tiles) {
            if(t.equals(new Tile(new Coordinate(x,y,0)))) {
                tile = t;
                break;
            }
        }
        TileColor color = tile.getBackgroundColor();
        int factor = 0;
        if(mode instanceof Normal || mode instanceof Humidity) factor = tile.getHumidityLevel();
        if(mode instanceof Altitude) factor = (int)tile.getAltitude();
        return mode.getColor(color.getR(), color.getG(), color.getB(), color.getA(), factor);
    }
    public  void setMode(String s){
        if (s.equals("normal")) {
            mode = new Normal();
        } else if (s.equals("altitude")){
            mode = new Altitude();
        }else if (s.equals("humidity")){
            mode = new Humidity();
        }
    };
    public  RandomContexte getRandom() {
        return random;
    }







}
