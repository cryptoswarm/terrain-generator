package map;

import geometrie.Dot;

import java.util.HashMap;

public class Lagoon implements Biome {

    private HashMap<Dot, Tile> lagoon;
    private TileColor color = TileColor.WATERBLUE;

    public Lagoon() {
        this.lagoon = new HashMap<>();
    }


    @Override
    public HashMap<Dot, Tile> getTiles() {
        return lagoon;
    }

    @Override
    public void addToBiome(Tile tile) {
        lagoon.put(tile.getTileCenter(), tile);
        tile.setBackgroundColor(color);
    }

    public boolean isNeighbor(Tile tile){
        boolean isPresent = false;
        for(HashMap.Entry<Dot, Tile> entry:lagoon.entrySet() ) {
            //Dot center = entry.getKey();
            Tile b = entry.getValue();
            for(Dot val : b.getNeighborPseudoCenters())
            {
                if(val.equals(tile.getTileCenter())){
                    isPresent = true;
                    break;
                }
            }
            if(isPresent) {
                break;
            }
        }
        return isPresent;
    }
}
