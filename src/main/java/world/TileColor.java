package world;

public enum TileColor {
    OCEANBLUE(0,64,128,255),
    WATERBLUE(0,255,255,255),
    SAND(223,159,0,255),
    LIGHTGREEN(0,255,0,255),
    DARKGREEN(0,70,0,255),
    DARKBLUE(0,0,255,255),
    BROWN(165,42,42, 255),
    CHESTNUT(149,69,53, 255),
    DARK(0,0,0,255);

    TileColor(int r, int g, int b, int a) {
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
