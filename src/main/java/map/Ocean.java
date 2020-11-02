package map;

import geometrie.Coordinate;

import java.util.HashMap;

public class Ocean implements Biome {
    private HashMap<Coordinate, Tile> ocean;
    final private TileColor color = TileColor.OCEANBLUE;

    public Ocean(){
        this.ocean = new HashMap<>();
    }

    /**
     *  Sauvergader les tuiles composant le biome ocean
     * @param tile une tuile
     */
    public void addToBiome(Tile tile){
        ocean.put(tile.getCenter(), tile);
        tile.setBackgroundColor(color);
    }

    /**
     * @return  les  tuiles composant les biome ocean
     */
    public HashMap<Coordinate, Tile> getTiles() {
        return ocean;
    }

}
