package World.Aquifer;

import World.Biome.Vegetation;
import World.Tile;
import World.TileColor;
import World.World;

import java.util.HashSet;
import static World.TileColor.DARKGREEN;

public class Nape extends Aquifer {
    final private Tile tile;
    final private HashSet<Tile> nape = new HashSet<>();
    TileColor color = DARKGREEN;

    public Nape(Tile tile) {
        this.tile = tile;
    }

    @Override
    public void apply(World w) {
        nape.add(tile);
        for(Tile i : tile.getNeighbors()) {
            if(i.getBiome() instanceof Vegetation) {
                nape.add(i);
            }
        }
        for(Tile i: nape) {
            i.setBackgroundColor(color);
            i.setHumidityLevel(5);
        }
        this.applyHumidityEffect(w, nape);

    }

    @Override
    public HashSet<Tile> getTiles() {
        return nape;
    }
}
