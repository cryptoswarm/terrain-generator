package geometrie;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dot {
    Coordonnee coordonnee;
    private List<Coordonnee> listOfDots = new ArrayList<>();

    public Dot(Coordonnee coordonnee) {
        this.coordonnee = coordonnee;
    }

    public Coordonnee getCoordonnee() {
        return coordonnee;
    }

    public void setCoordonnee(Coordonnee coordonnee) {
        this.coordonnee = coordonnee;
    }

    public void addDots(Coordonnee coordonnee){
        listOfDots.add(coordonnee);
    }

    public int getNbOfDots(){
        return listOfDots.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dot)) return false;
        Dot dot = (Dot) o;
        return coordonnee.equals(dot.coordonnee);
        //return coordonnee.getX() ==
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordonnee);
    }

    public float distance(Dot that) {
        float horizontal = this.getCoordonnee().getX() - that.getCoordonnee().getX();
        float vertical = this.getCoordonnee().getX() - that.getCoordonnee().getX();

        return (float) Math.sqrt(horizontal*horizontal + vertical*vertical);
    }
}
