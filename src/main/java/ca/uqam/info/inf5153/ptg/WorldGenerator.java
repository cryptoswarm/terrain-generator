package ca.uqam.info.inf5153.ptg;

import geometry.Coordinate;
import geometry.Line;
import randomStrategy.RandomContexte;
import translator.MeshReader;
import translator.MeshWriter;
import translator.Reader;
import translator.Writer;
import userInterface.UserArgs;
import world.Tile;
import world.TileColor;
import world.World;
import world.generator.Generator;
import world.generator.aquifer.LakeGenerator;
import world.generator.aquifer.RiverGenerator;
import world.generator.biome.BiomeGenerator;
import world.generator.interestPoints.InterestPointsGenerator;
import world.generator.island.IslandGenerator;
import world.generator.ressourcesProduction.RessourceGenerator;
import world.mode.Mode;
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
    private boolean production;
    private int [] pois;

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
        this.production = parsedArgs.isProductionActivated();
        this.pois = parsedArgs.getPois();
    }

    /**
     * Lire le fichier
     * Applique la logique d'affaire
     * Ecrire dans le fichier de sortie
     */
    public  void createWorld() {

        Reader meshReader = new MeshReader(fileName, this);
        meshReader.readFile();

        generateWorld(world);

        Writer meshWriter = new MeshWriter(this, fileName, outFileName);
        meshWriter.generateEndMesh();
    }

    /**
     *
     * @param h definier la longueur de la carte
     */

    public void setWorldHeight(int h){
        this.height = h;
    }

    /**
     *
     * @param w definir la largeur de la carte
     */
    public void setWorldWidth(int w){
        this.width = w;
    }

    /**
     *
     * @param tile la tuile à ajouter dans le world
     */
    public void addWorldTile(Tile tile){
        world.addTile(tile);
    }


    public  String getWorldTileColor(Coordinate coordinate){

        Tile tile = world.getTile(coordinate);
        TileColor color = tile.getBackgroundColor();
        int factor;
        factor = mode.getFactor(tile);
        return mode.getColor(color.getR(), color.getG(), color.getB(), color.getA(), factor);

    }

    public String getWorldLineColor(Line line){

        if(mode.getMode() == Mode.Modes.Normal){

            return world.getLineColor(line);
        }

        return "0:0:0:0";
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

        Generator riverGenerator= new RiverGenerator(nbsRiversSrc, soil, random);
        riverGenerator.generate(world);

        if( production ){
            Generator ressourcesGenerator = new RessourceGenerator();
            ressourcesGenerator.generate(world);
            Generator interestPointsGenerator = new InterestPointsGenerator(this.pois);
            interestPointsGenerator.generate(world);

        }
    }

}
