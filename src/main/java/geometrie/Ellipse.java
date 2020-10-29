package geometrie;

import map.Tile;

import java.util.Random;

public class Ellipse {


    private double majorAxis;
    private double minorAxis;
    private Dot ellipseCenter;
    private float h;
    private double a;
    private double b;
    private double k;

    public Ellipse(  int width, int height ){

        this.majorAxis = defineShape(width, height);
        this.minorAxis = findMinorAxis(majorAxis);
        this.ellipseCenter = setNewCenter(width, height);
        this.h = ellipseCenter.getCoordonnee().getX();
        this.k = ellipseCenter.getCoordonnee().getY();
        this.a = majorAxis/2 ;  //was 800/2 = 400
        this.b = minorAxis/2 ;  // was 500=2 = 250
    }


    public double defineShape(int width, int height){
        Random r = new Random();
        double major = Math.max(width, height);

        return r.nextInt((int)(major /5) ) + major/2;

    }

    private double findMinorAxis(double majorAxis ){
        Random r = new Random();
        return  r.nextInt((int)(majorAxis )/5) + majorAxis/2; //will depend on major
        // say major = 500 then we get  [250, 350]
    }


    private Dot setNewCenter( int width, int height ){

        Random r = new Random();
        Dot perfectCenter = new Dot(new Coordonnee((float) (width / 2.0), (float) (height / 2.0), 0 ));

        float x = r.nextInt( (int)perfectCenter.getCoordonnee().getX() )+  perfectCenter.getCoordonnee().getX()/2;

        //perfectCenter.getCoordonnee().getX()/3;  //wil give somthing between x=[250, 750]
        float y = r.nextInt( (int)perfectCenter.getCoordonnee().getY()/2 )+  perfectCenter.getCoordonnee().getY() ;// +

        // perfectCenter.getCoordonnee().getY()/3;  y = [500, 750]
        return  new Dot(new Coordonnee(x, y, 0));

    }

    public boolean isOutEllipse(Tile tile ){

        float x = tile.getTilePseudoCenter().getCoordonnee().getX();
        float y = tile.getTilePseudoCenter().getCoordonnee().getY();

        return tile.getTilePseudoCenter().distance(ellipseCenter) <= a && //b &&
                ( Math.pow( (x-h),2)/ Math.pow(b,2) )
                        + ( Math.pow((y-k), 2) / Math.pow(b/2, 2) ) <= 1;
    }
}
