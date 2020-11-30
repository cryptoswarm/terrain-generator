package world.generator.island;

import geometry.Coordinate;
import randomStrategy.RandomContexte;
import world.Tile;
import world.World;
import world.generator.Generator;
import world.generator.calculator.TileAttributesCalculator;

import java.util.NavigableMap;
import java.util.TreeMap;

public class IslandGenerator extends TileAttributesCalculator implements Generator {

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

        int diameter = calculateDiameter(w);
        System.out.println("diameter = "+diameter);
        Coordinate border = generateBorder();

        int islandNotGenerated = 0;
        IslandShape islandShape;

        if ("tortuga".equals(shape)) {
            islandShape = new EllipticIsland( height, width);
        } else {
            islandShape = new CircularIsland( height, width);
        }

        for(int n=0; n<nbIsland; n++) {
            if( !islandShape.createIsland(w, random, maxAltitude, border) ){
                ++islandNotGenerated;
            }
        }

        if(islandNotGenerated > 0){
            System.out.println("Nombre d'ile non construit à cause du manque de tuiles est : "+islandNotGenerated);
        }
    }

    public Coordinate generateBorder(){
        int shortest = Math.min(width, height);
        //magic
        float x = random.getRandomInt(shortest / 16) + (float)shortest*3/8;
        float y = random.getRandomInt(shortest / 16) + (float)shortest*3/4;
        return new Coordinate(x, y, 0);
    }

    public int calculateDiameter(World world){
        int nbTiles = world.getTilesNb();
        int islandNbTiles = random.getRandomInt(20)+200;
        double area = 0;
        double tileSurface;
        int i = 0;
        //TreeMap<Double, Tile> surfaceEachTile = new TreeMap<>();
        NavigableMap<Double, Tile> reverseOrder =  new TreeMap<>();
        for(Tile tile:world.getTiles().values()){
            System.out.println(tile.getCorner().toString());
            tileSurface = findTileSurface(tile);
            System.out.println("area = "+tileSurface);
            /*
            area += tileSurface;
            ++i;
            if(i == islandNbTiles){
                break;
            }

             */
            //surfaceEachTile.put(tileSurface, tile);
            reverseOrder.put(tileSurface, tile);
        }
        //reverseOrder.descendingKeyMap();
        for(NavigableMap.Entry<Double, Tile> entry:reverseOrder.entrySet()){

            area += entry.getKey();
            /*
            ++i;
            if(i == islandNbTiles){
                break;
            }

            */

            System.out.println(entry.getKey());
        }
        System.out.println(" area = "+area);


        return  (int)( 2* Math.sqrt( area / Math.PI) );
    }


}