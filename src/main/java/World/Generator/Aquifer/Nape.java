package World.Generator.Aquifer;

import Geometry.Coordinate;
import World.Generator.Biome.Vegetation;
import World.Tile;
import World.TileColor;
import World.World;

import java.util.HashMap;
import static World.TileColor.DARKGREEN;

public class Nape extends Aquifer {
    final private Tile tile;
    final private HashMap<Coordinate, Tile> nape = new HashMap<>();
    TileColor color = DARKGREEN;

    public Nape(Tile tile) {
        this.tile = tile;
    }

    @Override
    public void apply(World w) {
        nape.put(tile.getCenter(), tile);
        for(Tile i : tile.getNeighbors().values()) {
            if(i.getBiome() instanceof Vegetation) {
                nape.put(i.getCenter(), i);
            }
        }
        for(Tile i: nape.values()) {
            i.setBackgroundColor(color);
            i.setHumidityLevel(5);
        }
        this.applyHumidityEffect(w, nape);

    }

    @Override
    public HashMap<Coordinate, Tile> getTiles() {
        return nape;
    }
}
