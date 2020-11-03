package World;

public class Ocean implements Biome {
    final private TileColor color = TileColor.OCEANBLUE;

    public Ocean(){}

    public TileColor getColor() {
        return color;
    }
}
