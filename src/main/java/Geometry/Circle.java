package Geometry;

import java.util.Random;

public class Circle {

    private Coordinate center;
    private double bRadius;
    private double sRadius;
    private double shortestSide;

    public Circle(int width, int height, Random random) {
        float c1;
        float c2;
        do {
            c1 = random.nextFloat();
            c2 = 1 - c1;
        } while (c1 < c2 + 0.2 && c2 < 0.2);

        this.shortestSide = Math.min(width, height);
        this.bRadius = (shortestSide/2)*c1;
        this.sRadius = (shortestSide/2)*c2;
        this.center = new Coordinate((float) (width / 2.0), (float) (height / 2.0), 0 );
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
}
