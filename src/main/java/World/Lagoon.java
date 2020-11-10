package World;

public class Lagoon implements Biome {
    final private TileColor color = TileColor.WATERBLUE;

    public Lagoon() {

    }

    @Override
    public TileColor getColor() {
        return color;
    }
}
