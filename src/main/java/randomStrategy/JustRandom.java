package randomStrategy;

import java.util.Random;

public class JustRandom implements RandomStrategy{

    public int getRandom(int range){
        return (new Random()).nextInt(range+1);
    }
}
