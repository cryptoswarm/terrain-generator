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

        /*
        //int major = r.nextInt(height - width/2) + width;
        //System.out.println("major = "+major);
       // int major = r.nextInt(height );

        //int minor = r.nextInt(major);
        //int centerId = r.nextInt(tiles.size());
         */
        //this.majorAxis = majorAxis;
        this.majorAxis = findMajorAxis(width);
        this.minorAxis = findMinorAxis(majorAxis );
        //this.center = center;
        this.center = findTortugaCenter(center);
        this.h = center.getCoordonnee().getX();
        this.k = center.getCoordonnee().getY();
        //this.a = majorAxis / 2;
        //this.b = minorAxis / 2;
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
        float x = r.nextInt( (int)perfectCenter.getCoordonnee().getX() +  (int)perfectCenter.getCoordonnee().getX()/4 ) +

                perfectCenter.getCoordonnee().getX()/3;
        float y = r.nextInt( (int)perfectCenter.getCoordonnee().getY() +  (int)perfectCenter.getCoordonnee().getY()/4 ) +

                perfectCenter.getCoordonnee().getY()/3;
        return  new Dot(new Coordonnee(x, y, 0));

    }

    private double findMajorAxis(double width){
        Random r = new Random();
        return r.nextInt((int)(width /5) ) + width/3;  //let's say width = 1000 then
        //return r.nextInt((int)(width ) ) + width/4;   // we get something between 333 and 533
    }

    private double findMinorAxis(double majorAxis ){
        Random r = new Random();
        return  r.nextInt((int)(majorAxis )/5) + majorAxis/2; //will depend of major
                                                                        // say major = 500 then we get  [250, 350]
     }

     public boolean isCotiere(Dot dot){
        return dot.distance(center) > a;
     }

    public Vegetation getVegetation() {
        return vegetation;
    }

    public Plage getPlage() {
        return plage;
    }
}
