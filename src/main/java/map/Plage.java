package map;

import geometrie.Dot;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import static map.TileColor.SAND;

public class Plage implements Biome{


    private Map<Dot, Tile>  plage;
    private TileColor color = SAND;


    public Plage(){

        this.plage = new HashMap<>();
    }

    public void constructPlage( Tile tile){
        plage.put( tile.getTileCenter(), tile);
    }


    /*public Map<Dot, Tile> getPlage() {
        return plage;
    }*/

    public TileColor getColor() {
        return color;
    }
}
