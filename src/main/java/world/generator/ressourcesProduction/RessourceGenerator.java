package world.generator.ressourcesProduction;

import islandSet.Isle;
import world.Tile;
import world.TileColor;
import world.World;
import world.generator.Generator;
import world.generator.calculator.TileCaracteristicCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


public class RessourceGenerator  extends TileCaracteristicCalculator implements Generator {

    public static final int MAX = 255;
    private final TileColor color = TileColor.CHESTNUT;

    @Override
    public void generate(World world) {

        for(Isle isle:world.getIsleList() ){
            List<Tile> tileList = isle.getVegetationTiles();
            int nb = tileList.size();

            TreeMap<Double, List<Tile>> tileSurfaceList = orderIslesBasedOnSurface( tileList );
            TreeMap<Double, List<Tile>> steepEachTile = orderIslesBasedOnSteep(tileSurfaceList);
            applyRichiness( steepEachTile, nb);

        }
        world.reInitiliseTileColor();
    }

    public void applyRichiness( TreeMap<Double, List<Tile>> tileSurfaceList, int nbTiles){

        float ecartEachTile =  255f / nbTiles;
        float richiness;

        for(List<Tile> list:tileSurfaceList.values()){
            for(Tile tile:list ){
                richiness = ecartEachTile;
                tile.setRichiness(richiness);

            }
            ++ ecartEachTile;
        }
    }



    public TreeMap<Double, List<Tile>> orderIslesBasedOnSurface( List<Tile> tileList ){


        double surface;


        TreeMap<Double, List<Tile>> surfaceEachTile = new TreeMap<>();
        for(Tile tile:tileList){
            tile.setBackgroundColor(color);
            surface = findTileSurface(tile);

            addTile(surfaceEachTile, surface, tile);
        }
        return surfaceEachTile;
    }

    public TreeMap<Double, List<Tile>> orderIslesBasedOnSteep( TreeMap<Double, List<Tile>> surfaceEachTile ){
        TreeMap<Double, List<Tile>> steepEachTile = new TreeMap<>();
        double tileSteep;
        for(List<Tile> tileList:surfaceEachTile.values()){
            for(Tile tile:tileList){
                tileSteep = findTileSteep(tile);
                addTile(steepEachTile, tileSteep, tile);
            }
        }
        return steepEachTile;
    }

    public void addTile(TreeMap<Double, List<Tile>> surfaceEachTile, double surface, Tile tile){

        if (surfaceEachTile.containsKey(surface)) {
            surfaceEachTile.get(surface).add(tile);

        } else {
            List<Tile> tmp = new ArrayList<>();
            tmp.add(tile);
            surfaceEachTile.put(surface, tmp);
        }
    }
/*
    public double findTileSurface(Tile tile){

        List<Coordinate> coordinateList = new ArrayList<>(tile.getCorner());
        coordinateList.add(coordinateList.get(0));
        double sum1 = multiplyXByYNextCoordinate(coordinateList);
        double sum2 = multiplyYByXNextCoordinate(coordinateList);
        return  substractAndDivide(sum1, sum2);
    }

    public double findTileSteep(Tile tile){

        TreeMap<Float, Coordinate> altitudeTilesListe = new TreeMap<>();
        for(Coordinate coordinate:tile.getCorner()){
            float alt = coordinate.getZ();
            altitudeTilesListe.put(alt, coordinate);
        }
        Coordinate lowestAlt = altitudeTilesListe.get( altitudeTilesListe.firstKey() );
        Coordinate highestAlt = altitudeTilesListe.get( altitudeTilesListe.lastKey() );

        double distance = distanceHeighestToLowest( highestAlt, lowestAlt );
        double denivellation = highestAlt.getZ() - lowestAlt.getZ();
        return denivellation / distance;
    }

    private float distanceHeighestToLowest( Coordinate highestAltCoordinate, Coordinate lowestAltCoordinate ){
        return  highestAltCoordinate.distance( lowestAltCoordinate );
    }



    public double multiplyXByYNextCoordinate( List<Coordinate> coordinateList ){
        double sum1 = 0;
        for(int i=0; i<coordinateList.size(); i++){
            for(int j=i+1; j<coordinateList.size(); j++){
                sum1 += coordinateList.get(i).getX() * coordinateList.get(j).getY();
                break;
            }
        }
        return sum1;
    }

    public double multiplyYByXNextCoordinate( List<Coordinate> coordinateList ){
        double sum2 = 0;

        for(int i=0; i<coordinateList.size(); i++){
            for(int j=i+1; j<coordinateList.size(); j++) {
                sum2 += coordinateList.get(i).getY() * coordinateList.get(j).getX();
                break;
            }
        }
        return sum2;
    }

    public double substractAndDivide(double sum1, double sum2){
        return Math.abs(sum1 - sum2) / 2;
    }

 */






}
