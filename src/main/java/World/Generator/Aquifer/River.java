package World.Generator.Aquifer;

import World.Generator.Biome.Vegetation;
import World.Tile;
import World.World;
import World.TileColor;

import java.util.HashSet;

import static World.TileColor.*;

public class River extends Aquifer {
    private Tile tile;
    private HashSet<Tile> river = new HashSet<>();
    final private TileColor riverColor = WATERBLUE;
    final private TileColor riverSideColor = BROWN;

    public River(Tile tile) {
        this.tile = tile;
    }

    @Override
    public HashSet<Tile> getTiles() {
        return null;
    }

    @Override
    public void apply(World w) {
        river.add(tile);
        double riverHeight = tile.getAltitude();
        Tile tmp = tile;

        do {
            for(Tile i : tile.getNeighbors()) {
                if(i.getAltitude() < riverHeight) {
                    riverHeight = i.getAltitude();
                    tmp = i;
                }
            }
            if(tile == tmp) break;
            if(tile != tmp) {
                tile = tmp;
                river.add(tile);
            }
        } while (true); //how do I do that -__-

        for(Tile i: river) {
            i.setBackgroundColor(riverSideColor);
            i.setHumidityLevel(5);
        }
        this.applyHumidityEffect(w,river);
    }
}
