package map;


import geometrie.Coordinate;

import java.util.HashMap;
import java.util.Objects;

public class Tile {

    private Coordinate center;
    TileColor backgroundColor;
    int humidityLevel;
    HashMap<Coordinate, Tile> neighbors;

    public Tile(Coordinate center) {
        this.center = center;
        this.neighbors = new HashMap<Coordinate, Tile>();
        humidityLevel = 0;
    }

    public void addNeighbor(Tile neighbor) {
        this.neighbors.put(neighbor.getCenter(), neighbor);
    }

    public HashMap<Coordinate, Tile> getNeighbors() {
        return neighbors;
    }

    public void setBackgroundColor(TileColor c) {
        backgroundColor = c;
    }
    public TileColor getBackgroundColor() {
        return backgroundColor;
    }

    public Coordinate getCenter() {
        return this.center;
    }

    public int getHumidityLevel() {
        return humidityLevel;
    }
    public void setHumidityLevel(int humidityLevel) {
        this.humidityLevel = humidityLevel;
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
