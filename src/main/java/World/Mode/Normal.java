package World.Mode;

import World.Mode.Mode;
import World.World;

public class Normal implements Mode {
    @Override
    public String getColor(int R, int G, int B, int A, int humidity) {
        if (humidity == 0 || humidity == 1000) return R + ":" + G + ":" + B + ":" + A;
        return R + ":" + applyFactor(G,humidity) + ":" + B + ":" + A;
    }

    private static int applyFactor(int g, int factor){
        if (factor > 0){
            if (70 + factor >= 255) return 255;
            return 70 + factor;
        }
        return g;
    }
}
