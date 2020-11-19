package World.Generator.Island;


import World.Generator.WorldProcessor;
import World.Tile;
import World.World;


public abstract class Island implements WorldProcessor {
    public void validate(World w) throws Exception{
        for(Tile t: w.getTiles().values()){
            if(t.getAltitude() < 0){
                throw new Exception("Invalid Island");
            }
        }
    }

}
