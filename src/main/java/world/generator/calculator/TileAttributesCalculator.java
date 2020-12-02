package world.generator.calculator;

import geometry.Coordinate;
import world.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public  class TileAttributesCalculator {

    public TileAttributesCalculator() {
    }

    public double findTileSurface(Tile tile){

        List<Coordinate> coordinateList = new ArrayList<>(tile.getCorner());
        coordinateList.add(coordinateList.get(0));
        double sum1 = multiplyXByYNextCoordinate(coordinateList);
        double sum2 = multiplyYByXNextCoordinate(coordinateList);
        return  Math.abs(sum1 - sum2) / 2;
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
        int j = 0;
        for(int i=0; i<coordinateList.size() && j< coordinateList.size()-1; i++){
            j = i+1;
            sum1 += coordinateList.get(i).getX() * coordinateList.get(j).getY();

        }
        return sum1;
    }

    public double multiplyYByXNextCoordinate( List<Coordinate> coordinateList ){
        double sum2 = 0;
        int j = 0;
        for(int i=0; i<coordinateList.size() && j< coordinateList.size()-1; i++){
            j= i+1;
            sum2 += coordinateList.get(i).getY() * coordinateList.get(j).getX();

        }
        return sum2;
    }

}
