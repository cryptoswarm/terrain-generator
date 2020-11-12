package World.Biome;

import World.TileColor;

public class Vegetation implements Biome {

    TileColor color = TileColor.LIGHTGREEN;


    public Vegetation(){}

    @Override
    public TileColor getColor() {
        return color;
    }
}
