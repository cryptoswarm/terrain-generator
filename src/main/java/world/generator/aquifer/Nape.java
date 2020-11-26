package world.generator.aquifer;


import world.Tile;
import world.TileColor;
import world.World;
import world.soilType;
import java.util.HashSet;

import static world.TileColor.DARKGREEN;

public class Nape extends Aquifer {
    final private Tile tile;
    final private soilType soil;
    final private HashSet<Tile> nape = new HashSet<>();
    final private TileColor color = DARKGREEN;

    public Nape(Tile tile, soilType soil) {
        this.tile = tile;
        this.soil = soil;
    }

    @Override
    public void apply(World w) {
        nape.add(tile);
        for(Tile i : w.getNeighbor(tile)) {
            if(i.getBiome().getType().equals("vegetation")) {
                nape.add(i);
            }
        }
        for(Tile i: nape) i.setBackgroundColor(color);
        this.applyHumidityEffect(w, nape, soil);

    }
}
