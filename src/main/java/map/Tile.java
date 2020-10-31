package map;


import geometrie.Dot;

import java.util.HashSet;

public class Tile {

    private Dot center;
    TileColor backgroundColor;
    HashSet<Dot> neighbors;  //possible qu'on va devoir enlever les voisins d'ici
    int polygonId;

    public Tile(  Dot center, int polygonId ) {
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

    public Dot getTileCenter() {

        return this.center;
    }

    public int getPolygonId() {
        return polygonId;
    }

}
