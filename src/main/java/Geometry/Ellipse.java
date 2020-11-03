package Geometry;

import java.util.Random;

public class Ellipse {
    private Random random;
    private Coordinate ellipseCenter;
    private double a;
    private double b;

    public Ellipse(  int width, int height, Random random ){
        this.random = random;
        this.ellipseCenter = setNewCenter(width, height);
        double majorAxis = defineShape(width, height);
        double minorAxis = findMinorAxis(majorAxis);
        this.a = majorAxis/2;
        this.b = minorAxis/2;
        this.random = random;
    }


    private double defineShape(int width, int height){
        double major = Math.max(width, height);
        return random.nextInt((int)(major/5)) + major/2;
    }

    private double findMinorAxis(double majorAxis ){
        return  random.nextInt((int)(majorAxis )/5) + majorAxis/2;
    }

    private Coordinate setNewCenter(int width, int height ){
        Coordinate perfectCenter = new Coordinate((float)(width/2), (float)(height/2), 0 );
        float x = random.nextInt((int)perfectCenter.getX()) +  perfectCenter.getX()/2;
        float y = random.nextInt( (int)perfectCenter.getY()/2) + perfectCenter.getY();

        return  new Coordinate(x, y, 0);

    }

    public boolean isInEllipse(Coordinate c){

        float x = c.getX();
        float y = c.getY();
        float x1 = ellipseCenter.getX();
        float y1 = ellipseCenter.getY();
        return c.distance(ellipseCenter) <= a &&
                ( Math.pow( (x-x1),2)/ Math.pow(b,2) ) +
                        ( Math.pow((y-y1), 2) / Math.pow(b/2, 2) ) <= 1;
    }
}
