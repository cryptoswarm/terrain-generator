package World;

import Geometry.Circle;
import Geometry.Coordinate;
import Geometry.Line;
import Geometry.Shape;
import RandomStrategy.RandomContexte;

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

    public HashMap<Coordinate, Tile> getTiles() {
        return tiles;
    }


    public Tile getTile(float x, float y){
        return tiles.get(new Coordinate(x,y,0));
    }
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
    public HashSet<Tile> getNeighbor(Line l) {
        HashSet<Tile> neighbor = new HashSet<>();

        neighbor.addAll(getNeighbor(l.getC1()));
        neighbor.addAll(getNeighbor(l.getC2()));

        return neighbor;
    }
    public HashSet<Tile> getNeighbor(Tile t) {

        HashSet<Coordinate> coordinate = new HashSet<>();
        HashSet<Tile> neighbor = new HashSet<>();

        for(Line line: t.getBorder()){
            coordinate.add(line.getC1());
            coordinate.add(line.getC2());
        }
        for(Coordinate c: coordinate) {
            neighbor.addAll(getNeighbor(c));
        }
        return neighbor;

    }
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

    public void addTile(float x, float y) {
        Coordinate c = new Coordinate(x,y,0);
        Tile t = new Tile(c);
        tiles.put(c,t);
    }
    public void addLine(float x, float y, float x1, float y1, float x2, float y2){

        Coordinate c1 = new Coordinate(x1,y1,0);
        Coordinate c2 = new Coordinate(x2,y2,0);
        Tile t = tiles.get( new Coordinate(x,y,0) );
        t.addBorder(new Line(c1,c2));
    }

    public Tile findRandomTile(){
        ArrayList<Tile> tiles = new ArrayList<>(this.tiles.values());
        return tiles.get(random.getRandomInt(tiles.size()-1));

    }
    public Tile findRandomVegetationTile(){
        Tile tile;
        do {
            tile = findRandomTile();
        } while (!(tile.getBiome().getType().equals("vegetation")));
        return tile;
    }


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
     * @return   les tuiles qui composaent l'ile
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
                Coordinate c = tile.getCenter();

                if ( c.distance(circle.getCenter()) > circle.getSmallRadius() &&
                        c.distance(circle.getCenter()) <= circle.getBigRadius()) {
                    tile.setOnIsland(true);
                    tile.setInOcean(false);
                }
                if (c.distance(circle.getCenter()) <= circle.getSmallRadius()){
                    tile.setInLagoon(true);
                    tile.setInOcean(false);
                }
            }
        }
    }


    public  List<Coordinate> getAllCordinates() {
        return new ArrayList<>(tiles.keySet());
    }

}
