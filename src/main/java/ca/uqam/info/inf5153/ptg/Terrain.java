package ca.uqam.info.inf5153.ptg;

import ca.uqam.ace.inf5153.mesh.io.Structs;

public abstract class Terrain {
    Structs.Point center;

    abstract public boolean isTileOnTerrain(Tile t);
}
