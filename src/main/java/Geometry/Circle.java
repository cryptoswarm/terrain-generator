package Geometry;

import RandomStrategy.RandomContexte;

public class Circle implements Shape{

    private Coordinate center;
    private double bRadius;
    private double sRadius;
    private int diameter;


    public Circle(int diameter, RandomContexte random, Coordinate center ) {
        this.center = center;
        this.diameter = diameter;
        calculateRadiuses(random);
    }


    public Coordinate getCenter() {
        return center;
    }

    public double getBigRadius() {
        return bRadius;
    }
    public double getSmallRadius() {
        return sRadius;
    }

    public void calculateRadiuses(RandomContexte random){

        float c1;
        float c2;

        c1 = ( (float)random.getRandomInt(10)  / 100 )+ 0.70f;
        c2 = 1 - c1;

        this.bRadius = ((double) diameter/2)*c1;
        this.sRadius = ((double) diameter/2)*c2;

    }

    @Override
    public boolean isInShape(Coordinate c) {
        return Math.pow((c.getX() - center.getX()), 2) +
                Math.pow((c.getY() - center.getY()), 2) <= Math.pow(bRadius, 2);
    }
}
