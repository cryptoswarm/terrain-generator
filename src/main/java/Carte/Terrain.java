package Carte;

import ca.uqam.ace.inf5153.mesh.io.Structs;

public abstract class Terrain {
    Structs.Point center;

    abstract public boolean isTileOnTerrain(Tile t);
}
