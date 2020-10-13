package Carte;

public class Atoll extends Terrain {
    double radius;
    PseudoPoint terrainCenter;

    public Atoll(double radius, PseudoPoint terrainCenter) {
        this.radius = radius;
        this.terrainCenter = terrainCenter;
    }

    public boolean isTileOnTerrain(Tile t) {
        PseudoPoint tileCenter = t.getTilePseudoCenter();
        return (terrainCenter.distance(tileCenter) < radius);
    }
    

}
