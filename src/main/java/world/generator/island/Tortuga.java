package world.generator.island;

import geometry.Coordinate;
import geometry.Ellipse;
import islandSet.Isle;
import randomStrategy.RandomContexte;
import world.Tile;
import world.World;

import java.util.*;

public class Tortuga  extends Island {

    final private Ellipse ellipse;

    final private RandomContexte random;
    final private int maxAltitude;

    private List<Coordinate> uniqeCoordinates = new ArrayList<>();
    private Isle isle;

    public Tortuga( Isle isle, Ellipse  ellipse, RandomContexte random, int maxAltitude){

        this.isle = isle;
        this.ellipse = ellipse;
        this.random = random;
        this.maxAltitude = maxAltitude;
    }

    @Override
    public void apply(World world) {
        setBorders(world);
        defineAltitude(world, maxAltitude);
        world.addArchipelago(isle);
    }

    /**
     * On commence par la creation d'une ellipse qui va servire à trouver les tuiles se trouvant sur le sommet de l'ile.
     * Pour le reste des tuiles, on les ordonne  en fonction de leur distance du centre de l'ellipse
     * La plus proche au centre aura une altitude maximal
     * Autant qu'on s'eloigne du centre autant que l'altitude dimune
     *
     * @param world objet World permettant d'acceder aux methodes publiques
     * @param maxAltitude l'altitude maximale que l'ile va avoir
     */
    @Override
    public void defineAltitude(World world, int maxAltitude){

        Map<Double, List<Tile>> temp1 = new TreeMap<>();

        double distance;

        int nbIslandTiles = isle.getIsleTilesNumber();

        Ellipse islandTop = new Ellipse((int)Math.ceil(0.5*ellipse.getMajorRadius()), random, ellipse.getAngle(), ellipse.getEllipseCenter() );

        Set<Tile> islandSummitTiles = isle.getIsleTiles(islandTop);

        Set<Tile> islandExceptSummit = isle.getRemainingTiles(islandSummitTiles);

        for(Tile tile: islandExceptSummit){

            distance = Math.abs(tile.getCenter().distance( ellipse.getEllipseCenter() ));

            addTile(temp1, distance, tile);
        }

        for(Tile tile: islandSummitTiles){
            tile.setAltitude(maxAltitude);
        }

        float altMinimum = calculateTileAlt(nbIslandTiles, maxAltitude);
        applyAltitude(temp1, altMinimum, maxAltitude);


        try {
            verifierPente(ellipse.getEllipseCenter() , altMinimum);
        } catch (Exception e) {
            System.out.println("Une pente trop importante. impossible de generer un terrain");
            System.exit(0);
        }
    }

    public void applyAltitude( Map<Double, List<Tile>> temp, float diffrenceAltEachtile, int maxAltitude) {

        float currentAlt = (float) maxAltitude;

        for (List<Tile> tileList : temp.values()) {
            currentAlt -= diffrenceAltEachtile;
            for(Tile tile:tileList){
                applyAltitudeToTileCorners(tile, currentAlt, ellipse.getEllipseCenter(), diffrenceAltEachtile, uniqeCoordinates );


                uniqeCoordinates.addAll(tile.getCorner());
            }
        }
    }


    @Override
    public  void setBorders(World world){
        isle.defineTortugaBorders();
    }

    public void addTile(Map<Double, List<Tile>> temp, double distance, Tile tile){

        if (temp.containsKey(distance)) {
            temp.get(distance).add(tile);

        } else {
            List<Tile> tmp = new ArrayList<>();
            tmp.add(tile);
            temp.put(distance, tmp);
        }
    }

    public float calculateTileAlt(int tilesNb, int altMax){
        return (float) altMax / tilesNb;
    }

    public void verifierPente(Coordinate coordinate, float altMinimum ) throws Exception{

        double alt = coordinate.getZ();
        double denivellation;
        denivellation = Math.abs( alt - (double) altMinimum);
        double pente = denivellation  / ( ellipse.getMajorRadius()*100 );
        if(pente*100 > 3) throw new Exception("Invalid Island");
    }


}