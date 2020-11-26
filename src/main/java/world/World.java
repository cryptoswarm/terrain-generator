package world;

import geometry.Circle;
import geometry.Coordinate;
import geometry.Line;
import geometry.Shape;
import randomStrategy.RandomContexte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class World {
    final private RandomContexte random;
    final private HashMap<Coordinate, Tile> tiles;

    public World(RandomContexte random) {
        this.tiles = new HashMap<>();
        this.random = random;
    }

    /**
     *
     * @return  la listes des tuiles (Should be private but we have no other choice other than use it)
     */
    public HashMap<Coordinate, Tile> getTiles() {
        return tiles;
    }

    /**
     *
     * @param x la coordonnée sur l'axe des abscice
     * @param y la coordonnée sur l'axe des ordonnées
     * @return  la tuile equivalente
     */

    public Tile getTile(float x, float y){
        return tiles.get(new Coordinate(x,y,0));
    }

    /**
     * Une ligne possede deux coordonnées
     * @param x1  est la coordonnée sur l'axe des abscice du premier point
     * @param y1  est la coordonnée sur l'axe des ordonnées du premier point
     * @param x2  est la coordonnée sur l'axe des abscice du deuxiem point
     * @param y2  est la coordonnée sur l'axe des ordonnées du deuxiem point
     * @return   la couleur de la ligne
     */
    public String getLineColor(float x1, float y1, float x2, float y2){
        Line line = new Line(new Coordinate(x1,y1,0), new Coordinate(x2,y2,0));

        for(Tile tile: tiles.values()){
            for(Line l: tile.getBorder()){
                if(l.equals(line) && l.getColor() != null) {
                    line = l;
                    break;
                }
            }
        }
        TileColor color = line.getColor();
        if(color != null) {
            return color.toString();
        }

        return "0:0:0:0";
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
            for(Line line: tile.getBorder()){
                if(line.getC1().equals(c) || line.getC2().equals(c)){
                    lines.add(line);
                }
            }
        }
        return lines;
    }

    /**
     * Ajouter une tuile à la fois
     * A partir des coordonnées de la tuile on l'ajoute à la liste des tuiles composant la carte ( AKA WORLD )
     * @param x la coordonnée sur l'axe des abscice
     * @param y la coordonnée sur l'axe des ordonnées
     */
    public void addTile(float x, float y) {
        Coordinate c = new Coordinate(x,y,0);
        Tile t = new Tile(c);
        tiles.put(c,t);
    }

    /**
     * Pour pouvoir associer une ligne aux bordures d'une tuile
     * On doit trouver la tuile en question, pour cela on a besoin des coordonnées de la tuile
     * Aussi, on a besoin des coordonnees de la ligne
     *
     *
     * @param x est la coordonnée sur l'axe des abscice  d'une quelconque tuile
     * @param y est la coordonnée sur l'axe des ordonnées  d'une quelconque tuile
     *          Permetant de trouver la tuile équivalente
     *
     * @param x1  est la coordonnée sur l'axe des abscice du premier point
     * @param y1  est la coordonnée sur l'axe des ordonnées du premier point
     * @param x2  est la coordonnée sur l'axe des abscice du deuxiem point
     * @param y2  est la coordonnée sur l'axe des ordonnées du deuxiem point
     */
    public void addLine(float x, float y, float x1, float y1, float x2, float y2){

        Coordinate c1 = new Coordinate(x1,y1,-1);
        Coordinate c2 = new Coordinate(x2,y2,-1);
        Tile t = tiles.get( new Coordinate(x,y,0) );
        t.addBorder(new Line(c1,c2));
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
     * @return  une tuile généré aleatoirement appartenant au biome vegetation
     */
    public Tile findRandomVegetationTile(){
        Tile tile;
        do {
            tile = findRandomTile();
        } while (!(tile.getBiome().getType().equals("vegetation")));
        return tile;
    }

    /**
     *
     * @return  une coordonnée généré aleatoirement a partir de la listes des tuiles composant la carte
     */
    public Coordinate findRandomCoordinate(){

        Tile tile = findRandomVegetationTile();
        HashSet<Coordinate> coordinates = new HashSet<>();
        for(Line line: tile.getBorder()) {
            coordinates.add(line.getC1());
            coordinates.add(line.getC2());
        }
        ArrayList<Coordinate> c = new ArrayList<>(coordinates);
        return c.get(random.getRandomInt(c.size()-1));
    }

    /**
     *
     * @param s  La forme de l'ile qu'on veut créer
     * @return   les tuiles qui composent l'ile
     */
    public List<Tile> getIslandTiles( Shape s){

        List<Tile> tileList = new ArrayList<Tile>();
        for (Tile tile : tiles.values() ) {
            if( s.isInShape(tile.getCenter()) ){
                tileList.add(tile);
            }
        }
        return tileList;
    }

    public Tile getAtile(Coordinate coordinate){
        return tiles.get(coordinate);
    }


    public void setEllipticIslandBorders(Shape shape){

        for (Tile tile : tiles.values()) {

            if(tile.isInOcean()) {
                if (shape.isInShape(tile.getCenter())) {
                    tile.setOnIsland(true);
                    tile.setInOcean(false);
                }
            }
        }
    }

    public void setCircularIslandBorders(Circle circle){

        for (Tile tile : tiles.values()) {
            if(  tile.isInOcean() ) {

                if ( tile.getCenter().distance(circle.getCenter()) > circle.getSmallRadius() &&
                        tile.getCenter().distance(circle.getCenter()) <= circle.getBigRadius()) {
                    tile.setOnIsland(true);
                    tile.setInOcean(false);
                }
                if (tile.getCenter().distance(circle.getCenter()) <= circle.getSmallRadius()){
                    tile.setInLagoon(true);
                    tile.setInOcean(false);
                }
            }
        }
    }

    /**
     *
     * @return toutes le coordonnées composant la carte
     */
    public  List<Coordinate> getAllCordinates() {
        return new ArrayList<>(tiles.keySet());
    }

}
