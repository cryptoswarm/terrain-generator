package world.mode;

import world.Tile;
import world.TileColor;

public class Ressources extends Mode {

    TileColor color = TileColor.CHESTNUT;

    public Modes getMode(){

        return Modes.Ressources;

    };

    @Override
    public int getFactor(Tile tile){
        return (int)tile.getRichness();
    }

    @Override
    public String getColor(int R, int G, int B, int A, int factor) {
        //int value = factor;
        int value = applyFactor(0, factor);
        if (value == 0){
            return 0 + ":" + 0 + ":" + 0 + ":" + 255;
        }else{
            return color.getR() + ":" + color.getG() + ":" + color.getB() + ":" + value;

        }
    }

}
