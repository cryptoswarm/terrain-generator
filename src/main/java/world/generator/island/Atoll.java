package world.generator.island;

import geometry.Circle;
import geometry.Coordinate;
import islandSet.Isle;
import world.Tile;
import world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Atoll extends Island {


    private final Circle circle;
    private final int maxAltitude;

    private Isle isle;


    List<Coordinate> coordinateList = new ArrayList<>();

    public Atoll( Isle isle, Circle circle, int maxAltitude){
        this.circle = circle;
        this.maxAltitude = maxAltitude;
        this.isle = isle;
    }

    @Override
    public void apply(World world) {

        setBorders(world);
        defineAltitude(world, maxAltitude);
        world.addArchipelago(isle);

    }


    @Override
    public void defineAltitude(World world, int maxAltitude){

        TreeMap<Double, List<Tile>> sortedListTiles = new TreeMap<>();

        for( Tile tile:isle.getIslandTiles() ){
            double distance;

            distance = tile.getCenter().distance(circle.getCenter());
            orderTilesBasedOnDistanceFromCenter( sortedListTiles, distance, tile);
        }

        applyProfilAltimetrique(sortedListTiles, maxAltitude);

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

        float diffrenceAltEachtile = (float)maxAlt / isle.getIslandTiles().size();


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

        for (Tile tile : isle.getIslandTiles() ) {

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