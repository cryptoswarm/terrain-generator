package world.generator.aquifer;


import islandSet.Isle;
import world.Tile;
import world.TileColor;
import world.generator.biome.Biome;
import world.soilType;

import java.util.HashSet;

import static world.TileColor.DARKGREEN;

public class Nape extends Aquifer implements Biome {

    private Tile aquiferCenter;
    private soilType soil;
    final private HashSet<Tile> nape = new HashSet<>();
    final private TileColor color = DARKGREEN;
    private final  String type = "nape";

    public Nape() {}

    public void setAquiferCenter(Tile tile) {
        this.aquiferCenter = tile;
    }

    public void setSoil(soilType soil) {
        this.soil = soil;
    }

    @Override
    public TileColor getColor() {
        return color;
    }

    @Override
    public String getType() {
        return type;
    }



    @Override
    public void apply(Isle isle) {
        nape.add(aquiferCenter);
        for(Tile i : isle.getNeighbor(aquiferCenter)) {
            if(i.getBiome().getType().equals("vegetation")) {
                nape.add(i);
            }
        }
        for(Tile i: nape) i.setBackgroundColor(color);
        this.applyHumidityEffect(isle, nape, soil);

    }
}
