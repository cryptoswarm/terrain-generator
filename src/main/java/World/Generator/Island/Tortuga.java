package World.Generator.Island;

import Geometry.Coordinate;
import Geometry.Ellipse;
import RandomStrategy.RandomContexte;
import World.Tile;
import World.World;
import java.util.*;

public class Tortuga  extends Island {
    final private Ellipse ellipse;
    final private HashMap<Coordinate, Tile> tiles;
    final private RandomContexte random;
    final private int maxAltitude;

    public Tortuga(HashMap<Coordinate, Tile> tiles, Ellipse ellipse, RandomContexte random, int maxAltitude){
        this.ellipse = ellipse;
        this.tiles = tiles;
        this.random = random;
        this.maxAltitude = maxAltitude;
    }

    @Override
    public void apply(World world) {
        this.setBorders();
        this.defineAltitude(maxAltitude);
    }

    @Override
    public void defineAltitude(int maxAltitude){
        TreeMap<Double, List<Tile>> temp1 = new TreeMap<>();
        IslandShape islandShape = new EllipticIsland(tiles);
        Tile tileCenter = tiles.get( ellipse.getEllipseCenter() );
        double distance;
        int nbIslandTiles = 0;
        Ellipse ellipse1 = new Ellipse((int)Math.ceil(0.75*ellipse.getMajorRadius()), random, ellipse.getAngle(), ellipse.getEllipseCenter());

        for(Tile tile: tiles.values()){
            if(tile.getAltitude() == 1) {
                ++nbIslandTiles;
                if (!islandShape.inArea(tile, tileCenter, (int) ellipse1.getMajorRadius(), ellipse1.getAngle())) {
                    distance = Math.abs(tile.getCenter().distance(ellipse1.getEllipseCenter()));
                    if (distance > ellipse1.getMinorRadius()) {
                        distance = Math.abs(distance - ellipse1.getMinorRadius());
                    }
                    addTile(temp1, distance, tile);
                } else {
                    tile.setAltitude(maxAltitude);
                }
            }
        }

        float altMinimum = calculateTileAlt(nbIslandTiles, maxAltitude);
        applyProfilAltimetriqueBetweenFoyers(temp1, altMinimum, maxAltitude);

        try {
            verifierPente(tileCenter , altMinimum);
        } catch (Exception e) {
            System.out.println("Une pente trop importante. impossible de generer un terrain");
            System.exit(0);
        }
    }

    public void applyProfilAltimetriqueBetweenFoyers(TreeMap< Double,  List<Tile> > temp, float tileAlt, int maxAltitude) {
        float alt2 = (float) maxAltitude;

        for (List<Tile> tileList : temp.values()) {
            alt2 -= tileAlt;
            for (Tile tile : tileList) tile.setAltitude(alt2);
        }
    }

    @Override
    public  void setBorders(){
        for (Tile tile : tiles.values()) {
            if(tile.getAltitude() == -1) {
                if (ellipse.isInEllipse(tile.getCenter())) {
                    tile.setAltitude(1);
                }
            }
        }
    }


    public void addTile(TreeMap<Double, List<Tile>> temp, double distance, Tile tile){
        if (temp.containsKey(distance)) {
            temp.get(distance).add(tile);
        } else {
            List<Tile> tiles1 = new ArrayList<>();
            tiles1.add(tile);
            temp.put(distance, tiles1);
        }
    }

    public float calculateTileAlt(int tilesNb, int altMax){
        return (float) altMax / tilesNb;
    }

    public void verifierPente(Tile tileCenter, float altMinimum ) throws Exception{
        double alt = tileCenter.getAltitude();
        double denivellation;
        denivellation = Math.abs( alt - (double) altMinimum);
        double pente = denivellation  / ( ellipse.getMajorRadius()*100 );
        if(pente*100 > 3) throw new Exception("Invalid Island");
    }
}