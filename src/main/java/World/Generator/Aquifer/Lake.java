
package World.Generator.Aquifer;

import World.Generator.Biome.Vegetation;
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
        double lowestAltitude;
        lowestAltitude = tile.getAltitude();
        lake.add(tile);

        for(Tile i : tile.getNeighbors()) {
            if(i.getBiome().getType().equals("vegetation")) {
                lake.add(i);
                if(i.getAltitude() < lowestAltitude) {
                    lowestAltitude = i.getAltitude();
                }
            }
        }
        for(Tile i: lake) {
            i.setAltitude(lowestAltitude);
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
