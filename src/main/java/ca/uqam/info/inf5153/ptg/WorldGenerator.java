package ca.uqam.info.inf5153.ptg;

import randomStrategy.RandomContexte;
import translator.MeshReader;
import translator.MeshWriter;
import translator.Reader;
import translator.Writer;
import userInterface.UserArgs;
import world.generator.aquifer.LakeGenerator;
import world.generator.aquifer.RiverGenerator;
import world.generator.biome.BiomeGenerator;
import world.generator.Generator;
import world.generator.island.IslandGenerator;
import world.mode.Mode;
import world.Tile;
import world.TileColor;
import world.World;
import world.soilType;


public class WorldGenerator {

    private final World world;
    private final Mode mode;
    private final RandomContexte random;
    private final int nbsWaterSource;
    private final int nbsRiversSrc;
    private final soilType soil;
    private final String shape;
    private final int maxAltitude;
    private final String fileName;
    private final String outFileName;
    private final int nbsIsland;
    private int width;
    private int height;

    public WorldGenerator(UserArgs parsedArgs) {

        this.random = new RandomContexte(parsedArgs.getSeed());
        this.world = new World(random);
        this.nbsWaterSource = parsedArgs.getNbWaterSources();
        this.shape = parsedArgs.getShape();
        this.maxAltitude = parsedArgs.getMaxAltitude();
        this.nbsRiversSrc = parsedArgs.getRivers();
        this.soil = soilType.getSoilType(parsedArgs.getSoilType());
        this.fileName = parsedArgs.getInputFile();
        this.outFileName = parsedArgs.getOutputFile();
        this.nbsIsland = parsedArgs.getNbsIsland();
        this.mode = parsedArgs.getHeatmap();

    }

    public  void createWorld() {
        Reader meshReader = new MeshReader(fileName, this);
        meshReader.readFile();

        generateWorld(world);

        Writer meshWriter = new MeshWriter(this, fileName, outFileName);
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
        int factor;
        factor = mode.getFactor(tile);
        return mode.getColor(color.getR(), color.getG(), color.getB(), color.getA(), factor);

    }

    public String getWorldLineColor(float x1, float y1, float x2, float y2){

        return world.getLineColor(x1,y1,x2,y2);
    }

    private void generateWorld(World world) {

        Generator IslGenerator = new IslandGenerator(shape, width, height, maxAltitude, random, nbsIsland);
        IslGenerator.generate(world);

        Generator biomeGenerator = new BiomeGenerator();
        biomeGenerator.generate(world);

        if(nbsWaterSource != 0) {
            Generator lakeGenerator  = new LakeGenerator(nbsWaterSource, random, soil);
            lakeGenerator.generate(world);
        }

        Generator riverGenerator= new RiverGenerator(nbsRiversSrc, soil);
        riverGenerator.generate(world);

    }

}
