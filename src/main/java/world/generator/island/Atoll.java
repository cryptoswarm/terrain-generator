package world.generator.island;

import geometry.Circle;
import geometry.Coordinate;
import world.Tile;
import world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

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
    }

    @Override
    public void defineAltitude(World world, int maxAltitude){

        TreeMap<Double, List<Tile>> tileListDistance = new TreeMap<>();

        for(Tile tile:islandTiles){
            double distance;

            distance = tile.getCenter().distance(circle.getCenter());
            orderTilesBasedOnDistanceFromCenter( tileListDistance, distance, tile);

        }

        applyProfilAltimetrique(tileListDistance, maxAltitude);
        adjustProfile(tileListDistance);

    }

    private void orderTilesBasedOnDistanceFromCenter( TreeMap<Double, List<Tile>> tileListDistance, Double distance, Tile tile){

        if (tileListDistance.containsKey(distance)) {
            tileListDistance.get(distance).add(tile);
        } else {
            List<Tile> tiles = new ArrayList<>();
            tiles.add(tile);
            tileListDistance.put(distance, tiles);
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

    public void applyProfilAltimetrique(TreeMap<Double, List<Tile> > temp, int maxAlt){
        int milieu = temp.size()/2;
        float diffrenceAltEachtile = (float)maxAlt/milieu;
        float currentAlt = diffrenceAltEachtile;
        int i = 0;

        for(List<Tile> tileList: temp.values()) {
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
        //world.setCircularIslandBorders(circle);

        for (Tile tile : islandTiles) {

            if (tile.getCenter().distance(circle.getCenter()) > circle.getSmallRadius() &&
                    tile.getCenter().distance(circle.getCenter()) < circle.getBigRadius()) {
                tile.setOnIsland(true);
            }
            if (tile.getCenter().distance(circle.getCenter()) <= circle.getSmallRadius()) {
                tile.setInLagoon(true);
            }
            tile.setInOcean(false);
        }
    }

}