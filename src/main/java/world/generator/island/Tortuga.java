package world.generator.island;

import geometry.Coordinate;
import geometry.Ellipse;
import randomStrategy.RandomContexte;
import world.Tile;
import world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Tortuga  extends Island {

    final private Ellipse ellipse;

    final private RandomContexte random;
    final private int maxAltitude;
    private List<Tile> islandTiles;

    public Tortuga( List<Tile> islandTiles, Ellipse  ellipse, RandomContexte random, int maxAltitude){

        this.islandTiles = islandTiles;
        this.ellipse = ellipse;
        this.random = random;
        this.maxAltitude = maxAltitude;
    }

    @Override
    public void apply(World world) {
        setBorders(world);
        defineAltitude(world, maxAltitude);
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

        TreeMap<Double, List<Tile>> temp1 = new TreeMap<>();

        //Tile tileCenter = world.getAtile( ellipse.getEllipseCenter() );

        double distance;
        int nbIslandTiles = islandTiles.size();
        Ellipse islandTop = new Ellipse((int)Math.ceil(0.75*ellipse.getMajorRadius()), random, ellipse.getAngle(), ellipse.getEllipseCenter() );
        List<Tile> islandSummitTiles = world.getIslandTiles( islandTop );
        islandTiles.removeAll(islandSummitTiles);

        for(Tile tile: islandTiles){


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

    public void applyAltitude(TreeMap<Double, List<Tile>> temp, float tileAlt, int maxAltitude) {

        float alt2 = (float) maxAltitude;

        for (List<Tile> tileList : temp.values()) {
            alt2 -= tileAlt;
            for(Tile tile:tileList){

                applyAltitudeToTileCorners( tile, alt2 );
            }
        }
    }



    private void applyAltitudeToTileCorners( Tile tile, double alt ){
        double distance = Math.abs(tile.getCenter().distance(ellipse.getEllipseCenter()));
        for(Coordinate c: tile.getCorner()){
            double dist = c.distance(ellipse.getEllipseCenter() );
            if( dist > distance){

                c.setZ((float) Math.abs(alt - (dist - distance) ));
            }else if( dist < distance ){
                c.setZ((float) Math.abs(alt + (dist - distance) ));

            }else{
                c.setZ((float) Math.abs(alt ));
            }
        }
    }

    @Override
    public  void setBorders(World world){

        for(Tile tile:islandTiles){
            tile.setOnIsland(true);
            tile.setInOcean(false);
        }


        //world.setEllipticIslandBorders(ellipse);
    }

    public void addTile(TreeMap<Double, List<Tile>> temp, double distance, Tile tile){

        if (temp.containsKey(distance)) {
            temp.get(distance).add(tile);

        } else {
            List<Tile> tmp = new ArrayList<>();
            tmp.add(tile);
            temp.put(distance, tmp);
        }

    }

    public void addCoordinate(TreeMap<Double, List<Coordinate>> temp, double distance, Coordinate coordinate){

        if (temp.containsKey(distance)) {

            if( !containsSameCoordinate( temp.get(distance), coordinate)){
                temp.get(distance).add(coordinate);
            }

        } else {
            List<Coordinate> tmp = new ArrayList<>();
            tmp.add(coordinate);
            temp.put(distance, tmp);
        }

    }

    private boolean containsSameCoordinate( List<Coordinate> coordinateList, Coordinate coordinate ){
        boolean isFound = false;
        for(Coordinate c:coordinateList){
            if(c.equals(coordinate)){
                System.out.println("same coordinate found");
                isFound = true;
                break;
            }
        }
        return isFound;
    }


    public float calculateTileAlt(int tilesNb, int altMax){
        return (float) altMax / tilesNb;
    }

    public void verifierPente(Coordinate coordinate, float altMinimum ) throws Exception{
        //double alt = tileCenter.getAltitude();
        double alt = coordinate.getZ();
        double denivellation;
        denivellation = Math.abs( alt - (double) altMinimum);
        double pente = denivellation  / ( ellipse.getMajorRadius()*100 );
        if(pente*100 > 3) throw new Exception("Invalid Island");
    }
}