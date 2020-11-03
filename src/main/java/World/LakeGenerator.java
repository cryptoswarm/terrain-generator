package World;

import Geometry.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;

import static World.WorldGenerator.getRandom;

public class LakeGenerator implements Generator{
    private HashMap<Coordinate, Tile> tiles;
    private int nbsWaterSrc;
    private soilType soil;

    public LakeGenerator(HashMap<Coordinate, Tile> tiles, int nbsWaterSrc, soilType soil) {
        this.tiles = tiles;
        this.nbsWaterSrc = nbsWaterSrc;
        this.soil = soil;
    }

    @Override
    public void generate() {
        createWaterSource();
    }
    private Tile findRandomTile(HashMap<Coordinate, Tile> tiles){
        ArrayList<Coordinate> coordinates = new ArrayList<>(tiles.keySet());
        Coordinate randomCoordinate = coordinates.get( getRandom().nextInt( coordinates.size()) );
        return tiles.get(randomCoordinate);
    }
    private void createLake(int nbsLake){
        for(int i = 0; i < nbsLake; i++) {
            Tile tile;
            do {
                tile = findRandomTile(tiles);
            } while (!(tile.getBiome() instanceof Vegetation));

            Aquifer lake = new Lake(tile);
            applyHumidityEffect(lake.getTiles());
        }
    }
    private void createNape(int nbsNapes){
        for(int i = 0; i < nbsNapes; i++) {
            Tile tile;
            do {
                tile = findRandomTile(tiles);
            } while (!(tile.getBiome() instanceof Vegetation));
            Aquifer nape = new Nape(tile);
            applyHumidityEffect(nape.getTiles());
        }
    }
    public void createWaterSource() {
        int i  = getRandom().nextInt(nbsWaterSrc+1);
        createLake(nbsWaterSrc - i);
        createNape(i);
    }
    private void applyHumidityEffect(HashMap<Coordinate, Tile> waterSource){
        for(Tile i: tiles.values()) {
            if(i.getBiome() instanceof Vegetation){
                Float distance = getDistanceFromWaterSource(i, waterSource);
                if( distance < soil.getAffectedDistance()) {
                    if (i.getHumidityLevel() == 0 || i.getHumidityLevel() > Math.round(distance)) {
                        i.setHumidityLevel(Math.round(distance));
                    }
                }
            }

        }
    }
    private Float getDistanceFromWaterSource(Tile tile, HashMap<Coordinate, Tile> waterSource) {
        Float distance = (float)10000;
        for(Tile i: waterSource.values()){
            Float tmp = tile.getCenter().distance(i.getCenter());
            if( tmp < distance) distance = tmp;
        }
        return distance;
    }

}
