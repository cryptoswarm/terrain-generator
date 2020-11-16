package World.Generator.Aquifer;

import Geometry.Coordinate;
import World.Tile;
import World.World;
import World.TileColor;

import java.util.HashMap;

import static World.TileColor.*;

public class River extends Aquifer {
    private Tile tile;
    private HashMap<Coordinate, Tile> river = new HashMap<>();
    final private TileColor riverColor = WATERBLUE;
    final private TileColor riverSideColor = BROWN;

    public River(Tile tile) {
        this.tile = tile;
    }

    @Override
    public HashMap<Coordinate, Tile> getTiles() {
        return null;
    }

    @Override
    public void apply(World w) {
        river.put(tile.getCenter(), tile);
        double riverHeight = tile.getAltitude();
        Tile tmp = tile;

        do {
            for(Tile i : tile.getNeighbors().values()) {
                if(i.getAltitude() < riverHeight) {
                    riverHeight = i.getAltitude();
                    tmp = i;
                }
            }
            if(tile == tmp) break;
            if(tile != tmp) {
                tile = tmp;
                river.put(tile.getCenter(), tile);
            }
        } while (true);

        for(Tile i: river.values()) {
            i.setBackgroundColor(riverSideColor);
            i.setHumidityLevel(5);
        }
        this.applyHumidityEffect(w,river);
    }
}
