package map;

import geometrie.Dot;

import java.util.HashSet;

public class Aquifere {

    private Tile aquifereTileCenter;
    private HashSet<Dot> neighbors;

    public Aquifere(Vegetation vegetation) {
        this.aquifereTileCenter = vegetation.findRandomVegtalTile();
        this.neighbors = vegetation.findNeighbors(aquifereTileCenter);
    }

    public void setColor(){
        this.aquifereTileCenter.setBackgroundColor(TileColor.DARKGREEN);
    }

    public void propager(){

    }










}
