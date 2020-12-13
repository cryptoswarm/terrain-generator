package world.generator.interestPoints;

import world.Tile;
import world.World;
import world.generator.Generator;

import java.util.ArrayList;
import java.util.List;

public class RoadGenerator implements Generator {


    public static final String OCEAN_BIOME = "ocean";
    public static final String BEACH_BIOME = "beach";
    public static final String LAKE_BIOME = "lake";
    public static final String LAGOON_BIOME = "lagoon";

    @Override
    public void generate(World w) {

        List<ArrayList<Tile>> interestPointsListGlobal = w.getWorldinterestPoints();
        generateRoads(interestPointsListGlobal, w);

        List<Tile> ports = w.getIslePortInterestPoint();

        if(ports.size() >= 2){
            generateWaterWays(ports, w);
        }
    }

    /**
     *
     * Créer un chemin reliant deux iles d'un port à l'autre
     *
     * @param ports ensemble de tuiles abritant un port
     * @param w objet world
     */

    private void generateWaterWays( List<Tile> ports, World w) {

        Tile [] path = ports.toArray(new Tile [0]);

        sortPath(path);

        for(int i = 0; i < path.length; i ++){

            List<Tile> smallPath = findSmallPathWater(path[i], path[(i+1) % path.length], w);

            if(smallPath != null){
                addSmallPath(smallPath, w);
            }
        }

    }

    /**
     * A prtir d'une tuile de debut BEACH_BIOM et la suivante OCEAN_BIOME
     * On essaie de trouver une voisine au suivante dont la distance entre la voisine et celle de debut
     * est plus courte que celle entre celle de debut et la suivante
     *
     * @param start La tuile au debut du chemin
     * @param next la suivante
     * @param w  l'objet world
     * @return  deux tuiles
     */

    private List<Tile> findSmallPathWater(Tile start, Tile next, World w) {

        boolean hasNext = true;
        Tile current = start;

        List<Tile> path = new ArrayList<>();

        while(!current.equals(next) && hasNext){
            path.add(current);
            float distance = current.getCenter().distance(next.getCenter());
            Tile maybeNext = null;
            hasNext = false;
            for(Tile nTile : w.getNeighbor(current)){

                if(nTile.getItem().getType().equals(OCEAN_BIOME)){
                    float newDistance = nTile.getCenter().distance(next.getCenter());
                    if(distance > newDistance){
                        maybeNext = nTile;
                        distance = newDistance;
                        hasNext = true;
                    }
                }else if (nTile.getItem().getType().equals(BEACH_BIOME)){
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

    /**
     * Constituer un chemin reliant les tuiles entre eux dans une meme ile
     * Repeter l'operation pour l'ensemble des iles
     *
     * @param interestPointsListGlobal ensemble d'ensemble de tuiles
     * @param w objet world
     */

    private void generateRoads(List<ArrayList<Tile>> interestPointsListGlobal, World w) {

        for(ArrayList<Tile> interestPointList : interestPointsListGlobal){

            normalPath(interestPointList, w);

        }
    }

    /**
     * Constituer un chemin reliant les tuiles entre eux dans une meme ile
     * @param interestPointList  ensemble de tuiles
     * @param w objet world
     */

    private void normalPath(ArrayList<Tile> interestPointList, World w) {

        Tile current, next;
        Tile [] path = interestPointList.toArray(new Tile [0]);

        sortPath(path);

        for(int i = 0; i < path.length - 1; i ++){

            current = path[i];
            next = path[i+1];
            List<Tile> smallPath = findSmallPath(current, next, w);
            if(smallPath != null){
                addSmallPath(smallPath, w);
            }
        }

    }

    /**
     *
     * Ajouter un segment reliant le point centre de la tuile du debut avec celui de la fin
     * @param smallPath ensemble de deux tuiles, debut et fin
     * @param w objet world
     */

    private void addSmallPath(List<Tile> smallPath, World w) {


        for(int i = 0; i < smallPath.size() - 1; i++){
            w.setRoads(smallPath.get(i), smallPath.get(i+1));
        }
    }

    /**
     * A prtir d'une tuile de debut et la suivante
     * On essaie de trouver une voisine au suivante dont la distance entre la voisine et celle de debut
     * est plus courte que celle entre celle de debut et la suivante
     *
     * @param start La tuile au debut du chemin
     * @param next la suivante
     * @param w  l'objet world
     * @return  deux tuiles
     */

    private List<Tile> findSmallPath(Tile start, Tile next, World w) {

        boolean hasNext = true;
        Tile current = start;
        List<Tile> path = new ArrayList<>();

        while(!current.equals(next) && hasNext){
            path.add(current);
            float distance = current.getCenter().distance(next.getCenter());
            Tile maybeNext = null;
            hasNext = false;
            for(Tile nTile : w.getNeighbor(current)){

                if(!nTile.getItem().getType().equals(LAKE_BIOME) && !nTile.getItem().getType().equals(LAGOON_BIOME)
                        && !nTile.getItem().getType().equals(OCEAN_BIOME)){
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

    /**
     * Ordonner les tuiles en fonction de la distance entre eux
     * Ici la clef est la plus courte distance.
     *
     * @param path, un ensemble de tuile
     */

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

}
