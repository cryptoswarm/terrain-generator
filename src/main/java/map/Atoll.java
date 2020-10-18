package map;

import geometrie.Dot;

public class Atoll { //extends Terrain
    double bRadius;
    double sRadius;
    //PseudoPoint terrainCenter;
    Dot terrainCenter;
    public Atoll(double b, double s,  Dot terrainCenter) {
        this.bRadius = b;
        this.sRadius = s;
        this.terrainCenter = terrainCenter;
    }
/*
    public boolean isTileOnTerrain(Tile t) {
        Dot tileCenter = t.getTilePseudoCenter();
        return (terrainCenter.distance(tileCenter) < radius);
    }
  */

}
