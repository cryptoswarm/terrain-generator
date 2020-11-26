package world.mode;

import world.Tile;
import world.TileColor;

public class Humidity extends Mode {
    final private int invalid = -1;
    TileColor color = TileColor.DARKBLUE;

    @Override
    public int getFactor(Tile tile){
        return tile.getHumidityLevel();
    }

    @Override
    public String getColor(int R, int G, int B, int A, int factor) {
        int value = applyFactor(G, factor);
        if(factor == invalid) return 0 + ":" + 0 + ":" + 0 + ":" + 255;
        return color.getR() + ":" + color.getG() + ":" + 255 + ":" + (255 - value);
    }
}
