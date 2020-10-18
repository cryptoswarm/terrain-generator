package map;

import geometrie.Dot;

public abstract class Terrain {
    //Structs.Point center;
    Dot center;


    abstract public boolean isTileOnTerrain(Tile t);
}
