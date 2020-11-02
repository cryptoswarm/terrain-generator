package geometrie;

import java.util.Objects;

public class Coordinate {
    private  float x;
    private float y;
    private float z;

    public Coordinate(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float distance(Coordinate that) {
        float horizontal = this.x - that.x;
        float vertical = this.y - that.y;
        return (float) Math.sqrt( Math.pow(horizontal, 2) + Math.pow(vertical, 2) );
    }

    @Override
    public String toString() {
        return "Coordonnee{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;
        Coordinate that = (Coordinate) o;
        return Float.compare(that.x, x) == 0 &&
                Float.compare(that.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
