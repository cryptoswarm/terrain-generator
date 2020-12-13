package world.generator.ressourcesProduction;

import islandSet.Isle;
import world.Tile;
import world.World;
import world.generator.Generator;
import world.generator.calculator.TileAttributesCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class RessourceGenerator  implements Generator {

    private TileAttributesCalculator calculator = new TileAttributesCalculator();

    @Override
    public void generate(World world) {

        for(Isle isle:world.getIsleList() ){
            List<Tile> tileList = isle.getVegetationTiles();
            int nb = tileList.size();

            Map<Double, List<Tile>> tileSurfaceList = orderIslesBasedOnSurface( tileList );
            Map<Double, List<Tile>> steepEachTile = orderIslesBasedOnSteep(tileSurfaceList);
            applyRichiness( steepEachTile, nb);

        }
    }

    public void applyRichiness( Map<Double, List<Tile>> tileSurfaceList, int nbTiles){

        float ecartEachTile =  255f / nbTiles + 1;
        float richiness;

        for(List<Tile> list:tileSurfaceList.values()){
            for(Tile tile:list ){
                richiness = ecartEachTile;
                tile.setRichness(richiness);

            }
            ++ ecartEachTile;
        }
    }

    public Map<Double, List<Tile>> orderIslesBasedOnSurface( List<Tile> tileList ){


        double surface;


        Map<Double, List<Tile>> surfaceEachTile = new TreeMap<>();
        for(Tile tile:tileList){
            surface = calculator.findTileSurface(tile);

            addTile(surfaceEachTile, surface, tile);
        }
        return surfaceEachTile;
    }

    public Map<Double, List<Tile>> orderIslesBasedOnSteep( Map<Double, List<Tile>> surfaceEachTile ){

        Map<Double, List<Tile>> steepEachTile = new TreeMap<>();
        double tileSteep;
        for(List<Tile> tileList:surfaceEachTile.values()){
            for(Tile tile:tileList){
                tileSteep = calculator.findTileSteep(tile);
                addTile(steepEachTile, tileSteep, tile);
            }
        }
        return steepEachTile;
    }

    public void addTile( Map<Double, List<Tile>> surfaceEachTile, double surface, Tile tile){

        if (surfaceEachTile.containsKey(surface)) {
            surfaceEachTile.get(surface).add(tile);

        } else {
            List<Tile> tmp = new ArrayList<>();
            tmp.add(tile);
            surfaceEachTile.put(surface, tmp);
        }
    }



}
