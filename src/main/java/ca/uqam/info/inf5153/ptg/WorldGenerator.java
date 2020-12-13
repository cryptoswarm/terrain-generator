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
import world.generator.biome.Localization;
import world.generator.interestPoints.InterestPointsGenerator;
import world.generator.interestPoints.RoadGenerator;
import world.generator.island.IslandGenerator;
import world.generator.ressourcesProduction.RessourceGenerator;
import world.mode.Mode;
import world.soilType;

import java.util.ArrayList;


public class WorldGenerator {

    private final World world;
    private Mode mode;
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
    private Localization localization;
    private boolean road;

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
        this.localization = Localization.getLocalization(parsedArgs.getLocalisation());
        this.road = parsedArgs.isRoadActivated();
    }

    /**
     * Lire le fichier
     * Applique la logique d'affaire
     * Écrire dans le fichier de sortie
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
     * @param h définir la longueur de la carte
     */

    public void setWorldHeight(int h){
        this.height = h;
    }

    /**
     *
     * @param w définir la largeur de la carte
     */
    public void setWorldWidth(int w){
        this.width = w;
    }

    /**
     * Ajouter une tuile au monde
     *
     * @param tile une tuile
     */
    public void addWorldTile(Tile tile){
        world.addTile(tile);
    }

    /**
     *
     * @param coordinate  une coordonnée centre d'une tuile
     * @return la couleur de la tuile correspondant à cette coordonnée
     */


    public  String getWorldTileColor(Coordinate coordinate){

        Tile tile = world.getTile(coordinate);
        TileColor color = tile.getBackgroundColor();
        int factor;
        factor = mode.getFactor(tile);
        return mode.getColor(color.getR(), color.getG(), color.getB(), color.getA(), factor);

    }

    /**
     *
     * @param coordinate une coordonnée
     * @return la couleur de la coordonnée
     */

    public String getPointColor(Coordinate coordinate){


        if(mode.getMode() == Mode.Modes.Normal){

            Tile tile = world.getTile(coordinate);
            InterestPointsGenerator.POIS poisTile = tile.getPois();
            if (poisTile == InterestPointsGenerator.POIS.NOTHING){

                return getWorldTileColor(coordinate);
            }else{

                return poisTile.getTileColor().toString();
            }
        }

        return getWorldTileColor(coordinate);
    }

    /**
     *
     * @param line  une ligne
     * @return   la couleur de la ligne
     */

    public String getWorldLineColor(Line line){

        if(mode.getMode() == Mode.Modes.Normal){

            return world.getLineColor(line);
        }

        return "0:0:0:0";
    }


     /**
     * Ensemble des lignes constituant les routes reliants les différent biom entre eux
     * @return
     */

    public List<Line> getRoads(){

        if (mode.getMode() == Mode.Modes.Normal){
            return world.getRoads();
        }
        return new ArrayList<>();
    }

    /**
     * Enrichir le monde en se basant sur les caractéristiques voulant par l'utilisateur
     * L'ordre de la generation est important
     *
     * On commence par la generation des iles, le nombre et la forme
     * On ajoute des biomes aux iles, la localisation des biomes est prises en conséderation
     *
     * Ensuites, les sources d'eau, chaque ile va avoir le meme nombre source d'eau
     * Par contre, la distribution est différente
     *
     * Apres, on passe à la generation des rivieres
     *
     * Si la prodution est activée, on genere les resources.
     *
     * @param world l'objet world
     */

    private void generateWorld(World world) {

        Generator IslGenerator = new IslandGenerator(shape, width, height, maxAltitude, random, nbsIsland);
        IslGenerator.generate(world);

        Generator biomeGenerator = new BiomeGenerator(localization);
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
            Generator interestPointsGenerator = new InterestPointsGenerator(this.pois, random);
            interestPointsGenerator.generate(world);

            if(road){
                Generator rg = new RoadGenerator();
                rg.generate(world);
            }
        }
    }
}
