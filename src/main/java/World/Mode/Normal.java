package World.Mode;

public class Normal extends Mode {
    final int invalid = -1;
    @Override
    public String getColor(int R, int G, int B, int A, int humidity) {
        if (humidity == invalid) return R + ":" + G + ":" + B + ":" + A;
        return R + ":" + applyFactor(G,humidity) + ":" + B + ":" + A;
    }


}
