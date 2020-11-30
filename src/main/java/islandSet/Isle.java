package islandSet;

import geometry.Coordinate;
import randomStrategy.RandomContexte;
import world.Tile;

import java.util.ArrayList;
import java.util.HashSet;
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



    public Tile findRandomVegetationTile( RandomContexte random){
        Tile tile;
        do {
            tile = findRandomTile(random);
        } while (!(tile.getBiome().getType().equals("vegetation")));
        return tile;
    }

    public Tile findRandomTile(RandomContexte random){
        ArrayList<Tile> tiles = new ArrayList<>(islandTiles);
        return tiles.get(random.getRandomInt(tiles.size()-1));

    }

    public Coordinate findRandomCoordinate(RandomContexte random){

        Tile tile = findRandomVegetationTile(random);

        HashSet<Coordinate> coordinates = new HashSet<>(tile.getCorner());
        ArrayList<Coordinate> c = new ArrayList<>(coordinates);
        return c.get(random.getRandomInt(c.size()-1));
    }


}
