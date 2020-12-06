package world.generator.ressourcesProduction;

import islandSet.Isle;
import world.Tile;
import world.TileColor;
import world.World;
import world.generator.Generator;
import world.generator.calculator.TileAttributesCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


public class RessourceGenerator  implements Generator {

    public static final int MAX = 255;
    private final TileColor color = TileColor.CHESTNUT;

    private TileAttributesCalculator calculator = new TileAttributesCalculator();

    @Override
    public void generate(World world) {

        for(Isle isle:world.getIsleList() ){
            List<Tile> tileList = isle.getVegetationTiles();
            int nb = tileList.size();

            TreeMap<Double, List<Tile>> tileSurfaceList = orderIslesBasedOnSurface( tileList );
            TreeMap<Double, List<Tile>> steepEachTile = orderIslesBasedOnSteep(tileSurfaceList);
            applyRichiness( steepEachTile, nb);

        }
        world.reInitiliseTileColor();
    }

    public void applyRichiness( TreeMap<Double, List<Tile>> tileSurfaceList, int nbTiles){

        float ecartEachTile =  255f / nbTiles;
        float richiness;

        for(List<Tile> list:tileSurfaceList.values()){
            for(Tile tile:list ){
                richiness = ecartEachTile;
                tile.setRichiness(richiness);

            }
            ++ ecartEachTile;
        }
    }

    public TreeMap<Double, List<Tile>> orderIslesBasedOnSurface( List<Tile> tileList ){


        double surface;


        TreeMap<Double, List<Tile>> surfaceEachTile = new TreeMap<>();
        for(Tile tile:tileList){
            tile.setBackgroundColor(color);
            surface = calculator.findTileSurface(tile);

            addTile(surfaceEachTile, surface, tile);
        }
        return surfaceEachTile;
    }

    public TreeMap<Double, List<Tile>> orderIslesBasedOnSteep( TreeMap<Double, List<Tile>> surfaceEachTile ){
        TreeMap<Double, List<Tile>> steepEachTile = new TreeMap<>();
        double tileSteep;
        for(List<Tile> tileList:surfaceEachTile.values()){
            for(Tile tile:tileList){
                tileSteep = calculator.findTileSteep(tile);
                addTile(steepEachTile, tileSteep, tile);
            }
        }
        return steepEachTile;
    }

    public void addTile(TreeMap<Double, List<Tile>> surfaceEachTile, double surface, Tile tile){

        if (surfaceEachTile.containsKey(surface)) {
            surfaceEachTile.get(surface).add(tile);

        } else {
            List<Tile> tmp = new ArrayList<>();
            tmp.add(tile);
            surfaceEachTile.put(surface, tmp);
        }
    }



}
