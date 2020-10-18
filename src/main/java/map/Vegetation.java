package map;

import geometrie.Dot;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Vegetation {

    private Map<Dot, Tile>  vegetation;
    Color color;


    public Vegetation(){

        this.vegetation = new HashMap<>();
    }

    public void constructVegetation(Tile tuile){
        vegetation.put( tuile.getTilePseudoCenter(), tuile);
    }


    public Map<Dot, Tile> getTuileVege() {
        return vegetation;
    }

    public Tile getVegeTuildId(Dot dot) {
        return vegetation.get(dot);
    }

    public int vegeTuildNbr(){
        return vegetation.size();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
