package map;

import geometrie.Coordonnee;
import geometrie.Dot;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class Carte {
    int width;
    int height;
    Dot perfectCenter;
    HashMap<Dot, Tile> tiles;

    public Carte(int width, int height) {
        this.width = width;
        this.height = height;
        this.perfectCenter = new Dot(new Coordonnee((float) (width / 2.0), (float) (height / 2.0), 0 ));
        this.tiles = new LinkedHashMap<>();
    }
    
    public void addTile(Tile tile) {
        tiles.put(tile.center,tile);
    }
    
    public void createAtoll() {

        //int shortestSide = Math.min(width, height);

        //Atoll atoll = new Atoll((double)(shortestSide/2)*.7, (double)(shortestSide/2)*.4, perfectCenter);
        Atoll atoll = new Atoll( width, height);

        for(Map.Entry<Dot, Tile> entry:tiles.entrySet() ) {
            Dot center = entry.getKey();
            Tile b = entry.getValue();

            if(atoll.isInOcean(center)){
                atoll.defineOcean(center, b);
            }
            if(atoll.isInLagon(center)){
                b.setBackgroundColor(TileColor.WATERBLUE);
                atoll.defineLagon(center, b);
            }
        }

        for(Map.Entry<Dot, Tile> entry:tiles.entrySet() ) {
            Dot center = entry.getKey();
            Tile b = entry.getValue();

            if(atoll.isBetweenLagonAndOcean(center)) {

                if(atoll.isNeighborLagonOrOcean(b)){
                    b.setBackgroundColor(TileColor.SAND);
                    atoll.definePLage(b);
                }else{
                    b.setBackgroundColor(TileColor.MIDGREEN);
                    atoll.defineVegetation(b);
                }
            }
        }
    }

    public void createATortuga() {

        Tortuga tortuga = new Tortuga(width, height);//perfectCenter);

        Ocean ocean = new Ocean();
        //Vegetation vegetation = new Vegetation();
        //Plage plage = new Plage();

        for(Map.Entry<Dot, Tile> entry:tiles.entrySet() ) {
            Dot center = entry.getKey();
            Tile b = entry.getValue();

            if(tortuga.isInsideIslande(b)){
                b.setBackgroundColor(TileColor.MIDGREEN);
                tortuga.getVegetation().constructVegetation(b);
            }else{
                b.setBackgroundColor(TileColor.OCEANBLUE);
                ocean.constructOcean(center, b);
            }

        }
        for(Map.Entry<Dot, Tile> entry:tortuga.getVegetation().getTuileVege().entrySet()) {
            Dot center = entry.getKey();
            Tile b = entry.getValue();

            for (Dot val : b.getNeighborPseudoCenters()) {
                Tile temp = tiles.get(val);
                if(! tortuga.isInsideIslande(temp) ){
                    tiles.get(val).setBackgroundColor(TileColor.SAND);
                    tortuga.getPlage().constructPlage(b);
                }

            }
        }

    }

    public HashMap<Dot, Tile> getTiles() {
        return tiles;
    }

    public  TileColor getTileColor( Dot point){
        return tiles.get(point).backgroundColor;

    }
}
