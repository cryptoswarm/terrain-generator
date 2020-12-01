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

    public Atoll( List<Tile> islandTiles, Circle circle, int maxAltitude){
        this.circle = circle;
        this.maxAltitude = maxAltitude;
        this.islandTiles = islandTiles;
    }

    @Override
    public void apply(World world) {
        setBorders(world);
        defineAltitude(world, maxAltitude);
        Isle isle = new Isle(islandTiles);
        world.addArchipelago(isle);

    }

    @Override
    public void defineAltitude(World world, int maxAltitude){

        TreeMap<Double, List<Tile>> sortedListTiles = new TreeMap<>();

        for(Tile tile:islandTiles){
            double distance;

            distance = tile.getCenter().distance(circle.getCenter());
            orderTilesBasedOnDistanceFromCenter( sortedListTiles, distance, tile);
        }


        applyProfilAltimetrique(sortedListTiles, maxAltitude);
        adjustProfile(sortedListTiles);
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



    /**
     *
     * @param temp pour la meme coordonnée, il se peut qu'elle a différentes altitudes
     */
    public void adjustProfile(TreeMap<Double, List<Tile> > temp ){

        TreeMap<Coordinate, Float> uniqeCoordinates = new TreeMap<>();

        for(List<Tile> tileList:temp.values()){
            for(Tile tile:tileList){
                for(Coordinate coordinate:tile.getCorner()){

                    if(!uniqeCoordinates.containsKey(coordinate)){
                        uniqeCoordinates.put(coordinate, coordinate.getZ());
                    }else{
                        coordinate.setZ( uniqeCoordinates.get(coordinate) );
                    }
                }
            }
        }
    }

    public void applyProfilAltimetrique(TreeMap<Double, List<Tile> > sortedListTiles, int maxAlt){

        int milieu = sortedListTiles.size()/2;

        float diffrenceAltEachtile = (float)maxAlt / islandTiles.size();

        float currentAlt = diffrenceAltEachtile;
        int i = 0;

        for(List<Tile> tileList: sortedListTiles.values()) {
            if(i < milieu) {

                applyAltToEachListOfTiles(tileList, currentAlt, diffrenceAltEachtile);
                currentAlt += diffrenceAltEachtile;
                ++i;
            } else if (i >= milieu) {

                applyAltToEachListOfTiles(tileList, currentAlt, diffrenceAltEachtile);
                currentAlt -= diffrenceAltEachtile;
            }
        }

    }

    private void applyAltToEachListOfTiles(List<Tile> tileList, double currentAlt, float diffrenceAltEachtile ){
        for (Tile tile : tileList){
            applyAltitudeToTileCorners(tile, currentAlt, circle.getCenter(), diffrenceAltEachtile);
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