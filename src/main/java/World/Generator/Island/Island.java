package World.Generator.Island;

import World.Generator.WorldProcessor;
import World.World;

public abstract class Island implements WorldProcessor {
    abstract void defineAltitude(World world,  int maxAltitude );
    abstract void setBorders( World world );
}
