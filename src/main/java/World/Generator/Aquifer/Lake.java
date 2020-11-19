package World.Generator.Aquifer;

import Geometry.Coordinate;
import World.TileColor;
import World.Tile;
import World.World;
import World.soilType;
import java.util.HashMap;
import static World.TileColor.WATERBLUE;

public class Lake extends Aquifer {
    private Tile tile;
    private HashMap<Coordinate, Tile> lake = new HashMap<>();
    final private TileColor color = WATERBLUE;
    final private soilType soil;

    public Lake(Tile tile, soilType soil) {
        this.tile = tile;
        this.soil = soil;
    }

    @Override
    public void apply(World w) {
        double lowestAltitude;
        lowestAltitude = tile.getAltitude();
        lake.put(tile.getCenter(), tile);

        for(Tile i : w.getNeighbor(tile)) {
            if(i.getBiome().getType().equals("vegetation")) {
                lake.put(i.getCenter(), i);
                if(i.getAltitude() < lowestAltitude) {
                    lowestAltitude = i.getAltitude();
                }
            }
        }
        for(Tile i: lake.values()) {
            i.setAltitude(lowestAltitude);
            i.setBackgroundColor(color);
            i.setHumidityLevel(5);
        }
        this.applyHumidityEffect(w,lake,soil);
    }

    @Override
    public HashMap<Coordinate, Tile> getTiles() {
        return lake;
    }
}
