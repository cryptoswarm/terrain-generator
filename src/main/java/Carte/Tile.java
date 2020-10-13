package Carte;

public class Tile {

    private Point center;
    private Tile[] neighbor;

    public Tile(Point center) {
        this.center = center;
    }

    public Tile[] getNeighbor() {
        return neighbor;
    }
    public Point getCenter() {
        return center;
    }
}
