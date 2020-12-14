package world.mode;

import world.Tile;



public abstract class Mode {

     public enum Modes{
          Normal,
          Humidity,
          Altitude,
          Ressources
     }



     abstract public String getColor(int R, int G, int B, int A, int factor);

     abstract public int getFactor(Tile tile);

     abstract public Modes getMode();

     /**
      * Nous utilisons la valeur d'un facteur pour determiner la couleur de la tuile
      * En guise d'eviter que la valeur soit plus grande que la limite qui est 255.
      * @param c
      * @param factor pourrait etre l'altitude, l'humidity ou la richesse de la tuile
      * @return une valeur comprise entre 0 et 255
      */
     public int applyFactor(int c, int factor){

          if (factor > 0) return Math.min(70 + factor, 255);
          return c;

     }
}
