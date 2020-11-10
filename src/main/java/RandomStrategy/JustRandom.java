package RandomStrategy;

import java.util.Random;

public class JustRandom implements RandomStategy{

    public int getRandom(int range){

        return (new Random()).nextInt(range+1);
    }

}
