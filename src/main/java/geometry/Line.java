package geometry;

import world.TileColor;

import java.util.Objects;

public class Line {
    private Coordinate c1;
    private Coordinate c2;
    TileColor color;
    int flow;

    public Line(Coordinate c1, Coordinate c2) {
        this.c1 = c1;
        this.c2 = c2;
        this.flow = 0;
    }

    public void setColor(TileColor color) {
        this.color = color;
    }
    public void increaseFlow(){
        flow = flow +2;
    }

    public Coordinate getC1() {
        return c1;
    }
    public Coordinate getC2() {
        return c2;
    }
    public TileColor getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return c1.equals(line.c1) &&
                c2.equals(line.c2) || c2.equals(line.c1) && c1.equals(line.c2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(c1.getX() + c1.getY() + c2.getX() + c2.getY());
    }
}