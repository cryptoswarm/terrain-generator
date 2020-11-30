package world.generator.aquifer;

import islandSet.Isle;
import world.Tile;
import world.TileColor;
import world.generator.biome.Biome;
import world.soilType;

import java.util.HashSet;

import static world.TileColor.WATERBLUE;

public class Lake extends Aquifer implements Biome {

    private Tile aquiferCenter;
    final private HashSet<Tile> lake = new HashSet<>();
    final private TileColor color = WATERBLUE;
    private soilType soil;
    private final  String type = "lake";

    /*
    public Lake(Tile tile, soilType soil) {
        this.aquiferCenter = tile;
        this.soil = soil;
    }

     */

    public Lake() {}

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

        double lowestAltitude;
        lowestAltitude = aquiferCenter.getAltitude();
        lake.add(aquiferCenter);

        for(Tile tile : isle.getNeighbor(aquiferCenter)) {
            if(tile.getBiome().getType().equals("vegetation")) {

                lake.add(tile);
                if(tile.getAltitude() < lowestAltitude) {
                    lowestAltitude = tile.getAltitude();
                }
            }
        }
        for(Tile i: lake) {
            i.setBiome(new Lake());
            i.setAltitude(lowestAltitude);
            i.setBackgroundColor(color);
        }
        this.applyHumidityEffect(isle,lake,soil);
    }



}
