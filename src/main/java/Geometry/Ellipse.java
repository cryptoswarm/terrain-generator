package Geometry;

import RandomStrategy.RandomContexte;

import java.util.Random;

public class Ellipse {
    final private int majorAxis;
    final private Coordinate ellipseCenter;
    final private int angle;
    final private double minorRadius;
    final private double majorRadius;

    public Ellipse(int diameter, RandomContexte random, int angle, Coordinate center){
        this.majorAxis = diameter;
        this.ellipseCenter = center;
        this.angle = angle;
        this.majorRadius = (double)majorAxis/2;
        random = new RandomContexte(0);
        this.minorRadius = (random.getRandomInt(majorAxis/4) + (double)majorAxis/2 ) / 2;

    }

    public double getMajorRadius(){
        return   majorRadius;
    }

    public double getMinorRadius(){
        return minorRadius;
    }

    public boolean isInEllipse(Coordinate c){
        float x = c.getX();
        float y = c.getY();
        float h = ellipseCenter.getX();
        float k = ellipseCenter.getY();

        return c.distance(ellipseCenter) <= majorRadius &&
                ((Math.pow(((x-h)*Math.cos(angle) + (y-k)*Math.sin(angle)), 2) / Math.pow(majorRadius, 2)) +
                        (Math.pow(((x-h)*Math.sin(angle) - (y-k)*Math.cos(angle)), 2) / Math.pow(minorRadius, 2))) <= 1;
    }

    public Coordinate getEllipseCenter() {
        return ellipseCenter;
    }

    public int getAngle() {
        return angle;
    }
}
