package World.Generator.Island;

import Geometry.Coordinate;
import Geometry.Line;
import RandomStrategy.RandomContexte;
import World.Tile;
import World.World;

import java.util.List;

public abstract class IslandShape {


    abstract boolean createIsland(World world, RandomContexte random, int maxAltitude, Coordinate border);

    /**
     *
     * @param islandTiles les tuiles composant la nouvelle ile
     * @param h      La hauteur du monde
     * @param w      la largeur du monde
     * @return       True si l'ile est valide, False sinon
     *
     */

    public boolean validIsland(List<Tile> islandTiles, int h, int w) {
        boolean valid = true;
        boolean validAlt;
        boolean validLines;
        boolean validBiom;


        for (Tile tile : islandTiles) {  //get all tiles belonging to the new island

            validBiom = tile.isInOcean();  //if it other than ocean then it belongs already to another island
            validAlt = (tile.getAltitude() == 0);  // may be redundant but we will leave it for now
            validLines = validateLines(tile, h, w);  // based on the dimension of the new island, we check if all tiles are inside world

            if (!validAlt || !validLines || !validBiom ){
                valid = false;
                break;
            }
        }
        return valid;
    }

    /**
     *
     * @param tile  la tuile qu'on veut vérifier si elle est à l'interieur du monde
     * @param h     La hauteur du monde
     * @param w     la largeur du monde
     * @return      True si la tuile est à l'interieur, false sinon
     */
    public boolean validateLines(Tile tile, int h, int w ){
        boolean valid = true;

        for (Line l : tile.getBorder()) {
            if (l.getC1().getY() == 0 || l.getC1().getY() == h) {
                valid = false;
            }
            if (l.getC1().getX() == 0 || l.getC1().getX() == w) {
                valid = false;
            }
            if (l.getC2().getY() == 0 || l.getC2().getY() == h) {
                valid = false;
            }
            if (l.getC2().getX() == 0 || l.getC2().getX() == w) {
                valid = false;
            }
        }
        return valid;
    }

}
