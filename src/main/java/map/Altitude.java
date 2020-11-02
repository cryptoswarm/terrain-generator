package map;

public class Altitude implements Mode {
    TileColor color = TileColor.BROWN;
    @Override
    public String getColor(int R, int G, int B, int A, int factor) {
        int value = applyFactor(0, (int)factor);
        if (value == 0){
            return 0 + ":" + 0 + ":" + 0 + ":" + 255;
        }else{
            return color.getR() + ":" + color.getG() + ":" + color.getB() + ":" + value;

        }
    }

    private static int applyFactor(int g, int factor){
        if (factor > 0){
            if (70 + factor >= 255) return 255;
            return 70 + factor;
        }
        return g;
    }
}
