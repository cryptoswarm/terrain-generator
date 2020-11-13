package World.Generator.Biome;

import World.TileColor;

public class Vegetation implements Biome {

    TileColor color = TileColor.LIGHTGREEN;
    final private String type = "vegetation";


    public Vegetation(){}

    @Override
    public TileColor getColor() {
        return color;
    }

    @Override
    public String getType() {
        return type;
    }
}
