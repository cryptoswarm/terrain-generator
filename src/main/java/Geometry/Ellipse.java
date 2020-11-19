package Geometry;


import RandomStrategy.RandomContexte;

public class Ellipse implements Shape {

    final private double majorAxis;
    final private double minorAxis;
    final private Coordinate ellipseCenter;
    final private double majorRadius;
    final private double minorRadius;
    private int angle;

    public Ellipse(int width, int height, RandomContexte random){
        this.majorAxis = findMajorAxis(width, height, random);
        this.minorAxis = findMinorAxis(majorAxis, random);
        this.ellipseCenter = setNewCenter(width, height, random);
        this.majorRadius = majorAxis/2 ;
        this.minorRadius = minorAxis/2 ;
    }

    public Ellipse(int majorAxis, int minorAxis, Coordinate center, int angle){
        this.majorAxis = majorAxis;
        this.minorAxis = minorAxis;
        this.ellipseCenter = center;
        this.angle = angle;
        this.majorRadius = majorAxis/2 ;
        this.minorRadius = minorAxis/2 ;
    }

    public double findMajorAxis(int width, int height, RandomContexte r){
        double major = Math.max(width, height);
        return r.getRandomInt((int)(major /4) ) + major/4;
    }

    private double findMinorAxis(double majorAxis, RandomContexte r ){
        return  r.getRandomInt((int)(majorAxis )/4) + majorAxis/2;
    }

    private Coordinate setNewCenter(int width, int height, RandomContexte r ){
        Coordinate perfectCenter = new Coordinate((float) (width / 2.0), (float) (height / 2.0), 0 );

        float x = r.getRandomInt((int)perfectCenter.getX() )+  perfectCenter.getX()/2;
        float y = r.getRandomInt( (int)perfectCenter.getY() )+  perfectCenter.getY() /2 ;

        return  new Coordinate(x, y, 0);
    }

    @Override
    public boolean isInArea(Coordinate c) {

        float x = c.getX();
        float y = c.getY();

        return c.distance(ellipseCenter) <= majorRadius &&

                (  ( Math.pow( ( (x-ellipseCenter.getX())*Math.cos(angle) + (y-ellipseCenter.getY())*Math.sin(angle)), 2) / Math.pow(majorRadius, 2) ) +
                        (  Math.pow( ( (x-ellipseCenter.getX())*Math.sin(angle) - (y-ellipseCenter.getY())*Math.cos(angle)), 2) / Math.pow(minorRadius, 2) ) ) <=1;


    }

    public double getMajorAxis() {
        return majorAxis;
    }

    public int getAngle() {
        return angle;
    }

    @Override
    public int getDistanceFrom(Coordinate c) {
        return 0;
    }
}