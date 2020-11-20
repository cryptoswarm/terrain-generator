package World.Generator.Island;

import World.Generator.WorldProcessor;

public abstract class Island implements WorldProcessor {
    abstract void defineAltitude(  int maxAltitude );
    abstract void setBorders();
}
