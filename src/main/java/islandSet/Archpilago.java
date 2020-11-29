package islandSet;

import java.util.ArrayList;
import java.util.List;

public class Archpilago {

    private List<Isle> islandSet;

    public Archpilago() {
        this.islandSet = new ArrayList<>();
    }

    public void addIsle(Isle isle){
        islandSet.add(isle);
    }
}
