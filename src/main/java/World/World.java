package World;

import Geometry.Coordinate;
import RandomStrategy.RandomContexte;
import World.Generator.Aquifer.RiverGenerator;
import World.Generator.Biome.BiomeGenerator;
import World.Generator.Biome.Vegetation;
import World.Generator.Island.IslandGenerator;
import World.Generator.Aquifer.LakeGenerator;
import World.Mode.Altitude;
import World.Mode.Humidity;
import World.Mode.Mode;
import World.Mode.Normal;

import java.util.*;

public class World {
    private Mode mode;
    private  RandomContexte random;
    private  int nbsWaterSource;
    private int nbsRiversSrc;
    private  soilType soil;
    private  String islandType;
    private  int width;
    private  int height;
    private int maxAltitude;
    final private HashSet<Tile> tiles;



    public World() {
        this.tiles = new HashSet<>();
    }

    public void setMaxAltitude(int maxAltitude) {
        this.maxAltitude = maxAltitude;
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
    public void setSoil(String s){
        soil = soilType.getSoilType(s);
    }
    public void setNbsWaterSource(int i) {nbsWaterSource = i;}
    public void setSeed(int seed){
        random = new RandomContexte(seed);
    }
    public String getTileColor(float x, float y){
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
    public void setMode(String s){
        switch (s) {
            case "normal":
                mode = new Normal();
                break;
            case "altitude":
                mode = new Altitude();
                break;
            case "humidity":
                mode = new Humidity();
                break;
        }
    }
    public RandomContexte getRandom() {
        return random;
    }
    public soilType getSoil() {
        return soil;
    }
    public int getMaxAltitude() {
        return maxAltitude;
    }
    public void setNbsRiversSrc(int nbsRiversSrc) {
        this.nbsRiversSrc = nbsRiversSrc;
    }

    public void addTile(float x, float y) {
        tiles.add(new Tile(new Coordinate(x,y,0)));
    }
    public void generateWorld() {
        Handler h = new Handler();
        h.addGenerator(new IslandGenerator(islandType, width, height));
        h.addGenerator(new BiomeGenerator());
        if(nbsWaterSource != 0) {
            h.addGenerator(new LakeGenerator(nbsWaterSource, random));
        }

        h.addGenerator(new RiverGenerator(3));
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

    private Tile findRandomTile(){
        ArrayList<Tile> tiles = new ArrayList<>(this.tiles);
        return tiles.get(random.getRandomInt(tiles.size()-1));

    }
    public Tile findRandomVegetationTile(){
        Tile tile;
        do {
            tile = findRandomTile();
        } while (!(tile.getBiome() instanceof Vegetation));
        return tile;
    }







}
