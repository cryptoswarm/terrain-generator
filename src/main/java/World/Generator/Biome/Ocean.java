package World.Biome;


import World.TileColor;

public class Ocean implements Biome {
    final private TileColor color = TileColor.OCEANBLUE;

    public Ocean(){}

    public TileColor getColor() {
        return color;
    }
}
