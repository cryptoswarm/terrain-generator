package World.Mode;

public class Normal extends Mode {
    @Override
    public String getColor(int R, int G, int B, int A, int humidity) {
        if (humidity == 0 || humidity == 1000) return R + ":" + G + ":" + B + ":" + A;
        return R + ":" + applyFactor(G,humidity) + ":" + B + ":" + A;
    }


}
