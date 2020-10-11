package ca.uqam.info.inf5153.ptg;


import ca.uqam.ace.inf5153.mesh.io.Structs;

import java.util.HashSet;

public class Tile {
    Structs.Polygon polygon;
    Structs.Point center;
    TileColor backgroundColor;
    HashSet<PseudoPoint> neighbors;

    public Tile(Structs.Polygon polygon, Structs.Point center) {
        this.polygon = polygon;
        this.center = center;
        this.neighbors = new HashSet<>();
    }
    
    public void addNeighborPseudoCenter(PseudoPoint neighborPseudoCenter) {
        this.neighbors.add(neighborPseudoCenter);
    }

    public HashSet<PseudoPoint> getNeighborPseudoCenters() {
        return neighbors;
    }

    public void setBackgroundColor(TileColor c) {
        backgroundColor = c;
    }

    public TileColor getBackgroundColor() {
        return backgroundColor;
    }

    public PseudoPoint getTilePseudoCenter() {
        return new PseudoPoint(center);
    }
    

        
}
