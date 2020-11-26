package world.generator.island;

import geometry.Circle;
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

        TreeMap<Double, List<Tile>> treeMap = new TreeMap<>();

        for(Tile tile:islandTiles){
            double distance;

            distance = tile.getCenter().distance(circle.getCenter());
            if (treeMap.containsKey(distance)) {
                treeMap.get(distance).add(tile);
            } else {
                List<Tile> tiles = new ArrayList<>();
                tiles.add(tile);
                treeMap.put(distance, tiles);
            }
        }
        applyProfilAltimetrique(treeMap, maxAltitude);
    }

    public void applyProfilAltimetrique(TreeMap<Double, List<Tile>> temp, int maxAlt){
        int milieu = temp.size()/2;
        float tileAlt = (float)maxAlt/milieu;
        float alt = tileAlt;
        int i = 0;

        for(List<Tile> tileList: temp.values()) {
            if(i < milieu) {

                applyAltToEachListOfTiles(tileList, alt);
                alt = alt + tileAlt;
                ++i;
            } else if (i >= milieu) {

                applyAltToEachListOfTiles(tileList, alt);
                alt = alt - tileAlt;
            }
        }
    }

    private void applyAltToEachListOfTiles(List<Tile> tileList, double alt ){
        for (Tile tile : tileList){
            applyAltitudeToTileCorners(tile, alt, circle.getCenter() );
        }
    }

    @Override
    public void setBorders(World world){
        //world.setCircularIslandBorders(circle);

        for (Tile tile : islandTiles) {

            if ( tile.getCenter().distance(circle.getCenter()) > circle.getSmallRadius() &&
                    tile.getCenter().distance(circle.getCenter()) < circle.getBigRadius()) {
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