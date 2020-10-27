package geometrie;

public class Circle implements Shape {

    private Dot center;
    private double bRadius;
    private double sRadius;
    private int width;
    private int height;
    private double shortestSide;

    public Circle( int width, int height) {

        this.shortestSide = defineShape(width, height);
        this.bRadius = (shortestSide/2)*.7;
        this.sRadius = (shortestSide/2)*.4;
        this.center = new Dot(new Coordonnee((float) (width / 2.0), (float) (height / 2.0), 0 ));
        this.width = width;
        this.height = height;
    }

    @Override
    public  double defineShape(int width, int height){
       return  Math.min(width, height);
    }

    public Dot getCenter() {
        return center;
    }

    public double getbRadius() {
        return bRadius;
    }

    public double getsRadius() {
        return sRadius;
    }
}
