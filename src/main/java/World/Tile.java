package World;


import Geometry.Coordinate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Tile {

    final private Coordinate center;
    private Biome biome;
    private TileColor backgroundColor;
    private int humidityLevel;
    private double altitude;
    private boolean isInLagoon;
    private HashSet<Tile> neighbors;

    public Tile(Coordinate center) {
        this.center = center;
        this.neighbors = new HashSet<>();
        humidityLevel = 0;
        isInLagoon = false;
    }

    public void addNeighbor(Tile neighbor) {
        this.neighbors.add(neighbor);
    }

    public HashSet<Tile> getNeighbors() {
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
    public double getAltitude() {
        return altitude;
    }
    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
    public boolean isOnIsland() {
        return altitude > 0;
    }
    public void setInLagoon(boolean inLagoon) {
        isInLagoon = inLagoon;
    }
    public boolean isInLagoon() {
        return isInLagoon;
    }
    public void setBiome(Biome biome) {
        this.biome = biome;
    }
    public Biome getBiome() {
        return biome;
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
