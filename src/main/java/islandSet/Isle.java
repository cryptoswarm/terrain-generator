package islandSet;

import geometry.Coordinate;
import geometry.Line;
import randomStrategy.RandomContexte;
import world.Tile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Isle {



    private HashSet<Tile> islandTiles;
    public static final String VEGETATION_BIOM = "vegetation";
    public static final String BEACH_BIOM = "beach";

    public Isle(HashSet<Tile> islandTiles) {
        this.islandTiles = islandTiles;
    }

    public boolean isValide(){

        return !islandTiles.isEmpty();
    }


    public HashSet<Tile> getIslandTiles() {
        return islandTiles;
    }

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

        /*
        List<Coordinate> coordinates = world.getAllCordinates();
        boolean isValide = false;

        while (!coordinates.isEmpty()  && !isValide ) {

            Coordinate coordinate = coordinates.get(random.getRandomInt(coordinates.size()-1));
            Circle cir = new Circle(diameter, random, coordinate);
            HashSet<Tile> islandTiles = world.getIslandTiles( cir);

            if (validIsland( islandTiles,height,width, world)){
                isValide = true;

                this.isle = new Isle(islandTiles);
                this.circle = cir;
            }else{
                coordinates.remove(coordinate);
            }
        }
         */
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
        /*
        do {
            tile = findRandomTile(random);
        } while (!tile.getItem().getType().equals(VEGETATION_BIOM) );
        return tile;

         */
        return tile;
    }

    /**
     *
     * @param random controlled random seed
     * @return  On retourne une tuile aleatoire appartenant à l'ile
     */

    public Tile findRandomTile(RandomContexte random){
        ArrayList<Tile> tiles = new ArrayList<>(islandTiles);
        return tiles.get(random.getRandomInt(tiles.size()-1));

    }

    public Coordinate findRandomCoordinate(RandomContexte random){

        Tile tile = findRandomVegetationTile(random);

        HashSet<Coordinate> coordinates = new HashSet<>(tile.getCorner());
        ArrayList<Coordinate> c = new ArrayList<>(coordinates);
        return c.get(random.getRandomInt(c.size()-1));
    }


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

    public HashSet<Tile> getNeighbor(Coordinate c) {

        HashSet<Tile> neighbor = new HashSet<>();
        for(Tile tile: islandTiles){
            for(Line line: tile.getBorder()){
                if(c.equals(line.getC1()) || c.equals(line.getC2())){
                    neighbor.add(tile);
                }
            }
        }
        return neighbor;
    }


    public HashSet<Line> getLine(Coordinate c){

        HashSet<Line> lines = new HashSet<>();
        for(Tile tile: islandTiles){

            for (Line line : tile.getBorder()) {
                 if(line.isCoordinateValid(c)){
                     lines.add(line);
                 }
            }
        }
        return lines;
    }

    public HashSet<Tile> getNeighbor(Line l) {
        HashSet<Tile> neighbor = new HashSet<>();

        neighbor.addAll(getNeighbor(l.getC1()));
        neighbor.addAll(getNeighbor(l.getC2()));

        return neighbor;
    }

}
