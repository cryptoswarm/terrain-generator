package geometry;

import randomStrategy.RandomContexte;

public class Circle implements Shape{

    public static final int POWER = 2;
    private Coordinate center;
    private double bRadius;
    private double sRadius;
    private int diameter;
    private RandomContexte random;


    public Circle(int diameter, RandomContexte random, Coordinate center ) {
        this.center = center;
        this.diameter = diameter;
        this.random = random;
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

    private void calculateRadiuses(RandomContexte random){

        float c1;
        float c2;

        c1 = ( (float)random.getRandomInt(10)  / 100 )+ 0.70f;
        c2 = 1 - c1;

        this.bRadius = ((double) diameter/POWER)*c1;
        this.sRadius = ((double) diameter/POWER)*c2;

    }

    @Override

    public boolean isInShape(Coordinate c) {

        return Math.pow((c.getX() - center.getX()), POWER) +
                Math.pow((c.getY() - center.getY()), POWER) <= Math.pow(bRadius, POWER);

    }

    public int getDiameter() {
        return diameter;
    }

    public RandomContexte getRandom() {
        return random;
    }
}
