package World.Generator.Biome;

import World.TileColor;
import static World.TileColor.SAND;

public class Plage implements Biome {

    final private TileColor color = SAND;
    final private String type = "plage";

    public Plage(){}

    @Override
    public TileColor getColor() {
        return color;
    }

    @Override
    public String getType() {
        return null;
    }
}
