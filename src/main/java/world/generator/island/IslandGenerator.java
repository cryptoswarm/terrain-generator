package world.generator.island;

import geometry.Coordinate;
import randomStrategy.RandomContexte;
import world.generator.Generator;
import world.World;

public class IslandGenerator implements Generator {

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


}