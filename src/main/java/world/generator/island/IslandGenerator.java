package world.generator.island;

import randomStrategy.RandomContexte;
import world.Tile;
import world.World;
import world.generator.Generator;
import world.generator.calculator.TileAttributesCalculator;

import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

public class IslandGenerator extends TileAttributesCalculator implements Generator {

    public static final int AREA_OF_ISLAND = 92539;
    public static final int DIAMETER_Of_Island = 500;

    private final int width;
    private final int height;
    private final RandomContexte random;
    private final int maxAltitude;
    private final int nbIsland;
    private final String shape;


    public IslandGenerator(String shape, int w, int h, int maxAltitude, RandomContexte r, int nbIsland) {
        this.width = w;
        this.height = h;
        this.random = r;
        this.maxAltitude = maxAltitude;
        this.nbIsland = nbIsland;
        this.shape = shape;
    }

    @Override
    public void generate(World w) {

        //int diameter = (int)( 2* Math.sqrt( AREA_OF_ISLAND / Math.PI) );
        //int diameter = calculateDiameter(w);
        int islandNotGenerated = 0;
        IslandShape islandShape;


        if ("tortuga".equals(shape)) {
            islandShape = new EllipticIsland( height, width);
        } else {
            islandShape = new CircularIsland( height, width);
        }

        for(int n=0; n<nbIsland; n++) {

            if( !islandShape.createIsland(w, random, maxAltitude, DIAMETER_Of_Island) ){
                ++islandNotGenerated;
            }
        }

        if(islandNotGenerated > 0){
            System.out.println("Nombre d'ile non construit à cause du manque de tuiles est : "+islandNotGenerated);
        }
    }


    public int calculateDiameter(World world){

        int islandNbTiles = 256;
        double area = 0;
        double tileSurface;
        int i = 0;

        NavigableMap<Double, Tile> navigableMap =  new TreeMap<>();
        for(Tile tile:world.getTiles().values()){

            tileSurface = findTileSurface(tile);

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


}