package ca.uqam.info.inf5153.ptg;

public class Laguna extends Terrain{
    double radius;
    PseudoPoint terrainCenter;

    public Laguna(double radius, PseudoPoint terrainCenter) {
        this.radius = radius;
        this.terrainCenter = terrainCenter;
    }

    public boolean isTileOnTerrain(Tile t) {
        PseudoPoint tileCenter = t.getTilePseudoCenter();
        return (terrainCenter.distance(tileCenter) < radius);
    }



}
