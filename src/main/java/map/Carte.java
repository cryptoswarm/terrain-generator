package map;

import ca.uqam.ace.inf5153.mesh.io.Structs;
import geometrie.Coordonnee;
import geometrie.Dot;
import translator.Ocean;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class Carte {
    int width;
    int height;
    //PseudoPoint perfectCenter;
    Dot perfectCenter;
    //HashMap<PseudoPoint, Tile> tiles;
    HashMap<Dot, Tile> tiles;

    public Carte(int width, int height) {
        this.width = width;
        this.height = height;
        this.perfectCenter = new Dot(new Coordonnee((float) (width / 2.0), (float) (height / 2.0), 0 ));
        this.tiles = new LinkedHashMap<>();
    }
    
    public void addTile(Tile tile) {
        //PseudoPoint tileCenter = new PseudoPoint(tile.center);
        //Dot dot = new Dot(tile.center);
        //Dot dot = new Dot(new Coordonnee( tile.center.getCoordonnee().getX(), tile.center.getCoordonnee().getY(), 0 ));
        tiles.put(tile.center,tile);
    }
    
    public void createAtoll() {

        
        int shortestSide = Math.min(width, height);
       // Laguna laguna = new Laguna(shortestSide*.2, perfectCenter);
        Atoll atoll = new Atoll((double)(shortestSide/2)*.7, (double)(shortestSide/2)*.4, perfectCenter);
        //System.out.println("point centre = "+perfectCenter.getCoordonnee().toString());
        //for (Tile t: tiles.values()) {
        Ocean ocean = new Ocean();
        Lagon lagon = new Lagon();
        Plage plage = new Plage();
        Vegetation vegetation = new Vegetation();
        /*
        for(Map.Entry m:ocean.getOcean2().entrySet()) {
            //Map<Tuile, Integer>
            Structs.Property color = Structs.Property.newBuilder().setKey("color").setValue( ocean.getColor().getRGB() ).build();
            builder.getPolygonsBuilder( (Integer) m.getValue() ).addProperties(color);
            System.out.println("id of ocean tuile = "+ (Integer) m.getValue() );

        }
         */

        for(Map.Entry<Dot, Tile> entry:tiles.entrySet() ) {
            Dot center = entry.getKey();
            Tile b = entry.getValue();

            //System.out.println(" center.distance(perfectCenter) = "+center.distance(perfectCenter) + "and radius ="+ (shortestSide/2)*.7);
            if (center.distance(perfectCenter) >= atoll.bRadius) {

                b.setBackgroundColor(TileColor.OCEANBLUE);
                ocean.constructOcean(center, b);
                System.out.println("ocean tuile id =" + b.polygonId + " coordinate " + b.getTilePseudoCenter().getCoordonnee().toString());


            } else if (center.distance(perfectCenter) <= atoll.sRadius) {
                b.setBackgroundColor(TileColor.WATERBLUE);
                lagon.constructLagon(center, b);
                System.out.println("lagon tuile id =" + b.polygonId + " coordinate " + b.getTilePseudoCenter().getCoordonnee().toString());
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

        System.out.println("lagon size = "+lagon.getLagon().size() );



            //System.out.println("tiles.values()"+tiles.values().size() );

            /*
            if (laguna.isTileOnTerrain(t)) {
                boolean isBeach = false;
                //System.out.println("size of neighbor = "+ t.getNeighborPseudoCenters().size());
                for (Dot neighbor: t.getNeighborPseudoCenters()) {
                    System.out.println("tiles.get( neighbor ) ="+tiles.get( neighbor ).center.toString());
                    if ( !laguna.isTileOnTerrain( tiles.get( neighbor )) ) {
                        isBeach = true;
                    }
                }
                if (isBeach) {
                    t.setBackgroundColor(TileColor.SAND);
                } else {
                    t.setBackgroundColor(TileColor.WATERBLUE);
                }
            } else if (atoll.isTileOnTerrain(t)) {
                boolean isBeach = false;
                for (Dot neighbor: t.getNeighborPseudoCenters()) {
                    if (!atoll.isTileOnTerrain( tiles.get(neighbor) )) {
                        isBeach = true;
                    }
                }
                if (isBeach) {
                    t.setBackgroundColor(TileColor.SAND);
                } else {
                    t.setBackgroundColor(TileColor.MIDGREEN);
                }
            } else {    
                t.setBackgroundColor(TileColor.OCEANBLUE);
            }
            */

    }
    
    public TileColor getTileColor(Structs.Point point) { //TileColor getTileColor(Structs.Point point) {
        //PseudoPoint pseudoCenter = new PseudoPoint(tileCenter);

        TileColor color = null;
        Dot dot = new Dot(new Coordonnee(point.getX(),
                point.getY(), 0));
        //System.out.println( tiles.get(dot));
        try {

            color = tiles.get(dot).getBackgroundColor();
        }catch ( NullPointerException e){
            //System.out.println("i'm null");
        }
        return color;
    }

    public HashMap<Dot, Tile> getTiles() {
        return tiles;
    }
}
