package world;

import geometry.Coordinate;
import geometry.Line;
import geometry.Shape;
import islandSet.Isle;
import randomStrategy.RandomContexte;

import java.util.*;

public class World {

    private final  RandomContexte random;
    private final  HashMap<Coordinate, Tile> tiles;
    private List<Isle> isleList;
    private ArrayList<Line> roads = new ArrayList<>();
    private int width;
    private int height;

    public World(RandomContexte random) {

        this.tiles = new HashMap<>();
        this.random = random;
        this.isleList = new ArrayList<>();
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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

    public List<Line> getRoads(){
        return roads;
    }


    /**
     *
     * @param c une coordonnée
     * @return la liste des tuiles qui ont la meme coordonnée
     */
    public Set<Tile> getNeighbor(Coordinate c) {

        Set<Tile> neighbor = new HashSet<>();
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

    public Set<Tile> getNeighbor(Line l) {
        Set<Tile> neighbor = new HashSet<>();

        neighbor.addAll(getNeighbor(l.getC1()));
        neighbor.addAll(getNeighbor(l.getC2()));

        return neighbor;
    }

    /**
     *
     * @param t une tuile
     * @return la liste des tuiles qui intersectent à une quelconque coordonnée  de  la tuile t
     */

    public Set<Tile> getNeighbor(Tile t) {

        Set<Tile> neighbor = new HashSet<>();

        for(Coordinate c: t.getCorner()) {
            neighbor.addAll( getNeighbor(c) );
        }
        return neighbor;

    }

    /**
     *
     * @param c une coordonnée
     * @return la liste des lignes dont la coordonnée c est une de leurs coordonnées
     */
    public Set<Line> getLine(Coordinate c){

        Set<Line> lines = new HashSet<>();
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
     * @param s  La forme de l'ile qu'on veut créer
     * @return   les tuiles qui composent l'ile
     */
    public Set<Tile> getTilesInShape(Shape s){

        Set<Tile> tileList = new LinkedHashSet<>();
        for (Tile tile : tiles.values() ) {
            if( s.isInShape( tile.getCenter() ) ){
                tileList.add(tile);
            }
        }
        return tileList;
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

    /**
     *
     * @return une liste contenant les points d'interet de chaque ile
     */

    public List<ArrayList<Tile>> getWorldinterestPoints() {

        List<ArrayList<Tile>> interestPointsListGlobal = new ArrayList<>();

        for (Isle isle:isleList ) {

            interestPointsListGlobal.add( new ArrayList<>(isle.getIsleinterestPoints()) );
        }
        return interestPointsListGlobal;
    }

    /**
     *
     * @return list des tuiles contenant des ports
     */
    public List<Tile> getIslePortInterestPoint(){

        List<Tile> ports = new ArrayList<>();

        for(Isle isle:isleList){
            ports.addAll( isle.getIslePortInterestPoints() );
        }
        return ports;
    }

    /**
     * Trouver toutes les tuiles se trouvant à l'interieur des iles.
     * @return liste des tuiles.
     */

    public Set<Tile> getOnIslandTiles(){
        Set<Tile> inlandTiles = new HashSet<>();
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

    public Set<Tile> getOceanTiles(){ //
        Set<Tile> oceanTiles = new HashSet<>();
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

    public Set<Tile> getLagoonTiles(){

        Set<Tile> lagoonTiles = new HashSet<>();
        for(Tile tile:tiles.values()){
            if(tile.isInLagoon()){
                lagoonTiles.add(tile);
            }
        }
        return lagoonTiles;

    }



}
