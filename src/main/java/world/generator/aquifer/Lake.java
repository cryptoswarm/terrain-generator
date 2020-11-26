package world.generator.aquifer;

import world.TileColor;
import world.Tile;
import world.World;
import world.soilType;
import java.util.HashSet;

import static world.TileColor.WATERBLUE;

public class Lake extends Aquifer {
    final private Tile tile;
    final private HashSet<Tile> lake = new HashSet<>();
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
        lake.add(tile);

        for(Tile i : w.getNeighbor(tile)) {
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
        }
        this.applyHumidityEffect(w,lake,soil);
    }
}
