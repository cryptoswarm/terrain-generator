package World.Generator.Island;


import World.Tile;
import World.World;


public abstract class Island {

    public abstract void apply(World w);
    public void validate(World w) throws Exception{
        for(Tile t: w.getTiles()){
            if(t.getAltitude() < 0){
                throw new Exception("Invalid Island");
            }
        }
    }

}
