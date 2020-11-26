package world.generator.aquifer;

import geometry.Coordinate;
import geometry.Line;
import world.Tile;
import world.TileColor;
import world.World;
import world.soilType;

import java.util.HashSet;

public class River extends Aquifer {

    private static final String OCEAN = "ocean";
    private static final String LAGOON = "lagoon";
    private static final String PLAGE = "plage";

    private Coordinate coordinate;
    final private soilType soil;
    final private HashSet<Line> river = new HashSet<>();
    final private TileColor riverColor = TileColor.WATERBLUE;

    public River(Coordinate c, soilType soil) {
        this.coordinate = c;
        this.soil = soil;
    }

    @Override
    public void apply(World world) {

        findRiverPath( world, coordinate, river );
        HashSet<Tile> wetZone = applyRiverEffects( world );
        applyHumidityToAffectedTilesByRiver( world, wetZone );

    }

    /**
     *
     * @param world  l'objet world permettant d'acceder aux methodes publiques
     * @param coordinate  une coordnnée generee aleatoirement
     * @param river une liste contenant les lignes qui composent la riviere
     */

    private void findRiverPath(World world, Coordinate coordinate, HashSet<Line> river ) {

        double riverHeight = coordinate.getZ();
        Coordinate coordinateStart = coordinate;
        Coordinate tmpC = coordinate;

        Line tmpL = null;
        for (Line i : world.getLine(coordinate)) {
            Coordinate c1 = i.getC1();
            Coordinate c2 = i.getC2();
            if (c1.getZ() < riverHeight) {
                riverHeight = c1.getZ();
                tmpC = c1;
                tmpL = i;
            }
            if (c2.getZ() < riverHeight) {
                riverHeight = c2.getZ();
                tmpC = c2;
                tmpL = i;
            }
        }

        coordinate = tmpC;
        if(tmpL != null) {
            river.add(tmpL);
        }

        if (!isRiverEnded(world ) && !coordinate.equals(coordinateStart ) ) {
            findRiverPath( world , coordinate, river);
        }
    }

    /**
     * on fait couler la riviere jusqu'elle atteigne une sources d'eau
     * @param w  l'objet world permettant d'acceder aux methodes publiques
     * @return True si la riviere atteint une source d'eau
     */
    private boolean isRiverEnded( World w ){
        boolean end = false;
        for(Tile tile: w.getNeighbor(coordinate)){
            String s = tile.getBiome().getType();
            if(s.equals(OCEAN) || s.equals(LAGOON)|| s.equals(PLAGE)) {
                end = true;
            }
        }
        return end;
    }

    /**
     *  Appliquer la couleur sur les lignes composant la riviere
     *  Augmneter le flow
     *  Trouver les tuiles qui sont a cote de la riviere
     *
     * @param world l'objet world permettant d'acceder aux methodes publiques
     * @return
     */
    private HashSet<Tile> applyRiverEffects(World world){

        HashSet<Tile> wetZone = new HashSet<>();
        for(Line i: river) {
            wetZone.addAll( world.getNeighbor(i) );
            i.setColor(riverColor);
            i.increaseFlow();
        }
        return wetZone;
    }

    /**
     * On applique un facteur representant l'humidité
     * @param world
     * @param wetZone  les tuiles adjacentes à la riviere
     */
    private void applyHumidityToAffectedTilesByRiver( World world, HashSet<Tile> wetZone ){
        this.applyHumidityEffect(world,wetZone, soil);
    }
}