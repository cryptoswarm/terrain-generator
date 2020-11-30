package world.generator;

import islandSet.Isle;
import world.Tile;
import world.soilType;

public interface IslandProcessor {

    void apply(Isle isle);
    void setAquiferCenter(Tile tile);
    void setSoil(soilType soil);
}
