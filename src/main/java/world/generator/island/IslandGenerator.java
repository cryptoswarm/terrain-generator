package world.generator.island;

import randomStrategy.RandomContexte;
import world.World;
import world.generator.Generator;
import world.generator.calculator.TileAttributesCalculator;

public class IslandGenerator implements Generator {

    public static final int AREA_OF_ISLAND = 92539;
    public static final int DIAMETER_Of_Island = 500;

    private final RandomContexte random;
    private final int maxAltitude;
    private final int nbIsland;
    private IslandShape islandShape;

    private TileAttributesCalculator calculator = new TileAttributesCalculator();

    public IslandGenerator(IslandShape islandShape, int maxAltitude, RandomContexte r, int nbIsland) {

        this.random = r;
        this.maxAltitude = maxAltitude;
        this.nbIsland = nbIsland;
        this.islandShape = islandShape;
    }

    @Override
    public void generate(World w) {

        int islandNotGenerated = 0;

        for(int n=0; n<nbIsland; n++) {

            if( !islandShape.createIsland(w, random, maxAltitude, DIAMETER_Of_Island) ){
                ++islandNotGenerated;
            }
        }

        if(islandNotGenerated > 0){
            System.out.println("Nombre d'ile non construit à cause du manque de tuiles est : "+islandNotGenerated);
        }
    }

/*
    public int calculateDiameter(World world){

        int islandNbTiles = 256;
        double area = 0;
        double tileSurface;
        int i = 0;

        NavigableMap<Double, Tile> navigableMap =  new TreeMap<>();
        for(Tile tile:world.getTiles().values()){

            tileSurface = calculator.findTileSurface(tile);

            navigableMap.put(tileSurface, tile);
        }

        Set<Double> descendingKeys = navigableMap.descendingKeySet();  //ordonner les tuiles de la plus grande surface à la plus petite

        for(Double surface:descendingKeys){

            area += surface;
            ++i;
            if(i == islandNbTiles){
                break;
            }
        }

        return  (int)( 2* Math.sqrt( area / Math.PI) );
    }

 */



}