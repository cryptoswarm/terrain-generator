package World;

import Geometry.Coordinate;
import Geometry.Line;
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
    final private HashMap<Coordinate, Tile> tiles;



    public World() {
        this.tiles = new HashMap<>();
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
    public HashMap<Coordinate, Tile> getTiles() {
        return tiles;
    }
    public void setSoil(String s){
        soil = soilType.getSoilType(s);
    }
    public void setNbsWaterSource(int i) {nbsWaterSource = i;}
    public void setSeed(int seed){
        random = new RandomContexte(seed);
    }
    public void setNbsRiversSrc(int nbsRiversSrc) {
        this.nbsRiversSrc = nbsRiversSrc;
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

    public String getTileColor(float x, float y){
        Tile tile = tiles.get(new Coordinate(x,y,0));
        TileColor color = tile.getBackgroundColor();
        int factor = 0;
        if(mode instanceof Normal || mode instanceof Humidity) factor = tile.getHumidityLevel();
        if(mode instanceof Altitude) factor = (int)tile.getAltitude();
        return mode.getColor(color.getR(), color.getG(), color.getB(), color.getA(), factor);
    }
    public String getLineColor(float x1, float y1, float x2, float y2){
        Line line = new Line(new Coordinate(x1,y1,0), new Coordinate(x2,y2,0));

        for(Tile tile: tiles.values()){
            for(Line l: tile.getBorder()){
                if(l.equals(line) && l.getColor() != null) {
                    line = l;
                    break;
                }
            }
        }
        TileColor color = line.getColor();
        if(color != null) {
            return color.toString();
        }

        return "0:0:0:0";
    }
    public RandomContexte getRandom() {
        return random;
    }
    public soilType getSoil() {
        return soil;
    }
    public HashSet<Tile> getNeighbor(Coordinate c) {
        HashSet<Tile> neighbor = new HashSet<>();
        for(Tile tile: tiles.values()){
            for(Line line: tile.getBorder()){
                if(c.equals(line.getC1()) || c.equals(line.getC2())){
                    neighbor.add(tile);
                }
            }
        }
        return neighbor;
    }
    public HashSet<Line> getLine(Coordinate c){
        HashSet<Line> lines = new HashSet<>();
        for(Tile tile: tiles.values()){
            for(Line line: tile.getBorder()){
                if(line.getC1().equals(c) || line.getC2().equals(c)){
                    lines.add(line);
                }
            }
        }
        return lines;
    }

    public void addTile(float x, float y) {
        Coordinate c = new Coordinate(x,y,0);
        Tile t = new Tile(c);
        tiles.put(c,t);
    }
    public void addNeighbor(float x, float y, float nx, float ny) {
        Tile tile = tiles.get(new Coordinate(x,y,0));
        Tile neighbor = tiles.get(new Coordinate(nx,ny,0));
        tile.addNeighbor(neighbor);
    }
    public void addLine(float x, float y, float x1, float y1, float x2, float y2){
        Coordinate c1 = new Coordinate(x1,y1,0);
        Coordinate c2 = new Coordinate(x2,y2,0);
        Tile t = tiles.get(new Coordinate(x,y,0));
        t.addBorder(new Line(c1,c2));
    }
    public void generateWorld() {
        Handler h = new Handler();
        h.addGenerator(new IslandGenerator(islandType, width, height, maxAltitude));
        h.addGenerator(new BiomeGenerator());
        if(nbsWaterSource != 0) {
            h.addGenerator(new LakeGenerator(nbsWaterSource, random));
        }
        h.addGenerator(new RiverGenerator(nbsRiversSrc));

        h.process(this);
    }


    private Tile findRandomTile(){
        ArrayList<Tile> tiles = new ArrayList<>(this.tiles.values());
        return tiles.get(random.getRandomInt(tiles.size()-1));

    }
    public Tile findRandomVegetationTile(){
        Tile tile;
        do {
            tile = findRandomTile();
        } while (!(tile.getBiome() instanceof Vegetation));
        return tile;
    }
    public Coordinate findRandomCoordinate(){
        Tile tile = findRandomVegetationTile();
        HashSet<Coordinate> coordinates = new HashSet();
        for(Line line: tile.getBorder()) {
            coordinates.add(line.getC1());
            coordinates.add(line.getC2());
        }
        ArrayList<Coordinate> c = new ArrayList<>(coordinates);
        return c.get(random.getRandomInt(c.size()-1));
    }







}
