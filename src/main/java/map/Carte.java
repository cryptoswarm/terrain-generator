package map;

import geometrie.Dot;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class Carte {

    int width;
    int height;
    //Tortuga tortuga;
    //Atoll atoll;
    HashMap<Dot, Tile> tiles;

    public Carte(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new LinkedHashMap<>();
        //this.tortuga = new Tortuga(width, height);
        //this.atoll =  new Atoll( width, height);
    }
    
    public void addTile(Tile tile) {
        tiles.put(tile.center,tile);
    }
    
    public Atoll createAtoll() {

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
        return atoll;
    }

    public Tortuga createATortuga() {

        Tortuga tortuga = new Tortuga(width, height);//perfectCenter);

        Ocean ocean = new Ocean();

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

        return tortuga;
    }

    public void createAquifere(Vegetation vegetation, int nb){
        for(int i=0; i<nb; i++){
            Aquifere aquifere = new Aquifere(vegetation);
            //aquifere.setColor();
            //aquifere.propager();
            Lake lake = new Lake(aquifere);
            lake.setColor();
        }
    }


    public HashMap<Dot, Tile> getTiles() {
        return tiles;
    }

    public  TileColor getTileColor( Dot point){
        return tiles.get(point).backgroundColor;

    }
}
