package map;

import geometrie.Coordonnee;
import geometrie.Dot;
import translator.Ocean;

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

        int shortestSide = Math.min(width, height);

        Atoll atoll = new Atoll((double)(shortestSide/2)*.7, (double)(shortestSide/2)*.4, perfectCenter);
        Ocean ocean = new Ocean();
        Lagon lagon = new Lagon();
        Plage plage = new Plage();
        Vegetation vegetation = new Vegetation();

        for(Map.Entry<Dot, Tile> entry:tiles.entrySet() ) {
            Dot center = entry.getKey();
            Tile b = entry.getValue();
            if (center.distance(perfectCenter) >= atoll.bRadius) {

                b.setBackgroundColor(TileColor.OCEANBLUE);
                ocean.constructOcean(center, b);

            } else if (center.distance(perfectCenter) <= atoll.sRadius) {
                b.setBackgroundColor(TileColor.WATERBLUE);
                lagon.constructLagon(center, b);
            }
        }

        for(Map.Entry<Dot, Tile> entry:tiles.entrySet() ) {
            Dot center = entry.getKey();
            Tile b = entry.getValue();

            if( center.distance(perfectCenter) > atoll.sRadius && center.distance(perfectCenter) < atoll.bRadius){
                if( ocean.isNeighbor(b) || lagon.isNeighbor(b)){
                    b.setBackgroundColor(TileColor.SAND);
                    plage.constructPlage(b);
                }else {
                    b.setBackgroundColor(TileColor.DARKGREEN);
                    vegetation.constructVegetation(b);
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
