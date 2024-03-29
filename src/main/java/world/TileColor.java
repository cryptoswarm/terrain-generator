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
    DARK(0,0,0,255),
    PINK(255,192,203, 255),
    POWDERBLUE(173, 224,230, 255),
    WHITE(255,255,255,255),
    DESERT(202,226,20,255),
    SAVANE(147,226,20,255),
    TAIGA(75, 237, 252, 255),
    PRAIRIE(133,204,67,255),
    TOUNDRA(210,232,244,255),
    ICEBEACH(210,232,244,255),
    ROCKBEACH(78,82,89,255);

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
