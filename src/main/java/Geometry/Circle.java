package Geometry;

public class Circle implements Shape {


    Coordinate center;
    double radius;

    public Circle(Coordinate center, double radius){
        this.center = center;
        this.radius = radius;
    }

    @Override
    public boolean isInArea(Coordinate c) {
        return c.distance(this.center) <= radius;
    }

    @Override
    public int getDistanceFrom(Coordinate c) {
        return Math.abs((int)radius - (int) c.distance(center));
    }
}
