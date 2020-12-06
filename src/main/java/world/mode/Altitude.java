package world.mode;

import world.Tile;
import world.TileColor;

public class Altitude extends Mode {

    TileColor color = TileColor.BROWN;


    public Modes getMode(){

        return Modes.Altitude;

    };

    /**
     *
     * @param tile une quelconque tuile
     * @return l'altitude moyenne  de la tuile
     */
    @Override
    public int getFactor(Tile tile){
        return (int)tile.getAltitude();
    }

    /**
     *
     * @param R red
     * @param G green
     * @param B bleu
     * @param A
     * @param factor l'altitude moyenne  de la tuile
     * @return  le code RGBA equivalent
     */
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
