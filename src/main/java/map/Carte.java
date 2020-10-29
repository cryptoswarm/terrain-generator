package map;

import geometrie.Dot;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class Carte {

    int width;
    int height;

    HashMap<Dot, Tile> tiles;

    //HashMap< Tile, HashSet<Dot> > tileAndNeighbors;
    //HashSet<Dot> neighbors;

    Ocean ocean;

    public Carte(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new LinkedHashMap<>();
        //this.tileAndNeighbors = new LinkedHashMap<>();

        this.ocean = new Ocean();

    }
/*
    // Si on l'utilise, elle va service a ce qu'on puisse synchroniser les tuiles et le end mesh
    public HashMap<Tile, HashSet<Dot>> getTileAndNeighbors() {
        return tileAndNeighbors;
    }

 */
/*
    /**
     *  Cette methode n'est pas utilisé, son utilisation depend de notre implementation finale
     * @param tile une tuiles
     * @param neighbors  les voisins de cette tuile

    public void addTileAndNeighbors(Tile tile, HashSet<Dot> neighbors ){
       // this.neighbors = neighbors;
        tileAndNeighbors.put(tile, new HashSet<>(neighbors));
    }
*/


    public void addTile(Tile tile) {

        tiles.put(tile.getTileCenter(),tile);
    }

    /**
     *  Attention : Si on decide de donner à la carte la responsabilité de connaitre les voisins de chaque tuile
     *              donc, c'est l'implementation en commentaire qu'on va utiliser
     * @return Atoll
     */
    public Atoll createAtoll() {

        Atoll atoll = new Atoll( width , height); //width =1000

        for(Map.Entry<Dot, Tile> entry:tiles.entrySet() ) {
            Dot center = entry.getKey();
            Tile b = entry.getValue();

            if(atoll.isInOcean(center)){
                b.setBackgroundColor(TileColor.OCEANBLUE);
                ocean.constructOcean(center, b);
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

                if(atoll.isNeighborLagonOrOcean(ocean, b)){
                    b.setBackgroundColor(TileColor.SAND);
                    atoll.definePLage(b);
                }else{
                    b.setBackgroundColor(TileColor.MIDGREEN);
                    atoll.defineVegetation(b);
                }
            }
        }

/*
        for(Map.Entry<Tile, HashSet<Dot>> entry:tileAndNeighbors.entrySet() ) {

            Tile tile = entry.getKey();
            HashSet<Dot> neighbors = entry.getValue();

            if(atoll.isInOcean(tile.getTileCenter())){
                tile.setBackgroundColor(TileColor.OCEANBLUE);
                ocean.constructOcean(tile.getTileCenter(), tile);
            }

            if(atoll.isInLagon( tile.getTileCenter()) ){
                tile.setBackgroundColor(TileColor.WATERBLUE);
                atoll.defineLagon(tile.getTileCenter(), tile);
            }
        }

      for(Map.Entry<Tile, HashSet<Dot> > entry:tileAndNeighbors.entrySet() ) {

            Tile tile = entry.getKey();
            HashSet<Dot> neighbors = entry.getValue();

           if (atoll.isBetweenLagonAndOcean1(tile)) {

                //if( ocean.isNeighbor(tile) || atoll.getLagon().isNeighbor(tile) ){
                for (Dot dot : neighbors) {
                    //Tile tile1 = tiles.get(dot);

                    if(atoll.isNeighborLagonOrOcean1(ocean, dot)){
                        tile.setBackgroundColor( TileColor.SAND );
                        atoll.definePLage(tile);
                    }
                }
            }
        }

        for(Map.Entry<Tile, HashSet<Dot> > entry:tileAndNeighbors.entrySet() ) {

            Tile tile = entry.getKey();
            HashSet<Dot> neighbors = entry.getValue();

            if (atoll.isBetweenLagonAndOcean1(tile)) {

                for (Dot dot : neighbors) {

                    if( ! atoll.isNeighborLagonOrOcean1(ocean, dot) && tile.getBackgroundColor() != TileColor.SAND){

                        tile.setBackgroundColor(TileColor.MIDGREEN);
                        atoll.defineVegetation(tile);
                    }
                }
            }
        }

 */

        return atoll;
    }



    public Tortuga createATortuga() {

        Tortuga tortuga = new Tortuga(width, height);//perfectCenter);

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
    public void createLake( Vegetation vegetation, int nbWaterSources ){
        for(int i=0; i< nbWaterSources; i++){
            Aquifere aquifere = new Aquifere(vegetation);
            Lake lake = new Lake(aquifere);
            lake.findLakeNeighbors(vegetation);
            lake.setColorNeighbors();
            lake.setColor();
        }
    }

    /**
     *
     * @return le centre de chaque tuile et la tuile elle meme
     */

    public HashMap<Dot, Tile> getTiles() {
        return tiles;
    }
}
