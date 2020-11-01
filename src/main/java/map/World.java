package map;

import geometrie.Dot;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class World {
    private int width;
    private int height;
    private HashMap<Dot, Tile> tiles;
    private Biome plage;
    private Biome ocean;
    private Biome lagoon;
    private Biome vegetation;



    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new LinkedHashMap<>();
        plage = new Plage();
        ocean = new Ocean();
        vegetation = new Vegetation();
        lagoon = new Lagoon();
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public void addTile(Tile tile) {
        tiles.put(tile.getTileCenter(),tile);
    }

/*
    public void createAquifere(Vegetation vegetation, int nb){
        for(int i=0; i<nb; i++){
            Aquifere aquifere = new Aquifere(vegetation);
            //aquifere.setColor();
            //aquifere.propager();
            //Lake lake = new Lake(aquifere);
            //lake.setColor();
        }
    }

 */
    public void createLake( Vegetation vegetation, int nbWaterSources, String soilTypeChoice){
        for(int i=0; i< nbWaterSources; i++){

            Tile tile = vegetation.findRandomVegtalTile();
            java.util.HashMap<Dot, Tile> aquiferNeighbors =  vegetation.findAquiferNeighbors(tile);

            Aquifer aquifer = new Aquifer(tile, aquiferNeighbors);
            Lake lake = new Lake(aquifer);

            lake.findAdjacentLakeNeighbors(vegetation);
            lake.setColor(TileColor.WATERBLUE);
            //lake.setColorNeighbors(TileColor.DARKGREEN);
            java.util.Map<Double, Tile> tuileAndDistance =  lake.findDistanceFromAquifereCenter(vegetation);

            applyHumidityEffect(soilTypeChoice, tuileAndDistance);

        }
    }

    public void createNape( Vegetation vegetation, int nbWaterSources, String soilTypeChoice){
        for(int i=0; i< nbWaterSources; i++){

            Tile tile = vegetation.findRandomVegtalTile();
            java.util.HashMap<Dot, Tile> aquifereNeighbors =  vegetation.findAquiferNeighbors(tile);

            Aquifer aquifer = new Aquifer(tile, aquifereNeighbors);
            Lake lake = new Lake(aquifer);

            lake.findAdjacentLakeNeighbors(vegetation);
            lake.setColor(TileColor.DARKGREEN);
            //lake.setColorNeighbors(TileColor.DARKGREEN);
            java.util.Map<Double, Tile> tuileAndDistance =  lake.findDistanceFromAquifereCenter(vegetation);

            applyHumidityEffect(soilTypeChoice, tuileAndDistance);

        }
    }

    public void applyHumidityEffect(String soilTypeChoice, java.util.Map<Double, Tile> tuileAndDistance){
        int nbHumideTile;
        int nbLessHumideTile;
        int nbLessLessHumidTile;
        int totalAffectedTiles;
        int index;

        switch (soilTypeChoice){
            case "dry" :
                nbHumideTile = 15;
                nbLessHumideTile = 12;
                nbLessLessHumidTile = 8;
                totalAffectedTiles = nbHumideTile + nbLessHumideTile + nbLessLessHumidTile;
                index =0;
                for(java.util.Map.Entry<Double, Tile> entry:tuileAndDistance.entrySet()) {
                    Tile b = entry.getValue();
                    if(index < nbHumideTile){
                        System.out.println("my index is ="+index);
                        b.setBackgroundColor(TileColor.DARKGREEN);
                        index++;
                    }
                    if(index >= nbHumideTile-1 && index< nbHumideTile+nbLessHumideTile) {
                        System.out.println("my index is ="+index);
                        b.setBackgroundColor(TileColor.GREEN);
                        index++;
                    }
                    if(index >= nbHumideTile + nbLessHumideTile  && index< totalAffectedTiles) {
                        System.out.println("my index is ="+index);
                        b.setBackgroundColor(TileColor.LIGHTGREEN2);
                        index++;
                    }

                }
                break;
            case "regular":

                nbHumideTile = 20;
                nbLessHumideTile = 15;
                nbLessLessHumidTile = 12;
                totalAffectedTiles = nbHumideTile + nbLessHumideTile + nbLessLessHumidTile;
                index =0;
                for(java.util.Map.Entry<Double, Tile> entry:tuileAndDistance.entrySet()) {
                    Tile b = entry.getValue();
                    if(index < nbHumideTile){
                        System.out.println("my index is ="+index);
                        b.setBackgroundColor(TileColor.DARKGREEN);
                        index++;
                    }
                    if(index >= nbHumideTile-1 && index< nbHumideTile+nbLessHumideTile) {
                        System.out.println("my index is ="+index);
                        b.setBackgroundColor(TileColor.GREEN);
                        index++;
                    }
                    if(index >= nbHumideTile + nbLessHumideTile  && index< totalAffectedTiles) {
                        System.out.println("my index is ="+index);
                        b.setBackgroundColor(TileColor.LIGHTGREEN2);
                        index++;
                    }

                }
                break;
            case "wet":
                nbHumideTile = 35;
                nbLessHumideTile = 25;
                nbLessLessHumidTile = 20;
                totalAffectedTiles = nbHumideTile + nbLessHumideTile + nbLessLessHumidTile;
                index =0;
                for(java.util.Map.Entry<Double, Tile> entry:tuileAndDistance.entrySet()) {
                    Tile b = entry.getValue();
                    if(index < nbHumideTile){
                        System.out.println("my index is ="+index);
                        b.setBackgroundColor(TileColor.DARKGREEN);
                        index++;
                    }
                    if(index >= nbHumideTile-1 && index< nbHumideTile+nbLessHumideTile) {
                        System.out.println("my index is ="+index);
                        b.setBackgroundColor(TileColor.GREEN);
                        index++;
                    }
                    if(index >= nbHumideTile + nbLessHumideTile  && index< totalAffectedTiles) {
                        System.out.println("my index is ="+index);
                        b.setBackgroundColor(TileColor.LIGHTGREEN2);
                        index++;
                    }

                }
                break;
            default:
                System.out.println("soil type are three, either dry, or regular or wet ");
                System.exit(1);
        }
    }

    /**
     *
     * @return le centre de chaque tuile et la tuile elle meme
     */

    public HashMap<Dot, Tile> getTiles() {
        return tiles;
    }

    private boolean isTileInBiomes(Tile tile){
        Dot tileCenter = tile.getTileCenter();
        if (ocean.getTiles().get(tileCenter) != null) return true;
        if (plage.getTiles().get(tileCenter) != null) return true;
        if (lagoon.getTiles().get(tileCenter) != null) return true;
        if (vegetation.getTiles().get(tileCenter) != null) return true;
        return false;
    }

    public void createBiome(Island island) {
        //Ocean
        for (java.util.Map.Entry<Dot, Tile> entry : tiles.entrySet()) {
            Tile tile = entry.getValue();
            if(!island.isOnIsland(tile)){
                ocean.addToBiome(tile);
            }
        }

        //lagon
        if(island instanceof Atoll) {
            System.out.println("weird");
            for (java.util.Map.Entry<Dot, Tile> entry : island.getTiles().entrySet()) {
                Tile tile = entry.getValue();
                if (((Atoll)island).isInLagon(tile)) {
                    lagoon.addToBiome(tile);
                }
            }
        }

        //plage
        for (java.util.Map.Entry<Dot, Tile> entry : island.getTiles().entrySet()) {
            Tile tile = entry.getValue();
            if(!isTileInBiomes(tile)) {
                for (Dot neighbors : tile.neighbors) {
                    System.out.println("plage");
                    if (ocean.getTiles().get(neighbors) != null || lagoon.getTiles().get(neighbors) != null) {
                        plage.addToBiome(tile);
                        break;
                    }
                }
            }
        }

        //vegetation
        for (java.util.Map.Entry<Dot, Tile> entry : island.getTiles().entrySet()) {
            Tile tile = entry.getValue();
            System.out.println("vegetation");
            if(!isTileInBiomes(tile)){
                vegetation.addToBiome(tile);
            }
        }

    }
}
