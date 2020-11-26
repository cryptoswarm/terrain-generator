package world.mode;

import world.Tile;

public abstract class Mode {

     abstract public String getColor(int R, int G, int B, int A, int factor);

     abstract public int getFactor(Tile tile);

     public int applyFactor(int c, int factor){

          if (factor > 0) return Math.min(70 + factor, 255);
          return c;

     }
}
