package map;

import geometrie.Coordinate;

import java.util.*;

public class World {
    private int width;
    private int height;
    private soilType soil;
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
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public Biome getVegetation() {
        return vegetation;
    }
    public Biome getPlage() { return plage; }

    public void addTile(Tile tile) {
        tiles.put(tile.getCenter(),tile);
    }

    private Tile findRandomTile(HashMap<Coordinate, Tile> tiles){
        Random random = new Random();
        ArrayList<Coordinate> coordinates = new ArrayList<>(tiles.keySet());
        Coordinate randomCoordinate = coordinates.get( random.nextInt( coordinates.size()) );
        return tiles.get(randomCoordinate);
    }

    public void createLake(int lakesNbs){
        for(int i = 0; i <= lakesNbs; i++) {
            Tile tile = findRandomTile(vegetation.getTiles());
            Aquifer lake = new Lake(tile, vegetation.getTiles());
            applyHumidityEffect(lake.getTiles());
        }
    }

    public void createNape(int napeNbs){
        for(int i = 0; i <= napeNbs; i++) {
            Tile tile = findRandomTile(vegetation.getTiles());
            Aquifer nape = new Nape(tile, vegetation.getTiles());
            applyHumidityEffect(nape.getTiles());
        }
    }

    private void applyHumidityEffect(HashMap<Coordinate, Tile> waterSource){
        for(Tile i: tiles.values()){
            Float distance = getDistanceFromWaterSource(i, waterSource);
            if( distance < soil.getAffectedDistance()) {
                if (i.getHumidityLevel() > Math.round(distance)) {
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
