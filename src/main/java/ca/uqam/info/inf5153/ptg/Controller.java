package ca.uqam.info.inf5153.ptg;

import RandomStrategy.RandomContexte;
import Translator.*;
import UserInterface.UserArgs;
import World.World;
import World.soilType;
import World.Tile;
import World.TileColor;
import World.Generator.Aquifer.*;
import World.Generator.Biome.BiomeGenerator;
import World.Generator.Island.IslandGenerator;
import World.Mode.*;


public class Controller {
    final private World world;
    final private Mode mode;
    final private RandomContexte random;
    final private  int nbsWaterSource;
    final private int nbsRiversSrc;
    final private  soilType soil;
    final private  String islandType;
    final private int maxAltitude;
    final private String fileName;
    final private String outFileName;
    private int width;
    private int height;

    public Controller(UserArgs parsedArgs) {
        this.random = new RandomContexte(parsedArgs.getSeed());
        this.world = new World(random);
        this.nbsWaterSource = parsedArgs.getNbWaterSources();
        this.islandType = parsedArgs.getShape();
        this.maxAltitude = parsedArgs.getMaxAltitude();
        this.nbsRiversSrc = parsedArgs.getRivers();
        this.soil = soilType.getSoilType(parsedArgs.getSoilType());
        this.fileName = parsedArgs.getInputFile();
        this.outFileName = parsedArgs.getOutputFile();
        switch (parsedArgs.getHeatmap()) {
            case "altitude":
                this.mode = new Altitude();
                break;
            case "humidity":
                this.mode = new Humidity();
                break;
            default:
                this.mode = new Normal();
                break;
        }
    }

    public  void createWorld() {
        MeshReader meshReader = new MeshReader(fileName, this);
        meshReader.readFile();

        generateWorld(world);

        MeshWriter meshWriter = new MeshWriter(this, fileName, outFileName);
        meshWriter.generateEndMesh();
    }

    public void setWorldHeight(int h){
        this.height = h;
    }
    public void setWorldWidth(int w){
        this.width = w;
    }

    public void addWorldTile(float x, float y){
        world.addTile(x,y);
    }
    public void addWorldLine(float x, float y, float x1, float y1, float x2, float y2) {
        world.addLine(x,y,x1,y1,x2,y2);
    }

    public  String getWorldTileColor(float x, float y){
        Tile tile = world.getTile(x,y);
        TileColor color = tile.getBackgroundColor();
        int factor = 0;
        if(mode instanceof Normal || mode instanceof Humidity) factor = tile.getHumidityLevel();
        if(mode instanceof Altitude) factor = (int)tile.getAltitude();
        return mode.getColor(color.getR(), color.getG(), color.getB(), color.getA(), factor);
    }
    public String getWorldLineColor(float x1, float y1, float x2, float y2){
        return world.getLineColor(x1,y1,x2,y2);
    }

    private void generateWorld(World world) {
        Handler h = new Handler();
        h.addGenerator(new IslandGenerator(islandType, width, height, maxAltitude, random));
        h.addGenerator(new BiomeGenerator());
        if(nbsWaterSource != 0) {
            h.addGenerator(new LakeGenerator(nbsWaterSource, random, soil));
        }
        h.addGenerator(new RiverGenerator(nbsRiversSrc, soil));

        h.process(world);
    }

}
