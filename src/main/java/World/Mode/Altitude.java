package World.Mode;

import World.Tile;
import World.TileColor;

public class Altitude extends Mode {

    TileColor color = TileColor.BROWN;


    @Override
    public int getFactor(Tile tile){
        return (int)tile.getAltitude();
    }

    @Override
    public String getColor(int R, int G, int B, int A, int factor) {

        int value = applyFactor(0, factor);
        if (value == 0){
            return 0 + ":" + 0 + ":" + 0 + ":" + 255;
        }else{
            return color.getR() + ":" + color.getG() + ":" + color.getB() + ":" + value;

        }
    }
}
