package World.Generator.Biome;


import World.TileColor;

public class Ocean implements Biome {
    final private TileColor color = TileColor.OCEANBLUE;
    final private String type = "ocean";

    public Ocean(){}

    public TileColor getColor() {
        return color;
    }

    @Override
    public String getType() {
        return type;
    }
}
