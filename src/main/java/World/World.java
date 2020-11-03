package World;

import Geometry.Coordinate;

import java.util.*;

import static World.WorldGenerator.getRandom;

public class World {
    private int width;
    private int height;
    private soilType soil;
    private int nbsWaterSrc;
    private String shape;
    private HashMap<Coordinate, Tile> tiles;
    private Biome plage;
    private Biome ocean;
    private Biome lagoon;
    private Biome vegetation;



    public World() {
        this.tiles = new LinkedHashMap<>();
        plage = new Plage();
        ocean = new Ocean();
        vegetation = new Vegetation();
        lagoon = new Lagoon();
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setSoil(String soil) {
        this.soil = soilType.getSoilType(soil);
    }
    public void setNbsWaterSrc(int nbsWaterSrc) {
        this.nbsWaterSrc = nbsWaterSrc;
    }
    public void setShape(String shape) {
        this.shape = shape;
    }
    public String getShape() {
        return shape;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }


    public void addTile(Tile tile) {
        tiles.put(tile.getCenter(),tile);
    }

    private Tile findRandomTile(HashMap<Coordinate, Tile> tiles){
        ArrayList<Coordinate> coordinates = new ArrayList<>(tiles.keySet());
        Coordinate randomCoordinate = coordinates.get( getRandom().nextInt( coordinates.size()) );
        return tiles.get(randomCoordinate);
    }

    private void createLake(int nbsLake){
        for(int i = 0; i < nbsLake; i++) {
            Tile tile = findRandomTile(vegetation.getTiles());
            Aquifer lake = new Lake(tile, vegetation.getTiles());
            applyHumidityEffect(lake.getTiles());
        }
    }
    private void createNape(int nbsNapes){
        for(int i = 0; i < nbsNapes; i++) {
            Tile tile = findRandomTile(vegetation.getTiles());
            Aquifer nape = new Nape(tile, vegetation.getTiles());
            applyHumidityEffect(nape.getTiles());
        }
    }
    public void createWaterSource() {
        int i  = getRandom().nextInt(nbsWaterSrc+1);
        createLake(nbsWaterSrc - i);
        createNape(i);
    }

    private void applyHumidityEffect(HashMap<Coordinate, Tile> waterSource){
        for(Tile i: vegetation.getTiles().values()) {
            Float distance = getDistanceFromWaterSource(i, waterSource);
            if( distance < soil.getAffectedDistance()) {
                if (i.getHumidityLevel() == 0 || i.getHumidityLevel() > Math.round(distance)) {
                    i.setHumidityLevel(Math.round(distance));
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

    public HashMap<Coordinate, Tile> getTiles() {
        return tiles;
    }

    private boolean isTileInBiomes(Tile tile){
        Coordinate tileCenter = tile.getCenter();
        if (ocean.getTiles().get(tileCenter) != null) return true;
        if (plage.getTiles().get(tileCenter) != null) return true;
        if (lagoon.getTiles().get(tileCenter) != null) return true;
        if (vegetation.getTiles().get(tileCenter) != null) return true;
        return false;
    }

    public void createBiome(Island island) {
        //Ocean
        for (Tile tile : tiles.values()) {
            if(!island.isOnIsland(tile)) ocean.addToBiome(tile);
        }

        //lagon
        if(island instanceof Atoll) {
            for (Tile tile : island.getTiles().values()) {
                if (((Atoll)island).isInLagon(tile)) lagoon.addToBiome(tile);
            }
        }

        //plage
        for (Tile tile : island.getTiles().values()) {
            if(!isTileInBiomes(tile)) {
                for (Tile neighbors : tile.getNeighbors().values()) {
                    if (ocean.getTiles().get(neighbors.getCenter()) != null || lagoon.getTiles().get(neighbors.getCenter()) != null) {
                        plage.addToBiome(tile);
                        break;
                    }
                }
            }
        }

        //vegetation
        for (Tile tile : island.getTiles().values()) {
            if(!isTileInBiomes(tile)) vegetation.addToBiome(tile);
        }
    }



}
