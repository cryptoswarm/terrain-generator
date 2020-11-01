package map;

import geometrie.Dot;

import java.util.HashMap;

public class Ocean implements Biome {
    private HashMap<Dot, Tile> ocean;
    private TileColor color = TileColor.OCEANBLUE;

    public Ocean(){
        this.ocean = new HashMap<>();
    }

    /**
     *  Sauvergader les tuiles composant le biome ocean
     * @param tile une tuile
     */
    public void addToBiome(Tile tile){
        ocean.put(tile.getTileCenter(), tile);
        tile.setBackgroundColor(color);
    }

    /**
     * @return  les  tuiles composant les biome ocean
     */
    public HashMap<Dot, Tile> getTiles() {
        return ocean;
    }

}
