package map;

import geometrie.Dot;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Plage {


    private Map<Dot, Tile>  plage;
    Color color;


    public Plage(){

        this.plage = new HashMap<>();
    }

    public void constructPlage( Tile tuile){
        plage.put( tuile.getTileCenter(), tuile);
    }


    public Map<Dot, Tile> getPlage() {
        return plage;
    }

    public Tile getPlageTuildId(Dot dot) {
        return plage.get(dot);
    }

    public int plageTuildNbr(){
        return plage.size();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
