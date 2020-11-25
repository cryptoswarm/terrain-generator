package Geometry;

import RandomStrategy.RandomContexte;

public class Ellipse implements Shape{
    final private Coordinate ellipseCenter;
    final private int angle;
    final private double minorRadius;
    final private double majorRadius;

    public Ellipse(int diameter, RandomContexte random, int angle, Coordinate center){
        this.ellipseCenter = center;
        this.angle = angle;
        this.majorRadius = (double)diameter/2;
        this.minorRadius = (random.getRandomInt(diameter/4) + (double)diameter/2) / 2;

    }

    public double getMajorRadius(){
        return   majorRadius;
    }

    public double getMinorRadius(){
        return minorRadius;
    }

    public boolean isInShape(Coordinate c){

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
