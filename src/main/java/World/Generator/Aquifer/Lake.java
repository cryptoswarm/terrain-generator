
package World.Aquifer;

import World.Biome.Vegetation;
import World.TileColor;
import World.Tile;
import World.World;

import java.util.HashSet;
import static World.TileColor.WATERBLUE;

public class Lake extends Aquifer {
    private Tile tile;
    private HashSet<Tile> lake = new HashSet<>();
    final private TileColor color = WATERBLUE;

    public Lake(Tile tile) {
        this.tile = tile;
    }

    @Override
    public void apply(World w) {
        lake.add(tile);
        for(Tile i : tile.getNeighbors()) {
            if(i.getBiome() instanceof Vegetation) {
                lake.add(i);
            }
        }
        for(Tile i: lake) {
            i.setBackgroundColor(color);
            i.setHumidityLevel(5);
        }
        this.applyHumidityEffect(w,lake);
    }

    @Override
    public HashSet<Tile> getTiles() {
        return lake;
    }
}
