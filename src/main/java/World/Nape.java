package World;

import java.util.HashSet;
import static World.TileColor.DARKGREEN;

public class Nape implements Aquifer{
    private HashSet<Tile> nape = new HashSet<>();
    TileColor color = DARKGREEN;

    public Nape(Tile tile) {
        nape.add(tile);
        for(Tile i : tile.getNeighbors().values()) {
            if(i.getBiome() instanceof Vegetation) {
                nape.add(i);
            }
        }
        for(Tile i: nape) {
            i.setBackgroundColor(color);
            i.setHumidityLevel(5);
            //vegetation.remove(i.getCenter());
        }
    }

    @Override
    public HashSet<Tile> getTiles() {
        return nape;
    }
}
