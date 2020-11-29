package world.generator.ressourcesProduction;

import geometry.Coordinate;
import islandSet.Isle;
import world.Tile;
import world.TileColor;
import world.World;
import world.generator.Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


public class RessourceGenerator  implements Generator {

    public static final int MAX = 255;
    private final TileColor color = TileColor.CHESTNUT;

    @Override
    public void generate(World world) {

        for(Isle isle:world.getIsleList() ){
            List<Tile> tileList = isle.getVegetationTiles();
            int nb = tileList.size();

            TreeMap<Double, List<Tile>> tileSurfaceList = orderIslesBasedOnSurface( tileList );
            applyRichiness( tileSurfaceList, nb);

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
            surface = findTileSurface(tile);

            addTile(surfaceEachTile, surface, tile);
        }
        return surfaceEachTile;
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

    public void orderIslesBasedOnSteep(){

    }

    public double findTileSurface(Tile tile){

        List<Coordinate> coordinateList = new ArrayList<>(tile.getCorner());
        coordinateList.add(coordinateList.get(0));
        double sum1 = multiplyXByYNextCoordinate(coordinateList);
        double sum2 = multiplyYByXNextCoordinate(coordinateList);
        return  substractAndDivide(sum1, sum2);
    }



    public double multiplyXByYNextCoordinate( List<Coordinate> coordinateList ){
        double sum1 = 0;
        for(int i=0; i<coordinateList.size(); i++){
            for(int j=i+1; j<coordinateList.size(); j++){
                sum1 += coordinateList.get(i).getX() * coordinateList.get(j).getY();
                break;
            }
        }
        return sum1;
    }

    public double multiplyYByXNextCoordinate( List<Coordinate> coordinateList ){
        double sum2 = 0;

        for(int i=0; i<coordinateList.size(); i++){
            for(int j=i+1; j<coordinateList.size(); j++) {
                sum2 += coordinateList.get(i).getY() * coordinateList.get(j).getX();
                break;
            }
        }
        return sum2;
    }

    public double substractAndDivide(double sum1, double sum2){
        return Math.abs(sum1 - sum2) / 2;
    }






}
