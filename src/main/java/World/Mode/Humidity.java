package World.Mode;

import World.TileColor;

public class Humidity extends Mode {
    TileColor color = TileColor.DARKBLUE;
    @Override
    public String getColor(int R, int G, int B, int A, int factor) {
        int value = applyFactor(G, factor);
        if(factor == 0) return 0 + ":" + 0 + ":" + 0 + ":" + 255;
        return color.getR() + ":" + color.getG() + ":" + 255 + ":" + (255 - value);
    }
}
