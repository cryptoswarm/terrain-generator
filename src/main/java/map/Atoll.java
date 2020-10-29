package map;

import geometrie.Circle;
import geometrie.Dot;

public class Atoll {


    private Ocean ocean;
    private Lagon lagon;
    private Plage plage;
    private Vegetation vegetation;
    private Circle circle;


    public Atoll(int b, int s){

        this.circle = new Circle(b, s);
        this.ocean = new Ocean();
        this.lagon = new Lagon();
        this.plage = new Plage();
        this.vegetation = new Vegetation();
    }
/*
    @Override
    public Shape defineShape(int width, int height){
        //return  Math.min(width, height);
        return new Shape(width, height);
    }

*/
    public boolean isInOcean(Dot that ){

        return that.distance(circle.getCenter()) >= circle.getbRadius();
    }


    public boolean isInLagon(Dot that ){

        return that.distance(circle.getCenter()) <= circle.getsRadius();
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

    public Vegetation getVegetation(){
        return vegetation;
    }
}
