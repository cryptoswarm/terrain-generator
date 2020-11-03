package World;


import Geometry.Coordinate;
import java.util.HashMap;
import java.util.Objects;

public class Tile {

    final private Coordinate center;
    private Biome biome;
    private TileColor backgroundColor;
    private int humidityLevel;
    private double altitude;
    private HashMap<Coordinate, Tile> neighbors;

    public Tile(Coordinate center) {
        this.center = center;
        this.neighbors = new HashMap<>();
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
        if(backgroundColor != null) {
            return backgroundColor;
        }
        return biome.getColor();
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
