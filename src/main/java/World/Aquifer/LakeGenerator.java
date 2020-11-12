package World.Aquifer;

import RandomStrategy.RandomContexte;
import World.Biome.Vegetation;
import World.*;

import java.util.ArrayList;
import java.util.HashSet;

public class LakeGenerator implements Generator {
    private HashSet<Tile> tiles;
    private int nbsWaterSrc;
    private soilType soil;
    private RandomContexte r;

    public LakeGenerator(int nbsWaterSrc, soilType soil) {
        this.nbsWaterSrc = nbsWaterSrc;
        this.soil = soil;
    }

    @Override
    public void generate(World w) {
        this.r = w.getRandom();
        tiles = w.getTiles();
        createWaterSource();
    }
    private Tile findRandomTile(HashSet<Tile> Htiles){
        ArrayList<Tile> tiles = new ArrayList<>(Htiles);
        Tile randomTile = tiles.get(r.getRandomInt(tiles.size()-1));
        return randomTile;
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
        int i  = r.getRandomInt(nbsWaterSrc+1);
        createLake(nbsWaterSrc - i);
        createNape(i);
    }
    private void applyHumidityEffect(HashSet<Tile> waterSource){
        for(Tile i: tiles) {
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
    private Float getDistanceFromWaterSource(Tile tile, HashSet<Tile> waterSource) {
        Float distance = (float)10000;
        for(Tile i: waterSource){
            Float tmp = tile.getCenter().distance(i.getCenter());
            if( tmp < distance) distance = tmp;
        }
        return distance;
    }

}
