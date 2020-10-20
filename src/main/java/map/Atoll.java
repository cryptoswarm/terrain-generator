package map;

import geometrie.Dot;

public class Atoll {

    double bRadius;
    double sRadius;
    Dot terrainCenter;
    private Ocean ocean;
    private Lagon lagon;
    private Plage plage;
    private Vegetation vegetation;

    public Atoll(double b, double s,  Dot terrainCenter) {
        this.bRadius = b;
        this.sRadius = s;
        this.terrainCenter = terrainCenter;
        this.ocean = new Ocean();
        this.lagon = new Lagon();
        this.plage = new Plage();
        this.vegetation = new Vegetation();

    }
/*
    public void  isOutAtoll(Dot that, Tile b){
        if( that.distance(terrainCenter) >= bRadius ){
            b.setBackgroundColor(TileColor.OCEANBLUE);
            ocean.constructOcean(that, b);
        }
    }

 */

    public boolean isInOcean(Dot that ){
        return that.distance(terrainCenter) >= bRadius;
    }

/*
    public void  isInAtoll(Dot that, Tile b){
        if( that.distance(terrainCenter) <= sRadius ){
            b.setBackgroundColor(TileColor.WATERBLUE);
            lagon.constructLagon(that, b);
        }
    }
*/
    public boolean isInLagon(Dot that ){
        return that.distance(terrainCenter) <= sRadius;
    }

    public void defineOcean(Dot dot, Tile tile){
        ocean.constructOcean(dot, tile);
    }

    public void defineLagon(Dot dot, Tile tile){
        lagon.constructLagon(dot, tile);
    }

    public void definePLage(Tile b){
        plage.constructPlage(b);
    }
    public void defineVegetation(Tile b){
        vegetation.constructVegetation(b);
    }

    public boolean isBetweenLagonAndOcean(Dot that){
        return !isInLagon(that) && !isInOcean(that);
    }

    public boolean isNeighborLagonOrOcean(Tile b){
        return ocean.isNeighbor(b) || lagon.isNeighbor(b);
    }

}
