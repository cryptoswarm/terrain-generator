package map;

import geometrie.Ellipse;

public class Tortuga {

    private Vegetation vegetation;
    private Plage plage;
    private Ellipse ellipse;


    public Tortuga(int width, int height){ //Dot center) {

        this.ellipse = new Ellipse(width, height );
        this.vegetation = new Vegetation();
        this.plage = new Plage();
    }


    public boolean isInsideIslande(Tile tile){

        return ellipse.isOutEllipse(tile);
    }


    public Vegetation getVegetation() {
        return vegetation;
    }

    public Plage getPlage() {
        return plage;
    }
}
