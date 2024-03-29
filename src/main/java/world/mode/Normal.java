package world.mode;

import world.Tile;

public class Normal extends Mode {

    final int invalid = -1;

    public Modes getMode(){

        return Modes.Normal;

    };

    @Override
    public int getFactor(Tile tile){
        return tile.getHumidityLevel();
    }

    @Override
    public String getColor(int R, int G, int B, int A, int humidity) {

        if (humidity == invalid || humidity == 255){
            return R + ":" + G + ":" + B + ":" + A;
        }
        return R + ":" + applyFactor(G,humidity) + ":" + B + ":" + A;
    }
}
