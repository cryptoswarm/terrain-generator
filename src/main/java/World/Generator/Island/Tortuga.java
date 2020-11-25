package World.Generator.Island;

import Geometry.Coordinate;
import Geometry.Ellipse;
import RandomStrategy.RandomContexte;
import World.Tile;
import World.World;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Tortuga  extends Island {
    final private Ellipse ellipse;

    final private RandomContexte random;
    final private int maxAltitude;

    public Tortuga( Ellipse ellipse, RandomContexte random, int maxAltitude){
        this.ellipse = ellipse;

        this.random = random;
        this.maxAltitude = maxAltitude;
    }

    @Override
    public void apply(World world) {
        setBorders(world);
        defineAltitude(world, maxAltitude);
    }

    @Override
    public void defineAltitude(World world, int maxAltitude){

        List<Tile> islandTiles = world.getIslandTiles( ellipse );

        TreeMap<Double, List<Coordinate>> temp1 = new TreeMap<>();
        Tile tileCenter = world.getAtile( ellipse.getEllipseCenter() );

        double distance;
        int nbIslandTiles = islandTiles.size();
        Ellipse islandTop = new Ellipse((int)Math.ceil(0.75*ellipse.getMajorRadius()), random, ellipse.getAngle(), ellipse.getEllipseCenter());
        List<Tile> islandSummitTiles = world.getIslandTiles( islandTop );
        islandTiles.removeAll(islandSummitTiles);

        for(Tile tile: islandTiles){

            for(Coordinate c: tile.getCorner()){
                distance = Math.abs(c.distance(islandTop.getEllipseCenter()));
                if (distance > islandTop.getMinorRadius() && distance < islandTop.getMajorRadius() ) {
                    addTile(temp1,  Math.abs(distance - islandTop.getMinorRadius() ), c);
                }else{
                    addTile(temp1, Math.abs(distance - islandTop.getMajorRadius()), c);
                }

            }
        }
        for(Tile tile: islandSummitTiles){
            tile.setAltitude(maxAltitude);
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
    public  void setBorders(World world){

        world.setEllipticIslandBorders(ellipse);
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