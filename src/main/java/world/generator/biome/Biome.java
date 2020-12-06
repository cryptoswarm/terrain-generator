package world.generator.biome;

import world.WorldItem;
import world.generator.WorldProcessor;

public abstract class Biome implements WorldItem, WorldProcessor {

    public Boolean validLocalization(Localization localisation, int minT, int maxT, int minP, int maxP){
        int t = localisation.getTemperatureAverage();
        int p = localisation.getPrecipitationAverage();

        return p <= maxP && p >= minP &&
                t <= maxT && t >= minT;
    }
}