package world.generator.island;

import geometry.Coordinate;
import world.Tile;
import world.World;
import world.generator.WorldProcessor;

public abstract class Island implements WorldProcessor {

    /**
     * Le profil altimetrique prend en conséderation la forme de l'ile et l'altitude maximale
     * Example: Pour une ile circulaire, ce profil n'est appliqué que dans le biome vegetation
     *          L'altitude augmente autant qu'on s'eloigne de l'ocean ensuite elle diminue jusqu'on atteigne le lagon
     *          La diminution de l'altitude d'une tuile à l'autre est controllée.
     * @param world Objet world permettant d'acceder au methode publiques.
     * @param maxAltitude l'altitude maximale donnée à l'ile
     */

    abstract void defineAltitude(World world,  int maxAltitude );

    /**
     * En se basant sur la position d'une tuile par rapport au centre de l'ile, on assigne une boolean identifiant le biome
     * auquel la tuile appartient
     *
     * @param world Objet world permettant d'acceder au methode publiques.
     */
    abstract void setBorders( World world );

    /**
     * Le coin de la tuile le plus lointain du centre de la tuile par rapport au centre de l'ile aura une altitude
     * plus petite que celle donnée au coin le plus proche au centre de l'ile
     *
     * @param tile La tuile auquelle on veut appliquer l'altitude à ses coins
     * @param alt  l'altitude donnée à la tuile
     * @param centerOfShape  la coordonnée du centre de l'ile
     */
    public void applyAltitudeToTileCorners(Tile tile, double alt, Coordinate centerOfShape){
        double distance = Math.abs(tile.getCenter().distance( centerOfShape ));
        for(Coordinate c: tile.getCorner()){
            double dist = c.distance( centerOfShape );
            if( dist > distance){
                if(alt >= ( dist - distance) )
                    c.setZ((float) Math.abs(alt - (dist - distance) ));
                else {
                    c.setZ((float) alt);
                }
            }else if( dist < distance ){
                c.setZ((float) Math.abs(alt + ( Math.abs( dist - distance) ) ));

            }else{
                c.setZ( (float) alt );
            }
        }
    }
}
