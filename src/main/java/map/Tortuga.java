package map;

import geometrie.Dot;

public class Tortuga {

    private double majorAxis;
    private double minorAxis;
    private Dot center;
    private float h;
    private double a;
    private double b;
    private double k;


    public Tortuga(double majorAxis, double minorAxis, Dot center) {
        this.majorAxis = majorAxis;
        this.minorAxis = minorAxis;
        this.center = center;
        this.h = center.getCoordonnee().getX();
        this.k = center.getCoordonnee().getY();
        this.a = majorAxis / 2;
        this.b = minorAxis / 2;
    }

    public boolean isOnEllipse(Dot dot){

        float x = dot.getCoordonnee().getX();
        float y = dot.getCoordonnee().getY();


        return ( Math.pow( (x-h),2)/ Math.pow(a,2) )
                + ( Math.pow((y-k), 2) / Math.pow(b, 2) ) >= 1;
    }
}
