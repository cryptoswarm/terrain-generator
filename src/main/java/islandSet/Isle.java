package islandSet;

import world.Tile;

import java.util.ArrayList;
import java.util.List;

public class Isle {

    private List<Tile> islandTiles;

    public Isle(List<Tile> islandTiles) {
        this.islandTiles = islandTiles;
    }

    public List<Tile> getIslandTiles() {
        return islandTiles;
    }

    public List<Tile> getVegetationTiles(){
        List<Tile > tileList = new ArrayList<>();
        for(Tile tile:islandTiles){
            if(tile.getBiome().getType().equals("vegetation") ){
                tileList.add(tile);
            }
        }
        return tileList;
    }


}
