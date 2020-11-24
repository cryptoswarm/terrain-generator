package World.Generator.Aquifer;

import Geometry.Coordinate;
import Geometry.Line;
import World.Tile;
import World.World;
import World.TileColor;
import World.soilType;
import java.util.HashSet;
import static World.TileColor.*;

public class River extends Aquifer {

    private Coordinate coordinate;
    final private soilType soil;
    final private HashSet<Line> river = new HashSet<>();
    final private TileColor riverColor = WATERBLUE;

    public River(Coordinate c, soilType soil) {
        this.coordinate = c;
        this.soil = soil;
    }

    @Override
    public void apply(World w) {
        double riverHeight = coordinate.getZ();
        Coordinate tmpC = coordinate;
        boolean b = true;
        while (b){
            Line tmpL = null;
            for( Line i : w.getLine(coordinate) ) { // a hash set of lines
                Coordinate c1 = i.getC1();
                Coordinate c2 = i.getC2();
                if(c1.getZ() < riverHeight) {
                    riverHeight = c1.getZ();
                    tmpC = c1;
                    tmpL = i;
                }
                if(c2.getZ() < riverHeight) {
                    riverHeight = c2.getZ();
                    tmpC = c2;
                    tmpL = i;
                }
            }
            if(coordinate == tmpC) break;
            coordinate = tmpC;
            river.add(tmpL);
            for(Tile tile: w.getNeighbor(coordinate)){
                String s = tile.getBiome().getType();
                if(s.equals("ocean") || s.equals("lagoon")|| s.equals("plage")) {
                    b = false;
                }
            }
        }

        HashSet<Tile> wetZone = new HashSet<>();
        for(Line i: river) {
            wetZone.addAll(w.getNeighbor(i));
            i.setColor(riverColor);
            i.increaseFlow();
        }


        this.applyHumidityEffect(w,wetZone, soil);
    }
}
