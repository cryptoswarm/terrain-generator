package map;


import ca.uqam.ace.inf5153.mesh.io.Structs;
import geometrie.Dot;

import java.util.HashSet;

public class Tile {

    Structs.Polygon polygon;
    Dot center;
    TileColor backgroundColor;
    HashSet<Dot> neighbors;
    int polygonId;

    public Tile(Structs.Polygon polygon, Dot center, int polygonId) {
        this.polygon = polygon;
        this.center = center;
        this.neighbors = new HashSet<>();
        this.polygonId = polygonId;
    }

    public void addNeighborPseudoCenter(Dot neighborPseudoCenter) {
        this.neighbors.add(neighborPseudoCenter);
    }

    public HashSet<Dot> getNeighborPseudoCenters() {
        return neighbors;
    }

    public void setBackgroundColor(TileColor c) {
        backgroundColor = c;
    }

    public TileColor getBackgroundColor() {
        return backgroundColor;
    }

    public Dot getTilePseudoCenter() {

        return this.center;
    }

    public int getPolygonId() {
        return polygonId;
    }
}
