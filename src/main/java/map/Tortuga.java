package map;

import geometrie.Coordonnee;
import geometrie.Dot;

import java.util.Random;

public class Tortuga {

    private double majorAxis;
    private double minorAxis;
    private Dot center;
    private float h;
    private double a;
    private double b;
    private double k;
    private Vegetation vegetation;
    private Plage plage;


    public Tortuga(double width, Dot center) {


        this.majorAxis = findMajorAxis(width);
        this.minorAxis = findMinorAxis(majorAxis );

        this.center = findTortugaCenter(center);
        this.h = center.getCoordonnee().getX();
        this.k = center.getCoordonnee().getY();

        this.a = majorAxis ;  //was 800/2 = 400
        this.b = minorAxis ;  // was 500=2 = 250
        this.vegetation = new Vegetation();
        this.plage = new Plage();
    }

    //public boolean isOutEllipse(Dot dot){
    public boolean isOutEllipse(Tile tile, Dot dot){

        float x = dot.getCoordonnee().getX();
        float y = dot.getCoordonnee().getY();

        return ( Math.pow( (x-h),2)/ Math.pow(a,2) )
                + ( Math.pow((y-k), 2) / Math.pow(b, 2) ) >= 1;
    }

    public boolean isInsideIslande(Tile tile){

        float x = tile.getTilePseudoCenter().getCoordonnee().getX();
        float y = tile.getTilePseudoCenter().getCoordonnee().getY();

        return tile.getTilePseudoCenter().distance(center) <= a && //b &&
                ( Math.pow( (x-h),2)/ Math.pow(b,2) )
                        + ( Math.pow((y-k), 2) / Math.pow(b/2, 2) ) <= 1;
    }

    private Dot findTortugaCenter(Dot perfectCenter ){
        Random r = new Random();
        float x = r.nextInt( (int)perfectCenter.getCoordonnee().getX() )+  perfectCenter.getCoordonnee().getX()/2;

                //perfectCenter.getCoordonnee().getX()/3;  //wil give somthing between x=[250, 750]
        float y = r.nextInt( (int)perfectCenter.getCoordonnee().getY()/2 )+  perfectCenter.getCoordonnee().getY() ;// +

               // perfectCenter.getCoordonnee().getY()/3;  y = [500, 750]
        return  new Dot(new Coordonnee(x, y, 0));

    }

    private double findMajorAxis(double width){
        Random r = new Random();
        return r.nextInt((int)(width /5) ) + width/3;  //let's say width = 1000 then
        //return r.nextInt((int)(width ) ) + width/4;   // we get something between 333 and 833
    }

    private double findMinorAxis(double majorAxis ){
        Random r = new Random();
        return  r.nextInt((int)(majorAxis )/5) + majorAxis/2; //will depend of major
                                                                        // say major = 500 then we get  [250, 350]
     }

    public Vegetation getVegetation() {
        return vegetation;
    }

    public Plage getPlage() {
        return plage;
    }
}
