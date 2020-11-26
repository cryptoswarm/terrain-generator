package world;

public enum Ressource {
    WOOD(1,1,1,1);

    Ressource(int r, int g, int b, int a) {
        R = r;
        G = g;
        B = b;
        A = a;
    }

    private int R;
    private int G;
    private int B;
    private int A;

    public int getR() {
        return R;
    }
    public int getG() {
        return G;
    }
    public int getB() {
        return B;
    }
    public int getA() {
        return A;
    }

    @Override
    public String toString() {
        return R + ":" + G + ":" + B + ":" + A;
    }
}
