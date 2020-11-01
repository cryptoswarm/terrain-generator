package map;


import geometrie.Dot;

import java.util.HashSet;
import java.util.Objects;

public class Tile {

    private Dot center;
    TileColor backgroundColor;
    HashSet<Dot> neighbors;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return center.equals(tile.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(center);
    }
}
