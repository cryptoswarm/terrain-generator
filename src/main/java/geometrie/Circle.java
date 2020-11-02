package geometrie;

public class Circle {

    private Coordinate center;
    private double bRadius;
    private double mRadius;
    private double sRadius;
    private int width;
    private int height;
    private double shortestSide;

    public Circle( int width, int height) {

        this.shortestSide = Math.min(width, height);
        this.bRadius = (shortestSide/2)*.7;
        this.sRadius = (shortestSide/2)*.4;
        this.center = new Coordinate((float) (width / 2.0), (float) (height / 2.0), 0 );
        this.width = width;
        this.height = height;
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
