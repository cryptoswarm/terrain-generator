package world.generator.calculator;

import geometry.Coordinate;
import world.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public abstract  class TileCaracteristicCalculator {


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
}
