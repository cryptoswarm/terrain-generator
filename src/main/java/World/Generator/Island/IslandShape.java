package World.Generator.Island;

import Geometry.Coordinate;
import RandomStrategy.RandomContexte;
import World.Tile;

import java.util.Random;

public interface IslandShape {
    /**
     *
     * @param random  seed
     * @param maxAltitude  altitude maximale
     * @param x  coordonné sur l'axe des abscice
     * @param y  coordonné sur l'axe des ordonnées
     * @return True si l'ile est crée, False sinom
     */
    boolean createIsland(RandomContexte random, int maxAltitude, Coordinate coordinate);

    /**
     *
     * @param tile une tuile
     * @param tileCenter la tuile au centre de l'ile
     * @param diametre le diametre de l'ile
     * @param angle l'angle de rotation de l'ile
     * @return True si la tuile est à l'interieur de la nouvelle ile
     */
    boolean inArea(Tile tile, Tile tileCenter, int diametre, int angle );

    /**
     * La tuile à générée doit respecter deux conditions
     * la premiere : cette tuile doit etre à l'interieur de l'espace definit par x et y
     * la deuxieme : il doit avoir suffisament de tuiles autour d'elle pour pouvoir crée une ile
     *
     * @param x   coordonné sur l'axe des abscice
     * @param y   coordonné sur l'axe des ordonnées
     * @param angle  angle de rotation
     * @return  une tuile qui va etre le centre de la nouvelle ile
     */
    Tile findRandomTile(int angle, Coordinate coordinate);

    /**
     *
     * @param tile une tuile
     * @param x coordonné sur l'axe des abscice
     * @param y coordonné sur l'axe des ordonnées
     * @return True si la tuile est à l'interieur de l'espace, False sinon
     */
    boolean checkTilePosition(Tile tile, Coordinate coordinate);

    /**
     *
     * @param tileCenter tuile au centre de l'ile
     * @param diametre le diametre de l'ile
     * @param angle l'angle de rotation
     * @return True si la tuile est à l'interieur de l'espace; cet espace depend de la forme que l'ile va avoir
     */
    boolean checkBorders(Tile tileCenter, int diametre, int angle);



}
