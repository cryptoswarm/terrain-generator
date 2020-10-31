package map;

import geometrie.Dot;
import geometrie.Ellipse;

import java.util.HashMap;

public class Tortuga implements IslandType {

    private Vegetation vegetation;
    private Plage plage;
    private Ellipse ellipse;


    public Tortuga(int width, int height){ //Dot center) {

        this.ellipse = new Ellipse(width, height );
        this.vegetation = new Vegetation();
        this.plage = new Plage();
    }

    /**
     *
     * @param tile  la tuile qu'on veuille vérifier s'il elle est a l'interieur de l'ellipce
     * @return
     */
    public boolean isInsideIslande(Tile tile){

        return ellipse.isOutEllipse(tile);
    }

    /**
     *
     * @return la listes des tuiles composant le biom vegetation
     */
    public Vegetation getVegetation() {
        return vegetation;
    }

    /**
     *
     * @return la listes des tuiles composant le biom plage
     */

    public Plage getPlage() {
        return plage;
    }

    @Override
    public HashMap<Dot, Tile> getTiles() {
        return null;
    }
}
