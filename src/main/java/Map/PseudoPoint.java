package Map;

import ca.uqam.ace.inf5153.mesh.io.Structs;

import java.util.Objects;

public class PseudoPoint {
    float x;
    float y;

    public PseudoPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public PseudoPoint(Structs.Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }
    
    public float distance(PseudoPoint that) {
        float horizontal = this.x - that.x;
        float vertical = this.y - that.y;
        
        return (float) Math.sqrt(horizontal*horizontal + vertical*vertical);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PseudoPoint that = (PseudoPoint) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
