package World.Generator.Biome;

import World.TileColor;

public class Lagoon implements Biome {
    final private TileColor color = TileColor.WATERBLUE;
    final private String type = "lagoon";
    public Lagoon() {}

    @Override
    public TileColor getColor() {
        return color;
    }

    @Override
    public String getType() {
        return type;
    }
}
