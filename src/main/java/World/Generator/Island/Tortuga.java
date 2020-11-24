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
        TreeMap<Double, List<Coordinate>> temp1 = new TreeMap<>();
        Tile tileCenter = tiles.get(ellipse.getEllipseCenter());
        double distance;
        int nbIslandTiles = 0;
        Ellipse islandTop = new Ellipse((int)Math.ceil(0.75*ellipse.getMajorRadius()), random, ellipse.getAngle(), ellipse.getEllipseCenter());

        for(Tile tile: tiles.values()){
            if(ellipse.isInShape(tile.getCenter()) && !tile.isInOcean() ) {
                ++nbIslandTiles;
                if (!islandTop.isInShape(tile.getCenter())) {
                    for(Coordinate c: tile.getCorner()){
                        distance = Math.abs(c.distance(islandTop.getEllipseCenter()));
                        if (distance > islandTop.getMinorRadius()) {
                            distance = Math.abs(distance - islandTop.getMinorRadius());
                        }
                        addTile(temp1, distance, c);
                    }
                } else {
                    tile.setAltitude(maxAltitude);
                }
            }
        }

        float altMinimum = calculateTileAlt(nbIslandTiles, maxAltitude);
        applyAltitude(temp1, altMinimum, maxAltitude);

        try {
            verifierPente(tileCenter , altMinimum);
        } catch (Exception e) {
            System.out.println("Une pente trop importante. impossible de generer un terrain");
            System.exit(0);
        }
    }

    public void applyAltitude(TreeMap<Double, List<Coordinate>> temp, float tileAlt, int maxAltitude) {
        float alt2 = (float) maxAltitude;

        for (List<Coordinate> cList : temp.values()) {
            alt2 -= tileAlt;
            for (Coordinate c: cList) c.setZ(alt2);
        }
    }

    @Override
    public  void setBorders(){

        for (Tile tile : tiles.values()) {

            if(tile.isInOcean()) {
                if (ellipse.isInShape(tile.getCenter())) {
                    tile.setOnIsland(true);
                    tile.setInOcean(false);
                }
            }
        }
    }

    public void addTile(TreeMap<Double, List<Coordinate>> temp, double distance, Coordinate c){
        if (temp.containsKey(distance)) {
            temp.get(distance).add(c);
        } else {
            List<Coordinate> tmp = new ArrayList<>();
            tmp.add(c);
            temp.put(distance, tmp);
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