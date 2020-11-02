package map;

public class Humidity implements Mode{
    TileColor color = TileColor.DARKBLUE;
    @Override
    public String getColor(int R, int G, int B, int A, int factor) {
        int value = applyFactor(G, factor);
        if(factor == 0) return 0 + ":" + 0 + ":" + 0 + ":" + 255;
        return color.getR() + ":" + color.getG() + ":" + 255 + ":" + (255 - value);
    }

    private static int applyFactor(int g, int factor){
        if (factor > 0){
            if (70 + factor >= 255) return 255;
            return 70 + factor;
        }
        return g;
    }
}
