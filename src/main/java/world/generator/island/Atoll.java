package world.generator.island;

import geometry.Circle;
import geometry.Coordinate;
import islandSet.Isle;
import world.Tile;
import world.World;

import java.util.*;

public class Atoll extends Island {


    private final Circle circle;
    private final int maxAltitude;
    private List<Tile> islandTiles;
    //private HashSet<Coordinate> listOfCoordinate = new LinkedHashSet<>();
    //TreeMap<Coordinate, Float> uniqeCoordinates = new TreeMap<>();
    List<Coordinate> coordinateList = new ArrayList<>();

    public Atoll( List<Tile> islandTiles, Circle circle, int maxAltitude){
        this.circle = circle;
        this.maxAltitude = maxAltitude;
        this.islandTiles = islandTiles;
    }

    @Override
    public void apply(World world) {
        System.out.println("island tiles before all = "+islandTiles.size());
        setBorders(world);
        defineAltitude(world, maxAltitude);
        Isle isle = new Isle(islandTiles);
        world.addArchipelago(isle);
        System.out.println("island tiles after all = "+islandTiles.size());
    }

    @Override
    public void defineAltitude(World world, int maxAltitude){

        TreeMap<Double, List<Tile>> sortedListTiles = new TreeMap<>();

        for(Tile tile:islandTiles){
            double distance;

            distance = tile.getCenter().distance(circle.getCenter());
            orderTilesBasedOnDistanceFromCenter( sortedListTiles, distance, tile);
        }

        System.out.println("alt before applying profile");
        for(Tile tile:islandTiles){
            System.out.println( tile.getCorner().toString() );
        }


        applyProfilAltimetrique(sortedListTiles, maxAltitude);


        System.out.println("alt after applying profile");
        for(Tile tile:islandTiles){
            System.out.println( tile.getCorner().toString() );
        }


    }


    private void orderTilesBasedOnDistanceFromCenter( TreeMap<Double, List<Tile>> sortedListTiles, Double distance, Tile tile){

        if (sortedListTiles.containsKey(distance)) {
            sortedListTiles.get(distance).add(tile);
        } else {
            List<Tile> tiles = new ArrayList<>();
            tiles.add(tile);
            sortedListTiles.put(distance, tiles);
        }
    }


    public void applyProfilAltimetrique(TreeMap<Double, List<Tile> > sortedListTiles, int maxAlt){

        int milieu = sortedListTiles.size()/2;
        List<Coordinate> list = new ArrayList<>();

        float diffrenceAltEachtile = (float)maxAlt / islandTiles.size();

        float currentAlt = diffrenceAltEachtile;
        int i = 0;

        for(List<Tile> tileList: sortedListTiles.values()) {
            if(i < milieu) {

                for (Tile tile : tileList){

                    applyAltitudeToTileCorners(tile, currentAlt, circle.getCenter(), diffrenceAltEachtile, coordinateList );
                    coordinateList.addAll(tile.getCorner());

                }
                currentAlt += diffrenceAltEachtile;
                ++i;
            } else if (i >= milieu) {

                for (Tile tile : tileList){

                    applyAltitudeToTileCorners(tile, currentAlt, circle.getCenter(), diffrenceAltEachtile, coordinateList);
                    coordinateList.addAll(tile.getCorner());
                }
                currentAlt -= diffrenceAltEachtile;
            }
        }

    }


    @Override
    public void setBorders(World world){

        for (Tile tile : islandTiles) {

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