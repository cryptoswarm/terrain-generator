package world.generator.island;

import world.generator.WorldProcessor;
import world.World;

public abstract class Island implements WorldProcessor {
    abstract void defineAltitude(World world,  int maxAltitude );
    abstract void setBorders( World world );
}
