package world;


import geometry.Coordinate;
import geometry.Line;
import world.generator.biome.Biome;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;

public class Tile {

    final int invalid = -1;
    final private Coordinate center;
    private Biome biome;
    private TileColor backgroundColor;
    private int humidityLevel;
    private boolean isInLagoon;
    private boolean isInOcean;
    private boolean isOnIsland;
    final private HashSet<Line> border;
    private HashSet<Coordinate> corners;
    private float richiness;


    public Tile(Coordinate center) {
        this.center = center;
        this.border = new LinkedHashSet<>();
        this.corners = new LinkedHashSet<>();
        this.humidityLevel = invalid;
        this.isInLagoon = false;
        this.isOnIsland = false;
        this.isInOcean = true;
    }

    /**
     *
     * @param c la couleur donnée à la tuile
     */
    public void setBackgroundColor(TileColor c) {
        backgroundColor = c;
    }

    /**
     *
     * @return la couleur de la tuile
     */
    public TileColor getBackgroundColor() {
        return backgroundColor;
    }

    /**
     *
     * @return la coorodnnée du centre de la tuile
     */
    public Coordinate getCenter() {
        return this.center;
    }

    /**
     *
     * @return le niveau de l'humidity
     */
    public int getHumidityLevel() {
        return humidityLevel;
    }

    /**
     *
     * @param humidityLevel est le niveau à attribuer à une tuile
     */
    public void setHumidityLevel(int humidityLevel) {
        this.humidityLevel = humidityLevel;
    }

    /**
     *
     * @return l'altitude moyenne basée sur l'altitude des coins composant la tuile
     */
    public double getAltitude() {
        HashSet<Coordinate> coordinates = this.getCorner();
        float altitude = 0;
        for(Coordinate c: coordinates){
            altitude = altitude + c.getZ();
        }

        return (double)altitude / coordinates.size();
    }

    /**
     *
     * @param altitude l'altitude à donner à toutes les coins d'une tuile ( lors la creation d'un lac, ce dernier doit etre plat )
     */
    public void setAltitude(double altitude) {

        for(Coordinate c:getCorner()){
            c.setZ((float)altitude);
        }
    }

    /**
     *
     * @param inLagoon definit l'appartenance d'une tuile au biom lagon
     */
    public void setInLagoon(boolean inLagoon) {
        isInLagoon = inLagoon;
    }

    /**
     *
     * @return True si la tuile appartient au  biom lagon
     */
    public boolean isInLagoon() {
        return isInLagoon;
    }

    /**
     *
     * @return True si la tuile appartient au  biom ocean
     */
    public boolean isInOcean() {
        return isInOcean;
    }

    /**
     *
     * @param onIsland  definit l'appartenance d'une tuile au biom vegetation
     */
    public void setOnIsland(boolean onIsland ){
        isOnIsland = onIsland;
    }

    /**
     *
     * @return True si la tuile appartient au  biom vegetation
     */
    public boolean isOnIsland() {
        return isOnIsland;
    }

    /**
     *
     * @param inOcean  definit l'appartenance d'une tuile au biom ocean
     */

    public void setInOcean(boolean inOcean) {
        isInOcean = inOcean;
    }

    /**
     *
     * @param biome definir a quel biome appartient la tuile
     */
    public void setBiome(Biome biome) {
        this.biome = biome;
    }

    /**
     *
     * @return le type de biome auquel la tuile appartient
     */
    public Biome getBiome() {
        return biome;
    }

    /**
     *
     * @return la liste des lignes qui bordent la ltuile
     */
    public HashSet<Line> getBorder() {
        return border;
    }

    /**
     *
     * @return la listes des coordonnée des coins de la tuile
     */
    public HashSet<Coordinate> getCorner() {
        /*
        HashSet<Coordinate> c = new LinkedHashSet<>();
        for(Line l: border){
            c.add(l.getC1());
            c.add(l.getC2());
        }
        return c;

         */
        return corners;
    }

    public HashSet<Coordinate> getCornerOriginal() {

        HashSet<Coordinate> c = new LinkedHashSet<>();
        for(Line l: border){
            c.add(l.getC1());
            c.add(l.getC2());
        }
        return c;
    }

    /**
     *
     * @param l ajouter une ligne aux bordures de la tuile
     */

    public void addBorder(Line l){
        border.add(l);
    }

    public void addCorners(Coordinate corner1){
        corners.add(corner1);
    }

    /**
     *
     * @return le niveau de production de la tuile
     */
    public float getRichiness() {
        return richiness;
    }

    /**
     *
     * @param richiness un facteur indiquant le niveau de production de la tuile
     */

    public void setRichiness(float richiness) {
        this.richiness = richiness;
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
