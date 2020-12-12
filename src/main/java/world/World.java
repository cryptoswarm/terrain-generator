package world;

import geometry.Coordinate;
import geometry.Line;
import geometry.Shape;
import islandSet.Isle;
import randomStrategy.RandomContexte;

import java.util.*;

public class World {

    final private RandomContexte random;
    final private HashMap<Coordinate, Tile> tiles;
    private List<Isle> isleList;
    private final TileColor colorDark = TileColor.DARK;
    private ArrayList<Line> roads = new ArrayList<>();

    public World(RandomContexte random) {

        this.tiles = new HashMap<>();
        this.random = random;
        this.isleList = new ArrayList<>();
    }


    /**
     *
     * @param coordinate une coordonnée
     * @return  la tuile equivalente
     */

    public Tile getTile(Coordinate coordinate){
        return tiles.get(coordinate);
    }

    /**
     * On cherche la couleur de la ligne
     * @param line est la ligne qu'on cherche à obtenir sa couleur
     * @return   la couleur de la ligne
     */
    /*
    public String getLineColor(Line line){
        TileColor color = null;
        boolean isFound = false;

        for(int i=0; i<isleList.size() && !isFound; i++){
            for( Tile tile:isleList.get(i).getIslandTiles()){
                color = tile.getLineColor(line);
                if(color != null){
                    isFound = true;
                    break;
                }
            }
        }

        if(isFound){
            System.out.println("line found ");
        }

        if(color != null) {
            return color.toString();
        }

        return "0:0:0:0";
    }

     */

    /**
     * On cherche la couleur de la ligne
     * @param line est la ligne qu'on cherche à obtenir sa couleur
     * @return   la couleur de la ligne
     */

    public String getLineColor(Line line){
        TileColor color = null;

        for (Isle isle : isleList) {

            if ((color = isle.findLineColor(line)) != null) {

                break;
            }
        }
        if(color != null) {
            return color.toString();
        }

        return "0:0:0:0";
    }

    public void setRoads(Tile t1, Tile t2){

        roads.add(new Line(t1.getCenter(), t2.getCenter()));

    }

    public ArrayList<Line> getRoads(){
        return roads;
    }


    /**
     *
     * @param c une coordonnée
     * @return la liste des tuiles qui ont la meme coordonnée
     */
    public HashSet<Tile> getNeighbor(Coordinate c) {

        HashSet<Tile> neighbor = new HashSet<>();
        for(Tile tile: tiles.values()){
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
     * @param l est la ligne en commun entre deux tuiles
     * @return la liste des tuiles qui ont la meme ligne
     */

    public HashSet<Tile> getNeighbor(Line l) {
        HashSet<Tile> neighbor = new HashSet<>();

        neighbor.addAll(getNeighbor(l.getC1()));
        neighbor.addAll(getNeighbor(l.getC2()));

        return neighbor;
    }

    /**
     *
     * @param t une tuile
     * @return la liste des tuiles qui intersectent à une quelconque coordonnée  de  la tuile t
     */

    public HashSet<Tile> getNeighbor(Tile t) {

        HashSet<Coordinate> coordinate = new HashSet<>();
        HashSet<Tile> neighbor = new HashSet<>();

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
     *
     * @param c une coordonnée
     * @return la liste des lignes dont la coordonnée c est une de leurs coordonnées
     */
    public HashSet<Line> getLine(Coordinate c){

        HashSet<Line> lines = new HashSet<>();
        for(Tile tile: tiles.values()){

            for (Line line : tile.getBorder()) {
                if (line.getC1().equals(c) || line.getC2().equals(c)) {
                    lines.add(line);
                }
            }
        }
        return lines;
    }

    /**
     * Ajouter une tuile à la fois

     * @param  tile, la tuile à ajouter dans la listes des tuiles

     */
    public void addTile(Tile tile) {

        Coordinate c = tile.getCenter();
        tiles.put(c,tile);
    }



    /**
     *
     * @return une tuile généré aleatoirement a partir de la listes des tuiles composant la carte
     */
    public Tile findRandomTile(){
        ArrayList<Tile> tiles = new ArrayList<>(this.tiles.values());
        return tiles.get(random.getRandomInt(tiles.size()-1));

    }

    /**
     *
     * @return  une tuile généré aléatoirement appartenant au îles et n'étant pas de la beach
     */
    public Tile findRandomVegetationTile(){
        Tile tile;
        do {
            tile = findRandomTile();
        } while ((tile.getItem().getType().equals("beach")) || !tile.isOnIsland());
        return tile;
    }

    /**
     *
     * @return  une coordonnée généré aléatoirement a partir de la listes des tuiles composant la carte
     */
    public Coordinate findRandomCoordinate(){

        Tile tile = findRandomVegetationTile();

        HashSet<Coordinate> coordinates = new HashSet<>(tile.getCorner());
        ArrayList<Coordinate> c = new ArrayList<>(coordinates);
        return c.get(random.getRandomInt(c.size()-1));
    }

    /**
     *
     * @param s  La forme de l'ile qu'on veut créer
     * @return   les tuiles qui composent l'ile
     */
    public HashSet<Tile> getTilesInShape(Shape s){

        HashSet<Tile> tileList = new LinkedHashSet<>();
        for (Tile tile : tiles.values() ) {
            if( s.isInShape( tile.getCenter() ) ){
                tileList.add(tile);
            }
        }
        return tileList;
    }

    public Tile getAtile(Coordinate coordinate){
        return tiles.get(coordinate);
    }


    /**
     *
     * @return toutes le coordonnées composant la carte
     */
    public  List<Coordinate> getAllCordinates() {
        return new ArrayList<>(tiles.keySet());
    }

    public void addArchipelago(Isle isle){
        isleList.add(isle);
    }

    public List<Isle> getIsleList(){
        return isleList;
    }


    public void reInitiliseTileColor(){
        for(Tile tile:tiles.values()){
            if( ! tile.getItem().getType().equals("vegetation") ){
                tile.setBackgroundColor(colorDark);
            }
        }
    }

    public int getTilesNb(){
        return tiles.size();
    }


    public ArrayList<ArrayList<Tile>> getWorldinterestPoints() {

        ArrayList<ArrayList<Tile>> interestPointsListGlobal = new ArrayList<>();

        for (Isle isle:isleList ) {

            ArrayList<Tile> interestPointsList = isle.getIsleinterestPoints();

            interestPointsListGlobal.add(interestPointsList);
        }
        return interestPointsListGlobal;
    }


    public ArrayList<Tile> getIslePortInterestPoint(){
        /*

        ArrayList<ArrayList<Tile>> interestPointsListGlobal = getWorldinterestPoints();
        ArrayList<Tile> ports = new ArrayList<>();

        for(ArrayList<Tile> tilesList: interestPointsListGlobal){
            for(Tile tile: tilesList){
                if(tile.getPois() == InterestPointsGenerator.POIS.PORTS){
                    ports.add(tile);
                }
            }
        }
        return ports;

         */
        ArrayList<Tile> ports = new ArrayList<>();

        for(Isle isle:isleList){
            ports.addAll( isle.getIslePortInterestPoints() );
        }
        return ports;
    }

    /**
     * Trouver toutes les tuiles se trouvant à l'interieur des iles.
     * @return liste des tuiles.
     */

    public HashSet<Tile> getOnIslandTiles(){
        HashSet<Tile> inlandTiles = new HashSet<>();
        for(Tile tile:tiles.values()){
            if ( tile.isOnIsland()  ) {
                inlandTiles.add(tile);
            }
        }
        return inlandTiles;

    }

    /**
     * Only ocean tiles belonging to ocean biome
     * @return list of tiles
     */

    public HashSet<Tile> getOceanTiles(){
        HashSet<Tile> oceanTiles = new HashSet<>();
        for(Tile tile:tiles.values()){
            if ( tile.isInOcean()  ) {
                oceanTiles.add(tile);
            }
        }
        return oceanTiles;
    }

    /**
     * Only tiles belonging to lagoon biom
     * @return list of tiles
     */

    public HashSet<Tile> getLagoonTiles(){
        HashSet<Tile> lagoonTiles = new HashSet<>();
        for(Tile tile:tiles.values()){
            if(tile.isInLagoon()){
                lagoonTiles.add(tile);
            }
        }
        return lagoonTiles;

    }



}
