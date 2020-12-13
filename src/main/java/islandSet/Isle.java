package islandSet;

import geometry.Circle;
import geometry.Coordinate;
import geometry.Line;
import geometry.Shape;
import randomStrategy.RandomContexte;
import world.Tile;
import world.TileColor;
import world.generator.interestPoints.InterestPointsGenerator;

import java.util.*;

public class Isle {



    private Set<Tile> islandTiles;
    public static final String VEGETATION_BIOM = "vegetation";
    public static final String BEACH_BIOM = "beach";

    public Isle(Set<Tile> islandTiles) {
        this.islandTiles = islandTiles;
    }

    /**
     *
     * @return True si l'ile contient des tuile, false sinon
     */

    public boolean isValide(){

        return !islandTiles.isEmpty();
    }

    /**
     *
     * @return toutes les tuiles constituant l'ile (should be private)
     */

    public Set<Tile> getIslandTiles() {
        return islandTiles;
    }

    /**
     * Trouver l'ensemble de tuiles contituant le biom vegetation dans une ile
     * @return ensemble de tuile
     */

    public List<Tile> getVegetationTiles(){
        List<Tile > tileList = new ArrayList<>();
        for(Tile tile:islandTiles){
            if(!tile.getItem().getType().equals(BEACH_BIOM) && tile.isOnIsland() ){
                tileList.add(tile);
            }
        }
        return tileList;
    }

    /**
     *
     * @param random controlled random seed
     * @return  On retourne une tuile aleatoire du biome vegetation appartenant à l'ile
     */

    public Tile findRandomVegetationTile(RandomContexte random){


        boolean isValide = false;
        List<Tile> tempTilesList = new ArrayList<>(this.islandTiles);
        Tile tile = null;
        while (!tempTilesList.isEmpty() && !isValide){
            tile = findRandomTile(random);
            if(tile.getItem().getType().equals(VEGETATION_BIOM)){
                isValide = true;
            }else{
                tempTilesList.remove(tile);
            }
        }

        return tile;
    }

    /**
     *
     * @param random controlled random seed
     * @return  On retourne une tuile aleatoire appartenant à l'ile
     */

    public Tile findRandomTile(RandomContexte random){
        List<Tile> tiles = new ArrayList<>(islandTiles);
        return tiles.get(random.getRandomInt(tiles.size()-1));

    }

    /**
     * Une coordonnée de biom vegetation
     * @param random un seed random
     * @return une coordonnée
     */

    public Coordinate findRandomCoordinate(RandomContexte random){

        Tile tile = findRandomVegetationTile(random);

        Set<Coordinate> coordinates = new HashSet<>(tile.getCorner());
        List<Coordinate> c = new ArrayList<>(coordinates);
        return c.get(random.getRandomInt(c.size()-1));
    }

    /**
     * Trouver toutes les tuiles entourant la tuile en question
     * @param t  une tuile
     * @return ensemble de tuile
     */

    public Set<Tile> getNeighbor(Tile t) {

        Set<Coordinate> coordinate = new HashSet<>();
        Set<Tile> neighbor = new HashSet<>();

        for(Line line: t.getBorder()){
            coordinate.add(line.getC1());
            coordinate.add(line.getC2());
        }
        for(Coordinate c: coordinate) {
            neighbor.addAll( getNeighbor(c) );
        }
        return neighbor;
    }

    /**
     * Trouver l'ensemble de tuile qui se rencontrent dans la meme coordonnée
     * @param c  une coordonnée
     * @return  ensemble de tuile
     */

    public Set<Tile> getNeighbor(Coordinate c) {

        Set<Tile> neighbor = new HashSet<>();
        for(Tile tile: islandTiles){
            for(Line line: tile.getBorder()){
                if(c.equals(line.getC1()) || c.equals(line.getC2())){
                    neighbor.add(tile);
                }
            }
        }
        return neighbor;
    }

    /**
     *
     * @param c une coordonnée
     * @return enselble de lignes qui intersectent dans la coordonnée en question
     */


    public Set<Line> getLine(Coordinate c){

        Set<Line> lines = new HashSet<>();
        for(Tile tile: islandTiles){

            for (Line line : tile.getBorder()) {
                 if(line.isCoordinateValid(c)){
                     lines.add(line);
                 }
            }
        }
        return lines;
    }

    /**
     * Trouver toutes les tuiles qui partagent la meme line
     * @param l  une line (deux coordonnées )
     * @return ensemble de tuiles voisines
     */

    public Set<Tile> getNeighbor(Line l) {
        Set<Tile> neighbor = new HashSet<>();

        neighbor.addAll(getNeighbor(l.getC1()));
        neighbor.addAll(getNeighbor(l.getC2()));

        return neighbor;
    }

    /**
     *
     * @return ensemble de tuiles contenant tous les points d'interets se trouvant sur l'ile
     */

    public List<Tile> getIsleinterestPoints(){

        List<Tile> interestPointsList = new ArrayList<>();

        for (Tile tile : islandTiles ){

            if(tile.getPois() != InterestPointsGenerator.POIS.NOTHING){
                interestPointsList.add(tile);
            }
        }
        return interestPointsList;
    }

    /**
     *
     * @return ensemble de tuiles contenant un point d'interet de type port
     */

    public List<Tile> getIslePortInterestPoints(){

        List<Tile> interestPointsList = new ArrayList<>();

        for (Tile tile : islandTiles ){

            if(tile.getPois() == InterestPointsGenerator.POIS.PORTS){
                interestPointsList.add(tile);
            }
        }
        return interestPointsList;
    }

    /**
     * Trouver la couleur de la ligne d'une tuile de l'ile
     * @param line une ligne
     * @return la la couleur de la ligne
     */

    public TileColor findLineColor(Line line){
        TileColor color = null;
        for( Tile tile:islandTiles){
            color = tile.getLineColor(line);
            if(color != null){
                break;
            }
        }
        return color;
    }

    /**
     *
     * @param s  La forme de l'ile qu'on veut créer
     * @return   les tuiles qui composent l'ile
     */
    public Set<Tile> getIsleTiles( Shape s){

        Set<Tile> tileList = new LinkedHashSet<>();
        for (Tile tile : islandTiles ) {
            if( s.isInShape( tile.getCenter() ) ){
                tileList.add(tile);
            }
        }
        return tileList;
    }

    /**
     *
     * @return le nombre de tuiles composant l'ile
     */
    public int getIsleTilesNumber(){
        return islandTiles.size();
    }

    /**
     * Enlever les tuiles composant le sommit de l'ile de l'ensemble de l'ile
     * @param islandSummitTiles tuiles composant le sommit d'une ile
     * @return le reste des tuiles
     */

    public Set<Tile> getRemainingTiles( Set<Tile> islandSummitTiles ){
        Set<Tile> copy = new HashSet<>(islandTiles);
        copy.removeAll(islandSummitTiles);
        return copy;
    }

    /**
     * Une ile de forme tortuga
     * Définir à quel biome appartient chaque tuile de l'ile.
     */

    public void defineTortugaBorders() {

        for (Tile tile : islandTiles) {

            tile.setOnIsland(true);
            tile.setInOcean(false);
        }
    }

    /**
     * Une ile de forme atoll
     * @param circle  une cercle entourant l'ile
     */

    public void definerAtollShapedBorders(Circle circle){

        for (Tile tile : islandTiles ) {

            double distance = tile.getCenter().distance(circle.getCenter());

            if (distance > circle.getSmallRadius() && distance < circle.getBigRadius()) {

                tile.setOnIsland(true);
                tile.setInOcean(false);

            } else if (distance <= circle.getSmallRadius()) {

                tile.setInLagoon(true);
                tile.setInOcean(false);
            }

        }
    }




}
