package world.generator.interestPoints;

import islandSet.Isle;
import world.Tile;
import world.World;
import world.generator.Generator;
import java.util.ArrayList;

public class RoadGenerator implements Generator {


    @Override
    public void generate(World w) {
        ArrayList<ArrayList<Tile>> interestPointsListGlobal = getInterestPoints(w);

        generateRoads(interestPointsListGlobal, w);

        ArrayList<Tile> ports = getPorts(interestPointsListGlobal);

        if(ports.size() >= 2){
            generateWaterWays(ports, w);
        }
    }

    private void generateWaterWays(ArrayList<Tile> ports, World w) {

        Tile [] path = new Tile [ports.size()];

        for(int i = 0; i < path.length; i++){
            path[i] = ports.get(i);
        }

        sortPath(path);

        for(int i = 0; i < path.length; i ++){

            ArrayList<Tile> smallPath = findSmallPathWater(path[i], path[(i+1) % path.length], w);

            if(smallPath != null){
                addSmallPath(smallPath, w);
            }
        }

    }

    private ArrayList<Tile> findSmallPathWater(Tile start, Tile next, World w) {

        boolean hasNext = true;
        Tile current = start;
        ArrayList<Tile> path = new ArrayList<>();

        while(!current.equals(next) && hasNext){
            path.add(current);
            float distance = current.getCenter().distance(next.getCenter());
            Tile maybeNext = null;
            hasNext = false;
            for(Tile nTile : w.getNeighbor(current)){

                if(nTile.getItem().getType().equals("ocean")){
                    float newDistance = nTile.getCenter().distance(next.getCenter());
                    if(distance > newDistance){
                        maybeNext = nTile;
                        distance = newDistance;
                        hasNext = true;
                    }
                }else if (nTile.getItem().getType().equals("beach")){
                    if (nTile.equals(next)){
                        maybeNext = nTile;
                        distance = 0;
                        hasNext = true;

                    }
                }

            }

            if(hasNext){
                current = maybeNext;
            }
        }

        if(current.equals(next)){

            path.add(next);
            return path;
        }

        return null;

    }

    private ArrayList<Tile> getPorts(ArrayList<ArrayList<Tile>> interestPointsListGlobal) {

        ArrayList<Tile> ports = new ArrayList<>();

        for(ArrayList<Tile> tilesList: interestPointsListGlobal){
            for(Tile tile: tilesList){
                if(tile.getPois() == InterestPointsGenerator.POIS.PORTS){
                    ports.add(tile);
                }
            }
        }

        return ports;

    }

    private void generateRoads(ArrayList<ArrayList<Tile>> interestPointsListGlobal, World w) {

        for(ArrayList<Tile> interestPointList : interestPointsListGlobal){

            findIslandPaths(interestPointList, w);

        }
    }

    private void findIslandPaths(ArrayList<Tile> interestPointList, World w) {
            normalPath(interestPointList, w);

    }

    private void normalPath(ArrayList<Tile> interestPointList, World w) {

        Tile current, next;
        Tile [] path = new Tile [interestPointList.size()];

        for(int i = 0; i < path.length; i++){
            current = interestPointList.get(i);
            path[i] = current;
        }

        sortPath(path);

        for(int i = 0; i < path.length - 1; i ++){

            current = path[i];
            next = path[i+1];
            ArrayList<Tile> smallPath = findSmallPath(current, next, w);
            if(smallPath != null){
                addSmallPath(smallPath, w);
            }
        }

    }

    private void addSmallPath(ArrayList<Tile> smallPath, World w) {


        for(int i = 0; i < smallPath.size() - 1; i++){
            w.setRoads(smallPath.get(i), smallPath.get(i+1));
        }
    }

    private ArrayList<Tile> findSmallPath(Tile start, Tile next, World w) {

        boolean hasNext = true;
        Tile current = start;
        ArrayList<Tile> path = new ArrayList<>();

        while(!current.equals(next) && hasNext){
            path.add(current);
            float distance = current.getCenter().distance(next.getCenter());
            Tile maybeNext = null;
            hasNext = false;
            for(Tile nTile : w.getNeighbor(current)){

                if(!nTile.getItem().getType().equals("lake") && !nTile.getItem().getType().equals("lagoon")
                        && !nTile.getItem().getType().equals("ocean")){
                    float newDistance = nTile.getCenter().distance(next.getCenter());
                    if(distance > newDistance){
                        maybeNext = nTile;
                        distance = newDistance;
                        hasNext = true;
                    }
                }

            }

            if(hasNext){
                current = maybeNext;
            }
        }

        if(current.equals(next)){

            path.add(next);
            return path;
        }

        return null;
    }

    private void sortPath(Tile [] path){

        Tile current, next, maybeNext, temp;

        for(int i = 0; i < path.length - 1; i++){
            current = path[i];
            next = path[i + 1];
            int jNext = i + 1;
            float smallestDistance = current.getCenter().distance(next.getCenter());
            for(int j = i + 2; j < path.length; j++){
                maybeNext = path[j];
                float maybeSmallestDistance = current.getCenter().distance(maybeNext.getCenter());
                if(maybeSmallestDistance < smallestDistance){
                    next = maybeNext;
                    smallestDistance = maybeSmallestDistance;
                    jNext = j;
                }
            }
            temp = path[i + 1];
            path[i + 1] = next;
            path[jNext] = temp;
        }
    }

    private ArrayList<ArrayList<Tile>> getInterestPoints(World w) {

        ArrayList<ArrayList<Tile>> interestPointsListGlobal = new ArrayList<>();

        for(Isle isle: w.getIsleList()){
            ArrayList<Tile> interestPointsList = new ArrayList<>();

            for (Tile tile : isle.getIslandTiles()){

                if(tile.getPois() != InterestPointsGenerator.POIS.NOTHING){
                    interestPointsList.add(tile);
                }
            }

            interestPointsListGlobal.add(interestPointsList);
        }
        return interestPointsListGlobal;
    }
}
