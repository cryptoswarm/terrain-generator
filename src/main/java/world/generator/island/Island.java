package world.generator.island;

import geometry.Coordinate;
import world.Tile;
import world.World;
import world.generator.WorldProcessor;

import java.util.List;

public abstract class Island  implements WorldProcessor {

    public static final int INVALIDE_ALT = -1;

    /**
     * Le profil altimetrique prend en conséderation la forme de l'ile et l'altitude maximale
     * Example: Pour une ile circulaire, ce profil n'est appliqué que dans le biome vegetation
     *          L'altitude augmente autant qu'on s'eloigne de l'ocean ensuite elle diminue jusqu'on atteigne le lagon
     *          La diminution de l'altitude d'une tuile à l'autre est controllée.
     * @param world Objet world permettant d'acceder au methode publiques.
     * @param maxAltitude l'altitude maximale donnée à l'ile
     */

    public abstract void defineAltitude(World world,  int maxAltitude );

    /**
     * En se basant sur la position d'une tuile par rapport au centre de l'ile, on assigne une boolean identifiant le biome
     * auquel la tuile appartient
     *
     * @param world Objet world permettant d'acceder au methode publiques.
     */
    public abstract void setBorders( World world );

    /**
     * Le coin de la tuile le plus lointain du centre de la tuile
     * par rapport au centre de l'ile aura une altitude
     * plus petite que celle donnée au coin de la tuile le plus proche au centre de l'ile.
     *
     * la diffrence d'altitude entre un coin et l'autre est le resultat
     *  de la division de l'altitude moyenne par le nombre de coins.
     *
     * @param tile La tuile auquelle on veut appliquer l'altitude à ses coins
     * @param currentAlt  l'altitude donnée à la tuile
     * @param centerOfShape  la coordonnée du centre de l'ile
     */


    public void applyAltitudeToTileCorners(Tile tile, double currentAlt, Coordinate centerOfShape, float diffrenceAltEachtile, List<Coordinate> uniqeCoordinates) {

        double distance = tile.getCenter().distance(centerOfShape);
        int nbCorners = tile.getCorner().size();
        float diffrenceAltEachCorner = diffrenceAltEachtile / nbCorners;

        for (Coordinate c : tile.getCorner()) { //

            //if(!uniqeCoordinates.containsKey(c)) {
            if(!uniqeCoordinates.contains(c) ) {

                double dist = c.distance(centerOfShape);

                if (c.getZ() == INVALIDE_ALT) { //Sert à verifier si l'altitude de la coordonné est deja appliqué //c.getZ() == INVALIDE_ALT
                    //lorsqu'on a appliquer l'altitude d'une autre tuile.
                    if (dist > distance) {

                        c.setZ((float) currentAlt - diffrenceAltEachCorner);
                    } else if (dist < distance) {

                        c.setZ((float) currentAlt + diffrenceAltEachCorner);
                    } else {
                        c.setZ((float) currentAlt);
                    }
                }
            }else {

                for(Coordinate coordinate:uniqeCoordinates){
                    if(c.equals(coordinate)){
                        c.setZ(coordinate.getZ());
                        break;
                    }
                }
                System.out.println("alt of coordinates already set ");
            }
        }
    }








}
