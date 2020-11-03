package World;

import static World.TileColor.SAND;

public class Plage implements Biome {

    final private TileColor color = SAND;

    public Plage(){

    }

    @Override
    public TileColor getColor() {
        return color;
    }
}
