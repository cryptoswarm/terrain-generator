package World.Generator.Aquifer;

import Geometry.Coordinate;
import World.Generator.Biome.Vegetation;
import World.Tile;
import World.TileColor;
import World.World;
import World.soilType;

import java.util.HashMap;
import static World.TileColor.DARKGREEN;

public class Nape extends Aquifer {
    final private Tile tile;
    final private soilType soil;
    final private HashMap<Coordinate, Tile> nape = new HashMap<>();
    final private TileColor color = DARKGREEN;

    public Nape(Tile tile, soilType soil) {
        this.tile = tile;
        this.soil = soil;
    }

    @Override
    public void apply(World w) {
        nape.put(tile.getCenter(), tile);
        for(Tile i : w.getNeighbor(tile)) {
            if(i.getBiome() instanceof Vegetation) {
                nape.put(i.getCenter(), i);
            }
        }
        for(Tile i: nape.values()) {
            i.setBackgroundColor(color);
            i.setHumidityLevel(5);
        }
        this.applyHumidityEffect(w, nape, soil);

    }

    @Override
    public HashMap<Coordinate, Tile> getTiles() {
        return nape;
    }
}
