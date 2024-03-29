package world.generator.aquifer;

import geometry.Coordinate;
import geometry.Line;
import islandSet.Isle;
import randomStrategy.RandomContexte;
import world.Tile;
import world.TileColor;
import world.soilType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class River extends Aquifer {


    private soilType soil;
    private Tile aquiferCenter;

    private static final String OCEAN = "ocean";
    private static final String LAGOON = "lagoon";
    private static final String BEACH = "beach";

    private Coordinate coordinate;

    final private Set<Line> river = new HashSet<>();
    final private TileColor riverColor = TileColor.WATERBLUE;
    private  RandomContexte random;

    public River(RandomContexte random) {
        this.random = random;
    }


    public void setAquiferCenter(Tile tile) {
        this.aquiferCenter= tile;
    }

    public void setSoil(soilType soil) {
        this.soil = soil;
    }

    private  Coordinate generateRandomCoordinate(Tile tile){

        Set<Coordinate> coordinates = new HashSet<>(tile.getCorner());
        List<Coordinate> c = new ArrayList<>(coordinates);
        return c.get(random.getRandomInt(c.size()-1));

    }



    @Override
    public void apply(Isle isle) {

        this.coordinate = generateRandomCoordinate(aquiferCenter);
        findRiverPath( isle, coordinate, river);
        Set<Tile> wetZone = applyRiverEffects( isle);
        applyHumidityToAffectedTilesByRiver( isle, wetZone );

    }

    /**
     * @param coordinate  une coordonné générée aléatoirement
     * @param river une liste contenant les lignes qui composent la rivière
     */

    public void findRiverPath(Isle isle, Coordinate coordinate, Set<Line> river ) {

        double riverHeight = coordinate.getZ();
        Coordinate coordinateStart = coordinate;
        Coordinate tmpCoordinate = coordinate;

        Line tempLine = null;
        for (Line line : isle.getLine(coordinate)) {

            Coordinate c1 = line.getC1();
            Coordinate c2 = line.getC2();
            if (c1.getZ() < riverHeight ) {

                riverHeight = c1.getZ();
                tmpCoordinate = c1;
                tempLine = line;
            }

            if (c2.getZ() < riverHeight ) {

                riverHeight = c2.getZ();
                tmpCoordinate = c2;
                tempLine = line;
            }
        }

        coordinate = tmpCoordinate;
        if(tempLine != null) {
            river.add(tempLine);
        }

        if (!isRiverEnded(isle, coordinate )  && !coordinate.equals(coordinateStart ) ){
            findRiverPath( isle , coordinate, river);
        }
    }

    /**
     * on fait couler la rivière jusqu'à une sources d'eau
     * @param isle est l'ile dans laquelle on veut crée une rivière
     * @return True si la rivière atteint une source d'eau
     */
    private boolean isRiverEnded( Isle isle , Coordinate current){
        boolean end = false;
        for(Tile tile: isle.getNeighbor(current)){
            String s = tile.getItem().getType();
            if(s.equals(OCEAN) || s.equals(LAGOON)|| s.equals(BEACH)) {
                end = true;
            }
        }
        return end;
    }

    /**
     *  Appliquer la couleur sur les lignes composant la rivière
     *  Augmenter le flow
     *  Trouver les tuiles qui sont a cote de la rivière
     *
     * @param isle une ile
     * @return
     */
    private Set<Tile> applyRiverEffects(Isle isle){

        Set<Tile> wetZone = new HashSet<>();

        for(Line line: river) {
            wetZone.addAll( isle.getNeighbor(line) );

            line.setColor(riverColor);
            //line.increaseFlow();
        }
        return wetZone;
    }

    /**
     * On applique un facteur représentant l'humidité
     * @param isle est l'ile.
     * @param wetZone  les tuiles adjacentes à la rivière
     */
    private void applyHumidityToAffectedTilesByRiver( Isle isle, Set<Tile> wetZone ){
        this.applyHumidityEffect(isle,wetZone, soil);
    }
}
