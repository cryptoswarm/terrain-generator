package ca.uqam.info.inf5153.ptg;

import ca.uqam.ace.inf5153.mesh.io.Structs;

import java.util.HashMap;

public class Map{
    int width;
    int height;
    PseudoPoint perfectCenter;
    HashMap<PseudoPoint, Tile> tiles;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.perfectCenter = new PseudoPoint((float) (width / 2.0), (float) (height / 2.0));
        this.tiles = new HashMap<>();
    }
    
    public void addTile(Tile tile) {
        PseudoPoint tileCenter = new PseudoPoint(tile.center);
        tiles.put(tileCenter,tile);
    }
    
    public void createAtoll() {
        // beurk
        
        double shortestSide = Math.min(width, height);
        Laguna laguna = new Laguna(shortestSide*.2, perfectCenter);
        Atoll atoll = new Atoll(shortestSide*.4, perfectCenter);
        
        for (Tile t: tiles.values()) {
            if (laguna.isTileOnTerrain(t)) {
                boolean isBeach = false;
                for (PseudoPoint neighbor: t.getNeighborPseudoCenters()) {
                    if (!laguna.isTileOnTerrain(tiles.get(neighbor))) {
                        isBeach = true;
                    }
                }
                if (isBeach) {
                    t.setBackgroundColor(TileColor.SAND);
                } else {
                    t.setBackgroundColor(TileColor.WATERBLUE);
                }
            } else if (atoll.isTileOnTerrain(t)) {
                boolean isBeach = false;
                for (PseudoPoint neighbor: t.getNeighborPseudoCenters()) {
                    if (!atoll.isTileOnTerrain(tiles.get(neighbor))) {
                        isBeach = true;
                    }
                }
                if (isBeach) {
                    t.setBackgroundColor(TileColor.SAND);
                } else {
                    t.setBackgroundColor(TileColor.MIDGREEN);
                }
            } else {    
                t.setBackgroundColor(TileColor.OCEANBLUE);
            }
        }
    }
    
    public TileColor getTileColor(Structs.Point tileCenter) {
        PseudoPoint pseudoCenter = new PseudoPoint(tileCenter);
        return tiles.get(pseudoCenter).getBackgroundColor();
    }
}
